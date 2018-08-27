package com.minkey.controller;

import com.minkey.db.TaskHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务接口
 * <br>数据来自别的数据库
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskHandler taskHandler;
    

    @RequestMapping("/query")
    public String query(Long taskId) {
        logger.info("start: 执行query设备 taskId={} ",taskId);
        if(taskId == null){
            logger.info("taskId不能为空");
            return JSONMessage.createFalied("taskId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(taskHandler.query(taskId)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query设备 taskId={} ",taskId);
        }
    }

    @RequestMapping("/queryAll")
    public String queryAll() {
        logger.info("start: 执行query所有设备 ");

        try{
            return JSONMessage.createSuccess().addData(taskHandler.queryAll()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        logger.info("start: 执行count所有设备 ");

        try{
            return JSONMessage.createSuccess().addData(taskHandler.queryCount()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行count所有设备 ");
        }
    }
}