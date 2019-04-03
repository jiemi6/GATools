package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.command.Ping;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.CommonContants;
import com.minkey.contants.DeviceType;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.exception.SystemException;
import com.minkey.util.DetectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 检查链路中的设备的连接状态
 */
@Slf4j
@Component
public class DeviceConnectHandler {
    @Autowired
    DeviceCache deviceCache;
    /**
     * 设备联通性情况
     */
    @Autowired
    DeviceConnectCache deviceConnectCache;

    @Value("${system.debug:false}")
    private boolean isDebug;

    /**
     * 每10秒刷新连接情况
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void reflashConnect() {
        if(isDebug){
            return ;
        }

        Map<Long, Device> allDevices = deviceCache.allDevice();

        for (Device device:allDevices.values()){
            try {
                testDeviceConnect(device);
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
        }
    }



    /**
     * 异步测试设备联通情况
     * @param device
     */
    @Async
    public void testDeviceConnect(Device device){
        if(device == null){
            return;
        }

        boolean isConnect = false;
        try {
            isConnect = pingConnectTest(device);
        } catch (SystemException e) {
            log.error("ping设备异常",e);
        }
        long deviceId = device.getDeviceId();
        deviceConnectCache.putConnect(deviceId,isConnect);
    }

    public boolean pingConnectTest(Device device) {
        if (device.getDeviceType() == DeviceType.floder) {
            //设备如果是文件夹，默认就是联通的
            return true;
        }

        if (StringUtils.isEmpty(device.getIp())) {
            log.error("设备 {} ip不能为空",device.getDeviceName());
            return false;
        }

        boolean isConnect = false;
        //如果自己是探针
        if(device.getDeviceType() == DeviceType.detector) {
            //查询自己的探针服务
            DeviceService detectorService = deviceCache.getDetectorService8DetectorSelf(device.getDeviceId());
            //没有配置探针服务
            if(detectorService == null){
                log.error(String.format("探针<%s>没有配置探针服务，无法探测自己和外网服务器",device.getDeviceName()));
                return false;
            }
            //如果是探针自己，发送探针check
            return DetectorUtil.check(detectorService.getIp(), detectorService.getConfigData().getPort());
        }
        //如果是内网，
        if(device.getNetArea() == CommonContants.NETAREA_IN ){
            //直接访问
            isConnect = Ping.pingConnect(device.getIp());
        }else{
            //如果是外网，需要通过探针访问,获取探针信息
            DeviceService detectorService = deviceCache.getOneDetectorServer8DeviceId(device.getDeviceId());
            //
            if(detectorService == null){
                return false;
            }
            //判断探针是否联通
            boolean connect = deviceConnectCache.isOk(detectorService.getDeviceId());
            //如果探针连接失败，则无法测试，直接返回false；
            if (!connect){
                return false;
            }
            //发送探针请求
            return DetectorUtil.pingConnect(detectorService.getIp(),detectorService.getConfigData().getPort(),device.getIp());

        }
        return isConnect;
    }


    public JSONObject ping(Device device) {
        JSONObject jsonObject = new JSONObject(2);
        int pingAgainNum = 50;
        //默认ping4次
        jsonObject.put("total",Ping.defalut_pingTimes);
        //如果自己是探针 和 内网设备
        if(device.getDeviceType() == DeviceType.detector || device.getNetArea() == CommonContants.NETAREA_IN ){
            //需求要求先ping4次
            int successNum = Ping.ping(device.getIp(),Ping.defalut_pingTimes,Ping.defalut_intervalTime,Ping.defalut_timeout);
            //如果是0,丢全部包,直接返回
            if(successNum <= 0){
                jsonObject.put("successNum",0);
                return jsonObject;
            }else if(successNum >= 4){
                //不丢包,正常
                jsonObject.put("successNum",4);
                return jsonObject;
            }else {
                //如果是1,2,3个包,再ping50个包
                successNum = Ping.ping(device.getIp(),pingAgainNum,Ping.defalut_intervalTime,Ping.defalut_timeout);
                jsonObject.put("successNum",successNum);
                jsonObject.put("total",pingAgainNum);

                return jsonObject;
            }
        }else{
            //如果是外网，需要通过探针访问,获取探针信息
            DeviceService detectorService = deviceCache.getOneDetectorServer8DeviceId(device.getDeviceId());
            //
            if(detectorService == null){
                throw new SystemException(AlarmEnum.no_detector,"没有配置探针,无法检查外网设备");
            }
            //判断探针是否联通
            boolean connect = deviceConnectCache.isOk(detectorService.getDeviceId());
            //如果探针连接失败，则无法测试，直接返回false；
            if (!connect){
                throw new SystemException(AlarmEnum.no_detector_connect,"无法连接探针,无法检查外网设备");
            }

            //需求要求先ping4次
            JSONObject returnObj = DetectorUtil.ping(detectorService.getIp(),detectorService.getConfigData().getPort(),device.getIp()
                                                    ,Ping.defalut_pingTimes,Ping.defalut_intervalTime,Ping.defalut_timeout);

            int successNum = returnObj.getIntValue("successNum");
            //如果是0,丢全部包,直接返回
            if(successNum <= 0){
                jsonObject.put("successNum",0);
                return jsonObject;
            }else if(successNum >= 4){
                //不丢包,正常
                jsonObject.put("successNum",4);
                return jsonObject;
            }else {
                //如果是1,2,3个包,再ping50个包
                returnObj = DetectorUtil.ping(detectorService.getIp(),detectorService.getConfigData().getPort(),device.getIp()
                        ,pingAgainNum,Ping.defalut_intervalTime,Ping.defalut_timeout);

                successNum = returnObj.getIntValue("successNum");
                jsonObject.put("successNum",successNum);
                jsonObject.put("total",pingAgainNum);
                return jsonObject;
            }
        }
    }
}
