package com.minkey.controller;

import com.minkey.db.ConfigHandler;
import com.minkey.dto.JSONMessage;
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
            return JSONMessage.createFalied(e.toString()).toString();
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
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query配置 configKey={} ",configKey);
        }
    }


}