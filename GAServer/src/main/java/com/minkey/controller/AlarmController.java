package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.DeviceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.Link;
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

    /**
     * 任务告警
     * @return
     */
    @RequestMapping("/task")
    public String task(Integer currentPage, Integer pageSize, SeachParam seachParam, Long taskId) {
        log.info("start: 执行分页查询任务告警 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<AlarmLog> page = new Page(currentPage,pageSize);

            Page<AlarmLog> logs = alarmLogHandler.query8page(AlarmLog.BTYPE_TASK,page,seachParam,taskId);


            Object nameJson = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  taskIds = logs.getList().stream().map(alarmLog -> alarmLog.getBid()).collect(Collectors.toSet());
                Map<Long,String> nameMap = taskHandler.query8Ids(taskIds).stream().collect(Collectors.toMap(Task::getId, Task::getTaskName ));

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
    public String device(Integer currentPage,Integer pageSize, SeachParam seachParam, Long deviceId) {
        log.info("start: 分页查询设备告警 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<AlarmLog> page = new Page(currentPage,pageSize);

            Page<AlarmLog> logs = alarmLogHandler.query8page(AlarmLog.BTYPE_DEVICE,page, seachParam, deviceId);

            Object nameJson = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  deviceIds = logs.getList().stream().map(alarmLog -> alarmLog.getBid()).collect(Collectors.toSet());
                Map<Long,String> nameMap = deviceHandler.query8Ids(deviceIds).stream().collect(Collectors.toMap(Device::getDeviceId, Device::getDeviceName ));

                nameJson = JSONObject.toJSON(nameMap);
            }

            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameJson).toString();

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
    public String link(Integer currentPage,Integer pageSize, SeachParam seachParam, Long linkId) {
        log.info("start: 分页查询链路告警 currentPage={} ,pageSize={}" , currentPage,pageSize);
        try{
            Page<AlarmLog> page = new Page(currentPage,pageSize);

            Page<AlarmLog> logs = alarmLogHandler.query8page(AlarmLog.BTYPE_LINK,page, seachParam, linkId);

            Object nameJson = null;
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<Long>  linkIds = logs.getList().stream().map(alarmLog -> alarmLog.getBid()).collect(Collectors.toSet());
                Map<Long,String> nameMap = linkHandler.queryAllIdAndName().stream().filter(a -> linkIds.contains(a.getLinkId())).collect(Collectors.toMap(Link::getLinkId, Link::getLinkName ));

                nameJson = JSONObject.toJSON(nameMap);
            }
            return JSONMessage.createSuccess().addData(logs).addData("nameMap",nameJson).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 分页查询链路告警");
        }
    }


}