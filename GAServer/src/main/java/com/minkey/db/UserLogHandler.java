package com.minkey.db;

import com.minkey.dto.Page;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserLogHandler {
    private final static Logger logger = LoggerFactory.getLogger(UserLogHandler.class);

    private final String tableName = "t_userLog";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public Integer queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        return count;
    }

    public void insert(String userName,String loginIp, String msg) {
        int num = jdbcTemplate.update("INSERT into "+tableName+" ( userName,loginIp,msg) VALUES (?,?,?)",
                new Object[]{userName,loginIp,msg});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }


    public Page query8Page(Page page) {
        List<Map<String, Object>> devices = jdbcTemplate.query("select * from "+tableName +" ORDER BY userLogId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new ColumnMapRowMapper());

        page.setTotal(queryCount());
        page.setData(devices);

        return page;
    }
}
