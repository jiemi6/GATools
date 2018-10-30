package com.minkey.controller;

import com.minkey.db.AlarmLogHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报警接口
 */
@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    AlarmLogHandler alarmLogHandler;

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

            return JSONMessage.createSuccess().addData(logs).toString();
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

            return JSONMessage.createSuccess().addData(logs).toString();
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

            return JSONMessage.createSuccess().addData(logs).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 分页查询链路告警");
        }
    }


}