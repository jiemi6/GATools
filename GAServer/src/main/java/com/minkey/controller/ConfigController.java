package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.contants.Modules;
import com.minkey.db.ConfigHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.syslog.SysLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 配置接口
 */
@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    ConfigHandler configHandler;

    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/insert")
    public String insert(String configKey,String configData) {
        log.info("start: 执行insert配置 configKey={} ,configData={}",configKey,configData);
        if(StringUtils.isEmpty(configKey)){
            log.info("configKey不能为空");
            return JSONMessage.createFalied("configKey不能为空").toString();
        }
        if(StringUtils.isEmpty(configData)){
            log.info("configData不能为空");
            return JSONMessage.createFalied("configData不能为空").toString();
        }
        try{
            configHandler.insert(configKey,configData);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行insert配置 configKey={} ,configData={}",configKey,configData);
        }
    }

    @RequestMapping("/query")
    public String query(String configKey) {
        log.info("start: 执行query配置 configKey={} ",configKey);
        if(StringUtils.isEmpty(configKey)){
            log.info("configKey不能为空");
            return JSONMessage.createFalied("configKey不能为空").toString();
        }
        try{
            Map<String, Object>  data = configHandler.query(configKey);

            if(MapUtils.isNotEmpty(data)){
                //去掉key，不返回
                data.remove("configKey");
            }
            return JSONMessage.createSuccess().addData(data).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行query配置 configKey={} ",configKey);
        }
    }


    /**
     * 获取系统注册信息
     * @return
     */
    @RequestMapping("/systemInfo/get")
    public String systemInfoGet() {
        String configKey = ConfigEnum.SystemRegister.getConfigKey();
        return query(configKey);
    }


    /**
     * 系统注册，即录入一些资料
     * @return
     */
    @RequestMapping("/systemInfo/set")
    public String systemInfoSet(String systemName,String managerName,String managerPhone,String managerEmail) {
        log.info("start: 执行系统注册信息 {},{},{},{}",systemName,managerName,managerPhone,managerEmail);
        if(StringUtils.isEmpty(systemName)
                ||StringUtils.isEmpty(managerName)
                ||StringUtils.isEmpty(managerPhone)
                ||StringUtils.isEmpty(managerEmail)){

            return JSONMessage.createFalied("参数缺失").toString();
        }
        try{
            //直接存入config
            String configKey = ConfigEnum.SystemRegister.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("systemName",systemName);
            configData.put("managerName",managerName);
            configData.put("managerPhone",managerPhone);
            configData.put("managerEmail",managerEmail);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s设置系统注册资料",user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行系统注册信息 ");
        }
    }


    /**
     * 获取日志最大保留天数
     * @return
     */
    @RequestMapping("/logOverDay/get")
    public String logOverDayGet() {
        String configKey = ConfigEnum.LogOverDay.getConfigKey();
        return query(configKey);
    }



    /**
     * 日志最大保留天数，超过一定天数的日志将会被删除
     * @return
     */
    @RequestMapping("/logOverDay/set")
    public String logOverDaySet(Integer logOverDay) {
        log.info("start: 执行日志保留天数设置 {}",logOverDay);
        if(logOverDay == null || logOverDay <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            //直接存入config
            String configKey = ConfigEnum.LogOverDay.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("LogOverDay",logOverDay);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s设置日志回滚日期",user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行日志保留天数设置 ");
        }
    }

    /**
     * 获取自动体检时间设置
     * @return
     */
    @RequestMapping("/autoCheckTimes/get")
    public String autoCheckTimesGet() {
        String configKey = ConfigEnum.AutoCheckTimes.getConfigKey();
        return query(configKey);
    }



    /**
     * 设置自动体检时间
     * @return
     */
    @RequestMapping("/autoCheckTimes/set")
    public String autoCheckTimesSet(String[] checkTimes) {
        log.info("start: 设置自动体检时间 {}",String.valueOf(checkTimes));
        if(ArrayUtils.isEmpty(checkTimes)){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            //直接存入config
            String configKey = ConfigEnum.AutoCheckTimes.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("checkTimes",checkTimes);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s设置自动体检时间",user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 设置自动体检时间 ");
        }
    }

    /**
     * 报警短信配置
     * @return
     */
    @RequestMapping("/smsAlarm/set")
    public String smsAlarmSet(String smsUrl) {
        log.info("start: 执行报警短信配置");
        try{
            //直接存入config
            String configKey = ConfigEnum.SmsAlarmConfig.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("smsUrl",smsUrl);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s设置短信告警信息",user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行报警短信配置");
        }
    }


    /**
     * 获取报警短信设置
     * @return
     */
    @RequestMapping("/smsAlarm/get")
    public String smsAlarmGet() {
        String configKey = ConfigEnum.SmsAlarmConfig.getConfigKey();
        return query(configKey);
    }



    /**
     * 报警邮箱配置
     * @return
     */
    @RequestMapping("/emailAlarm/set")
    public String emailAlarmSet(String emailUser,String emailPwd,String emailServer) {
        log.info("start: 执行报警邮箱配置");
        try{
            //直接存入config
            String configKey = ConfigEnum.EmailAlarmConfig.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("emailUser",emailUser);
            configData.put("emailPwd",emailPwd);
            configData.put("emailServer",emailServer);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s设置邮箱告警信息",user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  报警邮箱配置");
        }
    }

    /**
     * 获取报警邮箱配置
     * @return
     */
    @RequestMapping("/emailAlarm/get")
    public String emailAlarmGet() {
        String configKey = ConfigEnum.EmailAlarmConfig.getConfigKey();
        return query(configKey);
    }


    @Autowired
    SysLogUtil sysLogUtil;
    /**
     * 设置本平台的syslog推送目的地，发送给其他服务器的日志收集服务器
     * @return
     */
    @RequestMapping("/syslog2other/set")
    public String syslog2otherSet(Boolean open ,String ip, String port) {
        if(open == null || open == false){
            log.info("start: 关闭syslog转发");
            //关闭
            sysLogUtil.closeSyslog2other();

        }else{
            log.info("start: 配置syslog转发 ip={},port=" ,ip,port);
            //保存配置
            //启动发送
            sysLogUtil.startSyslog2other(ip,port);
        }

        try{
            //直接存入config
            JSONObject configData = new JSONObject();
            configData.put("open",open);
            configData.put("ip",ip);
            configData.put("port",port);
            String configKey = ConfigEnum.Syslog2other.getConfigKey();
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s设置syslog转发信息",user.getuName()));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 配置syslog完成");
        }

    }

    /**
     * 获取置本平台的syslog推送目的地
     * @return
     */
    @RequestMapping("/syslog2other/get")
    public String syslog2otherGet() {
        String configKey = ConfigEnum.Syslog2other.getConfigKey();
        return query(configKey);
    }

    /**
     * ssh工具开关
     * @return
     */
    @RequestMapping("/sshd/set")
    public String sshdSet(Boolean open) {
        log.info("start: ssh工具 状态变更为 {}" ,open);
        //调用系统命令进行开关，
        if(open == null ){
            open = false;
        }

        if(open){
            //开启snmp服务
        }else{
            //停止snmp服务
        }

        try{
            //直接存入config
            String configKey = ConfigEnum.Sshd.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("open",open);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");

            userLogHandler.log(user, Modules.config, String.format("%s %s Ssh组件",user.getuName(),open?"开启":"关闭"));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  ssh工具 状态变更完成");
        }


    }

    /**
     * 获取ssh工具开关配置
     * @return
     */
    @RequestMapping("/sshd/get")
    public String sshdGet() {
        String configKey = ConfigEnum.Sshd.getConfigKey();
        return query(configKey);
    }

    /**
     * snmp工具开关
     * @return
     */
    @RequestMapping("/snmp/set")
    public String snmpSet(Boolean open) {
        log.info("start: snmp工具 状态变更为 {}" ,open);
        //调用系统命令进行开关，
        if(open == null ){
            open = false;
        }

        if(open){
            //开启snmp服务
        }else{
            //停止snmp服务
        }

        try{
            //直接存入config
            String configKey = ConfigEnum.Snmp.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("open",open);
            configHandler.insert(configKey,configData.toJSONString());

            User user = (User) httpSession.getAttribute("user");
            userLogHandler.log(user, Modules.config, String.format("%s %s Snmp组件",user.getuName(),open?"开启":"关闭"));

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  ssh工具 状态变更完成");
        }


    }

    /**
     * 获取ssh工具开关配置
     * @return
     */
    @RequestMapping("/snmp/get")
    public String snmpGet() {
        String configKey = ConfigEnum.Snmp.getConfigKey();
        return query(configKey);
    }

}