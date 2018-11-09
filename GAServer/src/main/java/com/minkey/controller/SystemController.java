package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.contants.Modules;
import com.minkey.db.CheckHandler;
import com.minkey.db.ConfigHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.Check;
import com.minkey.db.dao.CheckItem;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.executer.LocalExecuter;
import com.minkey.handler.SelfCheckJob;
import com.minkey.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 系统管理接口
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    ConfigHandler configHandler;

    @Autowired
    CheckHandler checkHandler;

    @Autowired
    SelfCheckJob selfCheckJob;

    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    HttpSession session;


    @Value("${system.debug:false}")
    private boolean isDebug;


    /**
     * 系统自检
     * @return
     */
    @RequestMapping("/check")
    public String check() {
        log.info("start: 执行系统自检");

        User user = (User) session.getAttribute("user");

        long checkId;
        try{
            Check check = new Check();
            check.setCheckName(user.getuName()+"发起本系统环境自检");
            check.setCheckType(Check.CHECKTYPE_SYSTEMSELF);
            check.setUid(user.getUid());
            //存入数据库，获取id
            checkId = checkHandler.insert(check);
        }catch (Exception e){
            //如果异常 认为db异常
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied("数据库错误，无法执行命令，"+e.getMessage()).toString();
        }

        try{
            //开始检查
            selfCheckJob.check(checkId);
            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行系统自检");
        }
    }

    /**
     * 系统自检信息获取，页面不断扫描此接口获取数据
     * @return
     */
    @RequestMapping("/checkInfo")
    public String checkInfo(Long checkId,Integer index) {
        log.info("start: 获取当前系统自检信息 checkId={}，index={}",checkId,index);
        if(checkId == null || checkId <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(index == null || index <= 0){
            index = 0;
        }
        try{
            List<CheckItem> checkItems = selfCheckJob.getResultList(checkId,index);
            return JSONMessage.createSuccess().addData("checkItems",checkItems).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行系统自检");
        }
    }

    /**
     * 关机/重启
     * @param reboot 是否为重启
     * @return
     */
    @RequestMapping("/shutDown")
    public String shutDown(Boolean reboot) {
        log.info("start: 执行shutDown");

        if(isDebug){
            return JSONMessage.createSuccess("暂时不真正执行，真的会关机的").toString();
        }
        try{
            User sessionUser = (User) session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser, Modules.link,String.format("%s %s系统 ",sessionUser.getuName(),reboot?"重启":"关机"));

            if(reboot != null && reboot){
                //重启
                //Minkey 关机命令暂时使用假的
                LocalExecuter.exec("shutdown -k now");
//                LocalExecuter.exec("shutdown -r now");
            }else{
                //关机
                LocalExecuter.exec("shutdown -k now");
//                LocalExecuter.exec("shutdown -h now");
            }

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行关机命令");
        }

    }


    /**
     * 备份设置，导出文件
     * @return
     */
    @RequestMapping("/bakupConfig")
    public String bakupConfig(HttpServletResponse response) {
        log.info("start: 备份设置");

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=config.data");

        try {
            byte[] data =StringUtil.string2Byte("ceshi一个数据");

            //Minkey 备份设置 未完成
            response.getOutputStream().write(data);

            User sessionUser = (User) session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser, Modules.link,String.format("%s 备份系统设置 ",sessionUser.getuName()));

            return null;
        } catch (IOException e) {
            return null;
        }finally {
            log.info("end: 备份设置");
        }
    }


    /**
     * 导入设置，导入配置文件，还原设置
     * @return
     */
    @RequestMapping("/reloadConfig")
    public String reloadConfig(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("start: 导入配置文件");
        if(file== null || file.isEmpty()){
            return JSONMessage.createFalied("上传文件为空").toString();
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();

        //文件大于3M
        if(size <=0 || size > 1024*1024*3){
            return JSONMessage.createFalied("上传文件太大").toString();
        }

        byte[] chars =  file.getBytes();
        String data = StringUtil.byte2String(chars);

        //Minkey 导入配置文件未完成
        log.info("导入的数据"+data);
        log.info("end: 导入配置文件");

        User sessionUser = (User) session.getAttribute("user");
        //记录用户日志
        userLogHandler.log(sessionUser, Modules.link,String.format("%s 导入配置文件，恢复系统设置 ",sessionUser.getuName()));

        return JSONMessage.createSuccess("导入成功").toString();
    }



    /**
     * 重置系统，恢复出厂设置
     * @return
     */
    @RequestMapping("/reset")
    public String reset() {

        //Minkey 重置系统未完成

        User sessionUser = (User) session.getAttribute("user");
        //记录用户日志
        userLogHandler.log(sessionUser, Modules.link,String.format("%s 恢复出厂设置 ",sessionUser.getuName()));

        return JSONMessage.createSuccess("重置成功").toString();
    }




    /**
     * get网卡信息，ip，网关，子掩码等
     * @return
     */
    @RequestMapping("/netWorkGet")
    public String netWorkGet() {

        //读取配置
        String configKey = ConfigEnum.NewWork.getConfigKey();

        Map<String, Object> data = configHandler.query(configKey);
        //检查规则

        //调用系统命令设置

        return JSONMessage.createSuccess().addData(data).toString();
    }

    /**
     * 本机网络设置
     * @return
     */
    @RequestMapping("/netWorkSet")
    public String netWorkSet(String localIp,String subnetMask,String gateway,String dns,String dnsBak) {
        log.info("start: 执行系统网络配置信息 {},{},{},{}",localIp,subnetMask,gateway,dns,dnsBak);
        if(StringUtils.isEmpty(localIp)
                || StringUtils.isEmpty(subnetMask)
                || StringUtils.isEmpty(gateway)
                || StringUtils.isEmpty(dns)){
            return JSONMessage.createFalied("参数缺失").toString();
        }
        if(!StringUtil.isIp(localIp)
                || !StringUtil.isIp(subnetMask)
                || !StringUtil.isIp(gateway)
                || !StringUtil.isIp(dns)){
            return JSONMessage.createFalied("参数格式错误").toString();
        }

        try{

            if(!isDebug){
                //调用命令设置,本会话内有效
                //ifconfig eth0 192.168.1.155 netmask 255.255.255.0
                LocalExecuter.exec("ifconfig eth0 "+localIp+" netmask "+subnetMask);
                //route insert default gw 192.168.1.1
                LocalExecuter.exec("route insert default gw "+gateway);
            }

            //Minkey 设置网关

            JSONObject config = new JSONObject();
            config.put("localIp",localIp);
            config.put("subnetMask",subnetMask);
            config.put("gateway",gateway);
            config.put("dns",dns);
            config.put("dnsBak",dnsBak);

            String configKey = ConfigEnum.NewWork.getConfigKey();
            configHandler.insert(configKey,config.toJSONString());

            User sessionUser = (User) session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser, Modules.link,String.format("%s 设置本机网络 ",sessionUser.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行系统注册信息 ");
        }
    }

    @RequestMapping("/test")
    public String ttt(){

        int i = 1/0;

        return JSONMessage.createSuccess().toString();
    }

}