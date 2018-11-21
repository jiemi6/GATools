package com.minkey.db;

import com.minkey.db.dao.User;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 */
@Component
public class UserHandler {
    private final String tableName = "t_user";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" WHERE status > -1 ",Long.class);
        return count;
    }

    public void insert(User user) {
        int num = jdbcTemplate.update("insert into "+tableName+" (name,uName,pwd, createUid,status,phone,email,wrongPwdNum,auth,loginIpStart,loginIpEnd,loginTimeStart,loginTimeEnd) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
                ,new Object[]{user.getName(),user.getuName(),user.getPwd(),user.getCreateUid(),user.getStatus(),user.getPhone(),user.getEmail(),user.getWrongPwdNum(),user.getAuth(),user.getLoginIpStart(),user.getLoginIpEnd(),user.getLoginTimeStart(),user.getLoginTimeEnd()});

        if(num == 0){
            throw new DataException("添加新用户失败");
        }
    }

    public void update(User user) {
        Long uid = user.getUid();
        String name = user.getuName();
        Integer auth = user.getAuth();


        StringBuffer sb = new StringBuffer("update "+tableName+" SET " );
        if(StringUtils.isNotEmpty(name)){
            sb.append(" name = '"+ name+"'").append(",");
        }
        if(StringUtils.isNotEmpty(user.getPhone())){
            sb.append(" phone = '"+user.getPhone()+"'").append(",");
        }
        if(StringUtils.isNotEmpty(user.getEmail())){
            sb.append(" email = '"+user.getEmail()+"'").append(",");
        }

        if(StringUtils.isNotEmpty(user.getLoginTimeStart())){
            sb.append(" loginTimeStart = '"+user.getLoginTimeStart()+"'").append(",");
        }
        if(StringUtils.isNotEmpty(user.getLoginTimeEnd())){
            sb.append(" loginTimeEnd = '"+user.getLoginTimeEnd()+"'").append(",");
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
     * 更新密码
     * @param uid
     */
    public void resetPwd(Long uid,String pwd) {
        int num = jdbcTemplate.update("update "+tableName+" set pwd = ? where status > -1 AND uid= ?",new Object[]{pwd,uid});

        if(num == 0){
            throw new DataException("更新用户密码失败");
        }
    }

    /**
     * 用户输入密码错误，次数加1,如果是第五次。 就直接设置为锁定
     * @param user
     */
    public void wrongPwd(User user) {
        //如果此时是第五次错误。则锁定
        if(user.getWrongPwdNum() == User.MAX_WRONGPWDNUM-1){
            int num = jdbcTemplate.update("update "+tableName+" set wrongPwdNum = wrongPwdNum + 1 ,status =? where status > -1 AND uid= ?",new Object[]{User.STATUS_LOCK,user.getUid()});
        }else{
            //只增加次数
            int num = jdbcTemplate.update("update "+tableName+" set wrongPwdNum = wrongPwdNum + 1 where status > -1 AND uid= ?",new Object[]{user.getUid()});
        }


    }

    /**
     * 清除次数,而且修改用户状态为正常
     * @param uid
     */
    public void cleanWrongPwdTime(Long uid) {
        int num = jdbcTemplate.update("update "+tableName+" set wrongPwdNum = 0 ,status=? where status > -1 AND uid= ?",new Object[]{User.STATUS_NORMAL,uid});


    }

    public User query(Long uid) {
        List<User>  userList = jdbcTemplate.query("select * from "+tableName+" where status > -1 AND uid= ?",new Object[]{uid}, new BeanPropertyRowMapper<>(User.class));
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }

    public User query8Name(String uName) {
        List<User> userList =  jdbcTemplate.query("select * from "+tableName+" where status > -1 AND  uName= ?",new Object[]{uName}, new BeanPropertyRowMapper<>(User.class));
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }

    /**
     * 查询所有用户，不包括已经删除的
     * @return
     */
    public List<User> queryAll() {
        List<User> userList = jdbcTemplate.query("select * from "+tableName + " where uid > 0 AND status > -1", new Object[]{}, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }


    public void del(Long uid) {
        int num = jdbcTemplate.update("update "+tableName+" set status = -1 where uid= ?",new Object[]{uid});

    }

    /**
     * 根据ids批量获取用户,包含已经删除的用户
     * @param uids
     * @return
     */
    public List<User> query8Ids(Set<Long> uids) {
        if(CollectionUtils.isEmpty(uids)){
            return null;
        }

        StringBuffer sqlIds = new StringBuffer(" 1=2 ");
        uids.forEach(uid -> {
            sqlIds.append(" or uid=" +uid);
        });
        List<User> devices = jdbcTemplate.query("select * from "+tableName +" where "+ sqlIds.toString(),
                new BeanPropertyRowMapper<>(User.class));

        return devices;
    }

}
