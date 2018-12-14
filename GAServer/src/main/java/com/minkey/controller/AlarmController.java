package com.minkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.contants.AlarmEnum;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.DeviceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.Task;
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
 * 报警接口
 */
@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    AlarmLogHandler alarmLogHandler;
    @Autowired
    LinkHandler linkHandler;
    @Autowired
    DeviceHandler deviceHandler;
    @Autowired
    TaskHandler taskHandler;
    @Autowired
    DeviceCache deviceCache;

    /**
     * 任务告警
     * @return
     */
    @RequestMapping("/task")
    public String task(Integer currentPage, Integer pageSize, SeachParam seachParam, Long bid) {
        log.info("start: 执行分页查询任务告警 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<AlarmLog> page = new Page(currentPage,pageSize);

            Page<AlarmLog> logs = alarmLogHandler.query8page(AlarmLog.BTYPE_TASK,page,seachParam,bid);


            Object nameJson = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  taskIds = logs.getList().stream().map(alarmLog -> alarmLog.getBid()).collect(Collectors.toSet());
                Map<Long,String> nameMap = taskHandler.query8TaskIds(taskIds).stream().collect(Collectors.toMap(Task::getTaskId, Task::getTaskName ));

                nameJson = JSONObject.toJSON(nameMap);
            }

            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameJson).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行分页查询任务告警 ");
        }
    }

    /**
     * 设备告警
     * @return
     */
    @RequestMapping("/device")
    public String device(Integer currentPage,Integer pageSize, SeachParam seachParam, Long bid) {
        log.info("start: 分页查询设备告警 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<AlarmLog> page = new Page(currentPage,pageSize);

            Page<AlarmLog> logs = alarmLogHandler.query8page(AlarmLog.BTYPE_DEVICE,page, seachParam, bid);

            Map<Long,String> nameMap = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  deviceIds = logs.getList().stream().map(alarmLog -> alarmLog.getBid()).collect(Collectors.toSet());
                nameMap = deviceCache.getName8DeviceIds(deviceIds);
            }

            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameMap).toString();

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  分页查询设备告警");
        }
    }

    /**
     * 链路告警
     * @return
     */
    @RequestMapping("/link")
    public String link(Integer currentPage,Integer pageSize, SeachParam seachParam, Long bid) {
        log.info("start: 分页查询链路告警 currentPage={} ,pageSize={}" , currentPage,pageSize);
        try{
            Page<AlarmLog> page = new Page(currentPage,pageSize);

            Page<AlarmLog> logs = alarmLogHandler.query8page(AlarmLog.BTYPE_LINK,page, seachParam, bid);

            Map<Long,String> nameMap = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  linkIds = logs.getList().stream().map(alarmLog -> alarmLog.getBid()).collect(Collectors.toSet());
                nameMap = deviceCache.getName8LinkIds(linkIds);

            }
            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameMap).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 分页查询链路告警");
        }
    }


    /**
     * 链路告警
     * @return
     */
    @RequestMapping("/getAllAlarmType")
    public String getAllAlarmType() {
        try{
            AlarmEnum[] alarmEnums = AlarmEnum.values();

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = null;
            for (AlarmEnum alarmEnum : alarmEnums) {
                jsonObject = new JSONObject();
                jsonObject.put(String.valueOf(alarmEnum.getAlarmType()),alarmEnum.getDesc());
                jsonArray.add(jsonObject);
            }
            return JSONMessage.createSuccess().addData("list",jsonArray).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 分页查询链路告警");
        }
    }


}