package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.DeviceType;
import com.minkey.db.*;
import com.minkey.db.dao.*;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 日志查看接口
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    SyslogHandler syslogHandler;

    @Autowired
    CheckHandler checkHandler;

    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    UserHandler userHandler;

    /**
     * 设备日志
     * @return
     */
    @RequestMapping("/device")
    public String device(Integer currentPage, Integer pageSize, SeachParam seachParam,Long deviceId) {
        log.info("start: 分页查询设备日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            Page<Syslog> page = new Page(currentPage,pageSize);

            Set<String> paramIps = null;
            //判断设备id和设备类型是否作为搜索条件
            if((deviceId != null && deviceId > 0) | seachParam.getType() != null){
                //找设备
                List<Device> deviceList = deviceHandler.query8IdAndType(deviceId,seachParam.getType());

                //如果找不到设备，则不用找日志了
                if(CollectionUtils.isEmpty(deviceList)){
                    page.setTotal(0);
                    return JSONMessage.createSuccess().addData(page).toString();
                }

                paramIps = deviceList.stream().map(device -> device.getIp()).collect(Collectors.toSet());
            }

            Page<Syslog> logs = syslogHandler.query8page(page,seachParam,paramIps);

            JSONObject ipJson  = new JSONObject();
            if(!CollectionUtils.isEmpty(logs.getList())){
                Set<String> ips = logs.getList().stream().filter(syslog -> StringUtils.isNotEmpty(syslog.getHost())).map(syslog -> syslog.getHost()).collect(Collectors.toSet());
                List<Device> deviceList = deviceHandler.query8ips(ips);
                Map<Long,Device> nameMap ;
                if (CollectionUtils.isEmpty(deviceList)) {
                    nameMap = new HashMap<>();
                }else{
                    nameMap = deviceList.stream().collect(Collectors.toMap(Device::getDeviceId, Device -> Device ));
                }
                //得到差集，数据库中未知的设备
                Set<String> unKnowIps = new HashSet<>();
                unKnowIps.addAll(ips);
                unKnowIps.removeAll(nameMap.keySet());
                unKnowIps.forEach(unKnowIp -> {
                    JSONObject tempDevice = new JSONObject();
                    tempDevice.put("deviceName",unKnowIp);
                    tempDevice.put("deviceType", DeviceType.unKnow);
                    tempDevice.put("deviceId",-1);

                    ipJson.put(unKnowIp,tempDevice);
                });
                nameMap.forEach((aLong, device) -> {
                    JSONObject tempDevice = new JSONObject();
                    tempDevice.put("deviceName",device.getDeviceName());
                    tempDevice.put("deviceType", device.getDeviceType());
                    tempDevice.put("deviceId",device.getDeviceId());

                    ipJson.put(device.getIp(),tempDevice);
                });
            }

            return JSONMessage.createSuccess().addData(logs).addData("ipMap",ipJson).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  分页查询设备日志");
        }
    }


    /**
     * 用户日志
     * @return
     */
    @RequestMapping("/user")
    public String user(Integer currentPage,Integer pageSize, SeachParam seachParam,Long uid) {
        log.info("start: 分页查询用户日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Page<UserLog> page = new Page(currentPage,pageSize);

            page = userLogHandler.query8Page(page,seachParam,uid);

            Object nameJson = null;
            if(!CollectionUtils.isEmpty(page.getList())){
                Set<Long> uids = page.getList().stream().map(userlog -> userlog.getUid()).collect(Collectors.toSet());
                Map<Long,String> nameMap = userHandler.query8Ids(uids).stream().collect(Collectors.toMap(User::getUid, User::getuName ));

                nameJson = JSONObject.toJSON(nameMap);
            }

            return JSONMessage.createSuccess().addData(page).addData("nameMap",nameJson).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  分页查询用户日志");
        }
    }

    /**
     * 体检发起日志
     * @return
     */
    @RequestMapping("/detection")
    public String detection(Integer currentPage,Integer pageSize, SeachParam seachParam,Long uid) {
        log.info("start: 分页查询体检发起日志 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(currentPage == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Page<Check> page = new Page(currentPage,pageSize);

            page = checkHandler.query8Page(page,seachParam,uid);

            Map<Long, String> nameMap = null;
            if(!CollectionUtils.isEmpty(page.getList())) {
                Set<Long> uids = page.getList().stream().map(check -> check.getUid()).collect(Collectors.toSet());
                nameMap = userHandler.query8Ids(uids).stream().collect(Collectors.toMap(User::getUid, User :: getuName));
            }

            return JSONMessage.createSuccess().addData(page).addData("nameMap",nameMap).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  分页查询体检发起日志");
        }
    }

}