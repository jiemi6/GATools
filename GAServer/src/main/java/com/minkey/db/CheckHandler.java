package com.minkey.db;

import com.minkey.db.dao.Check;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import com.minkey.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Component
public class CheckHandler {
    private final String tableName = "t_check";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Page<Check> query8Page(Page<Check> page, SeachParam seachParam, Long uid) {
        StringBuffer whereStr = new StringBuffer(" where 1=1");
        if(uid != null && uid >0){
            whereStr.append(" AND uid = "+uid);
        }
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        if(seachParam.getLevel() != null ){
            whereStr.append(" AND level = "+ seachParam.getLevel());
        }

        if(StringUtils.isNotEmpty(seachParam.getKeyword())){
            whereStr.append(" AND msg LIKE %"+ seachParam.getKeyword()+"%");
        }


        List<Check> devices = jdbcTemplate.query("select * from "+tableName + whereStr + " ORDER BY checkId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Check.class));

        page.setTotal(jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr,Integer.class));
        page.setData(devices);

        return page;
    }

    private int queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        return count;
    }


    public long insert(Check check){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            String sql = "insert into "+tableName+" (checkName,checkType, uid) values ('"+check.getCheckName()+"', "+check.getCheckType()+", "+check.getUid()+") ";

            //通过传入第二个参数,就会产生主键返回给我们
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            //返回的结果集中包含主键,注意：主键还可以是UUID,
            //复合主键等,所以这里不是直接返回一个整型
            rs = ps.getGeneratedKeys();
            long id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            return id;
        }catch (Exception e){
            throw new SystemException(e);
        }finally {
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeConnection(conn);
        }
    }


}
