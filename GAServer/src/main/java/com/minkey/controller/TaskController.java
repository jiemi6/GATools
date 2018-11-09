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
        log.info("start: 执行query设备 taskId={} ",taskId);
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
            log.info("end: 执行query设备 taskId={} ",taskId);
        }
    }

    @RequestMapping("/queryAll")
    public String queryAll() {
        log.info("start: 执行query所有设备 ");
        try{
            List<Task> taskList=taskHandler.queryAll();
            return JSONMessage.createSuccess().addData("list",taskList).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行query所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        log.info("start: 执行count所有设备 ");
        try{
            return JSONMessage.createSuccess().addData(taskHandler.queryCount()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行count所有设备 ");
        }
    }

    /**
     * 任务监控查询
     * @return
     */
    @RequestMapping("/monitor")
    public String monitor(Long linkId, Integer currentPage, Integer pageSize  ) {
        log.info("start: 任务监控查询 ");
        try{
            Page page = new Page(currentPage,pageSize);

            //Minkey 任务监控还没做
            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 任务监控查询 ");
        }
    }



}