package com.minkey.db;

import com.minkey.db.dao.AlarmLog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlarmLogHandler {
    private final String tableName = "t_alarmLog";
    @Autowired
    JdbcTemplate jdbcTemplate;



    public long queryCount(int bType) {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" WHERE bType = ?" ,new Object[]{bType},Long.class);
        return count;
    }

    public Page<AlarmLog> query8page(int bType, Page<AlarmLog> page, SeachParam seachParam, Long bid) {
        StringBuffer whereStr = new StringBuffer(" where bType=" + bType);
        if(bid != null && bid >0){
            whereStr.append(" AND bid = "+bid);
        }
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        if(seachParam.getLevel() != null ){
            whereStr.append(" AND level = "+ seachParam.getLevel());
        }

        if(seachParam.getType() != null ){
            whereStr.append(" AND type = "+ seachParam.getType());
        }

        if(StringUtils.isNotEmpty(seachParam.getKeyword())){
            whereStr.append(" AND msg LIKE '%"+ seachParam.getKeyword()+"%'");
        }

        List<AlarmLog> devices = jdbcTemplate.query("select * from "+tableName + whereStr.toString()+" ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(AlarmLog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr.toString(),Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insert(AlarmLog alarmLog) {
        int num = jdbcTemplate.update("insert into "+tableName+" (bid,bType,type, level,msg,createTime) VALUES (?,?,?,?,?,?)"
                ,new Object[]{alarmLog.getBid(),alarmLog.getbType(),alarmLog.getType(),alarmLog.getLevel(),alarmLog.getMsg(),alarmLog.getCreateTime()});


    }


}
