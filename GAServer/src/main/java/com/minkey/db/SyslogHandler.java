package com.minkey.db;

import com.minkey.db.dao.Syslog;
import com.minkey.dto.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Set;

@Component
public class SyslogHandler {
    private final static Logger logger = LoggerFactory.getLogger(SyslogHandler.class);

    private final String tableName = "t_syslog";
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


    public Page<Syslog> query8page(Page<Syslog> page) {
        List<Syslog> devices = jdbcTemplate.query("select * from "+tableName +" ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Syslog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insertAll(Set<Syslog> taskLogs) {
        if(CollectionUtils.isEmpty(taskLogs)){
            return;
        }
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (host,level,msg,createTime) VALUES (?,?,?,?)",
                taskLogs,taskLogs.size(),
                new ParameterizedPreparedStatementSetter<Syslog>() {
                    @Override
                    public void setValues(PreparedStatement ps, Syslog argument) throws SQLException {
                        ps.setString(1,argument.getHost());
                        ps.setInt(2,argument.getLevel());
                        ps.setString(3,argument.getMsg());
                        ps.setTimestamp(4,new Timestamp(argument.getCreateTime().getTime()));
                    }
                });
    }


}
