package com.minkey.util;

import com.minkey.command.Telnet;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.CommonContants;
import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DynamicDB {
    public static final int default_timeout = CommonContants.DEFAULT_TIMEOUT;


    /**
     * 所有系统需要访问的数据库
     * <br>key : ip:port/dbName <br/>
     */
    private Map<String,JdbcTemplate> jdbcTemplateMap = new HashMap();


    private JdbcTemplate getJdbcTemplate(DatabaseDriver databaseDriver, String ip,int port,String dbName, String userName , String password){
        //先测网络
        boolean isConnect;
        try {
            isConnect = Telnet.doTelnet(ip,port);
        } catch (SystemException e) {
            throw new SystemException(AlarmEnum.port_notConnect.getAlarmType(),String.format("数据库%s",e.getMessage()));
        }
        if(!isConnect){
            throw new SystemException(AlarmEnum.port_notConnect.getAlarmType(),String.format("Telnet数据库网络不通[%s:%s]",ip,port));
        }


        JdbcTemplate jdbcTemplate ;
        String jdbcUrl ;
        if(databaseDriver == DatabaseDriver.MYSQL){
            jdbcUrl = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            jdbcUrl = "jdbc:oracle:thin:@//"+ip+":"+port+"/"+dbName;
        }else if(databaseDriver == DatabaseDriver.SQLSERVER){
            jdbcUrl = "jdbcUrl:jdbc:sqlserver://"+ip+":"+port+";databasename="+dbName;
        }else {
            throw new SystemException("暂不支持数据库类型="+databaseDriver);
        }

        DataSource dataSource = null;
        try{
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .driverClassName(databaseDriver.getDriverClassName())
                    .username(userName)
                    .password(password);
            dataSource = dataSourceBuilder.build();

            dataSource.setLoginTimeout(1);
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.setQueryTimeout(default_timeout);

            //测试语句执行
            jdbcTemplate.execute(databaseDriver.getValidationQuery());

            return jdbcTemplate;
        }catch (Exception e ){
            throw build(e,databaseDriver);
        }

    }

    /**
     * 根据错误类型构造返回的异常
     * @param e
     * @param databaseDriver
     * @return
     */
    private SystemException build(Exception e, DatabaseDriver databaseDriver){

        if(databaseDriver == DatabaseDriver.MYSQL){
            String eStr = e.getCause().getCause().toString();

            // Mysql : Access denied for user 'root1'@'localhost' (using password: YES)
            if(eStr.contains("using password: YES")){
                //账号密码错误
                return new SystemException(AlarmEnum.db_wrongpwd.getAlarmType(),"数据库账号密码错误");

            }
        }

        return new SystemException(AlarmEnum.db_createError.getAlarmType(),"构造数据库连接异常,"+e.getMessage());
    }

    public boolean testDB(DBConfigData dbConfigData) throws SystemException{
        try{
            //获取jdbc操作模板
            JdbcTemplate jdbcTemplate = get8dbConfig(dbConfigData);

            jdbcTemplate.execute(dbConfigData.getDatabaseDriver().getValidationQuery());
            return true;
        }catch (SystemException  e){
            throw e;
        }catch (Exception e){
            throw new SystemException(String.format("测试数据库%s失败,msg=:",dbConfigData.toString(),e.getMessage()));
        }
    }

    private String createKey(String ip, int port,String dbName) {
        return ip+":"+port+"/"+dbName;
    }

    public JdbcTemplate get8dbConfig(DBConfigData dbConfigData) {
        String key = createKey(dbConfigData.getIp(), dbConfigData.getPort(), dbConfigData.getName());
        //先从缓存中获取
        JdbcTemplate jdbcTemplate = jdbcTemplateMap.get(key);
        //没有就新建
        if (jdbcTemplate == null) {
            jdbcTemplate = getJdbcTemplate(dbConfigData.getDatabaseDriver(),dbConfigData.getIp(),dbConfigData.getPort()
                    ,dbConfigData.getDbName(),dbConfigData.getName(),dbConfigData.getPwd());
            //放回缓存
            jdbcTemplateMap.put(key,jdbcTemplate);
        }

        return jdbcTemplate;
    }

}
