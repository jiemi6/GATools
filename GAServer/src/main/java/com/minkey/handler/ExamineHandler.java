package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.CheckStepCache;
import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceExplorerCache;
import com.minkey.contants.DeviceType;
import com.minkey.contants.MyLevel;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.DeviceHandler;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.DeviceExplorer;
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
    DeviceConnectHandler deviceConnectHandler;

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

    @Autowired
    TaskExamineHandler taskExamineHandler;

    @Autowired
    TaskHandler taskHandler;

    @Autowired
    DeviceServiceCheckManager deviceServiceCheckManager;


    @Async
    public void doAllInOne(long checkId) {
        Map<Long,Link> allLink = deviceCache.getAllLinkMap();
        for(Link link : allLink.values()){
            doLink(checkId,link);
        }

        for(Link link : allLink.values()){
            Set<Long> deviceIds = link.getDeviceIds();
            if(CollectionUtils.isEmpty(deviceIds)){
                continue;
            }
            for(Long deviceId: deviceIds){
                Device device = deviceCache.getDevice(deviceId);
                if(device == null){
                    continue;
                }
                doDevice(checkId,device);
            }
        }

        List<Task> allTask = taskHandler.queryAll();
        if(!CollectionUtils.isEmpty(allTask)){
            for(Task task : allTask){
                taskExamineHandler.doTask(checkId,task);
            }
        }
    }


    /**
     * 异步执行
     * @param checkId
     * @param device
     */
    @Async
    public void doDeviceAsync(long checkId, Device device) {
        doDevice(checkId,device);
    }
    /**
     * 单个设备体检
     * @param checkId
     * @param device
     */
    public void doDevice(long checkId, Device device) {
        if (device.getDeviceType() == DeviceType.floder) {
            //设备如果是文件夹，不做处理
            return;
        }
        CheckItem checkItem;

        //该设备所有的服务
        Set<DeviceService> deviceServiceList;
        //默认就只有一步 就是检查连接
        int totalStep = 1;
        // 检查网络联通性
//        boolean isConnect = deviceConnectHandler.pingConnectTest(device);

        String connectStr = deviceConnectHandler.ping(device);
        int connect = Integer.parseInt(connectStr.split("/")[0]);
        int total = Integer.parseInt(connectStr.split("/")[1]);
        //丢包率
        int loseRadio = Math.round(((total - connect) *100 ) / total);

        //相等,则显示正常
        if(connect == total){
            deviceServiceList = deviceCache.getDeviceService8DeviceId(device.getDeviceId());
            //网络联通性 + 硬件情况 + 所有服务个数(没有也加一个)
            totalStep = 1 + 1 + (CollectionUtils.isEmpty(deviceServiceList) ? 1 : deviceServiceList.size());
            //创建检查步数 缓存
            checkStepCache.create(checkId,totalStep);
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_NORMAL);
            checkItem.setResultMsg(String.format("<%s设备>网络连接状态正常,<br/>网络ping包%s个，%s个包丢失，丢包率为%s%",
                    device.getDeviceName(),total,total-connect,loseRadio));

            checkItemHandler.insert(checkItem);
        }else if(connect == 0){
            //不通就只有一步
            checkItem = new CheckItem(checkId,1);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            checkItem.setResultMsg(String.format("<%s设备>网络无法连接,请检查网络连通性,<br/>网络ping包%s个，%s个包丢失，丢包率为%s%",
                    device.getDeviceName(),total,total-connect,loseRadio));

            checkItemHandler.insert(checkItem);
            return;
        }else{
            //不通就只有一步
            checkItem = new CheckItem(checkId,1);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            checkItem.setResultMsg(String.format("<%s设备>网络连接状态不稳定,<br/>网络ping包%s个，%s个包丢失，丢包率为%s%,请检查网络稳定性",
                    device.getDeviceName(),total,total-connect,loseRadio));

            checkItemHandler.insert(checkItem);
            return;
        }

        //从缓存中直接获取硬件情况
        DeviceExplorer deviceExplorer = deviceExplorerCache.getDeviceExplorer(device.getDeviceId());
        checkItem = checkStepCache.createNextItem(checkId);
        if(deviceExplorer == null){
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("<%s设备>无法获取硬件资源信息",device.getDeviceName()));
        }else{
            checkItem.setCheckId(checkId);
            checkItem.setResultLevel(deviceExplorer.judgeLevel());
            checkItem.setResultMsg(String.format("<%s设备>硬件资源%s<%s>",
                    device.getDeviceName(),checkItem.getResultLevel() == MyLevel.LEVEL_NORMAL?"正常":"异常",deviceExplorer.showString()));
        }
        checkItemHandler.insert(checkItem);


        //找到探针服务
        DeviceService detectorService = deviceCache.getOneDetectorServer8DeviceId(device.getDeviceId());
        //如果设备不是探针，而且是外网机器，而且得到没有一个探针，则不用检查了，直接认为探测不到。
        if(!device.isDetector() && !device.isNetAreaIn() && detectorService == null){
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("<%s设备>属于外网设备，且链路没有配置可用的探针服务，无法探测该设备。",device.getDeviceName()));
            checkItemHandler.insert(checkItem);
            return;
        }

        //检查该设备所有服务
        if(CollectionUtils.isEmpty(deviceServiceList)){
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_WARN);
            checkItem.setResultMsg(String.format("<%s设备>没有配置服务!",device.getDeviceName()));
            checkItemHandler.insert(checkItem);
        }else{
            for(DeviceService deviceService :deviceServiceList){
                JSONObject jsonObject = deviceServiceCheckManager.checkDeviceService(device,deviceService,detectorService);
                boolean isOk = jsonObject.getBooleanValue("isOk");
                String msg = jsonObject.getString("msg");

                int level = isOk ? MyLevel.LEVEL_NORMAL : MyLevel.LEVEL_ERROR;
                checkItem = checkStepCache.createNextItem(checkId);
                checkItem.setResultLevel(level);
                checkItem.setResultMsg(String.format("<%s设备><%s服务>%s<br/>%s",
                        device.getDeviceName(), device.getDeviceName(),isOk ?"正常":"异常",deviceService.typeNameStr(),msg));
                checkItemHandler.insert(checkItem);
            }
        }
    }


    /**
     * 异步执行
     * @param checkId
     * @param link
     */
    @Async
    public void doLinkAynsc(long checkId, Link link) {
        doLink(checkId,link);
    }


    public void doLink(long checkId, Link link) {
        //链路报警主要是设备连通性的报警
        Set<Long> deviceIds = link.getDeviceIds();
        if(CollectionUtils.isEmpty(deviceIds)){
            return;
        }

        Map<Long,Device> deviceMap = deviceCache.getDevice8Ids(deviceIds);

        int totalStep = deviceMap.size()+1+1;
        checkStepCache.create(checkId,totalStep);
        CheckItem checkItem = checkStepCache.createNextItem(checkId);
        checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("开始检查链路[%s]中所有设备连通情况，该设备个数：%s",link.getLinkName(),deviceMap.size()));
        checkItemHandler.insert(checkItem);

        int notConnectNum = 0;
        boolean allisConnect = true;
        for(Long deviceId : deviceMap.keySet()){
            Device device = deviceMap.get(deviceId);

            boolean isConnect = deviceConnectHandler.pingConnectTest(device);

            if(isConnect){
                //创建检查步数 缓存
                checkItem = checkStepCache.createNextItem(checkId);
                checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("<%s设备>网络状态正常",device.getDeviceName()));
                checkItemHandler.insert(checkItem);
            }else{
                //不通就只有一步
                checkItem = checkStepCache.createNextItem(checkId);
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("链路[%s]中<%s设备>无法联通，请检查网络状态",link.getLinkName(),device.getDeviceName()));
                checkItemHandler.insert(checkItem);
                allisConnect = false;
                notConnectNum++;
            }
        }


        if(allisConnect){
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("链路[%s]所有设备网络状态正常",link.getLinkName()));
            checkItemHandler.insert(checkItem);
        }else{
            checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("链路[%s]中有[%s]台设备无法连接！",link.getLinkName(),notConnectNum));
            checkItemHandler.insert(checkItem);
        }
    }

}
