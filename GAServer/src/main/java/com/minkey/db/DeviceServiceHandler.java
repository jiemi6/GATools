package com.minkey.db;

import com.alibaba.fastjson.JSONObject;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.FTPConfigData;
import com.minkey.dto.SnmpConfigData;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
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

    public void insertAll(Device device,List<DeviceService> deviceService) {
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (deviceId,serviceName,ip,serviceType,configData) VALUES (?,?,?,?,?)",
                deviceService,deviceService.size(), new ParameterizedPreparedStatementSetter<DeviceService>() {
                    @Override
                    public void setValues(PreparedStatement ps, DeviceService argument) throws SQLException {
                        ps.setLong(1,device.getDeviceId());
                        ps.setString(2,argument.getServiceName());
                        ps.setString(3,device.getIp());
                        ps.setInt(4,argument.getServiceType());
                        ps.setString(5,argument.configDataStr());
                    }
                });

    }

    public void insert(Device device,DeviceService deviceService) {
        int num = jdbcTemplate.update("insert into "+tableName+" (deviceId,serviceName,ip,serviceType,configData) VALUES (?,?,?,?,?)",
                new Object[]{device.getDeviceId(),deviceService.getServiceName(),device.getIp(),deviceService.getServiceType(),deviceService.getConfigData()});

        if(num == 0){
            throw new DataException("新增设备服务失败");
        }
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

    class DeviceServiceRowMapper implements RowMapper {
        @Override
        public DeviceService mapRow(ResultSet rs, int rowNum) throws SQLException {
            DeviceService deviceService =new DeviceService();
            deviceService.setDeviceId(rs.getLong("deviceId"));
            deviceService.setIp(rs.getString("ip"));
            deviceService.setServiceId(rs.getLong("serviceId"));
            deviceService.setServiceName(rs.getString("serviceName"));
            deviceService.setServiceType(rs.getInt("serviceType"));

            switch (rs.getInt("serviceType")){
                case DeviceService.SERVICETYPE_DB :
                    deviceService.setConfigData(JSONObject.parseObject(rs.getString("configData"),DBConfigData.class));
                    break;
                case DeviceService.SERVICETYPE_SNMP :
                    deviceService.setConfigData(JSONObject.parseObject(rs.getString("configData"),SnmpConfigData.class));
                    break;
                case DeviceService.SERVICETYPE_FTP :
                    deviceService.setConfigData(JSONObject.parseObject(rs.getString("configData"),FTPConfigData.class));
                    break;
                default:
                    deviceService.setConfigData(JSONObject.parseObject(rs.getString("configData"),BaseConfigData.class));
                    break;

            }

            return deviceService;
        }
    }

}
