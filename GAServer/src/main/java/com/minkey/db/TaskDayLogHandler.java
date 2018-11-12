package com.minkey.db;

import com.minkey.db.dao.TaskDayLog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
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
public class TaskDayLogHandler {
    private final String tableName = "t_taskDayLog";
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

    public Page<TaskDayLog> query8page(Long linkId, Page<TaskDayLog> page, SeachParam seachParam) {
        StringBuffer whereStr = new StringBuffer(" where linkId=" + linkId);
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        List<TaskDayLog> devices = jdbcTemplate.query("select * from "+tableName + whereStr +" ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(TaskDayLog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr,Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insertAll(List<TaskDayLog> taskDayLogs) {
        if(CollectionUtils.isEmpty(taskDayLogs)){
            return;
        }
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetLogId,taskId,linkId,successNum,successFlow,errorNum,errorFlow,createTime) VALUES (?,?,?,?,?,?,?,?)",
                taskDayLogs, taskDayLogs.size(),
                new ParameterizedPreparedStatementSetter<TaskDayLog>() {
                    @Override
                    public void setValues(PreparedStatement ps, TaskDayLog argument) throws SQLException {
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


    public TaskDayLog querySum(long linkId, SeachParam seachParam) {
        StringBuffer whereStr = new StringBuffer(" where linkId=" + linkId);
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        Map<String, Object> taskLogList = jdbcTemplate.queryForMap("select  sum(successFlow) as successFlow,sum(successNum) as successNum,sum(errorFlow) as errorFlow,sum(errorNum) as errorNum  from "+tableName+ whereStr);
        TaskDayLog taskDayLog = new TaskDayLog();
        if(!CollectionUtils.isEmpty(taskLogList)){
            taskDayLog.setSuccessNum(bigDecimal2long((BigDecimal)taskLogList.get("successNum")));
            taskDayLog.setSuccessFlow(bigDecimal2long((BigDecimal)taskLogList.get("successFlow")));
            taskDayLog.setErrorNum(bigDecimal2long((BigDecimal)taskLogList.get("errorNum")));
            taskDayLog.setErrorFlow(bigDecimal2long((BigDecimal)taskLogList.get("errorFlow")));
        }
        return taskDayLog;

    }

    private long bigDecimal2long(BigDecimal bigDecimal){
        if(bigDecimal == null){
            return 0;
        }
        return bigDecimal.longValue();
    }
}
