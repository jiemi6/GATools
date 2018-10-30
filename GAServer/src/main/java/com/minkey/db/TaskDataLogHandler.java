package com.minkey.db;

import com.minkey.db.dao.TaskDataLog;
import com.minkey.db.dao.TaskLog;
import com.minkey.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class TaskDataLogHandler {
    private final String tableName = "t_taskDataLog";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryMaxId(long linkId) {
        Long count = jdbcTemplate.queryForObject("select max(targetLogId) from "+tableName+" WHERE linkId=?",new Object[]{linkId},Long.class);
        if(count == null){
            count = 0l;
        }
        return count;
    }

    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public Page<TaskLog> query8page(Page<TaskLog> page) {
        List<TaskLog> devices = jdbcTemplate.query("select * from "+tableName +" ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(TaskLog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insertAll(List<TaskDataLog> taskLogs) {
        if(CollectionUtils.isEmpty(taskLogs)){
            return;
        }
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetLogId,taskId,linkId,successNum,successFlow,errorNum,errorFlow,createTime) VALUES (?,?,?,?,?,?,?,?)",
                taskLogs,taskLogs.size(),
                new ParameterizedPreparedStatementSetter<TaskDataLog>() {
                    @Override
                    public void setValues(PreparedStatement ps, TaskDataLog argument) throws SQLException {
                        ps.setLong(1,argument.getTargetLogId());
                        ps.setString(2,argument.getTaskId());
                        ps.setLong(3,argument.getLinkId());
                        ps.setLong(4,argument.getSuccessNum());
                        ps.setLong(5,argument.getSuccessFlow());
                        ps.setLong(6,argument.getErrorNum());
                        ps.setLong(7,argument.getErrorFlow());
                        ps.setTimestamp(8,new Timestamp(argument.getCreateTime().getTime()));
                    }
                });
    }


}
