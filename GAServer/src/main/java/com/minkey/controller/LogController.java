package com.minkey.controller;

import com.minkey.db.CheckHandler;
import com.minkey.db.SyslogHandler;
import com.minkey.db.TaskLogHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.Check;
import com.minkey.db.dao.Syslog;
import com.minkey.db.dao.TaskLog;
import com.minkey.db.dao.UserLog;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志查看接口
 */
@RestController
@RequestMapping("/log")
public class LogController {
    private final static Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    TaskLogHandler taskLogHandler;

    @Autowired
    SyslogHandler syslogHandler;

    @Autowired
    CheckHandler checkHandler;

    /**
     * 任务日志
     * @return
     */
    @RequestMapping("/task")
    public String task(Integer currentPage,Integer pageSize) {
        logger.info("start: 执行分页查询任务日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Page<TaskLog> page = new Page(currentPage,pageSize);

            Page<TaskLog> logs = taskLogHandler.query8page(page);
            return JSONMessage.createSuccess().addData(logs).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行分页查询任务日志 ");
        }
    }

    /**
     * 设备日志
     * @return
     */
    @RequestMapping("/device")
    public String device(Integer currentPage,Integer pageSize) {
        logger.info("start: 分页查询设备日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<Syslog> page = new Page(currentPage,pageSize);

            Page<Syslog> logs = syslogHandler.query8page(page);
            return JSONMessage.createSuccess().addData(logs).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  分页查询设备日志");
        }
    }

    /**
     * 链路日志
     * @return
     */
    @RequestMapping("/link")
    public String link(Integer currentPage,Integer pageSize) {
        logger.info("start: 分页查询链路日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 分页查询链路日志");
        }
    }


    /**
     * 用户日志
     * @return
     */
    @RequestMapping("/user")
    public String user(Integer currentPage,Integer pageSize) {
        logger.info("start: 分页查询用户日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        try{
            Page<UserLog> page = new Page(currentPage,pageSize);

            page = userLogHandler.query8Page(page);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  分页查询用户日志");
        }
    }

    /**
     * 体检发起日志
     * @return
     */
    @RequestMapping("/detection")
    public String detection(Integer currentPage,Integer pageSize) {
        logger.info("start: 分页查询体检发起日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        try{
            Page<Check> page = new Page(currentPage,pageSize);

            page = checkHandler.query8Page(page);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  分页查询体检发起日志");
        }
    }



}