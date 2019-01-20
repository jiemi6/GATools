package com.minkey.db;

import com.minkey.db.dao.Syslog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import org.apache.commons.lang3.StringUtils;
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


    public Page<Syslog> query8page(Page<Syslog> page, SeachParam seachParam, Set<String> paramIps) {
        StringBuffer whereStr = new StringBuffer(" where 1=1");
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        if(seachParam.getLevel() != null ){
            whereStr.append(" AND level = "+ seachParam.getLevel());
        }

        if(StringUtils.isNotEmpty(seachParam.getKeyword())){
            whereStr.append(" AND msg LIKE %"+ seachParam.getKeyword()+"%");
        }

        if(!CollectionUtils.isEmpty(paramIps)){
            whereStr.append(" AND ( 1=2 ");
            paramIps.forEach(paramIp -> {
                whereStr.append(" or host='"+paramIp+"'");
            });
            whereStr.append(")");
        }

        List<Syslog> devices = jdbcTemplate.query("select * from "+tableName + whereStr + " ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Syslog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr,Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insertAll(Set<Syslog> taskLogs) {
        if(CollectionUtils.isEmpty(taskLogs)){
            return;
        }
        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (host,level,facility,msg,createTime) VALUES (?,?,?,?,?)",
                taskLogs,taskLogs.size(),
                new ParameterizedPreparedStatementSetter<Syslog>() {
                    @Override
                    public void setValues(PreparedStatement ps, Syslog argument) throws SQLException {
                        ps.setString(1,argument.getHost());
                        ps.setInt(2,argument.getLevel());
                        ps.setInt(3,argument.getFacility());
                        ps.setString(4,argument.getMsg());
                        ps.setTimestamp(5,new Timestamp(argument.getCreateTime().getTime()));
                    }
                });
    }


    public void clean(String deleteDayStr) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where createTime <= ?",new Object[]{deleteDayStr});
    }
}
