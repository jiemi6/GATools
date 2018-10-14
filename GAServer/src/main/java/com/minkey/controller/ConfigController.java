package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.db.ConfigHandler;
import com.minkey.dto.JSONMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置接口
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    private final static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    ConfigHandler configHandler;

    @RequestMapping("/insert")
    public String insert(String configKey,String configData) {
        logger.info("start: 执行insert配置 configKey={} ,configData={}",configKey,configData);
        if(StringUtils.isEmpty(configKey)){
            logger.info("configKey不能为空");
            return JSONMessage.createFalied("configKey不能为空").toString();
        }
        if(StringUtils.isEmpty(configData)){
            logger.info("configData不能为空");
            return JSONMessage.createFalied("configData不能为空").toString();
        }
        try{
            configHandler.insert(configKey,configData);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行insert配置 configKey={} ,configData={}",configKey,configData);
        }
    }

    @RequestMapping("/query")
    public String query(String configKey) {
        logger.info("start: 执行query配置 configKey={} ",configKey);
        if(StringUtils.isEmpty(configKey)){
            logger.info("configKey不能为空");
            return JSONMessage.createFalied("configKey不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(configHandler.query(configKey)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行query配置 configKey={} ",configKey);
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
        logger.info("start: 执行系统注册信息 {},{},{},{}",systemName,managerName,managerPhone,managerEmail);
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
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行系统注册信息 ");
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
        logger.info("start: 执行日志保留天数设置 {}",logOverDay);
        if(logOverDay == null || logOverDay <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            //直接存入config
            String configKey = ConfigEnum.LogOverDay.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("LogOverDay",logOverDay);
            configHandler.insert(configKey,configData.toJSONString());
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行系统注册信息 ");
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
        logger.info("start: 执行日志保留天数设置 {}",checkTimes);
        if(ArrayUtils.isEmpty(checkTimes)){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            //直接存入config
            String configKey = ConfigEnum.AutoCheckTimes.getConfigKey();
            JSONObject configData = new JSONObject();
            configData.put("checkTimes",checkTimes);
            configHandler.insert(configKey,configData.toJSONString());
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行系统注册信息 ");
        }
    }

    /**
     * 报警短信配置
     * @return
     */
    @RequestMapping("/smsAlarm")
    public String smsAlarm() {
        logger.info("start: 执行系统自检");
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }


    /**
     * 报警邮箱配置
     * @return
     */
    @RequestMapping("/emailAlarm")
    public String emailAlarm() {
        logger.info("start: 执行系统自检");
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

}