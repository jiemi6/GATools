package com.minkey.db;

import com.minkey.exception.SystemException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ConfigHandler {
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
            throw new SystemException("插入配置失败");
        }
    }

    public Map<String, Object> query(String configKey) {
        List<Map<String, Object>> configData = jdbcTemplate.queryForList("select configKey, configData from "+tableName+" where configKey= ?",new Object[]{configKey});
        if(CollectionUtils.isEmpty(configData)){
            return null;
        }
        return configData.get(0);
    }

    public List<Map<String, Object>> queryAll() {
            return jdbcTemplate.queryForList("select configKey, configData from "+tableName);


    }

}
