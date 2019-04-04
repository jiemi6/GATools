package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.cache.DeviceExplorerCache;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.DeviceType;
import com.minkey.contants.MyLevel;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.SourceHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.TaskSourceHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.DeviceExplorer;
import com.minkey.dto.SnmpConfigData;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 告警处理，告警数据来源
 */
@Slf4j
@Component
public class AlarmHandler {
    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    DeviceCache deviceCache;

    @Autowired
    DeviceConnectCache deviceConnectCache;

    @Autowired
    DeviceExplorerCache deviceExplorerCache;

    @Autowired
    TaskHandler taskHandler;

    @Autowired
    TaskSourceHandler taskSourceHandler;
    @Autowired
    SourceHandler sourceHandler;

    @Autowired
    SourceCheckHandler sourceCheckHandler;

    @Autowired
    DeviceServiceCheckManager deviceServiceCheckManager;

    @Autowired
    SnmpExploreHandler snmpExploreHandler;


    @Value("${system.debug:false}")
    private boolean isDebug;

    /**
     * 扫描所有设备状态
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void scanDeviceStatus() {
        if(isDebug){
            return ;
        }

        try {
            deviceStatus();
        } catch (Exception e) {
            log.error("扫描设备状态异常",e);
        }
    }

    public void deviceStatus() {
        //获取所有设备
        Map<Long, Device> allDevice = deviceCache.allDevice();

        if (CollectionUtils.isEmpty(allDevice)) {
            return;
        }
        //得到所有连接上的设备
        Set<Long> okSet = deviceConnectCache.getOkSet();

        //拷贝一份所有设备id,不能修改原来set中的值
        Set<Long> notOk = new HashSet<>(allDevice.keySet());
        //得到所有不ok的设备进行告警
        notOk.remove(okSet);

        Device device;
        AlarmLog alarmLog;
        if (!CollectionUtils.isEmpty(notOk)) {
            Set<AlarmLog> notOKLogs = new HashSet<>(notOk.size());
            for (Long deviceId : notOk) {
                device = allDevice.get(deviceId);
                if (device == null || device.getDeviceType() == DeviceType.floder) {
                    continue;
                }
                alarmLog = new AlarmLog();
                alarmLog.setBid(deviceId);
                alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                alarmLog.setLevel(MyLevel.LEVEL_ERROR);
                alarmLog.setType(AlarmEnum.ip_notConnect);
                alarmLog.setMsg(String.format("Ping <%s设备> <%s> 网络无法连接!", device.getDeviceName(), device.getIp()));

                //网络不通，更新设备级别为错误
                deviceCache.updateDeviceLevel(deviceId, MyLevel.LEVEL_ERROR);

                notOKLogs.add(alarmLog);
            }
            alarmLogHandler.insertAll(notOKLogs);
        }

        if (!CollectionUtils.isEmpty(okSet)) {
            Set<AlarmLog> explorerLogs = new HashSet<>(okSet.size());
            //能连上的获取性能指标
            for (Long deviceId : okSet) {
                device = allDevice.get(deviceId);
                alarmLog = checkExplorer(device);

                if (alarmLog != null) {
                    explorerLogs.add(alarmLog);
                }

                explorerLogs.addAll(checkDeviceService(device));
            }

            alarmLogHandler.insertAll(explorerLogs);
        }
    }

    private AlarmLog checkExplorer(Device device) {
        if (device == null) {
            return null;
        }
        long deviceId = device.getDeviceId();
        if (device.getDeviceType() == DeviceType.floder) {
            //文件夹，永远为正常
            deviceCache.updateDeviceLevel(deviceId, MyLevel.LEVEL_NORMAL);
            return null;
        }
        DeviceExplorer deviceExplorer = deviceExplorerCache.getDeviceExplorer(deviceId);

        AlarmLog alarmLog;
        if (deviceExplorer == null) {
            alarmLog = new AlarmLog();
            alarmLog.setBid(deviceId);
            alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
            alarmLog.setLevel(MyLevel.LEVEL_WARN);
            alarmLog.setType(AlarmEnum.no_snmpservice);
            alarmLog.setMsg(String.format("<%s设备>硬件性能指标无法获取，请检查snmp设置!", device.getDeviceName()));

            //没有性能指标，更新设备级别为正常
            deviceCache.updateDeviceLevel(deviceId, MyLevel.LEVEL_NORMAL);
            return alarmLog;
        } else {
            //如果是警告,性能只有警告没有错误
            if (MyLevel.LEVEL_WARN == deviceExplorer.judgeLevel()) {
                alarmLog = new AlarmLog();
                alarmLog.setBid(deviceId);
                alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                alarmLog.setLevel(MyLevel.LEVEL_WARN);
                alarmLog.setType(AlarmEnum.shebeixingneng);
                alarmLog.setMsg(String.format("<%s设备>硬件性能指标告警! %s", device.getDeviceName(), deviceExplorer.showString()));

                //更新设备级别为警告
                deviceCache.updateDeviceLevel(deviceId, MyLevel.LEVEL_WARN);
                return alarmLog;
            } else {
                //更新设备级别为正常
                deviceCache.updateDeviceLevel(deviceId, MyLevel.LEVEL_NORMAL);
                return null;
            }
        }

    }

    private Set<AlarmLog> checkDeviceService(Device device) {
        AlarmLog alarmLog;

        Set<AlarmLog> explorerLogs = new HashSet<>();
        long deviceId = device.getDeviceId();
        //找到探针服务
        DeviceService detectorService = deviceCache.getOneDetectorServer8DeviceId(device.getDeviceId());
        //如果设备不是探针，而且是外网机器，而且得到没有一个探针，则不用检查了，直接认为探测不到。
        if (!device.isDetector() && !device.isNetAreaIn() && detectorService == null) {
            //属于外网设备，并且没有配置可用的探针服务，无法探测该设备。
            return explorerLogs;
        }

        Set<DeviceService> deviceServiceList = deviceCache.getDeviceService8DeviceId(deviceId);
        //检查该设备所有服务
        if (CollectionUtils.isEmpty(deviceServiceList)) {
            //没有配置服务，不报警
            return explorerLogs;
        } else {
            for (DeviceService deviceService : deviceServiceList) {
                JSONObject jsonObject = deviceServiceCheckManager.checkDeviceService(device,deviceService,detectorService);
                boolean isOk = jsonObject.getBooleanValue("isOk");
                if (!isOk) {
                    String msg = jsonObject.getString("msg");
                    alarmLog = new AlarmLog();
                    alarmLog.setBid(deviceId);
                    alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                    alarmLog.setLevel(MyLevel.LEVEL_ERROR);
                    alarmLog.setType(AlarmEnum.shebeifuwu);
                    alarmLog.setMsg( String.format("<%s设备><%s服务>%s<br/>%s",
                            device.getDeviceName(), device.getDeviceName(),isOk ?"正常":"异常",deviceService.typeNameStr(),msg));
                    explorerLogs.add(alarmLog);
                }
            }
            return explorerLogs;
        }
    }


    /**
     * 链路告警扫描，只扫描链路设备的连通性
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void link() {
        //获取所有链路
        Map<Long, Link> allLink = deviceCache.getAllLinkMap();
        if (CollectionUtils.isEmpty(allLink)) {
            return;
        }

        //得到所有连接上的设备
        Set<Long> okSet = deviceConnectCache.getOkSet();

        AlarmLog alarmLog;
        Set<Long> notOkDeviceIds;
        Set<Long> okDeviceIds;
        Set<String> allDeviceName;
        for (Link link : allLink.values()) {
            Set<AlarmLog> linkLogs = new HashSet<>(allLink.size());
            okDeviceIds = link.getDeviceIds();
            if (CollectionUtils.isEmpty(okDeviceIds)) {
                continue;
            }

            //拷贝一份id，不能操作原来的set
            notOkDeviceIds = new HashSet<>(okDeviceIds);
            //移除掉所有能连上的设备,剩下的就是连不上的设备
            notOkDeviceIds.removeAll(okSet);

            //如果为空，证明全部连上了
            if (CollectionUtils.isEmpty(notOkDeviceIds)) {
                continue;
            }

            alarmLog = new AlarmLog();
            alarmLog.setBid(link.getLinkId());
            alarmLog.setbType(AlarmLog.BTYPE_LINK);
            alarmLog.setLevel(MyLevel.LEVEL_ERROR);
            alarmLog.setType(AlarmEnum.ip_notConnect);

            allDeviceName = new HashSet<>(notOkDeviceIds.size());
            //拼装名称
            for (Long deviceId : notOkDeviceIds) {
                Device device = deviceCache.getDevice(deviceId);
                if (device == null) {
                    continue;
                }
                allDeviceName.add(device.getDeviceName());
            }
            alarmLog.setMsg(String.format("<%s链路>中有%s个设备掉线，分别为<%s>", link.getLinkName(), notOkDeviceIds.size(), StringUtils.join(allDeviceName), ","));
            linkLogs.add(alarmLog);

            Set<Device>  allDetector = deviceCache.getDetector8linkId(link.getLinkId());

            if(CollectionUtils.isEmpty(allDetector)){
                alarmLog = new AlarmLog();
                alarmLog.setBid(link.getLinkId());
                alarmLog.setbType(AlarmLog.BTYPE_LINK);
                alarmLog.setLevel(MyLevel.LEVEL_WARN);
                alarmLog.setType(AlarmEnum.no_detector);
                alarmLog.setMsg(String.format("<%s链路>没有配置探针，无法探测外网设备!", link.getLinkName()));
                linkLogs.add(alarmLog);
            }

            Map<Long, Device> deviceMap= deviceCache.getDevice8Ids(link.getDeviceIds());
            Device tas = null;
            Device uas = null;
            for(Device device: deviceMap.values()){
                //找到TAS
                if(device.getDeviceType() == DeviceType.xinrenduanshujujiaohuanxitong){
                    tas = device;
                }else if(device.getDeviceType() == DeviceType.feixinrenduanshujujiaohuanxitong){
                    uas = device;
                }
            }

            //没有tas设备
            if(tas == null) {
                alarmLog = new AlarmLog();
                alarmLog.setBid(link.getLinkId())
                        .setbType(AlarmLog.BTYPE_LINK)
                        .setLevel(MyLevel.LEVEL_ERROR)
                        .setType(AlarmEnum.no_TAS)
                        .setMsg(String.format("<%s链路>没有配置TAS设备！", link.getLinkName()));
                linkLogs.add(alarmLog);
            }
            //没有tas设备
            if(uas == null) {
                alarmLog = new AlarmLog();
                alarmLog.setBid(link.getLinkId())
                        .setbType(AlarmLog.BTYPE_LINK)
                        .setLevel(MyLevel.LEVEL_ERROR)
                        .setType(AlarmEnum.no_UAS)
                        .setMsg(String.format("<%s链路>没有配置UAS设备！", link.getLinkName()));
                linkLogs.add(alarmLog);
            }
            alarmLogHandler.insertAll(linkLogs);
        }
    }

    /**
     * 任务告警扫描
     * 扫描任务是否存活
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void task() {

        //获取所有任务
        List<Task> allTask = taskHandler.queryAll();

        if (CollectionUtils.isEmpty(allTask)) {
            return;
        }

        //所有task当前的状态
        Map<Integer, Set<Long>> taskLevel = new HashMap<>();
        int level;
        for (Task task : allTask) {
            //检查task
            try {
                level = checkTask(task);
            } catch (SystemException e) {
                log.error(e.getMessage());
                continue;
            }

            if (taskLevel.get(level) == null) {
                Set<Long> set = new HashSet<>();
                set.add(task.getTaskId());
                taskLevel.put(level, set);
            } else {
                taskLevel.get(level).add(task.getTaskId());
            }
        }

        for (Integer lev : taskLevel.keySet()) {
            taskHandler.updateLevel(taskLevel.get(lev), lev);
        }

    }

    private int checkTask(Task task)throws SystemException {
        String taskTargetId = task.getTargetTaskId();

        Set<AlarmLog> taskAlarm = new HashSet<>();
        TaskSource taskSource = taskSourceHandler.query(task.getLinkId(), taskTargetId);
        AlarmLog alarmLog;
        if (taskSource == null) {
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId());
            alarmLog.setbType(AlarmLog.BTYPE_TASK);
            alarmLog.setLevel(MyLevel.LEVEL_ERROR);
            alarmLog.setType(AlarmEnum.no_source);
            alarmLog.setMsg(String.format("<%s任务>没有配置数据源",task.getTaskName()));
            taskAlarm.add(alarmLog);
        } else {
            DeviceService detectorService = deviceCache.getDetectorService8linkId(task.getLinkId());

            String fromSourceId = taskSource.getFromResourceId();
            String toSourceId = taskSource.getToResourceId();

            //检查数据来源方
            Source fromSource = sourceHandler.query(task.getLinkId(), fromSourceId);
            Set<AlarmLog> alarmLogSet = sourceCheckHandler.testSource(task, fromSource, detectorService);
            taskAlarm.addAll(alarmLogSet);

            //检查数据出口方
            Source toSource = sourceHandler.query(task.getLinkId(), toSourceId);
            alarmLogSet = sourceCheckHandler.testSource(task, toSource, detectorService);
            taskAlarm.addAll(alarmLogSet);

            //检查任务进程是否存在
            alarmLogSet = checkTaskProcess(task,detectorService);
            taskAlarm.addAll(alarmLogSet);
        }


        alarmLogHandler.insertAll(taskAlarm);

        int level = MyLevel.LEVEL_NORMAL;
        for (AlarmLog alarmLogTemp : taskAlarm) {
            if (alarmLogTemp == null) {
                continue;
            }
            if (alarmLogTemp.getLevel() > level) {
                level = alarmLogTemp.getLevel();
            }
        }
        return level;
    }

    /**
     * 检查UAS 和 TAS上任务进程是否都存在
     */
    private Set<AlarmLog> checkTaskProcess(Task task, DeviceService detectorService){
        Link link = deviceCache.getLink8Id(task.getLinkId());
        Map<Long, Device> deviceMap= deviceCache.getDevice8Ids(link.getDeviceIds());
        Device tas = null;
        Device uas = null;
        for(Device device: deviceMap.values()){
            //找到TAS
            if(device.getDeviceType() == DeviceType.xinrenduanshujujiaohuanxitong){
                tas = device;
            }else if(device.getDeviceType() == DeviceType.feixinrenduanshujujiaohuanxitong){
                uas = device;
            }
        }

        Set<AlarmLog> alarmLogs = new HashSet<>();
        AlarmLog alarmLog = null;
        //如果设备不存在
        if(tas == null){
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.no_TAS)
                    .setMsg(String.format("<%s>所在链路没有没有配置%s！",task.getTaskName(),"TAS"));
        }else{
            //tas在内网
            alarmLog = checkTaskProcess(task,tas,null,"TAS");
        }
        alarmLogs.add(alarmLog);

