package com.minkey.db;

import com.minkey.db.dao.User;
import com.minkey.db.dao.UserLog;
import com.minkey.dto.Page;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserLogHandler {
    private final static Logger logger = LoggerFactory.getLogger(UserLogHandler.class);

    private final String tableName = "t_userLog";
    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 插入一条用户日志
     * @param user
     * @param moduleName
     * @param log
     */
    public void log(User user, String moduleName, String log){
        UserLog userLog = new UserLog();
        userLog.setLoginIp(user.getLoginIp());
        userLog.setuName(user.getuName());
        userLog.setModuleName(moduleName);
        userLog.setUid(user.getUid());
        userLog.setMsg(log);
        insert(userLog);
    }

    public Integer queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        return count;
    }

    public void insert(UserLog userLog) {
        int num = jdbcTemplate.update("INSERT into "+tableName+" ( uid,uName,loginIp,moduleName,msg) VALUES (?,?,?,?,?)",
                new Object[]{userLog.getUid(),userLog.getuName(),userLog.getLoginIp(),userLog.getModuleName(),userLog.getMsg()});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }


    public Page<UserLog> query8Page(Page<UserLog> page) {
        List<UserLog> devices = jdbcTemplate.query("select * from "+tableName +" ORDER BY userLogId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(UserLog.class));

        page.setTotal(queryCount());

        page.setData(devices);

        return page;
    }
}
