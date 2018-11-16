package com.minkey.util;

import com.minkey.command.Telnet;
import com.minkey.dto.DBConfigData;
import com.minkey.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public static final int default_timeout = 1000;


    /**
     * 所有系统需要访问的数据库
     * <br>key : ip:port/dbName <br/>
     */
    private Map<String,JdbcTemplate> jdbcTemplateMap = new HashMap();


    private JdbcTemplate getJdbcTemplate(DatabaseDriver databaseDriver, String ip,int port,String dbName, String userName , String password){
        //先测网络
        if(!Telnet.doTelnet(ip,port)){
            throw new DataException("数据库网络不同");
        }
        JdbcTemplate jdbcTemplate ;
        String jdbcUrl ;
        if(databaseDriver == DatabaseDriver.MYSQL){
            jdbcUrl = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8";
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            jdbcUrl = "jdbc:oracle:thin:@//"+ip+":"+port+"/"+dbName;
        }else if(databaseDriver == DatabaseDriver.SQLSERVER){
            jdbcUrl = "jdbcUrl:jdbc:sqlserver://"+ip+":"+port+";databasename="+dbName;
        }else {
            throw new DataException("暂不支持数据库类型="+databaseDriver);
        }

        try{
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .driverClassName(databaseDriver.getDriverClassName())
                    .username(userName)
                    .password(password);
            DataSource dataSource = dataSourceBuilder.build();
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.setQueryTimeout(default_timeout);
            jdbcTemplate.execute(databaseDriver.getValidationQuery());
        }catch (Exception e ){
            throw new DataException("构造数据库连接异常",e);
        }

        return jdbcTemplate;
    }

    public boolean testDB(DBConfigData dbConfigData){
        try{
            //获取jdbc操作模板
            JdbcTemplate jdbcTemplate = get8dbConfig(dbConfigData);

            jdbcTemplate.execute(dbConfigData.getDatabaseDriver().getValidationQuery());
            return true;
        }catch (Exception e){
            log.error("测试连接数据库失败:"+dbConfigData.toString(),e);
            return false;
        }
    }

    private void putIn(String ip,int port ,String dbName, JdbcTemplate jdbcTemplate){
        String key = createKey(ip,port,dbName);
        jdbcTemplateMap.put(key,jdbcTemplate);
    }

    private JdbcTemplate get(String ip,int port,String dbName){
        String key = createKey(ip,port,dbName);
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return jdbcTemplateMap.get(key);
    }

    private String createKey(String ip, int port,String dbName) {
        return ip+":"+port+"/"+dbName;
    }


    public JdbcTemplate get8dbConfig(DBConfigData dbConfigData) {
        //先从缓存中获取
        JdbcTemplate jdbcTemplate = get(dbConfigData.getIp(), dbConfigData.getPort(), dbConfigData.getName());
        //没有就新建
        if (jdbcTemplate == null) {
            jdbcTemplate = getJdbcTemplate(dbConfigData.getDatabaseDriver(),dbConfigData.getIp(),dbConfigData.getPort()
                    ,dbConfigData.getDbName(),dbConfigData.getName(),dbConfigData.getPwd());
            //放回缓存
            putIn(dbConfigData.getIp(), dbConfigData.getPort(), dbConfigData.getName(), jdbcTemplate);
        }

        return jdbcTemplate;
    }

}
