package com.minkey.controller;

import com.minkey.db.DeviceHandler;
import com.minkey.db.dao.Device;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
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