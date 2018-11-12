package com.minkey.handler;

import com.minkey.cache.CheckStepCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.command.SnmpUtil;
import com.minkey.contants.MyLevel;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.dao.CheckItem;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.db.dao.Link;
import com.minkey.dto.DeviceExplorer;
import com.minkey.util.DetectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 检查log
 */
@Slf4j
@Component
public class ExamineHandler {
    @Autowired
    CheckItemHandler checkItemHandler;

    @Autowired
    DeviceConnectCache deviceConnectCache;

    @Autowired
    DeviceStatusHandler deviceStatusHandler;

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    @Autowired
    CheckStepCache checkStepCache;

    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    LinkHandler linkHandler;

    public void doAllInOne(long checkId) {
    }


    /**
     * 单个设备体检
     * @param checkId
     * @param deviceId
     */
    @Async
    public void doDevice(long checkId, Long deviceId) {
        CheckItem checkItem;
        Device device = deviceHandler.query(deviceId);
        if(device == null){
            log.error("发起单个设备体检，体检设备不存在 deviceId = {}" ,deviceId);
            //不存在就只有一步
            checkItem = new CheckItem(checkId,1);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            checkItem.setResultMsg(String.format("设备不存在，设备id=%s",deviceId));
            checkItemHandler.insert(checkItem);
            return;
        }

        //该设备所有的服务
        List<DeviceService> deviceServiceList;
        //默认就只有一步 就是检查连接
        int totalStep = 1;
        //Minkey 检查网络联通性,暂时只支持内网
        boolean isConnect = deviceStatusHandler.pingTest(device,null);

        if(isConnect){
            deviceServiceList = deviceServiceHandler.query8Device(deviceId);
            //网络联通性 + 硬件情况 + 所有服务个数(没有也加一个)
            totalStep = 1 + 1 + (CollectionUtils.isEmpty(deviceServiceList) ? 1 : deviceServiceList.size());
            //创建检查步数 缓存
            checkStepCache.create(checkId,totalStep);
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_NORMAL);
            checkItem.setResultMsg(String.format("设备[%s]网络状态正常",device.getDeviceName()));

            checkItemHandler.insert(checkItem);
        }else{
            //不通就只有一步
            checkItem = new CheckItem(checkId,1);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            checkItem.setResultMsg(String.format("设备[%s]无法联通，请检查网络状态",device.getDeviceName()));
            checkItemHandler.insert(checkItem);
            return;
        }

        //获取硬件情况，只能获取内网的， 外网需要探针
        DeviceExplorer deviceExplorer = deviceStatusHandler.getDeviceExplorer(deviceId);
        checkItem = checkStepCache.createNextItem(checkId);
        if(deviceExplorer == null){
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("设备[%s]无法获取硬件资源信息",device.getDeviceName()));
        }else{
            checkItem.setCheckId(checkId);
            checkItem.setResultLevel(deviceExplorer.judgeLevel());
            checkItem.setResultMsg(String.format("设备[%s]硬件资源 cpu:%s,mem:%s,disk:%s",
                    device.getDeviceName(),
                    deviceExplorer.getCpu().getUseRateStr(),
                    deviceExplorer.getDisk().getUseRateStr(),
                    deviceExplorer.getMem().getUseRateStr()));
        }
        checkItemHandler.insert(checkItem);

        //检查该设备所有服务
        if(CollectionUtils.isEmpty(deviceServiceList)){
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("设备[%s]没有配置服务!",device.getDeviceName()));
        }else{
            deviceServiceList.forEach(deviceService -> {
                checkDeviceService(checkId,device,deviceService);
            });
        }

    }

    void checkDeviceService(long checkId, Device device, DeviceService deviceService) {
        CheckItem checkItem = checkStepCache.createNextItem(checkId);
        int level = MyLevel.LEVEL_NORMAL;
        boolean isOk= false;
        String msg = "";
        switch (deviceService.getServiceType()){
            //如果是探针，调用探针的check接口
            case DeviceService.SERVICETYPE_DETECTOR :
                isOk = DetectorUtil.check(deviceService.getIp(),deviceService.getConfigData().getPort());
                level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                msg = String.format("设备[%s]探针服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_DB:
                //Minkey db服务检查
                level = MyLevel.LEVEL_NORMAL;
                msg = String.format("设备[%s]数据库服务正常%s",device.getDeviceName());
                break;
            case DeviceService.SERVICETYPE_SNMP:
                SnmpUtil snmpUtil =new SnmpUtil(deviceService.getIp());
                isOk = snmpUtil.testConnect();
                level = MyLevel.LEVEL_NORMAL;
                msg = String.format("设备[%s]SNMP服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_FTP:
                isOk = true;
                level = MyLevel.LEVEL_NORMAL;
                msg = String.format("设备[%s]FTP服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_SSH:
                isOk = true;
                level = MyLevel.LEVEL_NORMAL;
                msg = String.format("设备%s的SSH服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
        }

        checkItem.setResultLevel(level);
        checkItem.setResultMsg(msg);
        checkItemHandler.insert(checkItem);
    }

    public void doTask(long checkId, Long taskId) {
        //需要检查任务数据源 和 数据存放地 两边的情况


        //检查任务进程是否存在

    }

    public void doLink(long checkId, Long linkId) {
        //链路报警主要是设备连通性的报警
        Link link = linkHandler.query(linkId);
        if(link == null){
            return ;
        }
        Set<Long> deviceIds = link.getDeviceIds();
        if(CollectionUtils.isEmpty(deviceIds)){
            return;
        }

        List<Device> deviceList = deviceHandler.query8Ids(deviceIds);
        if(CollectionUtils.isEmpty(deviceList)){
            return;
        }
        Map<Long,Device> deviceMap = deviceList.stream().collect(Collectors.toMap(Device::getDeviceId, Device -> Device ));

        int totalStep = deviceMap.size()+1+1;
        checkStepCache.create(checkId,totalStep);
        CheckItem checkItem = checkStepCache.createNextItem(checkId);
        checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("开始检查链路中所有设备连通情况，该设备个数：%s",deviceMap.size()));
        checkItemHandler.insert(checkItem);

        boolean allisConnect = true;
        for(Long deviceId : deviceIds){
            Device device = deviceMap.get(deviceId);

            boolean isConnect = deviceStatusHandler.pingTest(device,null);

            if(isConnect){
                //创建检查步数 缓存
                checkItem = checkStepCache.createNextItem(checkId);
                checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("设备[%s]网络状态正常",device.getDeviceName()));
                checkItemHandler.insert(checkItem);
            }else{
                //不通就只有一步
                checkItem = checkStepCache.createNextItem(checkId);
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("链路[%s]中设备[%s]无法联通，请检查网络状态",link.getLinkName(),device.getDeviceName()));
                checkItemHandler.insert(checkItem);
                allisConnect = false;
            }
        }


        if(allisConnect){
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("链路[%s]网络状态正常",link.getLinkName()));
            checkItemHandler.insert(checkItem);
        }else{
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("链路[%s]中有断线设备！",link.getLinkName()));
            checkItemHandler.insert(checkItem);
        }
    }
}
