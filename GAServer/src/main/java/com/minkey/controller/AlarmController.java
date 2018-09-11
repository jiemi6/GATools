package com.minkey.controller;

import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报警接口
 */
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    private final static Logger logger = LoggerFactory.getLogger(AlarmController.class);


    /**
     * 设备报警
     * @return
     */
    @RequestMapping("/device")
    public String device() {
        logger.info("start: 执行系统自检");

        //直接读syslog

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
     * 链路报警
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
     * 任务报警
     * @return
     */
    @RequestMapping("/task")
    public String task() {
        logger.info("start: 执行系统自检");

        //直接读第三方数据库


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