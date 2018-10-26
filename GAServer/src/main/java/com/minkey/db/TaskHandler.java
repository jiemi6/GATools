package com.minkey.db;

import com.minkey.db.dao.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class TaskHandler {
    private final static Logger logger = LoggerFactory.getLogger(TaskHandler.class);

    private final String tableName = "t_task";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }


    public Task query(Long taskId) {
            return jdbcTemplate.queryForObject("select configKey, configData from "+tableName+" where taskId= ?",new Object[]{taskId},Task.class);
    }

    public List<Task> queryAll() {
        return jdbcTemplate.queryForList("select configKey, configData from "+tableName,Task.class);
    }

    public void insertAll(List<Task> tasks) {
        if(CollectionUtils.isEmpty(tasks)){
            return;
        }

        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetId, taskName,linkId,status) VALUES (?,?,?,?)",
                tasks,tasks.size(),
                new ParameterizedPreparedStatementSetter<Task>() {
                    @Override
                    public void setValues(PreparedStatement ps, Task argument) throws SQLException {
                        ps.setString(1,argument.getTargetId());
                        ps.setString(2,argument.getTaskName());
                        ps.setLong(3,argument.getLinkId());
                        ps.setInt(4,argument.getStatus());
                    }
                });

    }


    public void del(Long linkId) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where linkId= ?",new Object[]{linkId});

    }
}
