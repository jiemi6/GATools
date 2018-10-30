package com.minkey.db;

import com.minkey.db.dao.TaskLog;
import com.minkey.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Component
public class TaskLogHandler {
    private final String tableName = "t_taskLog";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryMaxId(long linkId) {
        //targetLogId 为字符串，如果需要比较字符串大小，要先加0
        Long count = jdbcTemplate.queryForObject("select max(targetLogId+0) from "+tableName+" WHERE linkId=?",new Object[]{linkId},Long.class);
        if(count == null){
            count = 0l;
        }
        return count;
    }

    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public Page<TaskLog> query8page(Long linkId, Page<TaskLog> page) {
        List<TaskLog> devices = jdbcTemplate.query("select * from "+tableName +" where linkId= ? ORDER BY logId desc limit ?,?",
                new Object[]{linkId,page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(TaskLog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName+" where linkId= ?  ",new Object[]{linkId},Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insertAll(List<TaskLog> taskLogs) {
        if(CollectionUtils.isEmpty(taskLogs)){
            return;
        }
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetLogId,taskId,linkId,successNum,successFlow,errorNum,errorFlow,createTime) VALUES (?,?,?,?,?,?,?,?)",
                taskLogs,taskLogs.size(),
                new ParameterizedPreparedStatementSetter<TaskLog>() {
                    @Override
                    public void setValues(PreparedStatement ps, TaskLog argument) throws SQLException {
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


    public TaskLog querySum(long linkId) {
        Map<String, Object> taskLogList = jdbcTemplate.queryForMap("select  sum(successFlow) as successFlow,sum(successNum) as successNum,sum(errorFlow) as errorFlow,sum(errorNum) as errorNum  from "+tableName+" where linkId= ?",new Object[]{linkId});
        TaskLog taskLog = new TaskLog();
        if(!CollectionUtils.isEmpty(taskLogList)){
            taskLog.setSuccessNum(bigDecimal2long((BigDecimal)taskLogList.get("successNum")));
            taskLog.setSuccessFlow(bigDecimal2long((BigDecimal)taskLogList.get("successFlow")));
            taskLog.setErrorNum(bigDecimal2long((BigDecimal)taskLogList.get("errorNum")));
            taskLog.setErrorFlow(bigDecimal2long((BigDecimal)taskLogList.get("errorFlow")));
        }
        return taskLog;

    }

    private long bigDecimal2long(BigDecimal bigDecimal){
        if(bigDecimal == null){
            return 0;
        }
        return bigDecimal.longValue();
    }
}
