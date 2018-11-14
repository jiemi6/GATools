package com.minkey.handler;

import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.cache.DeviceExplorerCache;
import com.minkey.contants.AlarmType;
import com.minkey.contants.DeviceType;
import com.minkey.contants.MyLevel;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.Link;
import com.minkey.dto.DeviceExplorer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 告警处理，告警数据来源
 */
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

        


    }


}
