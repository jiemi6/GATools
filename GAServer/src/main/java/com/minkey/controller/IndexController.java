package com.minkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.db.*;
import com.minkey.db.analysis.AlarmDayLog;
import com.minkey.db.analysis.AlarmDayLogHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.JSONMessage;
import com.minkey.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    DeviceCache deviceCache;

    @Autowired
    TaskHandler taskHandler;
    /**
     * 总拓扑图
     * @return
     */
    @RequestMapping("/topology")
    public String topology() {
        log.debug("start: 查询总拓扑图");
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
                Map<Long, Device> allDevice = deviceCache.getDevice8Ids(deviceIds);
                if(!CollectionUtils.isEmpty(allDevice)){
                    allDevice.forEach((deviceid,device) -> {
                        if(device != null) {
                            JSONObject deviceJson = new JSONObject();
                            deviceJson.put("ip", device.getIp());
                            deviceJson.put("deviceName", device.getDeviceName());
                            deviceJson.put("icon", device.getIcon());
                            deviceJson.put("netArea", device.getNetArea());

                            allDeviceJson.put(deviceid, deviceJson);
                        }
                    });
                }
            }

            return JSONMessage.createSuccess().addData("list",linkList).addData("devices",allDeviceJson).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end:  查询总拓扑图 ");
        }
    }


    /**
     * 报警总览
     * @param selectDate
     * @return
     */
    @RequestMapping("/alarmOverview")
    public String AlarmOverview(String selectDate){
        log.debug("start: 根据时间获取报警总览异常，selectDate={} ",selectDate);
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

        //算出上个月时间
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        int day = c.get(Calendar.DATE);
        //前7天
        c.set(Calendar.DATE, day - 7);
        Date lastWeekDay = c.getTime();

        JSONArray jsonArray = new JSONArray();

        List rinkingList = alarmLogHandler.queryRinking(type,lastWeekDay,today);
        Map<Long,String> nameMap = null;
        if(!CollectionUtils.isEmpty(rinkingList)){
            Set<Long>  bidSet  = new HashSet<>(rinkingList.size());
            jsonArray = (JSONArray) JSONArray.toJSON(rinkingList);
            for (Object jo : jsonArray) {
                bidSet.add(((JSONObject)jo).getLong("bid"));
            }

            switch (type){
                case AlarmLog.BTYPE_LINK:
                    nameMap = deviceCache.getName8LinkIds(bidSet);
                    break;
                case AlarmLog.BTYPE_DEVICE :
                    nameMap = deviceCache.getName8DeviceIds(bidSet);
                    break;
                case AlarmLog.BTYPE_TASK:
                    nameMap = taskHandler.query8TaskIds(bidSet).stream().collect(Collectors.toMap(Task::getTaskId, Task::getTaskName ));
                    break;
            }
        }

        return JSONMessage.createSuccess().addData("rinking",jsonArray).addData("nameMap",nameMap).toString();
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
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

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
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        int day = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, day - 1);
        Date lastMonthDay = c.getTime();

        int taskTypeDB = Task.taskType_db;
        TaskDayLog dbTaskDayLog = taskDayLogHandler.query8days(taskTypeDB,lastMonthDay,today);
        //总db同步条数
        long totalData = dbTaskDayLog.getSuccessNum();

        int taskTypeFTP = Task.taskType_ftp;
        TaskDayLog ftpTaskDayLog = taskDayLogHandler.query8days(taskTypeFTP,lastMonthDay,today);
        //总文件数
        long totalFile = ftpTaskDayLog.getSuccessNum();
        //总流量
        long totalFlow = ftpTaskDayLog.getSuccessFlow() + dbTaskDayLog.getSuccessFlow();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("totalData",totalData);
        jsonObject.put("totalFile",totalFile);
        jsonObject.put("totalFlow",totalFlow);

        return JSONMessage.createSuccess().addData(jsonObject).toString();
    }


    @Autowired
    CheckItemHandler checkItemHandler;
    /**
     * 首页滚动log
     * @return
     */
    @RequestMapping("/rollLog")
    public String rollLog(){
        //直接查找最后一次体检记录

        //取最后10个
        List<CheckItem> checkItems = checkItemHandler.getLast10();

        Set<String> logs = checkItems.stream().map(checkItem -> checkItem.getResultMsg()).collect(Collectors.toSet());

        return JSONMessage.createSuccess().addData("rollLog",logs).toString();
    }

}