package com.minkey.handler;

import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.command.Ping;
import com.minkey.contants.CommonContants;
import com.minkey.contants.DeviceType;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.util.DetectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * 检查链路中的设备的连接状态
 */
@Slf4j
@Component
public class DeviceStatusHandler {
    @Autowired
    DeviceCache deviceCache;
    /**
     * 设备联通性情况
     */
    @Autowired
    DeviceConnectCache deviceConnectCache;

    /**
     * 每5秒刷新连接情况
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void reflashConnect() {
        //Minkey 不应该只刷新link，应该刷一次所有设备？
        deviceCache.getAllLinkMap().forEach((aLong, link) ->  {
            //有可能为空，链路没有配探针
            DeviceService deviceService = deviceCache.getDetectorService8linkId(aLong);
            Set<Long> allDeviceId = link.getDeviceIds();
            for (Long deviceId:allDeviceId){
                try {
                    testDeviceConnect(deviceCache.getDevice(deviceId), deviceService);
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }
            }
        });
    }



    /**
     * 异步测试设备联通情况
     * @param device
     */
    @Async
    public void testDeviceConnect(Device device,DeviceService detectorService){
        if(device == null){
            return;
        }

        long deviceId = device.getDeviceId();
        boolean isConnect = pingTest(device,detectorService);
        deviceConnectCache.putConnect(deviceId,isConnect);
    }

    public boolean pingTest(Device device,DeviceService detectorService) {
        if (device.getDeviceType() == DeviceType.floder) {
            //设备如果是文件夹，默认就是联通的
            return true;
        }

        if (StringUtils.isEmpty(device.getIp())) {
            log.error("设备 {} ip不能为空",device.getDeviceName());
            return false;
        }

        boolean isConnect = false;
        if(device.getDeviceType() == DeviceType.detector) {
            //如果自己是探针，而且没有配置探针服务
            if(detectorService == null){
                return false;
            }
            //如果是探针自己，发送探针check
            return DetectorUtil.check(detectorService.getIp(), detectorService.getConfigData().getPort());
        }
        //如果是内网，
        if(device.getNetArea() == CommonContants.NETAREA_IN ){
            //直接访问
            isConnect = Ping.javaPing(device.getIp(),1000);
        }else{
            //如果是外网，需要通过探针访问,获取探针信息
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
            return DetectorUtil.ping(detectorService.getIp(),detectorService.getConfigData().getPort(),device.getIp());

        }
        return isConnect;
    }




}
