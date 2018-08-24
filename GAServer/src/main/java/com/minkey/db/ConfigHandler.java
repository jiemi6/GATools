package com.minkey.db;

import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ConfigHandler {
    private final static Logger logger = LoggerFactory.getLogger(ConfigHandler.class);

    private final String tableName = "t_config";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public void insert(String configKey,String configData) {
        int num = jdbcTemplate.update("replace into "+tableName+" (configKey, configData) VALUES (?,?)",new Object[]{configKey,configData});

        if(num == 0){
            throw new DataException("插入配置失败");
        }
    }

    public Map<String, Object> query(String configKey) {
            return jdbcTemplate.queryForMap("select configKey, configData from "+tableName+" where configKey= ?",new Object[]{configKey});
    }

    public List<Map<String, Object>> queryAll() {
            return jdbcTemplate.queryForList("select configKey, configData from "+tableName);


    }

}
