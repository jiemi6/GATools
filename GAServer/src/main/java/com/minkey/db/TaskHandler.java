package com.minkey.db;

import com.minkey.db.dao.Task;
import com.minkey.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class TaskHandler {
    private final String tableName = "t_task";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+"  t,t_link tl where t.linkId=tl.linkId AND  t.status > 0",Integer.class);
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
        return jdbcTemplate.query("select * from "+tableName +" t,t_link tl where t.linkId=tl.linkId ", new BeanPropertyRowMapper<>(Task.class));
    }

    public void insertAll(List<Task> tasks) {
        if(CollectionUtils.isEmpty(tasks)){
            return;
        }

        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetTaskId, taskName,linkId,status,taskType) VALUES (?,?,?,?,?)",
                tasks,tasks.size(),
                new ParameterizedPreparedStatementSetter<Task>() {
                    @Override
                    public void setValues(PreparedStatement ps, Task argument) throws SQLException {
                        ps.setString(1,argument.getTargetTaskId());
                        ps.setString(2,argument.getTaskName());
                        ps.setLong(3,argument.getLinkId());
                        ps.setInt(4,argument.getStatus());
                        ps.setInt(5,argument.getTaskType());
                    }
                });

    }


    public void del8LinkId(Long linkId) {
        int num = jdbcTemplate.update("update "+tableName+" set status=-100 where linkId= ?",new Object[]{linkId});

    }

    public List<Task> query8TaskIds(Set<Long> taskIds) {
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

    public List<Task> query8LinkAndTargetIds(Long linkId, Set<String> targetTaskIds) {
        if(CollectionUtils.isEmpty(targetTaskIds)){
            return null;
        }

        StringBuffer sqlIds = new StringBuffer(" AND ( 1=2 ");
        targetTaskIds.forEach(taskId -> {
            sqlIds.append(" or targetTaskId=" + taskId);
        });
        sqlIds.append(")");
        List<Task> devices = jdbcTemplate.query("select * from "+tableName +" where linkId=? "+ sqlIds.toString(),
                new Object[]{linkId},new BeanPropertyRowMapper<>(Task.class));

        return devices;
    }

    public void updateLevel(Set<Long> taskIds, int taskLevel) {
        if(CollectionUtils.isEmpty(taskIds)){
            return;
        }

        StringBuffer whereStr = new StringBuffer(" where ( 1=2 ");
        taskIds.forEach(taskId -> {
            whereStr.append(" or taskId=" + taskId);
        });
        whereStr.append(")");
        int num = jdbcTemplate.update("update "+tableName+" set level=? "+whereStr,new Object[]{taskLevel});

    }

    public Page<Task> query8LinkId(Long linkId, Page<Task> page) {
        List<Task> tasks = jdbcTemplate.query("select * from "+tableName + " where linkId=? ORDER BY level desc limit ?,?",
                new Object[]{linkId,page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Task.class));

        page.setData(tasks);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName + " where linkId=?",new Object[]{linkId},Integer.class);
        page.setTotal(total);

        return page;
    }

    public boolean isExist(long linkId, String targetTaskId) {
        List<Task> taskList= jdbcTemplate.query("select * from "+tableName+" where linkId=? AND targetTaskId=?",
                new Object[]{linkId,targetTaskId}, new BeanPropertyRowMapper<>(Task.class));
        if(CollectionUtils.isEmpty(taskList)){
            return false;
        }
        return true;
    }

    public void insert(Task task) {
        List<Task> tasks = new ArrayList<>(1);
        tasks.add(task);
        insertAll(tasks);
    }


    public void update(Task task) {
        int num = jdbcTemplate.update("update "+tableName+" set taskName=?,status=?,taskType=? WHERE targetTaskId=? AND linkId=?"
                ,new Object[]{task.getTaskName(),task.getStatus(),task.getTaskType(),task.getTargetTaskId(),task.getLinkId()});
    }
}
