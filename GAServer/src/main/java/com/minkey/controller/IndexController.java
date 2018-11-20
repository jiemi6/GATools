package com.minkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.db.DeviceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskDayLogHandler;
import com.minkey.db.analysis.AlarmDayLog;
import com.minkey.db.analysis.AlarmDayLogHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.Task;
import com.minkey.db.dao.TaskDayLog;
import com.minkey.dto.JSONMessage;
import com.minkey.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 报警接口
 */
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    AlarmDayLogHandler alarmDayLogHandler;

    @Autowired
    TaskDayLogHandler taskDayLogHandler;

    /**
     * 总拓扑图
     * @return
     */
    @RequestMapping("/topology")
    public String topology() {
        log.info("start: 查询总拓扑图");
        try{

            List<Link> linkList = linkHandler.queryAll();
            Set<Long> deviceIds = new HashSet<>();
            linkList.forEach(link -> {
                //去掉不需要的字段-dbConfigData
                link.setDbConfigData(null);
                //得到所有的节点id
                deviceIds.addAll(link.getDeviceIds());
            });

            Map<Long,JSONObject> allDeviceJson = new HashMap<>();
            if(!CollectionUtils.isEmpty(deviceIds)){
                //获取该id对应的所有设备名称和ip
                List<Device> allDevice = deviceHandler.query8Ids(deviceIds);
                if(!CollectionUtils.isEmpty(allDevice)){
                    allDevice.forEach(device -> {
                        JSONObject deviceJson = new JSONObject();
                        deviceJson.put("ip",device.getIp());
                        deviceJson.put("deviceName",device.getDeviceName());
                        deviceJson.put("icon",device.getIcon());
                        deviceJson.put("netArea",device.getNetArea());

                        allDeviceJson.put(device.getDeviceId(),deviceJson);
                    });
                }
            }

            return JSONMessage.createSuccess().addData("list",linkList).addData("devices",allDeviceJson).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  查询总拓扑图 ");
        }
    }


    /**
     * 报警总览
     * @param selectDate
     * @return
     */
    @RequestMapping("/alarmOverview")
    public String AlarmOverview(String selectDate){
        log.info("start: 根据时间获取报警总览异常，selectDate={} ",selectDate);
        if(StringUtils.isEmpty(selectDate)){
            return JSONMessage.createFalied("selectDate不能为空").toString();
        }

        Date date = DateUtil.strFormatDate(selectDate,DateUtil.format_day);
        if(date == null){
            return JSONMessage.createFalied("selectDate格式不正确，必须为yyyy-MM-dd").toString();
        }

        AlarmDayLog alarmDayLog = alarmDayLogHandler.query8day(date);


        return JSONMessage.createSuccess().addData(alarmDayLog).toString();
    }

    /**
     * 报警排行榜(最近一周)
     * @param  type 1：链路 ；2 ：设备 ； 3：任务
     * @return
     */
    @RequestMapping("/alarmRinking")
    public String alarmRinking(Integer type){
        if(type == null){
            type = 1;
        }

        JSONArray jsonArray = new JSONArray();

        switch (type){
            case 1:
                jsonArray = alarmRinking_link();
                break;
            case 2:
                jsonArray = alarmRinking_device();
                break;
            case 3:
                jsonArray = alarmRinking_task();
                break;
        }
        return JSONMessage.createSuccess().addData("rinking",jsonArray).toString();
    }

    private JSONArray alarmRinking_device() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","设备1");
        jsonObject.put("number",4);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备2");
        jsonObject.put("number",2);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备3");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备8");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备5");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备9");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备10");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","设备7");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        return jsonArray;
    }

    private JSONArray alarmRinking_task() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","任务1");
        jsonObject.put("number",6);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","任务2");
        jsonObject.put("number",4);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","任务3");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","任务4");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","任务5");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        return jsonArray;
    }

    private JSONArray alarmRinking_link() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","链路1");
        jsonObject.put("number",2);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","链路2");
        jsonObject.put("number",1);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","链路3");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("name","链路4");
        jsonObject.put("number",0);
        jsonArray.add(jsonObject);

        return jsonArray;
    }

    /**
     * 报警统计曲线图，最近一个月
     * @return
     */
    @RequestMapping("/alarmStatistics")
    public String alarmStatistics(){
        //算出上个月时间
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, day - 1);
        Date lastMonthDay = c.getTime();

        List<AlarmDayLog> alarmDayLogs = alarmDayLogHandler.query8days(lastMonthDay,today);

        return JSONMessage.createSuccess().addData("list",alarmDayLogs).toString();
    }


    /**
     * 数据流量/文件统计 （月）
     * @return
     */
    @RequestMapping("/statistics")
    public String statistics(){
        //算出上个月时间
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, day - 1);
        Date lastMonthDay = c.getTime();

        int taskTypeDB = Task.taskType_db;
        TaskDayLog dbTaskDayLog = taskDayLogHandler.query8days(taskTypeDB,lastMonthDay,today);
        if(dbTaskDayLog == null){
            dbTaskDayLog = new TaskDayLog();
        }
        //总db同步条数
        long totalDataNum = dbTaskDayLog.getSuccessNum();

        int taskTypeFTP = Task.taskType_ftp;
        TaskDayLog ftpTaskDayLog = taskDayLogHandler.query8days(taskTypeFTP,lastMonthDay,today);
        if(ftpTaskDayLog == null){
            ftpTaskDayLog = new TaskDayLog();
        }
        //总文件数
        long totalFile = ftpTaskDayLog.getSuccessNum();
        //总流量
        long totalFlow = ftpTaskDayLog.getSuccessFlow() + dbTaskDayLog.getSuccessFlow();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("totalData",totalDataNum);
        jsonObject.put("totalFile",totalFile);
        jsonObject.put("totalFlow",totalFlow);

        return JSONMessage.createSuccess().addData(jsonObject).toString();
    }


}