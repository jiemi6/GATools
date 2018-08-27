package com.minkey.controller;

import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志查看接口
 */
@RestController
@RequestMapping("/log")
public class LogController {
    private final static Logger logger = LoggerFactory.getLogger(LogController.class);


    /**
     * 设备日志
     * @return
     */
    @RequestMapping("/device")
    public String device() {
        logger.info("start: 执行系统自检");
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

    /**
     * 链路日志
     * @return
     */
    @RequestMapping("/link")
    public String link() {
        logger.info("start: 执行系统自检");
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }


    /**
     * 用户日志
     * @return
     */
    @RequestMapping("/user")
    public String user() {
        logger.info("start: 执行系统自检");
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

    /**
     * 体检日志
     * @return
     */
    @RequestMapping("/detection")
    public String detection() {
        logger.info("start: 执行系统自检");
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }



}