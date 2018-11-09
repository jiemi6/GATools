package com.minkey.db;

import com.minkey.db.dao.DeviceLog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import com.minkey.exception.DataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class DeviceLogHandler {
    private final String tableName = "t_deviceLog";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(DeviceLog deviceLog) {
        int num = jdbcTemplate.update("INSERT into "+tableName+" ( deviceId,ip,level,msg,type) VALUES (?,?,?,?,?)",
                new Object[]{deviceLog.getDeviceId(),deviceLog.getIp(),deviceLog.getLevel(),deviceLog.getMsg(),deviceLog.getType()});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }


    public Page<DeviceLog> query8Page(Page<DeviceLog> page, SeachParam seachParam, Set<Long> deviceIds) {
        StringBuffer whereStr = new StringBuffer(" where 1=1");

        if(!CollectionUtils.isEmpty(deviceIds)){
            whereStr.append(" AND ( 1=2 ");
            deviceIds.forEach(deviceId ->{
                whereStr.append(" OR deviceId = "+deviceId);
            });
            whereStr.append(")");
        }
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        if(seachParam.getLevel() != null ){
            whereStr.append(" AND level = "+ seachParam.getLevel());
        }

        if(StringUtils.isNotEmpty(seachParam.getKeyword())){
            whereStr.append(" AND msg LIKE %"+ seachParam.getKeyword()+"%");
        }


        List<DeviceLog> deviceLogs = jdbcTemplate.query("select * from "+tableName + whereStr +" ORDER BY deviceLogId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(DeviceLog.class));

        page.setData(deviceLogs);

        page.setTotal(jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr,Integer.class));

        return page;
    }

    public Map<String,Long> querySum(Long linkId, SeachParam seachParam) {

        return  null;
    }
}
