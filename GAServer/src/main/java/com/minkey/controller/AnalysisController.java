package com.minkey.controller;

import com.minkey.db.DeviceLogHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskDayLogHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 统计分析
 */
@Slf4j
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    TaskDayLogHandler taskDayLogHandler;

    @Autowired
    DeviceLogHandler deviceLogHandler;

    @Autowired
    TaskHandler taskHandler;

    @Autowired
    LinkHandler linkHandler;

    /**
     * 任务统计分析 分页数据
     * @return
     */
    @RequestMapping("/task")
    public String task(Long linkId,Integer currentPage,Integer pageSize, SeachParam seachParam) {
        log.info("start: 执行分页查询任务统计分析数据列表 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(linkId == null || linkId <=0
                || currentPage == null || currentPage <=0
                || pageSize == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Page<TaskDayLog> page = new Page(currentPage,pageSize);


            Page<TaskDayLog> logs = taskDayLogHandler.query8page(linkId,page,seachParam);
            Map<String, String> nameMap = null;
            if(!CollectionUtils.isEmpty(page.getList())) {
                Set<String> targetTaskIds = page.getList().stream().map(taskDayLog -> taskDayLog.getTargetTaskId()).collect(Collectors.toSet());
                nameMap = taskHandler.query8LinkAndTargetIds(linkId,targetTaskIds).stream().collect(Collectors.toMap(Task::getTargetTaskId, Task :: getTaskName));
            }

            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameMap).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行分页查询任务统计分析数据列表 ");
        }
    }

    /**
     * 任务统计分析 总计
     * @return
     */
    @RequestMapping("/taskCount")
    public String taskCount(Long linkId, SeachParam seachParam) {
        log.info("start: 执行查询任务统计分析数据总计");
        if(linkId == null || linkId <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{

            TaskDayLog allSum = taskDayLogHandler.querySum(linkId,seachParam);
            return JSONMessage.createSuccess().addData("sum",allSum).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行分页查询任务统计分析数据列表 ");
        }
    }

    /**
     * 设备运行统计
     * @param linkId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/device")
    public String device(Long linkId, Integer currentPage, Integer pageSize , SeachParam seachParam) {
        log.info("start: 执行分页查询设备运行统计分析数据列表 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(linkId == null || linkId <=0
                || currentPage == null || currentPage <=0
                || pageSize == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Page<DeviceLog> page = new Page(currentPage,pageSize);

            Link link = linkHandler.query(linkId);
            if(link == null || CollectionUtils.isEmpty(link.getDeviceIds())){
                page.setTotal(0);
                return JSONMessage.createSuccess().addData(page).toString();
            }
            //Minkey 设备运行统计实现
            Page<DeviceLog> logs = deviceLogHandler.query8Page(page, seachParam,link.getDeviceIds());

            return JSONMessage.createSuccess().addData(logs).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行分页查询设备运行统计分析数据列表 ");
        }
    }

    /**
     * 设备运行统计分析 总计
     * @return
     */
    @RequestMapping("/deviceCount")
    public String deviceCount(Long linkId, SeachParam seachParam) {
        log.info("start: 执行查询设备运行统计分析数据总计");
        if(linkId == null || linkId <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Link link = linkHandler.query(linkId);
            if(link == null || CollectionUtils.isEmpty(link.getDeviceIds())){
                return JSONMessage.createSuccess().toString();
            }
            //Minkey 设备运行统计
            deviceLogHandler.querySum(linkId,seachParam);

            return JSONMessage.createSuccess().addData("sum",null).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行分页查询设备运行统计分析数据列表 ");
        }
    }
}