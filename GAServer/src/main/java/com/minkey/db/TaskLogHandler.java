package com.minkey.db;

import com.minkey.db.dao.TaskLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class TaskLogHandler {
    private final static Logger logger = LoggerFactory.getLogger(TaskLogHandler.class);

    private final String tableName = "t_taskLog";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryMaxId() {
        Long count = jdbcTemplate.queryForObject("select max(*) from "+tableName+" ",Long.class);
        if(count == null){
            count = 0l;
        }
        return count;
    }

    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }


    public TaskLog query(Long taskLogId) {
            return jdbcTemplate.queryForObject("select configKey, configData from "+tableName+" where taskLogId= ?",new Object[]{taskLogId},TaskLog.class);
    }


    public void insertAll(List<TaskLog> taskLogs) {
        if(CollectionUtils.isEmpty(taskLogs)){
            return;
        }
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetLogId,taskId,successNum,successFlow,errorNum,errorFlow,createTime) VALUES (?,?,?,?,?,?,?)",
                taskLogs,taskLogs.size(),
                new ParameterizedPreparedStatementSetter<TaskLog>() {
                    @Override
                    public void setValues(PreparedStatement ps, TaskLog argument) throws SQLException {
                        ps.setLong(1,argument.getTargetLogId());
                        ps.setString(2,argument.getTaskId());
                        ps.setLong(3,argument.getSuccessNum());
                        ps.setLong(4,argument.getSuccessFlow());
                        ps.setLong(5,argument.getErrorNum());
                        ps.setLong(6,argument.getErrorFlow());
                        ps.setDate(7,new Date(argument.getCreateTime().getTime()));
                    }
                });
    }


}
