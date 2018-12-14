package com.minkey.controller;

import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceExplorerCache;
import com.minkey.contants.DeviceType;
import com.minkey.contants.Modules;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.db.dao.User;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.DeviceExplorer;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import com.minkey.handler.DeviceConnectHandler;
import com.minkey.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 设备接口
 */
@Slf4j
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    @Autowired
    DeviceConnectHandler deviceConnectHandler;

    @Autowired
    DeviceExplorerCache deviceExplorerCache;

    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    DeviceCache deviceCache;

    @Autowired
    HttpSession session;

    @Autowired
    AlarmLogHandler alarmLogHandler;

    private JSONMessage checkParam(Device device){
        if(StringUtils.isEmpty(device.getDeviceName())){
            return JSONMessage.createFalied("name不能为空格式错误");
        }
        if(StringUtils.isEmpty(device.getIcon())){
            return JSONMessage.createFalied("图标不能为空");
        }

        if(device.getDeviceType() != DeviceType.floder && StringUtils.isNotEmpty(device.getIp())){
            if(!StringUtil.isIp(device.getIp())){
                return JSONMessage.createFalied("ip格式错误");
            }
        }

        List<DeviceService> paramList = device.getDeviceServiceList();
        if(!CollectionUtils.isEmpty(paramList)){
            for(DeviceService deviceService : paramList) {
                String configDataStr = deviceService.getConfigDataStr();
                if(StringUtils.isEmpty(configDataStr)){
                    return JSONMessage.createFalied("服务配置不能为空");
                }

                BaseConfigData baseConfigData = DeviceService.conventConfigData8str(deviceService.getServiceType(),configDataStr);
                deviceService.setConfigData(baseConfigData);

                if (deviceService.getServiceType() == DeviceService.SERVICETYPE_DETECTOR && StringUtils.isEmpty(deviceService.getIp())) {
                    return JSONMessage.createFalied("探针服务ip不能为空");
                }
                if(device.getDeviceType() != DeviceType.floder){
                    if(deviceService.getConfigData() == null){
                        return JSONMessage.createFalied(String.format("服务%s没有配置数据",deviceService.getServiceName()));
                    }
                }
            }
        }

        return JSONMessage.createSuccess();
    }

    @RequestMapping("/insert")
    public String insert( Device device) {
        log.debug("start: 执行insert设备 device={} ",device);

        //检查参数
        JSONMessage jsonMessage = checkParam(device);
        if(!jsonMessage.isSuccess()){
            return jsonMessage.toString();
        }

        try{
            List<DeviceService> paramList = device.getDeviceServiceList();
            long deviceId = deviceHandler.insert(device);
            device.setDeviceId(deviceId);

            if(!CollectionUtils.isEmpty(paramList)){
                paramList.forEach(deviceService -> {
                    if(StringUtils.isEmpty(deviceService.getIp())){
                        deviceService.setIp(device.getIp());
                    }
                });
                deviceServiceHandler.insertAll(device,paramList);
            }

            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,Modules.device,String.format("[%s]新增设备[%s]",sessionUser.getuName(),device.getDeviceName()));

            //刷新缓存
            deviceCache.refresh();
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行insert设备 ");
        }
    }

    @RequestMapping("/update")
    public String update( Device device) {
        log.debug("start: 执行update设备 device={} ",device);

        if(device.getDeviceId() < 0){
            return JSONMessage.createFalied("deviceId错误").toString();
        }

        //检查参数
        JSONMessage jsonMessage = checkParam(device);
        if(!jsonMessage.isSuccess()){
            return jsonMessage.toString();
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

            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,Modules.device,String.format("[%s]修改设备信息，设备名称[%s]。",sessionUser.getuName(),device.getDeviceName()));

            //刷新缓存
            deviceCache.refresh();

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行update设备");
        }
    }

    @RequestMapping("/query")
    public String query(Long deviceId) {
        log.debug("start: 执行query设备 deviceId={} ",deviceId);
        if(deviceId == null || deviceId <=0){
            log.info("deviceId不能为空");
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }
        try{

            Device device = deviceHandler.query(deviceId);
            device.setDeviceServiceList(deviceServiceHandler.query8Device(deviceId));
            return JSONMessage.createSuccess().addData(device).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行query设备 deviceId={} ",deviceId);
        }
    }

    @RequestMapping("/query8Page")
    public String query8Page(Integer currentPage,Integer pageSize) {
        log.debug("start: 执行分页查询设备 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<Device> page = new Page(currentPage,pageSize);

            page = deviceHandler.query8Page(page);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行分页查询所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        log.debug("start: 执行count所有设备 ");

        try{
            return JSONMessage.createSuccess().addData("count",deviceHandler.queryCount()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行count所有设备 ");
        }
    }

    @RequestMapping("/delete")
    public String delete(Long deviceId) {
        log.debug("start: 执行删除设备 deviceId={} ",deviceId);
        if(deviceId == null || deviceId <=0){
            log.info("deviceId不能为空");
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }

        try{
            //是否属于某个链路
            Set<Long> allLinkId = deviceCache.getLink8DeviceId(deviceId);
            if(!CollectionUtils.isEmpty(allLinkId)){
                Map<Long,String> allName = deviceCache.getName8LinkIds(allLinkId);
                return JSONMessage.createFalied(String.format("设备属于链路[%s],请先从链路中移除设备。",StringUtils.join(allName.values(),","))).toString();
            }

            Device device = deviceCache.getDevice(deviceId);
            //删除设备报警数据
            alarmLogHandler.delete8Id(AlarmLog.BTYPE_DEVICE,deviceId);
            //先删除设备服务
            deviceServiceHandler.delete8DeviceId(deviceId);
            //删除设备
            deviceHandler.delete(deviceId);

            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,Modules.device,String.format("[%s]删除设备[%s]。",sessionUser.getuName(),device.getDeviceName()));

            //刷新缓存
            deviceCache.refresh();

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行删除设备 ");
        }
    }


    @RequestMapping("/queryAllDetector")
    public String queryAllDetector() {
        log.debug("start: 查询所有探针设备 ");

        try{
            List<Device> all = deviceHandler.query8Type(DeviceType.detector);

            return JSONMessage.createSuccess().addData("list",all).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 查询所有探针设备 ");
        }
    }

    /**
     * 查询某一个终端的实时资源消耗
     * @return
     */
    @RequestMapping("/queryExplorer")
    public String queryExplorer(Long deviceId) {
        log.debug("start: 查询某一个终端的实时资源消耗 deviceId={}",deviceId);
        if(deviceId == null || deviceId <=0){
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }
        try{
            DeviceExplorer deviceExplorer = deviceExplorerCache.getDeviceExplorer(deviceId);
            return JSONMessage.createSuccess().addData("deviceExplorer" ,deviceExplorer).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 查询某一个终端的实时资源消耗 ");
        }
    }
}