package com.minkey.db;

import com.minkey.db.dao.User;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统用户
 */
@Component
public class UserHandler {
    private final static Logger logger = LoggerFactory.getLogger(UserHandler.class);

    private final String tableName = "t_user";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public void insert(User user) {
        int num = jdbcTemplate.update("insert into "+tableName+" (uName,pwd, createUid,status,phone,email,wrongPwdNum,auth,loginIpStart,loginIpEnd,loginTimeStart,loginTimeEnd) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"
                ,new Object[]{user.getuName(),user.getPwd(),user.getCreateUid(),user.getStatus(),user.getPhone(),user.getEmail(),user.getWrongPwdNum(),user.getAuth(),user.getLoginIpStart(),user.getLoginIpEnd(),user.getLoginTimeStart(),user.getLoginTimeEnd()});

        if(num == 0){
            throw new DataException("添加新用户失败");
        }
    }

    public void update(User user) {
        Long uid = user.getUid();
        String uName = user.getuName();
        Integer auth = user.getAuth();


        StringBuffer sb = new StringBuffer("update "+tableName+" SET " );
        if(StringUtils.isNotEmpty(uName)){
            sb.append(" uName = '"+uName+"'").append(",");
        }
        if(StringUtils.isNotEmpty(user.getPhone())){
            sb.append(" phone = '"+user.getPhone()+"'").append(",");
        }
        if(StringUtils.isNotEmpty(user.getEmail())){
            sb.append(" email = '"+user.getEmail()+"'").append(",");
        }

        if(user.getLoginTimeStart() != 0){
            sb.append(" loginTimeStart = "+user.getLoginTimeStart()).append(",");
        }
        if(user.getLoginTimeEnd() != 0){
            sb.append(" loginTimeEnd = "+user.getLoginTimeEnd()).append(",");
        }

        if(StringUtils.isNotEmpty(user.getLoginIpStart())){
            sb.append(" loginIpStart = '"+user.getLoginIpStart()+"'").append(",");
        }
        if(StringUtils.isNotEmpty(user.getLoginIpEnd())){
            sb.append(" loginIpEnd = '"+user.getLoginIpEnd()+"'").append(",");
        }


        if(auth != null){
            sb.append(" auth = "+auth);
        }
        sb.append(" where uid="+uid);

        int num = jdbcTemplate.update(sb.toString());

        if(num == 0){
            throw new DataException("修改用户不存在");
        }
    }

    /**
     * 重置密码
     * @param uid
     */
    public void resetPwd(Long uid,String pwd) {
        int num = jdbcTemplate.update("update "+tableName+" set pwd = ? where uid= ?",new Object[]{pwd,uid});

        if(num == 0){
            throw new DataException("重置用户密码失败");
        }
    }

    /**
     * 用户输入密码错误，次数加1
     * @param uid
     */
    public void wrongPwd(Long uid) {
        int num = jdbcTemplate.update("update "+tableName+" set wrongPwdNum = wrongPwdNum + 1 where uid= ?",new Object[]{uid});

        if(num == 0){
            throw new DataException("更新用户密码输入次数失败");
        }
    }

    /**
     * 清除次数
     * @param uid
     */
    public void cleanWrongPwdTime(Long uid) {
        int num = jdbcTemplate.update("update "+tableName+" set wrongPwdNum = 0 where uid= ?",new Object[]{uid});

        if(num == 0){
            throw new DataException("清除用户密码输入次数失败");
        }
    }

    public User query(Long uid) {
        List<User>  userList = jdbcTemplate.query("select * from "+tableName+" where uid= ?",new Object[]{uid}, new BeanPropertyRowMapper<>(User.class));
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }

    public User query8Name(String uName) {
        List<User> userList =  jdbcTemplate.query("select * from "+tableName+" where uName= ?",new Object[]{uName}, new BeanPropertyRowMapper<>(User.class));
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }

    public List<User> queryAll() {
        List<User> userList = jdbcTemplate.query("select * from "+tableName , new Object[]{}, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }


    public void del(Long uid) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where uid= ?",new Object[]{uid});

        if(num == 0){
            throw new DataException("删除用户不存在");
        }
    }

}
