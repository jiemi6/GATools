package com.minkey.db;

import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.BaseConfigData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DeviceServiceHandler {
    private final String tableName = "t_deviceService";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        return count;
    }

    public void insertAll(Device device,List<DeviceService> deviceServiceList) {
        deviceServiceList.forEach(deviceService -> {
            if(StringUtils.isEmpty(deviceService.getIp())){
                //设备ip赋值给设备服务
                deviceService.setIp(device.getIp());
            }
        });
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (deviceId,serviceName,ip,serviceType,configData) VALUES (?,?,?,?,?)",
                deviceServiceList,deviceServiceList.size(), new ParameterizedPreparedStatementSetter<DeviceService>() {
                    @Override
                    public void setValues(PreparedStatement ps, DeviceService argument) throws SQLException {
                        ps.setLong(1,device.getDeviceId());
                        ps.setString(2,argument.getServiceName());
                        ps.setString(3, argument.getIp());
                        ps.setInt(4,argument.getServiceType());
                        ps.setString(5,argument.configDataStr());
                    }
                });

    }


    public DeviceService query(Long serviceId) {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName+" where serviceId= ?",
        new Object[]{serviceId},new DeviceServiceRowMapper());
        if(CollectionUtils.isEmpty(deviceServices)){
            return null;
        }
        return deviceServices.get(0);
    }

    public List<DeviceService> query8Device(long deviceId) {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName +" where deviceId=?",
                new Object[]{deviceId},new DeviceServiceRowMapper());
        return deviceServices;
    }

    public List<DeviceService> query8Type(int deviceServiceType) {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName +" where serviceType=?",
                new Object[]{deviceServiceType},new DeviceServiceRowMapper());
        return deviceServices;
    }

    public DeviceService query8Device(long deviceId, int serviceType) {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName +" where deviceId=? AND serviceType=?",
                new Object[]{deviceId,serviceType},new DeviceServiceRowMapper());
        if(CollectionUtils.isEmpty(deviceServices)){
            return null;
        }
        return deviceServices.get(0);
    }

    public void delete8DeviceId(Long deviceId) {
        int num = jdbcTemplate.update("delete from "+tableName +" where deviceId=?",new Object[]{deviceId});
    }

    public List<DeviceService> queryAll() {
        List<DeviceService> deviceServices = jdbcTemplate.query("select * from "+tableName ,new DeviceServiceRowMapper());
        return deviceServices;
    }

    class DeviceServiceRowMapper implements RowMapper {
        @Override
        public DeviceService mapRow(ResultSet rs, int rowNum) throws SQLException {
            DeviceService deviceService =new DeviceService();
            deviceService.setDeviceId(rs.getLong("deviceId"));
            deviceService.setIp(rs.getString("ip"));
            deviceService.setServiceId(rs.getLong("serviceId"));
            deviceService.setServiceName(rs.getString("serviceName"));
            deviceService.setServiceType(rs.getInt("serviceType"));

            BaseConfigData baseConfigData = DeviceService.conventConfigData8str(rs.getInt("serviceType"),rs.getString("configData"));
            deviceService.setConfigData(baseConfigData);

            return deviceService;
        }
    }

}
