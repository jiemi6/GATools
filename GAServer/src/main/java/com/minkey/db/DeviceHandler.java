package com.minkey.db;

import com.minkey.db.dao.Device;
import com.minkey.dto.Page;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Set;

@Component
public class DeviceHandler {
    private final String tableName = "t_device";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public long insert(Device device) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String sql =String.format("INSERT into "+tableName+" (deviceName,ip,deviceType,area,netArea,icon) VALUES (%s,%s,%s,%s,%s,%s)" ,
                    "'"+device.getDeviceName()+"'","'"+device.getIp()+"'",device.getDeviceType(),device.getArea(),device.getNetArea(),device.getIcon()) ;

            //通过传入第二个参数,就会产生主键返回给我们
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            //返回的结果集中包含主键,注意：主键还可以是UUID,
            //复合主键等,所以这里不是直接返回一个整型
            rs = ps.getGeneratedKeys();
            long id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            return id;
        }catch (Exception e){
            throw e;
        }finally {
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeConnection(conn);
        }

    }

    public void replace(Device device) {
        int num = jdbcTemplate.update("replace into "+tableName+" (deviceId,deviceName,ip,deviceType,area,netArea,icon) VALUES (?,?,?,?,?,?,?)",
                new Object[]{device.getDeviceId(),device.getDeviceName(),device.getIp(),device.getDeviceType(),device.getArea(),device.getNetArea(),device.getIcon()});

        if(num == 0){
            throw new DataException("插入失败");
        }
    }

    public List<Device> queryAll() {
        List<Device> devices = jdbcTemplate.query("select * from "+tableName+" where 1=1",
        new BeanPropertyRowMapper<>(Device.class));

        return devices;
    }

    public Device query(Long deviceId) {
        List<Device> devices = jdbcTemplate.query("select * from "+tableName+" where deviceId= ?",
        new Object[]{deviceId},new BeanPropertyRowMapper<>(Device.class));
        if(CollectionUtils.isEmpty(devices)){
            return null;
        }
        return devices.get(0);
    }

    public List<Device> query8Type(int deviceType) {
        List<Device> devices = jdbcTemplate.query("select * from "+tableName+" where deviceType= ?",
                new Object[]{deviceType},new BeanPropertyRowMapper<>(Device.class));
        if(CollectionUtils.isEmpty(devices)){
            return null;
        }
        return devices;
    }

    public Page<Device> query8Page(Page<Device> page) {
        List<Device> devices = jdbcTemplate.query("select * from "+tableName +" ORDER BY deviceId limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Device.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        page.setTotal(total);

        return page;
    }

    /**
     * 根据ids批量获取设备
     * @param deviceIds
     * @return
     */
    public List<Device> query8Ids(Set<Long> deviceIds) {
        if(CollectionUtils.isEmpty(deviceIds)){
            return null;
        }

        StringBuffer sqlIds = new StringBuffer(" 1=2 ");
        deviceIds.forEach(deviceId -> {
            sqlIds.append(" or deviceId=" +deviceId);
        });
        List<Device> devices = jdbcTemplate.query("select * from "+tableName +" where "+ sqlIds.toString(),
                new BeanPropertyRowMapper<>(Device.class));

        return devices;
    }

    public void delete(long deviceId) {
        int num = jdbcTemplate.update("delete from "+tableName +" where deviceId = ?",
                new Object[]{deviceId});
    }

    public List<Device> query8ips(Set<String> ips) {
        if(CollectionUtils.isEmpty(ips)){
            return null;
        }

        StringBuffer sqlIds = new StringBuffer(" 1=2 ");
        ips.forEach(ip -> {
            sqlIds.append(" or ip=" + ip);
        });
        List<Device> devices = jdbcTemplate.query("select * from "+tableName +" where "+ sqlIds.toString(),
                new BeanPropertyRowMapper<>(Device.class));

        return devices;

    }

    public List<Device> query8IdAndType(Long deviceId, Integer type) {
        StringBuffer whereStr = new StringBuffer(" where 1=1 " );
        if(deviceId != null && deviceId >0){
            whereStr.append(" AND deviceId = "+deviceId);
        }

        if(type != null ){
            whereStr.append(" AND deviceType = "+ type);
        }

        List<Device> devices = jdbcTemplate.query("select * from "+tableName + whereStr,
                new BeanPropertyRowMapper<>(Device.class));

        return devices;
    }
}
