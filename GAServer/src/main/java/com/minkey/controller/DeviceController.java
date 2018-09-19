package com.minkey.controller;

import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 设备接口
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    @RequestMapping("/insert")
    public String insert( Device device) {
        logger.info("start: 执行insert设备 device={} ",device);

        try{
            deviceHandler.replace(device);

            List<DeviceService> paramList = device.getDeviceServiceList();

            deviceServiceHandler.insertAll(paramList);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行insert设备 device={} ",device);
        }
    }

    @RequestMapping("/update")
    public String update( Device device) {
        logger.info("start: 执行insert设备 device={} ",device);

        try{
            deviceHandler.replace(device);
            //先删除
            deviceServiceHandler.delete8DeviceId(device.getDeviceId());

            List<DeviceService> paramList = device.getDeviceServiceList();

            //在增加
            deviceServiceHandler.insertAll(paramList);

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

            Device device = deviceHandler.query(deviceId);
            device.setDeviceServiceList(deviceServiceHandler.query8Device(deviceId));
            return JSONMessage.createSuccess().addData(device).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query设备 deviceId={} ",deviceId);
        }
    }

    @RequestMapping("/query8Page")
    public String query8Page(Integer currentPage,Integer pageSize) {
        logger.info("start: 执行分页查询设备 ");

        try{
            Page page = new Page(currentPage,pageSize);

            page = deviceHandler.query8Page(page);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行分页查询所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        logger.info("start: 执行count所有设备 ");

        try{
            return JSONMessage.createSuccess().addData("count",deviceHandler.queryCount()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行count所有设备 ");
        }
    }


    /**
     * 查询某一个终端的实时资源消耗
     * @return
     */
    @RequestMapping("/queryExplorer")
    public String queryExplorer(Long deviceId) {
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