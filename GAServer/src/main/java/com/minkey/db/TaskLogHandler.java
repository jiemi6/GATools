package com.minkey.db;

import com.minkey.db.dao.TaskLog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskLogHandler {
    private final String tableName = "t_taskLog";
    @Autowired
    JdbcTemplate jdbcTemplate;



    public long queryCount(int bType) {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" WHERE bType = ?" ,new Object[]{bType},Long.class);
        return count;
    }

    public Page<TaskLog> query8page(int bType, Page<TaskLog> page, SeachParam seachParam, Long bid) {
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

        List<TaskLog> devices = jdbcTemplate.query("select * from "+tableName + whereStr.toString()+" ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(TaskLog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr.toString(),Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insert(TaskLog taskLog) {
        int num = jdbcTemplate.update("insert into "+tableName+" (taskId,linkId,level,errorType,msg) VALUES (?,?,?,?,?)"
                ,new Object[]{taskLog.getTaskId(),taskLog.getLinkId(),taskLog.getLevel(),taskLog.getErrorType(),taskLog.getMsg()});


    }


}
