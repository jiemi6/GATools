package com.minkey.controller;

import com.minkey.db.CheckHandler;
import com.minkey.db.ConfigHandler;
import com.minkey.db.dao.Check;
import com.minkey.db.dao.CheckItem;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.executer.LocalExecuter;
import com.minkey.scheduled.SelfCheckJob;
import com.minkey.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 系统管理接口
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    private final static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    ConfigHandler configHandler;

    @Autowired
    CheckHandler checkHandler;

    @Autowired
    SelfCheckJob selfCheckJob;

    @Autowired
    HttpSession session;
    /**
     * 系统自检
     * @return
     */
    @RequestMapping("/check")
    public String check() {
        logger.info("start: 执行系统自检");

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
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied("数据库错误，无法执行命令，"+e.getMessage()).toString();
        }

        try{
            //开始检查
            selfCheckJob.check(checkId);
            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

    /**
     * 系统自检信息获取，页面不断扫描此接口获取数据
     * @return
     */
    @RequestMapping("/checkInfo")
    public String checkInfo(Long checkId,Integer index) {
        logger.info("start: 获取当前系统自检信息");
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
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

    /**
     * 关机/重启
     * @param reboot 是否为重启
     * @return
     */
    @RequestMapping("/shutDown")
    public String shutDown(Boolean reboot) {
        logger.info("start: 执行shutDown");

        try{
            if(reboot != null && reboot){
                //重启
                LocalExecuter.exec("shutdown -r now");
            }else{
                //关机
                LocalExecuter.exec("shutdown -h now");
            }

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行关机命令");
        }

    }


    /**
     * 备份设置，导出文件
     * @return
     */
    @RequestMapping("/bakupConfig")
    public String bakupConfig() {



        return JSONMessage.createSuccess().toString();
    }


    /**
     * 导入设置，导入配置文件，还原设置
     * @return
     */
    @RequestMapping("/reloadConfig")
    public String reloadConfig(@RequestParam("fileName") MultipartFile file) {
        if(file.isEmpty()){
            return JSONMessage.createFalied("上传文件为空").toString();
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();

        //文件大于3M
        if(size <=0 || size > 1024*1024*3){
            return JSONMessage.createFalied("上传文件太大").toString();
        }

        try {
            byte[] chars = new byte[(int) size];
            file.getInputStream().read(chars);
            String txt = StringUtil.byte2String(chars);


            logger.info(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONMessage.createSuccess().toString();

    }



    /**
     * 重置系统，恢复出厂设置
     * @return
     */
    @RequestMapping("/reset")
    public String reset() {
        return JSONMessage.createSuccess().toString();

    }




    /**
     * get网卡信息，ip，网关，子掩码等
     * @return
     */
    @RequestMapping("/netWorkGet")
    public String netWorkGet() {

        //检查规则

        //调用系统命令设置

        return JSONMessage.createSuccess().toString();

    }

    /**
     * 本机网络设置
     * @return
     */
    @RequestMapping("/netWorkSet")
    public String netWorkSet(String localIp,String subnetMask,String gateway,String dns,String dnsBak) {
        logger.info("start: 执行系统注册信息 {},{},{},{}",localIp,subnetMask,gateway,dns,dnsBak);
        if(StringUtils.isEmpty(localIp)
                ||StringUtils.isEmpty(subnetMask)
                ||StringUtils.isEmpty(gateway)
                ||StringUtils.isEmpty(dns)){
            return JSONMessage.createFalied("参数缺失").toString();
        }
        if(StringUtil.isIp(localIp)
                ||StringUtil.isIp(subnetMask)
                ||StringUtil.isIp(gateway)
                ||StringUtil.isIp(dns)){
            return JSONMessage.createFalied("参数格式错误").toString();
        }


        try{
            //调用命令设置,本会话内有效
            //ifconfig eth0 192.168.1.155 netmask 255.255.255.0
            LocalExecuter.exec("ifconfig eth0 "+localIp+" netmask "+subnetMask);
            //route add default gw 192.168.1.1
            LocalExecuter.exec("route add default gw "+gateway);

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行系统注册信息 ");
        }
    }

}