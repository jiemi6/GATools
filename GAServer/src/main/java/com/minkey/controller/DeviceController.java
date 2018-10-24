package com.minkey.controller;

import com.minkey.contants.DeviceType;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.DeviceExplorer;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import com.minkey.handler.DeviceStatusHandler;
import com.minkey.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    DeviceStatusHandler deviceStatusHandler;

    @RequestMapping("/insert")
    public String insert( Device device) {
        logger.info("start: 执行insert设备 device={} ",device);

        if(StringUtils.isEmpty(device.getDeviceName())){
            return JSONMessage.createFalied("name不能为空格式错误").toString();
        }

        if(device.getDeviceType() != DeviceType.floder && StringUtils.isNotEmpty(device.getIp())){
            if(!StringUtil.isIp(device.getIp())){
                return JSONMessage.createFalied("ip格式错误").toString();
            }
        }

        try{
            long deviceId = deviceHandler.insert(device);
            device.setDeviceId(deviceId);

            List<DeviceService> paramList = device.getDeviceServiceList();
            if(!CollectionUtils.isEmpty(paramList)){
                deviceServiceHandler.insertAll(device,paramList);
            }
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行insert设备 device={} ",device);
        }
    }

    @RequestMapping("/update")
    public String update( Device device) {
        logger.info("start: 执行insert设备 device={} ",device);

        if(device.getDeviceId() < 0){
            return JSONMessage.createFalied("deviceId错误").toString();
        }

        if(StringUtils.isEmpty(device.getDeviceName())){
            return JSONMessage.createFalied("name不能为空格式错误").toString();
        }

        if(device.getDeviceType() != DeviceType.floder && StringUtils.isNotEmpty(device.getIp())){
            if(!StringUtil.isIp(device.getIp())){
                return JSONMessage.createFalied("ip格式错误").toString();
            }
        }

        try{
            deviceHandler.replace(device);
            //先删除
            deviceServiceHandler.delete8DeviceId(device.getDeviceId());

            List<DeviceService> deviceServiceList = device.getDeviceServiceList();
            if(!CollectionUtils.isEmpty(deviceServiceList)){
                //在增加
                deviceServiceHandler.insertAll(device ,deviceServiceList);
            }

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行insert设备 device={} ",device);
        }
    }

    @RequestMapping("/query")
    public String query(Long deviceId) {
        logger.info("start: 执行query设备 deviceId={} ",deviceId);
        if(deviceId == null || deviceId <=0){
            logger.info("deviceId不能为空");
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }
        try{

            Device device = deviceHandler.query(deviceId);
            device.setDeviceServiceList(deviceServiceHandler.query8Device(deviceId));
            return JSONMessage.createSuccess().addData(device).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行query设备 deviceId={} ",deviceId);
        }
    }

    @RequestMapping("/query8Page")
    public String query8Page(Integer currentPage,Integer pageSize) {
        logger.info("start: 执行分页查询设备 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<Device> page = new Page(currentPage,pageSize);

            page = deviceHandler.query8Page(page);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
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
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行count所有设备 ");
        }
    }

    @RequestMapping("/delete")
    public String delete(Long deviceId) {
        logger.info("start: 执行删除设备 ");
        if(deviceId == null || deviceId <=0){
            logger.info("deviceId不能为空");
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }

        try{
            //先删除
            deviceServiceHandler.delete8DeviceId(deviceId);

            deviceHandler.delete(deviceId);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行删除设备 ");
        }
    }


    @RequestMapping("/queryAllDetector")
    public String queryAllDetector() {
        logger.info("start: 查询所有探针设备 ");

        try{
            List<Device> all = deviceHandler.query8Type(DeviceType.detector);

            return JSONMessage.createSuccess().addData("list",all).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 查询所有探针设备 ");
        }
    }

    /**
     * 查询某一个终端的实时资源消耗
     * @return
     */
    @RequestMapping("/queryExplorer")
    public String queryExplorer(Long deviceId) {
        logger.info("start: 查询某一个终端的实时资源消耗 deviceId={}",deviceId);

        try{
            DeviceExplorer deviceExplorer = deviceStatusHandler.getDeviceExplorer(deviceId);
            return JSONMessage.createSuccess().addData("deviceExplorer" ,deviceExplorer).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 查询某一个终端的实时资源消耗 ");
        }
    }
}