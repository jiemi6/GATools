package com.minkey.db;

import com.minkey.db.dao.DeviceService;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Component
public class DeviceServiceHandler {
    private final static Logger logger = LoggerFactory.getLogger(DeviceServiceHandler.class);

    private final String tableName = "t_deviceService";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        return count;
    }

    public void insertAll(List<DeviceService> deviceService) {
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (deviceId,serviceName,serviceType,configData) VALUES (?,?,?,?)",
                deviceService,deviceService.size(), new ParameterizedPreparedStatementSetter<DeviceService>() {
                    @Override
                    public void setValues(PreparedStatement ps, DeviceService argument) throws SQLException {
                        ps.setLong(1,argument.getDeviceId());
                        ps.setString(2,argument.getServiceName());
                        ps.setInt(3,argument.getServiceType());
                        ps.setString(4,argument.getConfigData());
                    }
                });

    }

    public void insert(DeviceService deviceService) {
        int num = jdbcTemplate.update("insert into "+tableName+" (deviceId,serviceName,serviceType,configData) VALUES (?,?,?,?)",
                new Object[]{deviceService.getDeviceId(),deviceService.getServiceName(),deviceService.getServiceType(),deviceService.getConfigData()});

        if(num == 0){
            throw new DataException("新增设备服务失败");
        }
    }

    public DeviceService query(Long serviceId) {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName+" where serviceId= ?",
        new Object[]{serviceId},new BeanPropertyRowMapper<>(DeviceService.class));
        if(CollectionUtils.isEmpty(deviceServices)){
            return null;
        }
        return deviceServices.get(0);
    }

    public List<DeviceService> query8Device(long deviceId) {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName +" where deviceId=?",
                new Object[]{deviceId},new BeanPropertyRowMapper<>(DeviceService.class));
        return deviceServices;
    }

    public void delete8DeviceId(Long deviceId) {
        int num = jdbcTemplate.queryForObject("delete from "+tableName +" where deviceId=?",
                new Object[]{deviceId},Integer.class);
    }
}
