package com.minkey.controller;

import com.minkey.dao.Device;
import com.minkey.db.DeviceHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备接口
 */
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
    public String query(Long deviceId) {
        logger.info("start: 执行query设备 deviceId={} ",deviceId);
        if(deviceId == null){
            logger.info("deviceId不能为空");
            return JSONMessage.createFalied("deviceId不能为空").toString();
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

    @RequestMapping("/queryAll")
    public String queryAll() {
        logger.info("start: 执行query所有设备 ");

        try{
            return JSONMessage.createSuccess().addData(deviceHandler.queryAll()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        logger.info("start: 执行count所有设备 ");

        try{
            return JSONMessage.createSuccess().addData(deviceHandler.queryCount()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行count所有设备 ");
        }
    }
}