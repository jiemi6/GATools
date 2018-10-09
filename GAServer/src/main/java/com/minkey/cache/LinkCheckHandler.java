package com.minkey.cache;

import com.minkey.command.Ping;
import com.minkey.db.DeviceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.Link;
import com.minkey.dto.DeviceExplorer;
import com.minkey.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 处理链路检查
 */
public class LinkCheckHandler {


    /**
     * 链路id
     */
    private long linkId;

    private Map<Long,Device> linkDeviceMap = new HashMap<>();


    public LinkCheckHandler(long linkId) {
        this.linkId = linkId;
    }

    /**
     * 探针设备
     */
    private Device detector;

    public void init(){
        LinkHandler linkHandler = null;


        //获取该链路的所有设备
        Link link = linkHandler.query(linkId);
        DeviceCache deviceCache =null;

        DeviceHandler deviceHandler = null;


        Set<Long> deviceIds = link.getDeviceSet();
        List<Device> linkDevices =deviceHandler.query8Ids(deviceIds);

        linkDeviceMap.forEach((aLong, device) -> {
            if(device.getDeviceType() == Device.DeviceType.Detector.type){
                //找到探针
                detector = device;
            }


        });
    }


    /**
     * 每5秒刷新终端数据
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void refresh() {
        //先测试探针是否正常，如果探针不正常，外网就无法探测，外网设备全部为checking。
        if(detector != null){
            testDevice(detector);
        }

        //先遍历一遍连接性
        linkDeviceMap.forEach((aLong, device) -> {
            //如果不是文件夹
            if (device.getDeviceType() != Device.DeviceType.FOLDER.type) {
                testDevice(device);
            }

        });


    }

    /**
     * 通过snmp获取硬件的资源信息
     *
     */
    @Async
    public void getDeviceExplorer8Snmp(Device device) {
        //文件夹直接返回空
        if (device.getDeviceType() != Device.DeviceType.FOLDER.type) {
            return ;
        }

        //通过snmp命令获取 设备硬件信息
        // 如果是内网，或者是探针
        if(device.getNetArea() == Device.NETAREA_IN || device.getDeviceType() == Device.DeviceType.Detector.type){

        }else{
            //得到可以访问的探针


            //通过探针获取硬件信息
        }

        DeviceExplorer deviceExplorer = null;
        DeviceCache.putDeviceExplorer(device.getDeviceId(),deviceExplorer);

    }


    @Async
    public void testDevice(Device device){
        long deviceId = device.getDeviceId();
        boolean isConnect = testConnect(device);
        if(isConnect){
            //获取硬件资源信息
            getDeviceExplorer8Snmp(device);
        }
        DeviceCache.putConnect(deviceId,isConnect);
    }

    private boolean testConnect(Device device) {
        if (device == null) {
            throw new SystemException("设备不能为空");
        }
        if (device.getDeviceType() == Device.DeviceType.FOLDER.type) {
            //设备如果是文件夹，默认就是联通的
            return true;
        }

        if (StringUtils.isEmpty(device.getIp())) {
            throw new SystemException("设备ip不能为空");
        }

        boolean isConnect = false;
        //如果是内网，或者是探针
        if(device.getNetArea() == Device.NETAREA_IN || device.getDeviceType() == Device.DeviceType.Detector.type){
            //直接访问
            isConnect = Ping.javaPing(device.getIp());
        }else{

            //如果是外网，需要通过探针访问
            //获取探针，
            //判断探针是否联通
            //发送探针请求

        }
        return isConnect;
    }

}
