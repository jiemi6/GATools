package com.minkey.controller;

import com.minkey.contants.ErrorCodeEnum;
import com.minkey.db.UserHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.util.DateUtil;
import com.minkey.util.IPUtil;
import com.minkey.util.StringUtil;
import com.minkey.util.VCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final String moduleName = "用户模块";

    @Autowired
    UserHandler userHandler;

    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    HttpSession session;

    private final String vCodeKey = "vcode";

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/getVCode")
    public String getVCode(HttpServletResponse response,HttpServletRequest request) {
        char[] vcode = VCodeUtil.getCode(VCodeUtil.VCODE_NUM);

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);

        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            request.getSession();
            sos = response.getOutputStream();
            ImageIO.write(VCodeUtil.generateCodeAndPic(vcode), "jpeg", sos);
            sos.close();

            request.getSession().setAttribute(vCodeKey,String.valueOf(vcode));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }
        return null;
    }

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/checkVCode")
    public String checkVCode(String vcode) {
        log.info("start: 执行验证码检查");
        try{
            if(StringUtils.equals(vcode,(String)session.getAttribute(vCodeKey))){
                return JSONMessage.createSuccess().toString();
            }else{
                return JSONMessage.createFalied("验证码错误").toString();
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行验证码检查");
        }
    }

    /**
     * 登录接口
     * @return
     */
    @RequestMapping("/login")
    public String login( HttpServletRequest request, String uName, String pwd, String vcode) {
        log.info("start: 执行用户登陆");
        try{
            if(StringUtils.equals(vcode,(String)session.getAttribute(vCodeKey))){
                return JSONMessage.createFalied("验证码错误").toString();
            }

            User user = userHandler.query8Name(uName);

            if(user == null){
                return JSONMessage.createFalied("用户名密码错误").toString();
            }

            if(user.getWrongPwdNum() >= User.MAX_WRONGPWDNUM){
                return JSONMessage.createFalied("用户名密码错误次数达到上限，用户锁定，请联系管理员重置密码。").toString();
            }

            if(!StringUtils.equals(StringUtil.md5(pwd),user.getPwd())){
                //admin用户
                if(user.getUid() != User.ADMIN_UID){
                    //增加锁定次数
                    userHandler.wrongPwd(user.getUid());
                }
                return JSONMessage.createFalied("用户名密码错误").toString();
            }

            //判断登陆ip
            String loginIp = IPUtil.getClientIp(request);
            Long ipStart = IPUtil.ipToLong(user.getLoginIpStart());
            Long ipEnd = IPUtil.ipToLong(user.getLoginIpEnd());
            if(ipStart == null  && ipEnd== null ){
                //不限制
            }else{
                Long ip = IPUtil.ipToLong(loginIp);
                if(ipStart > ip || ipEnd < ip){
                    return JSONMessage.createFalied("非安全ip段内不允许登陆").toString();
                }
            }
            user.setLoginIp(loginIp);

            //判断登陆时间
            if(StringUtils.isEmpty(user.getLoginTimeStart()) && StringUtils.isEmpty(user.getLoginTimeEnd())){
                //不限制
            }else {
                SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
                try {
                    Date now = sf.parse(sf.format(new Date()));
                    Date beginTime = sf.parse(user.getLoginTimeStart());
                    Date endTime = sf.parse(user.getLoginTimeEnd());
                    Boolean flag = DateUtil.belongCalendar(now, beginTime, endTime);
                    if(!flag){
                        return JSONMessage.createFalied("非安全时间段内不允许登陆").toString();
                    }
                } catch (ParseException e) {
                    log.error("时间转换异常",e);
                    return JSONMessage.createFalied("非安全时间段内不允许登陆,时间转换异常").toString();
                }
            }

            //放入session
            session.setAttribute("user",user);
            //清除错误次数
            userHandler.cleanWrongPwdTime(user.getUid());
            //记录登陆日志
            userLogHandler.log(user,moduleName,String.format("用户%s登陆成功",uName));

            return JSONMessage.createSuccess().addData("sessionId",session.getId()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            session.removeAttribute(vCodeKey);
            log.info("end:  用户登陆完成");
        }
    }

    @RequestMapping("/loginUserInfo")
    public String loginUserInfo() {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return  JSONMessage.createFalied(ErrorCodeEnum.No_Login).toString();
        }

        return JSONMessage.createSuccess().addData(user).toString();
    }


    /**
     * 登出接口
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        log.info("start: 执行用户登出");
        try{
            User user = (User) session.getAttribute("user");
            //移除session中user
            session.removeAttribute("user");
            if(user != null){
                //记录登陆日志
                userLogHandler.log(user,moduleName,"用户登出成功");
            }
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行用户登出");
        }
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("/insert")
    public String insert(User user) {
        log.info("start: 执行添加用户");
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

            User sessionUser = (User)session.getAttribute("user");

            //设置创建人
            user.setCreateUid(sessionUser.getUid());

            userHandler.insert(user);

            //记录用户日志
            userLogHandler.log(sessionUser,moduleName,String.format("%s 新增用户%s成功",sessionUser.getuName(),user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行添加用户");
        }
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/del")
    public String del(Long uid) {
        log.info("start: 执行删除用户");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        //保留id内用户不允许删除
        if(uid < 100){
            return JSONMessage.createFalied("保留用户不允许删除").toString();
        }

        try{
            userHandler.del(uid);

            User sessionUser = (User)session.getAttribute("user");

            //记录用户日志
            userLogHandler.log(sessionUser,moduleName,String.format("%s 删除用户uid = %s成功",sessionUser.getuName(),uid));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行删除用户");
        }
    }

    /**
     * 查询用户
     * @return
     */
    @RequestMapping("/query")
    public String query(Long uid) {
        log.info("start: 执行根据uid查询用户");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            User user = userHandler.query(uid);

            return JSONMessage.createSuccess().addData(user).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行根据uid查询用户");
        }
    }



    /**
     * 重置用户密码
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(Long uid) {
        log.info("start: 执行重置用户密码");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            String defaultPwd = StringUtil.md5("123456");
            userHandler.resetPwd(uid,defaultPwd);

            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,moduleName,String.format("%s 重置用户密码 uid = %s 成功",sessionUser.getuName(),uid));

            return JSONMessage.createSuccess("密码重置为：123456").toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行重置用户密码");
        }
    }


    /**
     * 用户修改密码
     * @return
     */
    @RequestMapping("/updatePwd")
    public String updatePwd(String oldPwd,String newPwd) {
        log.info("start: 执行修改当前登陆用户自己的密码 ");
        if(StringUtils.isEmpty(newPwd)){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            User sessionUser = (User)session.getAttribute("user");

            if(StringUtils.equals(sessionUser.getPwd(),StringUtil.md5(oldPwd))){
                return JSONMessage.createFalied("原密码错误").toString();
            }

            String defaultPwd = StringUtil.md5(newPwd);
            userHandler.resetPwd(sessionUser.getUid(),defaultPwd);

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行修改当前登陆用户自己的密码");
        }
    }



    /**
     * 解锁用户
     * @return
     */
    @RequestMapping("/unlock")
    public String unlock(Long uid) {
        log.info("start: 执行解锁用户");
        if(uid == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            userHandler.cleanWrongPwdTime(uid);

            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,moduleName,String.format("%s解锁用户 uid = %s 成功",sessionUser.getuName(),uid));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行解锁用户");
        }
    }


    /**
     * 修改用户
     * @return
     */
    @RequestMapping("/update")
    public String update(User user) {
        log.info("start: 执行修改用户信息");
        if(user.getUid() == null){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(user.getAuth() == null && StringUtils.isEmpty(user.getuName()) ){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            userHandler.update(user);

            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,moduleName,String.format("%s 修改用户%s信息成功",sessionUser.getuName(),user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行修改用户信息");
        }
    }


    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/queryAll")
    public String queryAll() {
        log.info("start: 获取所有用户信息");
        try{
            List<User> users = userHandler.queryAll();

            return JSONMessage.createSuccess().addData("users",users).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 获取所有用户信息");
        }
    }
}