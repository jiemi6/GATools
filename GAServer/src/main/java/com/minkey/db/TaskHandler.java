package com.minkey.db;

import com.minkey.db.dao.Task;
import com.minkey.exception.DataException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    public void repleaceAll(List<Task> tasks) {
        if(CollectionUtils.isEmpty(tasks)){
            return;
        }
        String sql = null;
        List<String> sqlValues = new ArrayList<>(tasks.size());
        tasks.forEach(task -> {
            sqlValues.add(String.format("(%s,%s,%s)","'"+task.getTaskId()+"'","'"+task.getTaskName()+"'",task.getLinkId()));

        });
        sql += StringUtils.join(sqlValues,",");

        int num = jdbcTemplate.update("replace into "+tableName+" (taskId, taskName,linkId) VALUES "+sql);

        if(num == 0){
            throw new DataException("更新失败");
        }
    }
}
