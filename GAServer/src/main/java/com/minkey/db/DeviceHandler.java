package com.minkey.db;

import com.minkey.dao.Device;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceHandler {
    private final static Logger logger = LoggerFactory.getLogger(DeviceHandler.class);

    private final String tableName = "t_device";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public void insert(Device device) {
        int num = jdbcTemplate.update("replace into "+tableName+" (configKey, configData) VALUES (?,?)",new Object[]{device});

        if(num == 0){
            throw new DataException("插入配置失败");
        }
    }

    public Device query(long deviceId) {
            return jdbcTemplate.queryForObject("select configKey, configData from "+tableName+" where deviceId= ?",new Object[]{deviceId},Device.class);
    }

    public List<Device> queryAll() {
        return jdbcTemplate.queryForList("select configKey, configData from "+tableName,Device.class);
    }
}