        //如果设备不存在
        if(uas == null){
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.no_UAS)
                    .setMsg(String.format("<%s>所在链路没有没有配置%s！",task.getTaskName(),"UAS"));
        }else{
            //检查uas，uas在外网
            alarmLog = checkTaskProcess(task,uas,detectorService,"UAS");
        }
        alarmLogs.add(alarmLog);

        return alarmLogs;
    }

    private AlarmLog checkTaskProcess(Task task, Device device, DeviceService detectorService, String deviceType){
        AlarmLog alarmLog = null;
        DeviceService snmpDeviceService = null;

        Set<DeviceService> deviceServiceSet = deviceCache.getDeviceService8DeviceId(device.getDeviceId());
        if(!CollectionUtils.isEmpty(deviceServiceSet)){
            for (DeviceService deviceService : deviceServiceSet) {
                //找snmp服务
                if(deviceService.getServiceType() == DeviceService.SERVICETYPE_SNMP){
                    snmpDeviceService = deviceService;
                    break;
                }
            }
        }

        //没有配置snmp服务
        if(snmpDeviceService == null){
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.no_snmpservice)
                    .setMsg(String.format("%s设备<%s>没有配置SNMP服务！",deviceType,device.getDeviceName()));
        }else{
            SnmpConfigData snmpConfigData = (SnmpConfigData) snmpDeviceService.getConfigData();

            boolean isProcessExist = snmpExploreHandler.checkProcess(task.getTargetTaskId(),snmpConfigData,detectorService);

            if(!isProcessExist){
                alarmLog = new AlarmLog();
                alarmLog.setBid(task.getTaskId())
                        .setbType(AlarmLog.BTYPE_TASK)
                        .setLevel(MyLevel.LEVEL_ERROR)
                        .setType(AlarmEnum.no_taskProcess)
                        .setMsg(String.format("%s设备<%s>上任务进程<%s>不存在！",deviceType,device.getDeviceName(),task.getTaskName()));
            }
        }

        return alarmLog;
    }

}