package com.minkey.db;

import com.minkey.dao.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
}
