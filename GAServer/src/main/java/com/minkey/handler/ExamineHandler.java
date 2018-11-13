package com.minkey.handler;

import com.minkey.cache.CheckStepCache;
import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceExplorerCache;
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
import com.minkey.dto.*;
import com.minkey.executer.SSHExecuter;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
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
 * 体检类，发起体检并保存检查结果
 */
@Slf4j
@Component
public class ExamineHandler {
    @Autowired
    CheckItemHandler checkItemHandler;

    @Autowired
    DeviceCache deviceCache;

    @Autowired
    DeviceStatusHandler deviceStatusHandler;

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    @Autowired
    DeviceExplorerCache deviceExplorerCache;

    @Autowired
    CheckStepCache checkStepCache;

    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;

    @Autowired
    FTPUtil ftpUtil;


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

        //找到探针服务
        DeviceService detectorService = deviceCache.getOneDetectorServer8DeviceId(deviceId);
        //该设备所有的服务
        List<DeviceService> deviceServiceList;
        //默认就只有一步 就是检查连接
        int totalStep = 1;
        //Minkey 检查网络联通性,暂时只支持内网
        boolean isConnect = deviceStatusHandler.pingTest(device,detectorService);

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

        //从缓存中直接获取硬件情况
        DeviceExplorer deviceExplorer = deviceExplorerCache.getDeviceExplorer(deviceId);
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

        //如果设备不是探针，而且是外网机器，而且得到没有一个探针，则不用检查了，直接认为探测不到。
        if(!device.isDetector() && !device.isNetAreaIn() && detectorService == null){
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("设备[%s]属于外网设备，并且没有找到可用的探针服务，无法得知设备信息。",device.getDeviceName()));
            checkItemHandler.insert(checkItem);
            return;
        }

        //检查该设备所有服务
        if(CollectionUtils.isEmpty(deviceServiceList)){
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("设备[%s]没有配置服务!",device.getDeviceName()));
            checkItemHandler.insert(checkItem);
        }else{
            deviceServiceList.forEach(deviceService -> {
                checkDeviceService(checkId,device,deviceService,detectorService);
            });
        }

    }

    void checkDeviceService(long checkId, Device device, DeviceService deviceService,DeviceService detectorService) {
        CheckItem checkItem = checkStepCache.createNextItem(checkId);
        int level = MyLevel.LEVEL_NORMAL;
        BaseConfigData baseConfigData;
        boolean isOk;
        String msg = "";
        switch (deviceService.getServiceType()){
            //如果是探针，调用探针的check接口
            case DeviceService.SERVICETYPE_DETECTOR :
                isOk = DetectorUtil.check(deviceService.getIp(),deviceService.getConfigData().getPort());
                level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                msg = String.format("设备[%s]探针服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_DB:
                DBConfigData dbConfigData = (DBConfigData) deviceService.getConfigData();
                if(device.isNetAreaIn()){
                    isOk = dynamicDB.testDB(dbConfigData);
                }else{
                    if(detectorService == null){
                        isOk = false;
                    }else{
                        isOk = DetectorUtil.testDB(detectorService.getIp(),detectorService.getConfigData().getPort(),dbConfigData);
                    }
                }
                level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                msg = String.format("设备[%s]数据库服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_SNMP:
                SnmpConfigData snmpConfigData = (SnmpConfigData) deviceService.getConfigData();
                SnmpUtil snmpUtil =new SnmpUtil(snmpConfigData);
                if(device.isNetAreaIn()){
                    isOk = snmpUtil.testConnect();
                }else{
                    if(detectorService == null){
                        isOk = false;
                    }else{
                        isOk = DetectorUtil.testSNMP(detectorService.getIp(),detectorService.getConfigData().getPort(),snmpConfigData);
                    }
                }

                level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                msg = String.format("设备[%s]SNMP服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_FTP:
                FTPConfigData ftpConfigData = (FTPConfigData) deviceService.getConfigData();
                if(device.isNetAreaIn()){
                    isOk = ftpUtil.testFTPConnect(ftpConfigData);
                }else{
                    if(detectorService == null){
                        isOk = false;
                    }else{
                        isOk = DetectorUtil.testFTP(detectorService.getIp(),detectorService.getConfigData().getPort(), ftpConfigData);
                    }
                }

                level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                msg = String.format("设备[%s]FTP服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
            case DeviceService.SERVICETYPE_SSH:
                baseConfigData = deviceService.getConfigData();
                if(device.isNetAreaIn()){
                    isOk = SSHExecuter.testConnect(baseConfigData);
                }else{
                    if(detectorService == null){
                        isOk = false;
                    }else{
                        isOk = DetectorUtil.testSSH(detectorService.getIp(),detectorService.getConfigData().getPort(), baseConfigData);
                    }
                }

                level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                msg = String.format("设备%s的SSH服务%s",device.getDeviceName(),isOk ? "正常" : "异常，连接失败");
                break;
        }

        checkItem.setResultLevel(level);
        checkItem.setResultMsg(msg);
        checkItemHandler.insert(checkItem);
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

    public void doTask(long checkId, Long taskId) {
    }
}
