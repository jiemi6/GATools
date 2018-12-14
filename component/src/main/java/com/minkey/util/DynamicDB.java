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

    private String createKey(String ip, int port,String dbName) {
        return ip+":"+port+"/"+dbName;
    }

    private JdbcTemplate getJdbcTemplate(DatabaseDriver databaseDriver, String ip,int port,String dbName, String userName , String password){
        //先测网络
        boolean isConnect;
        try {
            isConnect = Telnet.doTelnet(ip,port);
        } catch (SystemException e) {
            throw new SystemException(AlarmEnum.port_notConnect,String.format("数据库%s",e.getMessage()));
        }
        if(!isConnect){
            throw new SystemException(AlarmEnum.port_notConnect,String.format("Telnet数据库网络不通[%s:%s]",ip,port));
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
            String exceptionStr = e.getCause().getCause().toString();
            throw build(exceptionStr,databaseDriver);
        }

    }

    /**
     * 根据错误类型构造返回的异常
     * @param exceptionStr
     * @param databaseDriver
     * @return
     */
    private SystemException build(String exceptionStr, DatabaseDriver databaseDriver){

        if(databaseDriver == DatabaseDriver.MYSQL){
            // Access denied for user 'root'@'localhost' (using password: YES)
            if(exceptionStr.contains("using password: YES")){
                //账号密码错误
                return new SystemException(AlarmEnum.db_wrongpwd);
            }else if (exceptionStr.contains("Unknown database")){
                //Unknown database 'smz'  数据库名称错误,不存在
                return new SystemException(AlarmEnum.db_databaseName_noexist);
            }else if(exceptionStr.contains("Connection refused")){
                //java.net.ConnectException: Connection refused: connect
                return new SystemException(AlarmEnum.port_notConnect);
            }


        }

        return new SystemException(AlarmEnum.db_createError.getAlarmType(),"数据库连接异常,"+exceptionStr);
    }

    /**
     * 测试数据库是否连接正常
     * @param dbConfigData
     * @return
     * @throws SystemException
     */
    public boolean testDBConnect(DBConfigData dbConfigData) throws SystemException{
        try{
            //获取jdbc操作模板
            JdbcTemplate jdbcTemplate = get8dbConfig(dbConfigData);

            jdbcTemplate.execute(dbConfigData.getDatabaseDriver().getValidationQuery());
            return true;
        }catch (SystemException  e){
            throw e;
        }catch (Exception e){
            throw new SystemException(AlarmEnum.port_notConnect,String.format("测试数据库%s失败,msg=:",dbConfigData.toString(),e.getMessage()));
        }
    }

    /**
     * 获取数据库连接
     * @param dbConfigData
     * @return
     */
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

    public boolean testDB(DBConfigData dbConfigData) throws SystemException{
        try{
            //获取jdbc操作模板
            JdbcTemplate jdbcTemplate = get8dbConfig(dbConfigData);

            return true;
        }catch (SystemException  e){
            throw e;
        }catch (Exception e){
            throw new SystemException(AlarmEnum.port_notConnect,String.format("测试数据库%s失败,msg=:",dbConfigData.toString(),e.getMessage()));
        }
    }

}
