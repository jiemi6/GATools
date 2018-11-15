package com.minkey.handler;

import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.cache.DeviceExplorerCache;
import com.minkey.contants.AlarmType;
import com.minkey.contants.DeviceType;
import com.minkey.contants.MyLevel;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.SourceHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.TaskSourceHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.DeviceExplorer;
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

    /**
     * 扫描所有设备状态
     *
     */
    @Scheduled(cron = "0 */1 * * * ?")
//    @Scheduled(cron = "0/30 * * * * ?")
    public void deviceStatus(){
        //获取所有设备
        Map<Long, Device> allDevice = deviceCache.allDevice();

        if(CollectionUtils.isEmpty(allDevice)){
            return;
        }
        Set<Long> allIds = allDevice.keySet();

        //得到所有连接上的设备
        Set<Long> okSet = deviceConnectCache.getOkSet();
        allIds.removeAll(okSet);

        //得到所有不ok的设备进行告警
        Set<Long> notOk = allIds;

        Device device;
        AlarmLog alarmLog;
        if(!CollectionUtils.isEmpty(notOk)){
            Set<AlarmLog> notOKLogs = new HashSet<>(notOk.size());
            for (Long deviceId : notOk) {
                device = allDevice.get(deviceId);
                if(device.getDeviceType() == DeviceType.floder) {
                    continue;
                }
                alarmLog = new AlarmLog();
                alarmLog.setBid(deviceId);
                alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                alarmLog.setLevel(MyLevel.LEVEL_ERROR);
                alarmLog.setType(AlarmType.wangluobutong);
                alarmLog.setMsg(String.format("设备%s[%s]网络无法连接!",device.getDeviceName(),device.getIp()));

                notOKLogs.add(alarmLog);
            }
            alarmLogHandler.insertAll(notOKLogs);
        }

        if(!CollectionUtils.isEmpty(okSet)){
            DeviceExplorer deviceExplorer;
            Set<AlarmLog> explorerLogs = new HashSet<>(okSet.size());
            Map<Long,DeviceExplorer> allDeviceExplorer = deviceExplorerCache.getDeviceExplorerMap(okSet);
            //能连上的获取性能指标
            for (Long deviceId : okSet) {
                device = allDevice.get(deviceId);
                if(device == null){
                    continue;
                }
                if(device.getDeviceType() == DeviceType.floder) {
                    continue;
                }
                deviceExplorer = allDeviceExplorer.get(deviceId);
                if(deviceExplorer == null){
                    alarmLog = new AlarmLog();
                    alarmLog.setBid(deviceId);
                    alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                    alarmLog.setLevel(MyLevel.LEVEL_WARN);
                    alarmLog.setType(AlarmType.shebeixingneng);
                    alarmLog.setMsg(String.format("设备%s[%s]硬件性能指标无法获取，请检查snmp设置!",device.getDeviceName(),device.getIp()));

                    explorerLogs.add(alarmLog);
                }else{
                    //如果是警告,性能只有警告没有错误
                    if(MyLevel.LEVEL_WARN == deviceExplorer.judgeLevel()){
                        alarmLog = new AlarmLog();
                        alarmLog.setBid(deviceId);
                        alarmLog.setbType(AlarmLog.BTYPE_DEVICE);
                        alarmLog.setLevel(MyLevel.LEVEL_WARN);
                        alarmLog.setType(AlarmType.shebeixingneng);
                        alarmLog.setMsg(String.format("设备%s[%s]硬件性能指标告警! %s",device.getDeviceName(),device.getIp(),deviceExplorer.showString()));

                        explorerLogs.add(alarmLog);
                    }
                }
            }
            alarmLogHandler.insertAll(explorerLogs);
        }
    }


    /**
     * 链路告警扫描，只扫描链路设备的连通性
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void link(){
        //获取所有链路
        Map<Long,Link> allLink = deviceCache.getAllLinkMap();
        if(CollectionUtils.isEmpty(allLink)){
            return;
        }

        //得到所有连接上的设备
        Set<Long> okSet = deviceConnectCache.getOkSet();

        //获取所有设备
        Map<Long, Device> allDevice = deviceCache.allDevice();

        Set<AlarmLog> linkLogs = new HashSet<>(allLink.size());
        AlarmLog alarmLog;
        Set<Long>  deviceIds;
        Set<String> allDeviceName;
        for(Link link:allLink.values()){
            deviceIds = link.getDeviceIds();
            if(CollectionUtils.isEmpty(deviceIds)){
                continue;
            }

            //移除掉所有能连上的设备,剩下的就是连不上的设备
            deviceIds.removeAll(okSet);

            //如果为空，证明全部连上了
            if(CollectionUtils.isEmpty(deviceIds)) {
                continue;
            }

            alarmLog = new AlarmLog();
            alarmLog.setBid(link.getLinkId());
            alarmLog.setbType(AlarmLog.BTYPE_LINK);
            alarmLog.setLevel(MyLevel.LEVEL_ERROR);
            alarmLog.setType(AlarmType.shebeidiushi);

            allDeviceName = new HashSet<>(deviceIds.size());
            //拼装名称
            for (Long deviceId : deviceIds) {
                Device device = allDevice.get(deviceId);
                if(device == null){
                    continue;
                }
                allDeviceName.add(device.getDeviceName());
            }
            alarmLog.setMsg(String.format("链路%s中有%s个设备掉线，设备名称为%s",link.getLinkName(),deviceIds.size(), StringUtils.join(allDeviceName),","));

            linkLogs.add(alarmLog);

        }
        alarmLogHandler.insertAll(linkLogs);
    }

    /**
     * 任务告警扫描
     * 扫描任务是否存活
     *
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void task(){

        //获取所有任务
        List<Task> allTask = taskHandler.queryAll();

        if(CollectionUtils.isEmpty(allTask)){
            return;
        }

        //所有task当前的状态
        Map<Integer,Set<Long>> taskLevel = new HashMap<>();
        int level;
        for (Task task : allTask) {
            //检查task
            level = checkTask(task);

            if(taskLevel.get(level) == null){
                Set<Long> set =new HashSet<>();
                set.add(task.getTaskId());
                taskLevel.put(level,set);
            }else{
                taskLevel.get(level).add(task.getTaskId());
            }
        }

        for(Integer lev : taskLevel.keySet()){
            taskHandler.updateLevel(taskLevel.get(lev),lev);
        }

    }

    private int checkTask(Task task){
        String taskTargetId = task.getTargetTaskId();

        Set<AlarmLog> taskAlarm = new HashSet<>();
        TaskSource taskSource = taskSourceHandler.query(task.getLinkId(),taskTargetId);
        AlarmLog alarmLog;
        if(taskSource == null){
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId());
            alarmLog.setbType(AlarmLog.BTYPE_TASK);
            alarmLog.setLevel(MyLevel.LEVEL_ERROR);
            alarmLog.setType(AlarmType.no_source);
            taskAlarm.add(alarmLog);
            return MyLevel.LEVEL_ERROR;
        }else{
            DeviceService detectorService = deviceCache.getDetectorService8linkId(task.getLinkId());

            String fromSourceId = taskSource.getFromResourceId();
            String toSourceId = taskSource.getToResourceId();

            Source fromSource = sourceHandler.query(task.getLinkId(),fromSourceId);
            boolean isConnect = sourceCheckHandler.testSource(fromSource,detectorService);
            alarmLog = build(task,fromSource,isConnect);
            taskAlarm.add(alarmLog);

            Source toSource = sourceHandler.query(task.getLinkId(),toSourceId);
            isConnect = sourceCheckHandler.testSource(toSource,detectorService);
            alarmLog = build(task,toSource,isConnect);
            taskAlarm.add(alarmLog);

            //Minkey 检查任务进程是否存在


        }

        int level = MyLevel.LEVEL_NORMAL;
        for (AlarmLog log : taskAlarm) {
            if(log.getLevel() > level){
                level = log.getLevel();
            }
        }
        return level;
    }

    private AlarmLog build(Task task, Source fromSource, boolean isConnect){
        if(isConnect) {
            return null;
        }
        String msg = String.format("任务[%s]%s%s数据源[%s]连接失败",
                task.getTaskName(),
                fromSource.getSourceType(),
                fromSource.isNetAreaIn()?"内网":"外网",
                fromSource.getSname());

        AlarmLog alarmLog = new AlarmLog();
        alarmLog.setBid(task.getTaskId())
                .setbType(AlarmLog.BTYPE_TASK)
                .setLevel(MyLevel.LEVEL_ERROR)
                .setType(AlarmType.wangluobutong)
                .setMsg(msg);

        return alarmLog;
    }


}
