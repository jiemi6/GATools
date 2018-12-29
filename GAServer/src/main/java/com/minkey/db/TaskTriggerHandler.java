package com.minkey.db;

import com.minkey.db.dao.TaskSource;
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

/**
 * 任务与表的触发器对应关系
 */
@Component
public class TaskTriggerHandler {
    private final String tableName = "t_taskTrigger";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public TaskSource query(Long linkId,String taskTargetId) {
        List<TaskSource> taskList= jdbcTemplate.query("select * from "+tableName+" where linkId= ? AND taskId= ?",
                new Object[]{linkId,taskTargetId}, new BeanPropertyRowMapper<>(TaskSource.class));
        if(CollectionUtils.isEmpty(taskList)){
            return null;
        }
        return taskList.get(0);

    }

    public void insertAll(List<TaskSource> taskSourceList) {
        if(CollectionUtils.isEmpty(taskSourceList)){
            return;
        }

        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetId, taskId,linkId,fromResourceId,toResourceId,createTime) VALUES (?,?,?,?,?,?)",
                taskSourceList,taskSourceList.size(),
                new ParameterizedPreparedStatementSetter<TaskSource>() {
                    @Override
                    public void setValues(PreparedStatement ps, TaskSource argument) throws SQLException {
                        ps.setString(1,argument.getTargetId());
                        ps.setString(2,argument.getTaskId());
                        ps.setLong(3,argument.getLinkId());
                        ps.setString(4,argument.getFromResourceId());
                        ps.setString(5,argument.getToResourceId());
                        ps.setTimestamp(6,new Timestamp(argument.getCreateTime().getTime()));
                    }
                });
    }


    public void del8LinkId(Long linkId) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where linkId= ?",new Object[]{linkId});
    }


}
