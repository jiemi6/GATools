package com.minkey.controller;

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

            JSONObject allDeviceJson = new JSONObject();
            if(!CollectionUtils.isEmpty(deviceIds)){
                //获取该id对应的所有设备名称和ip
                List<Device> allDevice = deviceHandler.query8Ids(deviceIds);
                if(!CollectionUtils.isEmpty(allDevice)){
                    allDevice.forEach(device -> {
                        JSONObject deviceJson = new JSONObject();
                        deviceJson.put("ip",device.getIp());
                        deviceJson.put("deviceName",device.getDeviceName());

                        allDeviceJson.put(device.getDeviceId()+"",deviceJson);
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

}