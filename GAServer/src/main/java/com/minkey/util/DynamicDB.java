package com.minkey.util;

import com.minkey.exception.DataException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DynamicDB {
    private final static Logger logger = LoggerFactory.getLogger(DynamicDB.class);

    /**
     * 所有系统需要访问的数据库
     * <br>key : ip:port/dbName <br/>
     */
    private Map<String,JdbcTemplate> jdbcTemplateMap = new HashMap();

    public JdbcTemplate getJdbcTemplate(String url,DatabaseDriver databaseDriver,String userName ,String password){
        JdbcTemplate jdbcTemplate = null;
        try{
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                    .url(url)
                    .driverClassName(databaseDriver.getDriverClassName())
                    .username(userName)
                    .password(password);
            DataSource dataSource = dataSourceBuilder.build();
            jdbcTemplate = new JdbcTemplate(dataSource);

            jdbcTemplate.execute(databaseDriver.getValidationQuery());
        }catch (Exception e ){
            logger.error("构造数据库连接异常",e);
            throw new DataException("构造数据库连接异常",e);
        }

        return jdbcTemplate;
    }


    public void putIn(String ip,int port ,String dbName, JdbcTemplate jdbcTemplate){
        String key = createKey(ip,port,dbName);
        jdbcTemplateMap.put(key,jdbcTemplate);
    }

    public JdbcTemplate get(String ip,int port,String dbName){
        String key = createKey(ip,port,dbName);
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return jdbcTemplateMap.get(key);
    }

    private String createKey(String ip, int port,String dbName) {
        return ip+":"+port+"/"+dbName;
    }

}
