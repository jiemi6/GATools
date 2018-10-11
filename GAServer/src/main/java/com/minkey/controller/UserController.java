package com.minkey.controller;

import com.minkey.db.UserHandler;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.log.UserLog;
import com.minkey.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserHandler userHandler;

    @Autowired
    UserLog userLog;

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/getVCode")
    public String getVCode(HttpSession session,HttpServletResponse resp) {
        char[] vcode = VCodeUtil.getCode(VCodeUtil.VCODE_NUM);

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write(VCodeUtil.generateCodeAndPic(vcode), "jpeg", sos);
            sos.close();

            session.setAttribute("vcode",String.valueOf(vcode));
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }
        return null;
    }

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/checkVCode")
    public String checkVCode(HttpSession session,String vcode) {

        try{
            if(StringUtils.equals(vcode,(String)session.getAttribute("vcode"))){
                return JSONMessage.createSuccess().toString();
            }else{
                return JSONMessage.createFalied("验证码错误").toString();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

    /**
     * 登录接口
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session, HttpServletRequest request, String uName, String pwd, String vcode) {
        logger.info("start: 执行用户登陆");
        try{
            if(StringUtils.equals(vcode,(String)session.getAttribute("vcode"))){
                return JSONMessage.createFalied("验证码错误").toString();
            }

            User user = userHandler.query8Name(uName);

            if(user == null){
                return JSONMessage.createFalied("用户名密码错误").toString();
            }

            if(user.getWrongPwdNum() >= User.MAX_WRONGPWDNUM){
                return JSONMessage.createFalied("用户名密码错误次数达到上限，用户锁定").toString();
            }

            if(!StringUtils.equals(StringUtil.md5(pwd),user.getPwd())){
                //增加锁定次数
                userHandler.wrongPwd(user.getUid());
                return JSONMessage.createFalied("用户名密码错误").toString();
            }

            //判断登陆ip
            String loginIp = IPUtil.getClientIp(request);
            long ipStart = IPUtil.ipToLong(user.getLoginIpStart());
            long ipEnd = IPUtil.ipToLong(user.getLoginIpEnd());
            if(ipStart == 0  && ipEnd== 0 ){
                //不限制
            }else{
                long ip = IPUtil.ipToLong(loginIp);
                if(ipStart > ip || ipEnd < ip){
                    return JSONMessage.createFalied("非安全ip段内不允许登陆").toString();
                }
            }
            user.setLoginIp(loginIp);

            //判断登陆时间
            if(user.getLoginTimeStart() == 0 && user.getLoginTimeEnd() ==0){
                //不限制
            }else {
                SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
                try {
                    Date now = sf.parse(sf.format(new Date()));
                    Date beginTime = sf.parse(DateUtil.dateFormatStr(new Date(user.getLoginTimeStart()),DateUtil.format_time));
                    Date endTime = sf.parse(DateUtil.dateFormatStr(new Date(user.getLoginTimeEnd()),DateUtil.format_time));
                    Boolean flag = DateUtil.belongCalendar(now, beginTime, endTime);
                    if(!flag){
                        return JSONMessage.createFalied("非安全时间段内不允许登陆").toString();
                    }
                } catch (ParseException e) {
                    logger.error("时间转换异常",e);
                    return JSONMessage.createFalied("非安全时间段内不允许登陆,时间转换异常").toString();
                }
            }

            //放入session
            session.setAttribute("user",user);
            //清除错误次数
            userHandler.cleanWrongPwdTime(user.getUid());
            //记录登陆日志
            userLog.log(user,"用户登陆成功");

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  用户登陆成功");
        }
    }


    /**
     * 登出接口
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("start: 执行用户登出");
        try{
            User user = (User) session.getAttribute("user");
            if(user != null){
                //移除session中user
                session.removeAttribute("user");
                //记录登陆日志
                userLog.log(user,"用户登出成功");
            }
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行用户登出");
        }
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("/insert")
    public String insert(HttpSession session,User user) {
        logger.info("start: 执行添加用户");
        if(user == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(StringUtils.isEmpty(user.getuName())){
            return JSONMessage.createFalied("用户名不能为空").toString();
        }
        if(StringUtils.isEmpty(user.getPwd())){
            return JSONMessage.createFalied("密码不能为空").toString();
        }

        try{
            User dbUser = userHandler.query8Name(user.getuName());

            if(dbUser != null){
                return JSONMessage.createFalied("用户名已经存在").toString();
            }

            //密码加密
            user.setPwd(StringUtil.md5(user.getPwd()));

            //设置创建人
            user.setCreateUid(((User)session.getAttribute("user")).getUid());

            userHandler.insert(user);

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行添加用户");
        }
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/del")
    public String del(Long uid) {
        logger.info("start: 执行删除用户");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            userHandler.del(uid);

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行删除用户");
        }
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/query")
    public String query(Long uid) {
        logger.info("start: 执行根据uid查询用户");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            User user = userHandler.query(uid);

            return JSONMessage.createSuccess().addData(user).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行根据uid查询用户");
        }
    }



    /**
     * 重置用户密码
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(Long uid) {
        logger.info("start: 执行重置用户密码");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            String defaultPwd = StringUtil.md5("123456");
            userHandler.resetPwd(uid,defaultPwd);

            return JSONMessage.createSuccess("密码重置为：123456").toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行重置用户密码");
        }
    }


    /**
     * 解锁用户
     * @return
     */
    @RequestMapping("/unlock")
    public String unlock(Long uid) {
        logger.info("start: 执行解锁用户");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            userHandler.cleanWrongPwdTime(uid);

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行解锁用户");
        }
    }


    /**
     * 修改用户
     * @return
     */
    @RequestMapping("/update")
    public String update(User user) {
        logger.info("start: 执行修改用户信息");
        if(user.getUid() == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(user.getAuth() == null && StringUtils.isEmpty(user.getuName()) ){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            userHandler.update(user);

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行修改用户信息");
        }
    }


    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/queryAll")
    public String queryAll() {
        logger.info("start: 获取所有用户信息");
        try{
            List<User> users = userHandler.queryAll();

            return JSONMessage.createSuccess().addData("users",users).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 获取所有用户信息");
        }
    }
}