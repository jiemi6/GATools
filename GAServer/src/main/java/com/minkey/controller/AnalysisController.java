package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.contants.AlarmEnum;
import com.minkey.db.*;
import com.minkey.db.dao.DeviceLog;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.Task;
import com.minkey.db.dao.TaskDayLog;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import com.minkey.handler.AlarmHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
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

    @Autowired
    AlarmHandler alarmHandler;

    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    DeviceCache deviceCache;


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

            //查询每个设备报警次数
            Page<Map<String, Object>> logs = alarmLogHandler.queryDevice8Page(page, seachParam,link.getDeviceIds());

            Map<Long,String> nameMap = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  deviceIds =new HashSet<>(logs.getList().size());
                for(Map map : logs.getList()){
                    deviceIds.add(Long.valueOf((String)map.get("bid")));
                }
                nameMap = deviceCache.getName8DeviceIds(deviceIds);
            }

            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameMap).toString();
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
            if(link == null){
                return JSONMessage.createFalied("链路不存在").toString();
            }
            if(CollectionUtils.isEmpty(link.getDeviceIds())){
                return JSONMessage.createFalied("链路没有设备").toString();
            }
            JSONObject totalData = new JSONObject();
            //设备总数
            totalData.put("deviceNum",link.getDeviceIds().size());
            //报警的设备数量
            totalData.put("alarmDeviceNum",alarmLogHandler.queryDeviceCount(link.getDeviceIds(),seachParam));
            //所有设备总共报警的次数
            totalData.put("alarmNum",alarmLogHandler.queryTotalCount(link.getDeviceIds(),seachParam));
            //设备连通性报警次数
            totalData.put("alarmNum_connect",alarmLogHandler.queryTotalCount(link.getDeviceIds(),seachParam,AlarmEnum.wangluobutong.getAlarmType()));
            //设备服务报警次数
            totalData.put("alarmNum_service",alarmLogHandler.queryTotalCount(link.getDeviceIds(),seachParam,AlarmEnum.shebeifuwu.getAlarmType()));
            //设备性能报警次数
            totalData.put("alarmNum_explore",alarmLogHandler.queryTotalCount(link.getDeviceIds(),seachParam,AlarmEnum.shebeixingneng.getAlarmType()));


            return JSONMessage.createSuccess().addData(totalData).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行分页查询设备运行统计分析数据列表 ");
        }
    }
}