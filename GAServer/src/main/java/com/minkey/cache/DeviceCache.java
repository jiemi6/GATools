package com.minkey.cache;

import com.minkey.contants.DeviceType;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.db.dao.Link;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 检查链路中的设备的连接状态
 */
@Slf4j
@Component
public class DeviceCache {
    @Autowired
    private DeviceHandler deviceHandler;

    @Autowired
    private DeviceServiceHandler deviceServiceHandler;

    @Autowired
    private LinkHandler linkHandler;

    /**
     * 所有链路
     * key，主键链路id
     */
    private Map<Long,Link> allLinkMap = new HashMap<>();
    /**
     * 所有的设备
     * key： 主键设备id
     */
    private Map<Long,Device> allDeviceMap = new HashMap<>();

    /**
     * 所有的探针服务缓存
     * key : 探针设备id
     * value ： 探针服务
     */
    private Map<Long,DeviceService> allDetectorServiceMap = new HashMap<>();

    /**
     *链接对应的探针服务缓存
     * key ：链路id，
     * value ：探针服务
     */
    private Map<Long,DeviceService> allLinkDetectorServiceMap = new HashMap<>();


    /**
     * 设备与链路之间的关系
     * key： 设备id
     * value： 所属于的链路集合，
     */
    private Map<Long,Set<Long>> deviceMapLink = new HashMap<>();


    /**
     * 设备对应的snmp服务缓存
     * key：设备id
     * value ： 该设备的snmp服务
     */
    private Map<Long,DeviceService> deviceSNMPServiceMap = new HashMap<>();

    /**
     * 是否重载，标志位、锁
     */
    private Boolean reload = false;

    @Async
    public void refresh(){
        synchronized(reload){
            //如果本来就是true，证明正在执行中
            if(reload){
                return;
            }
            reload.notify();
            reload = true;
        }
    }

    private void startLinsten() {
        while (true) {
            synchronized (reload) {
                //如果不需要reload则wait
                if(!reload){
                    try {
                        reload.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }

                try{
                    initDB2Cache();
                    log.info("刷新链路和设备数据到缓存中");
                }catch (Exception e){
                    log.error("刷新数据库数据到缓存中异常",e);
                }

                reload = false;
            }
        }
    }


    /**
     * 初始化方法，内置监听器，当链路+设备发生任何数据发生变化时候，就重载缓存
     */
    public void init(){
        try{
            initDB2Cache();
            log.info("初始化数据到缓存.");
        }catch (Exception e){
            log.info("初始化数据到缓存异常",e);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                startLinsten();
            }
        },"CacheLinstener").start();

    }



    /**
     * 初始化每天晚上自动执行一次
     * 每天凌晨 1：00执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void initDB2Cache(){
        //所有链路
        List<Link> links = linkHandler.queryAll();

        if(CollectionUtils.isEmpty(links)){
            //清空链路缓存
            allLinkMap = new HashMap<>();
            return;
        }else {
            //重新赋值
            allLinkMap = links.stream().collect(Collectors.toMap(Link::getLinkId, Link -> Link));
        }

        List<Device> allDevices =deviceHandler.queryAll();
        if(CollectionUtils.isEmpty(allDevices)){
            //清空设备缓存
            allDeviceMap = new HashMap<>();
            return;
        }else {
            //重新赋值
            allDeviceMap = allDevices.stream().collect(Collectors.toMap(Device::getDeviceId, Device -> Device));
        }

        List<DeviceService> deviceServiceList = deviceServiceHandler.query8Type(DeviceService.SERVICETYPE_DETECTOR);
        if(CollectionUtils.isEmpty(deviceServiceList)){
            //清空探针缓存
            allDetectorServiceMap = new HashMap<>();
        }else{
            //重新赋值
            allDetectorServiceMap = deviceServiceList.stream().collect(Collectors.toMap(DeviceService::getDeviceId, DeviceService-> DeviceService));
        }

        List<DeviceService> snmpServiceList = deviceServiceHandler.query8Type(DeviceService.SERVICETYPE_SNMP);
        if(CollectionUtils.isEmpty(snmpServiceList)){
            //清空探针缓存
            deviceSNMPServiceMap = new HashMap<>();
        }else{
            //重新赋值
            deviceSNMPServiceMap = snmpServiceList.stream().collect(Collectors.toMap(DeviceService::getDeviceId, DeviceService-> DeviceService));
        }

        for(Link link: links){
            Set<Long> deviceIds = link.getDeviceIds();
            if (CollectionUtils.isEmpty(deviceIds)){
                continue;
            }
            //找探针
            for(Long deviceId: deviceIds){
                putInDeviceMapLink(deviceId,link.getLinkId());

                Device device = allDeviceMap.get(deviceId);
                if (device == null){
                    continue;
                }
                //如果是探针
                if(device.getDeviceType() == DeviceType.detector){
                    //找探针服务
                    DeviceService deviceService = allDetectorServiceMap.get(deviceId);
                    //放入缓存
                    allLinkDetectorServiceMap.put(link.getLinkId(),deviceService);
                    break;
                }
            }
        }

    }


    private void putInDeviceMapLink(long deviceId,long linkId){
        if(deviceMapLink.containsKey(deviceId)){
            deviceMapLink.get(deviceId).add(linkId);
        }else {
            Set<Long> linkIds = new HashSet<>(1);
            linkIds.add(linkId);
            deviceMapLink.put(deviceId,linkIds);
        }
    }

    public Device getDevice(Long deviceId) {
        return allDeviceMap.get(deviceId);
    }

    public Map<Long,Link> getAllLinkMap() {
        return allLinkMap;
    }

    public DeviceService getDetectorService8linkId(Long linkId) {
        return allDetectorServiceMap.get(linkId);
    }

    public DeviceService getSNMPService8deviceId(long deviceId) {
        return deviceSNMPServiceMap.get(deviceId);
    }

    public Set<Long> getLink8DeviceId(Long deviceId) {
        return deviceMapLink.get(deviceId);
    }

    /**
     * 根据设备id查找一个探针服务
     * <br> 1：根据设备id找到所属于linkIds
     * <br> 2 : 遍历所有linkIds的探针，取到一个探针服务
     * @return
     */
    public DeviceService getOneDetectorServer8DeviceId(Long deviceId){
        Set<Long> linkIds = deviceMapLink.get(deviceId);
        if(CollectionUtils.isEmpty(linkIds)){
            return null;
        }

        for (Long linkId : linkIds) {
            DeviceService detectorService = allLinkDetectorServiceMap.get(linkId);
            if(detectorService != null){
                return detectorService;
            }
        }
        return null;
    }

    public Map<Long, Device> allDevice() {
        return allDeviceMap;
    }

    /**
     * 根据链路id获取链路名称
     * @param allLinkId
     */
    public Map<Long, String> getName8LinkIds(Set<Long> allLinkId) {
        Map<Long,String> allName = new HashMap<>(allLinkId.size());
        for(Long linkId :allLinkId){
            Link link = allLinkMap.get(linkId);
            if(link == null){
                allName.put(linkId,null);
            }else{
                allName.put(linkId,link.getLinkName());
            }
        }
        return allName;

    }

    public Link getLink8Id(Long linkId) {
        return allLinkMap.get(linkId);
    }
}
