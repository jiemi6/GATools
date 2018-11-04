package com.minkey.db;

import com.minkey.db.dao.User;
import com.minkey.db.dao.UserLog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import com.minkey.exception.DataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class UserLogHandler {
    private final String tableName = "t_userLog";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void log(HttpSession session, String moduleName, String log){
        User user = (User) session.getAttribute("user");
        this.log(user,moduleName,log);
    }
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


    public Page<UserLog> query8Page(Page<UserLog> page, SeachParam seachParam, Long uid) {
        StringBuffer whereStr = new StringBuffer(" where 1=1");
        if(uid != null && uid >0){
            whereStr.append(" AND uid = "+uid);
        }
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        if(StringUtils.isNotEmpty(seachParam.getKeyWord())){
            whereStr.append(" AND msg LIKE %"+ seachParam.getKeyWord()+"%");
        }

        List<UserLog> userLogs = jdbcTemplate.query("select * from "+tableName + whereStr +" ORDER BY userLogId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(UserLog.class));

        page.setTotal(jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr,Integer.class));

        page.setData(userLogs);

        return page;
    }
}
