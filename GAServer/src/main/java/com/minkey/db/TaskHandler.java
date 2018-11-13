package com.minkey.db;

import com.minkey.db.dao.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Component
public class TaskHandler {
    private final String tableName = "t_task";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }


    public Task query(Long taskId) {
        List<Task> taskList= jdbcTemplate.query("select * from "+tableName+" where taskId= ?",
            new Object[]{taskId}, new BeanPropertyRowMapper<>(Task.class));
        if(CollectionUtils.isEmpty(taskList)){
            return null;
        }
        return taskList.get(0);

    }

    public List<Task> queryAll() {
        return jdbcTemplate.query("select * from "+tableName, new BeanPropertyRowMapper<>(Task.class));
    }

    public void insertAll(List<Task> tasks) {
        if(CollectionUtils.isEmpty(tasks)){
            return;
        }

        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetId, taskName,linkId,status,taskType) VALUES (?,?,?,?,?)",
                tasks,tasks.size(),
                new ParameterizedPreparedStatementSetter<Task>() {
                    @Override
                    public void setValues(PreparedStatement ps, Task argument) throws SQLException {
                        ps.setString(1,argument.getTargetId());
                        ps.setString(2,argument.getTaskName());
                        ps.setLong(3,argument.getLinkId());
                        ps.setInt(4,argument.getStatus());
                        ps.setInt(5,argument.getTaskType());
                    }
                });

    }


    public void del8LinkId(Long linkId) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where linkId= ?",new Object[]{linkId});

    }

    public List<Task> query8Ids(Set<Long> taskIds) {
        if(CollectionUtils.isEmpty(taskIds)){
            return null;
        }

        StringBuffer sqlIds = new StringBuffer(" 1=2 ");
        taskIds.forEach(taskId -> {
            sqlIds.append(" or taskId=" + taskId);
        });
        List<Task> devices = jdbcTemplate.query("select * from "+tableName +" where "+ sqlIds.toString(),
                new BeanPropertyRowMapper<>(Task.class));

        return devices;
    }
}
