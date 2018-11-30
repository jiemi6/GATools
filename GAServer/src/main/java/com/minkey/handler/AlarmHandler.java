package com.minkey.handler;

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
import com.minkey.dto.SeachParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * 扫描所有设备状态
     */
    @Scheduled(cron = "0 */1 * * * ?")
//    @Scheduled(cron = "0/30 * * * * ?")
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
                alarmLog.setType(AlarmEnum.wangluobutong);
                alarmLog.setMsg(String.format("%s[%s]网络无法连接!", device.getDeviceName(), device.getIp()));

                //网络不同，更新设备级别为错误
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
            alarmLog.setType(AlarmEnum.shebeifuwu);
            alarmLog.setMsg(String.format("%s[%s]硬件性能指标无法获取，请检查snmp设置!", device.getDeviceName(), device.getIp()));

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
                alarmLog.setMsg(String.format("%s[%s]硬件性能指标告警! %s", device.getDeviceName(), device.getIp(), deviceExplorer.showString()));

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
                boolean isOk = deviceServiceCheckManager.checkDeviceService(device, deviceService, detectorService);
                if (!isOk) {
                    alarmLog = new AlarmLog();
                    alarmLog.setBid(deviceId);
                    alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                    alarmLog.setLevel(MyLevel.LEVEL_ERROR);
                    alarmLog.setType(AlarmEnum.shebeifuwu);
                    alarmLog.setMsg(String.format("%s[%s]%s服务异常", device.getDeviceName(),device.getIp(), deviceService.typeNameStr()));
                    explorerLogs.add(alarmLog);
                }
            }
            return explorerLogs;
        }
    }


    /**
     * 链路告警扫描，只扫描链路设备的连通性
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void link() {
        //获取所有链路
        Map<Long, Link> allLink = deviceCache.getAllLinkMap();
        if (CollectionUtils.isEmpty(allLink)) {
            return;
        }

        //得到所有连接上的设备
        Set<Long> okSet = deviceConnectCache.getOkSet();

        Set<AlarmLog> linkLogs = new HashSet<>(allLink.size());
        AlarmLog alarmLog;
        Set<Long> notOkDeviceIds;
        Set<Long> okDeviceIds;
        Set<String> allDeviceName;
        for (Link link : allLink.values()) {
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
            alarmLog.setType(AlarmEnum.wangluobutong);

            allDeviceName = new HashSet<>(notOkDeviceIds.size());
            //拼装名称
            for (Long deviceId : notOkDeviceIds) {
                Device device = deviceCache.getDevice(deviceId);
                if (device == null) {
                    continue;
                }
                allDeviceName.add(device.getDeviceName());
            }
            alarmLog.setMsg(String.format("[%s]中有%s个设备掉线，设备名称为%s", link.getLinkName(), notOkDeviceIds.size(), StringUtils.join(allDeviceName), ","));
            linkLogs.add(alarmLog);

            Set<Device>  allDetector = deviceCache.getDetector8linkId(link.getLinkId());

            if(CollectionUtils.isEmpty(allDetector)){
                alarmLog = new AlarmLog();
                alarmLog.setBid(link.getLinkId());
                alarmLog.setbType(AlarmLog.BTYPE_LINK);
                alarmLog.setLevel(MyLevel.LEVEL_WARN);
                alarmLog.setType(AlarmEnum.no_detector);
                alarmLog.setMsg(String.format("[%s]没有配置探针，无法探测外网设备!", link.getLinkName()));
                linkLogs.add(alarmLog);
            }

        }
        alarmLogHandler.insertAll(linkLogs);
    }

    /**
     * 任务告警扫描
     * 扫描任务是否存活
     */
    @Scheduled(cron = "0 */1 * * * ?")
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
            level = checkTask(task);

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

    private int checkTask(Task task) {
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
            alarmLog.setMsg(String.format("%s没有配置数据源",task.getTaskName()));
            taskAlarm.add(alarmLog);
        } else {
            DeviceService detectorService = deviceCache.getDetectorService8linkId(task.getLinkId());

            String fromSourceId = taskSource.getFromResourceId();
            String toSourceId = taskSource.getToResourceId();

            Source fromSource = sourceHandler.query(task.getLinkId(), fromSourceId);
            boolean isConnect = sourceCheckHandler.testSource(fromSource, detectorService);
            alarmLog = build(task, fromSource, isConnect);
            taskAlarm.add(alarmLog);

            Source toSource = sourceHandler.query(task.getLinkId(), toSourceId);
            isConnect = sourceCheckHandler.testSource(toSource, detectorService);
            alarmLog = build(task, toSource, isConnect);
            taskAlarm.add(alarmLog);

            //Minkey 检查任务进程是否存在
        }

        int level = MyLevel.LEVEL_NORMAL;
        for (AlarmLog tempLog : taskAlarm) {
            if (tempLog == null) {
                continue;
            }
            if (tempLog.getLevel() > level) {
                level = tempLog.getLevel();
            }
        }
        return level;
    }

    private AlarmLog build(Task task, Source fromSource, boolean isConnect) {
        if (isConnect) {
            return null;
        }
        String msg = String.format("[%s]%s%s数据源[%s]连接失败",
                task.getTaskName(),
                fromSource.getSourceType(),
                fromSource.isNetAreaIn() ? "内网" : "外网",
                fromSource.getSname());

        AlarmLog alarmLog = new AlarmLog();
        alarmLog.setBid(task.getTaskId())
                .setbType(AlarmLog.BTYPE_TASK)
                .setLevel(MyLevel.LEVEL_ERROR)
                .setType(AlarmEnum.wangluobutong)
                .setMsg(msg);
        return alarmLog;
    }


}