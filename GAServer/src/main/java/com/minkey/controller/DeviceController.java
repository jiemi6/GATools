package com.minkey.controller;

import com.minkey.dao.Device;
import com.minkey.db.DeviceHandler;
import com.minkey.dto.JSONMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    DeviceHandler deviceHandler;

    @RequestMapping("/insert")
    public String insert(Device device) {
        logger.info("start: 执行insert设备 device={} ",device);

        try{
            deviceHandler.insert(device);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行insert设备 device={} ",device);
        }
    }

    @RequestMapping("/query")
    public String query(long deviceId) {
        logger.info("start: 执行query设备 deviceId={} ",deviceId);
        if(deviceId == 0){
            logger.info("deviceId不能为空");
            return JSONMessage.createFalied("configKey不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(deviceHandler.query(deviceId)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query设备 deviceId={} ",deviceId);
        }
    }


}