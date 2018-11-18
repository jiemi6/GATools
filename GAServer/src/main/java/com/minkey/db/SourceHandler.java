package com.minkey.db;

import com.minkey.db.dao.Source;
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

@Component
public class SourceHandler {
    private final String tableName = "t_source";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public Source query(Long linkId, String sourceId) {
        List<Source> taskList= jdbcTemplate.query("select * from "+tableName+" where linkId= ? AND targetId= ?",
                new Object[]{linkId,sourceId}, new BeanPropertyRowMapper<>(Source.class));
        if(CollectionUtils.isEmpty(taskList)){
            return null;
        }
        return taskList.get(0);

    }


    public void insertAll(List<Source> taskSourceList) {
        if(CollectionUtils.isEmpty(taskSourceList)){
            return;
        }

        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (targetId,linkId,sname,dbVersion,sourceType,ip,port,dbName,name,pwd,createTime,netArea) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
                taskSourceList,taskSourceList.size(),
                new ParameterizedPreparedStatementSetter<Source>() {
                    @Override
                    public void setValues(PreparedStatement ps, Source argument) throws SQLException {
                        ps.setString(1,argument.getTargetId());
                        ps.setLong(2,argument.getLinkId());
                        ps.setString(3,argument.getSname());
                        ps.setString(4,argument.getDbVersion());
                        ps.setString(5,argument.getSourceType());
                        ps.setString(6,argument.getIp());
                        ps.setInt(7,argument.getPort());
                        ps.setString(8,argument.getDbName());
                        ps.setString(9,argument.getName());
                        ps.setString(10,argument.getPwd());
                        ps.setTimestamp(11,new Timestamp(argument.getCreateTime().getTime()));
                        ps.setInt(12,argument.getNetArea());
                    }
                });
    }


    public void del8LinkId(Long linkId) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where linkId= ?",new Object[]{linkId});

    }
}
