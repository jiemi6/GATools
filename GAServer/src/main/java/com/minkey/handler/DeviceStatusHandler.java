package com.minkey.handler;

import com.minkey.cache.DeviceConnectCache;
import com.minkey.command.Ping;
import com.minkey.contants.DeviceType;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.db.dao.Link;
import com.minkey.dto.DeviceExplorer;
import com.minkey.util.DetectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 检查链路中的设备的连接状态
 */
@Slf4j
@Component
public class DeviceStatusHandler {
    @Autowired
    private DeviceHandler deviceHandler;

    @Autowired
    private DeviceServiceHandler deviceServiceHandler;

    @Autowired
    private LinkHandler linkHandler;

    @Autowired
    SnmpExploreHandler snmpExploreHandler;

    /**
     * 设备联通性情况
     */
    @Autowired
    DeviceConnectCache deviceConnectCache;
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
     * 设备硬件资源map
     * key ：硬件id
     * value： 资源对象
     */
    private Map<Long,DeviceExplorer> deviceExplorerMap = new HashMap<>();

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
    private Map<Long,DeviceService> allLinkServiceMap = new HashMap<>();


    private void cleanAllCache(){
        //清空链路缓存
        allLinkMap = new HashMap<>();
        //清空设备缓存
        allDeviceMap = new HashMap<>();
        //清空探针缓存
        allDetectorServiceMap = new HashMap<>();
        //清空链路探针缓存
        allLinkServiceMap = new HashMap<>();
        //设备连接情况缓存
        deviceConnectCache.cleanAll();

        //清空设备资源缓存
        deviceExplorerMap = new HashMap<>();
    }

    /**
     * 初始化每天晚上自动执行一次
     * 每天凌晨 1：00执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void init(){
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

        for(Link link: links){
            Set<Long> deviceIds = link.getDeviceIds();
            if (CollectionUtils.isEmpty(deviceIds)){
                continue;
            }
            //找探针
            for(Long deviceId: deviceIds){
                Device device = allDeviceMap.get(deviceId);
                if (device == null){
                    continue;
                }
                //如果是探针
                if(device.getDeviceType() == DeviceType.detector){
                    //找探针服务
                    DeviceService deviceService = allDetectorServiceMap.get(deviceId);
                    //放入缓存
                    allLinkServiceMap.put(link.getLinkId(),deviceService);
                    break;
                }
            }
        }

        snmpExploreHandler.init();
    }


    /**
     * 每5秒刷新连接情况
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void reflashConnect() {
        allLinkMap.forEach((aLong, link) ->  {
            DeviceService deviceService = allLinkServiceMap.get(aLong);
            Set<Long> allDeviceId = link.getDeviceIds();
            for (Long deviceId:allDeviceId){
                try {
                    testDeviceConnect(allDeviceMap.get(deviceId), deviceService);
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }
            }
        });
    }


    /**
     * 每5秒刷新硬件资源
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void reflashExplorer() {
        allLinkMap.forEach((aLong, link) ->  {
            DeviceService deviceService = allLinkServiceMap.get(aLong);
            Set<Long> allDeviceId = link.getDeviceIds();
            for (Long deviceId:allDeviceId){
                try {
                    //如果连接正常，则获取
                    if(deviceConnectCache.isOk(deviceId)){
                        getDeviceExplorer(allDeviceMap.get(deviceId), deviceService);
                    }
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }
            }
        });
    }


    /**
     * 通过snmp获取硬件的资源信息
     *
     */
    @Async
    public void getDeviceExplorer(Device device,DeviceService detectorService){
        if(device == null){
            return;
        }
        //文件夹直接返回空
        if (device.getDeviceType() == DeviceType.floder) {
            return ;
        }

        DeviceExplorer deviceExplorer = null;
        //通过snmp命令获取 设备硬件信息
        // 如果是内网，或者是探针
        if(device.getNetArea() == Device.NETAREA_IN || device.getDeviceType() == DeviceType.detector){
            deviceExplorer = snmpExploreHandler.get(device);
        }else{
            //通过探针获取硬件信息
            deviceExplorer = snmpExploreHandler.get(device,detectorService);
        }

        putDeviceExplorer(device.getDeviceId(),deviceExplorer);
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
        if(!isConnect){
            //如果断开了，则删掉所有的硬件资源信息
            deviceExplorerMap.remove(device.getDeviceId());
        }
        deviceConnectCache.putConnect(deviceId,isConnect);
    }

    private boolean pingTest(Device device,DeviceService detectorService) {
        if (device.getDeviceType() == DeviceType.floder) {
            //设备如果是文件夹，默认就是联通的
            return true;
        }

        if (StringUtils.isEmpty(device.getIp())) {
            log.error("设备 {} ip不能为空",device.getDeviceName());
            return false;
        }

        boolean isConnect = false;
        //如果是内网，或者是探针
        if(device.getNetArea() == Device.NETAREA_IN || device.getDeviceType() == DeviceType.detector){
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


    /**
     * 批量获取
     * @param deviceIds 需要获取的硬件id集合
     * @return 得到的具有数据的结果，map的size小于等于参数ids的size
     */
    public Map<Long,DeviceExplorer> getDeviceExplorerMap(Set<Long> deviceIds){
        Map<Long,DeviceExplorer> returnMap = new HashMap<>(deviceIds.size());
        deviceIds.forEach(aLong -> {
            returnMap.put(aLong, deviceExplorerMap.get(aLong));
        });

        return returnMap;
    }

    /**
     * 获取硬件资源，有可能返回空
     * @param deviceId
     * @return 有可能返回null；
     */
    public DeviceExplorer getDeviceExplorer(Long deviceId){
        return deviceExplorerMap.get(deviceId);
    }


    public void putDeviceExplorer(long deviceId, DeviceExplorer deviceExplorer) {
        deviceExplorerMap.put(deviceId,deviceExplorer);
    }

    public Set<Long> queryAllConnect() {
        return deviceConnectCache.getOkSet();
    }

    public Device getDevice8Id(Long deviceId) {
        return allDeviceMap.get(deviceId);
    }
}
