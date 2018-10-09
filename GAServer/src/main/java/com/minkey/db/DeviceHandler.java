package com.minkey.db;

import com.minkey.db.dao.Device;
import com.minkey.dto.Page;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

    public void replace(Device device) {
        int num = jdbcTemplate.update("replace into "+tableName+" (configKey, configData) VALUES (?,?)",new Object[]{device});

        if(num == 0){
            throw new DataException("插入配置失败");
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

    public Page query8Page(Page page) {
        List<Device> devices = jdbcTemplate.query("select * from "+tableName +" ORDER BY deviceId limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Device.class));

        page.setData(devices);

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

        StringBuffer sqlIds = new StringBuffer(" 1=1 ");
        deviceIds.forEach(deviceId -> {
            sqlIds.append("or deviceId=" +deviceId);
        });
        List<Device> devices = jdbcTemplate.query("select * from "+tableName +" where "+ sqlIds.toString(),
                new BeanPropertyRowMapper<>(Device.class));

        return devices;
    }
}
