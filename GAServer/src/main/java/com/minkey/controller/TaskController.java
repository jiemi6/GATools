package com.minkey.controller;

import com.minkey.db.TaskHandler;
import com.minkey.db.dao.Task;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务接口
 * <br>数据来自别的数据库
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskHandler taskHandler;
    

    @RequestMapping("/query")
    public String query(Long taskId) {
        log.debug("start: 执行query任务 taskId={} ",taskId);
        if(taskId == null){
            log.info("taskId不能为空");
            return JSONMessage.createFalied("taskId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(taskHandler.query(taskId)).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行query任务 taskId={} ",taskId);
        }
    }


    /**
     *
     * @return
     */
    @RequestMapping("/queryAll")
    public String queryAll() {
        log.debug("start: 执行query所有任务 ");

        try{
            List<Task> taskList=taskHandler.queryAll();
            return JSONMessage.createSuccess().addData("list",taskList).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行query所有任务 ");
        }
    }

    /**
     * 废弃
     * @return
     */
    @Deprecated
    @RequestMapping("/queryCount")
    public String queryCount() {
        log.debug("start: 执行count所有任务 ");
        try{
            return JSONMessage.createSuccess().addData(taskHandler.queryCount()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行count所有任务 ");
        }
    }

    /**
     * 任务监控查询
     * @return
     */
    @RequestMapping("/monitor")
    public String monitor(Long linkId, Integer currentPage, Integer pageSize  ) {
        log.debug("start: 任务监控查询 ");
        if(linkId == null){
            return JSONMessage.createFalied("linkId不能为空").toString();
        }
        try{
            Page<Task> page = new Page(currentPage,pageSize);

            //任务监控就查任务表，根据任务level告警
            page = taskHandler.query8LinkId(linkId,page);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 任务监控查询 ");
        }
    }



}