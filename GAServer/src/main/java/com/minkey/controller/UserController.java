package com.minkey.controller;

import com.minkey.db.UserHandler;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.log.UserLog;
import com.minkey.util.StringUtil;
import com.minkey.util.VCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public String login(HttpSession session,String uName,String pwd,String vcode) {
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

            //放入session
            session.setAttribute("user",user);
            //清除错误次数
            userHandler.cleanWrongPwdTime(user.getUid());
            //记录登陆日志
            userLog.log(user.getUid(),String.format("用户%s登陆成功",user.getuName()));

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
            //移除session中user
            session.removeAttribute("user");
            //记录登陆日志
            userLog.log(user.getUid(),String.format("用户%s登出成功",user.getuName()));
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
    public String update(Long uid,String uName,String pwd,Integer auth) {
        logger.info("start: 执行修改用户信息");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(auth == null && StringUtils.isEmpty(uName) && StringUtils.isEmpty(pwd)){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            userHandler.update(uid,uName,StringUtil.md5(pwd),auth);

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