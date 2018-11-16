package com.minkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.db.DeviceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.Link;
import com.minkey.dto.JSONMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("totalLink",6);
        jsonObject.put("alarmLink",2);

        jsonObject.put("totalDevice",15);
        jsonObject.put("alarmDevice",5);


        jsonObject.put("totalTask",52);
        jsonObject.put("alarmTask",7);

        return JSONMessage.createSuccess().addData(jsonObject).toString();
    }

    /**
     * 报警排行榜(周)
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
     * 报警统计
     * @return
     */
    @RequestMapping("/alarmStatistics")
    public String alarmStatistics(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("link",alarmStatistics_link());
        jsonObject.put("device",alarmStatistics_device());
        jsonObject.put("task",alarmStatistics_task());

        return JSONMessage.createSuccess().addData(jsonObject).toString();
    }

    private JSONArray alarmStatistics_link(){
        JSONArray jsonArray = new JSONArray();
        JSONObject node = new JSONObject();
        node.put("date","2018-01-01");
        node.put("number",12);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-02");
        node.put("number",8);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-03");
        node.put("number",10);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-04");
        node.put("number",6);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-05");
        node.put("number",2);
        jsonArray.add(node);

        return jsonArray;
    }

    private JSONArray alarmStatistics_device(){
        JSONArray jsonArray = new JSONArray();
        JSONObject node = new JSONObject();
        node.put("date","2018-01-01");
        node.put("number",2);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-02");
        node.put("number",3);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-03");
        node.put("number",0);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-04");
        node.put("number",10);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-05");
        node.put("number",2);
        jsonArray.add(node);

        return jsonArray;

    }

    private JSONArray alarmStatistics_task(){
        JSONArray jsonArray = new JSONArray();
        JSONObject node = new JSONObject();
        node.put("date","2018-01-01");
        node.put("number",5);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-02");
        node.put("number",7);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-03");
        node.put("number",2);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-04");
        node.put("number",1);
        jsonArray.add(node);

        node = new JSONObject();
        node.put("date","2018-01-05");
        node.put("number",6);
        jsonArray.add(node);

        return jsonArray;

    }


    /**
     * 统计 （月）
     * @return
     */
    @RequestMapping("/statistics")
    public String statistics(){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("totalNum",5464);
        jsonObject.put("totalFlow",15465465452l);
        jsonObject.put("totalFile",34652l);

        return JSONMessage.createSuccess().addData(jsonObject).toString();
    }


}