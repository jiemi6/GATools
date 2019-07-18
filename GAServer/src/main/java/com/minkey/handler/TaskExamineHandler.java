package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.CheckStepCache;
import com.minkey.cache.DeviceCache;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.DeviceType;
import com.minkey.contants.MyLevel;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.SourceHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.TaskSourceHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.FTPConfigData;
import com.minkey.dto.SnmpConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;

/**
 * 任务另起一个类
 */
@Slf4j
@Component
public class TaskExamineHandler {
    @Autowired
    CheckItemHandler checkItemHandler;

    @Autowired
    CheckStepCache checkStepCache;

    @Autowired
    DeviceCache deviceCache;

    @Autowired
    TaskHandler taskHandler;

    @Autowired
    TaskSourceHandler taskSourceHandler;

    @Autowired
    SourceHandler sourceHandler;

    @Autowired
    DynamicDB dynamicDB;

    @Autowired
    FTPUtil ftpUtil;

    @Autowired
    SourceCheckHandler sourceCheckHandler;

    @Async
    public void doTaskAsync(long checkId, Task task) {
        doTask(checkId,task);
    }

    public void doTask(long checkId, Task task) {
        //需要检查任务数据源 和 数据存放地 两边的情况
        CheckItem checkItem;

        String taskTargetId = task.getTargetTaskId();

        TaskSource taskSource = taskSourceHandler.query(task.getLinkId(),taskTargetId);

        if(taskSource == null){
            checkItem = new CheckItem(checkId,1);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            checkItem.setResultMsg(String.format("<%s任务>没有找到的数据源信息",task.getTaskName()));
            checkItemHandler.insert(checkItem);
            //没有找到数据源配置
            return;
        }

        int totalStep = 2+1+1;
        checkStepCache.create(checkId,totalStep);

        DeviceService detectorService = deviceCache.getDetectorService8linkId(task.getLinkId());

        String fromSourceId = taskSource.getFromResourceId();
        String toSourceId = taskSource.getToResourceId();

        Source fromSource = sourceHandler.query(task.getLinkId(),fromSourceId);
        checkItem = testSource(checkId, task, fromSource,detectorService);
        checkItemHandler.insert(checkItem);

        Source toSource = sourceHandler.query(task.getLinkId(),toSourceId);
        checkItem = testSource(checkId, task, toSource,detectorService);
        checkItemHandler.insert(checkItem);


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

        //检查任务进程是否存在
        checkItem = checkTaskProcess(checkId,task,tas,null,"TAS");
        checkItemHandler.insert(checkItem);
        checkItem = checkTaskProcess(checkId,task,uas,detectorService,"UAS");
        checkItemHandler.insert(checkItem);
    }


    @Autowired
    SnmpExploreHandler snmpExploreHandler;

    private CheckItem checkTaskProcess(long checkId,Task task,Device device,DeviceService detectorService,String deviceType){
        DeviceService snmpDeviceService = null;

        CheckItem checkItem = checkStepCache.createNextItem(checkId);
        //如果设备不存在
        if(device == null){
            //不检查进程，链路会报警
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("<%s任务>没有找到%s设备",task.getTaskName(),deviceType));
            return checkItem;
        }else {
            Set<DeviceService> deviceServiceSet = deviceCache.getDeviceService8DeviceId(device.getDeviceId());
            if (!CollectionUtils.isEmpty(deviceServiceSet)) {
                for (DeviceService deviceService : deviceServiceSet) {
                    //找snmp服务
                    if (deviceService.getServiceType() == DeviceService.SERVICETYPE_SNMP) {
                        snmpDeviceService = deviceService;
                        break;
                    }
                }
            }

            //没有配置snmp服务
            if (snmpDeviceService == null) {
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("<%s任务><%s>%s设备没有配置SNMP服务！",task.getTaskName(),device.getDeviceName(),deviceType));
                return checkItem;
            }
            SnmpConfigData snmpConfigData = (SnmpConfigData) snmpDeviceService.getConfigData();

            boolean isProcessExist = snmpExploreHandler.checkProcess(task.getTargetTaskId(), snmpConfigData, detectorService);

            if (isProcessExist) {
                checkItem.setResultLevel(MyLevel.LEVEL_NORMAL).setResultMsg(String.format("<%s任务><%s>%s设备任务进程正常！",task.getTaskName(),device.getDeviceName(),deviceType));
            }else{
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("<%s任务><%s>%s设备任务进程不存在！",task.getTaskName(),device.getDeviceName(),deviceType));
            }
            return checkItem;

        }
    }


    private CheckItem testSource(long checkId, Task task, Source source, DeviceService detectorService){
        //如果是外网而且没有探针
        if(!source.isNetAreaIn() && detectorService == null){
            String logstr = String.format("<%s任务>没有部署探针，无法探测外网资源%s",task.getTaskName(), source.getSname());
            log.error(logstr);
            CheckItem checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(logstr);
            return checkItem;
        }

        if(StringUtils.equals(source.getSourceType(),Source.sourceType_db)){
            return testSource_db(checkId,task,source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_ftp)){
            return testSource_ftp(checkId,task,source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_video)){
            return testSource_video(checkId,task,source,detectorService);
        }else{
            //未知数据格式
            String msg = String.format("<%s任务><%s>未知数据源类型<%>",task.getTaskName(), source.getSname(),source.getSourceType());
            log.error(msg);
            CheckItem checkItem = checkStepCache.createNextItem(checkId);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(msg);
            return checkItem;
        }
    }

    private CheckItem testSource_ftp(long checkId, Task task, Source source, DeviceService detectorService) {
        JSONObject returnJson =testSourceConnect_ftp(source,detectorService);

        CheckItem checkItem = checkStepCache.createNextItem(checkId);

        boolean isAlarm = false;
        Integer alarmType = returnJson.getInteger("alarmType");
        if(alarmType != null){
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            if(alarmType == AlarmEnum.ftp_io_error.getAlarmType()){
                checkItem.setResultMsg(String.format("<%s任务><%s>FTP数据源网络不通!",task.getTaskName(),source.getSname()));
            }else if(alarmType == AlarmEnum.ftp_wrongpwd.getAlarmType()){
                checkItem.setResultMsg(String.format("<%s任务><%s>FTP数据源用户名密码错误!用户名:%s,密码:%s",task.getTaskName(),source.getSname(),source.getName(),source.getPwd()));
            }else if(alarmType == AlarmEnum.port_notConnect.getAlarmType()){
                checkItem.setResultMsg(String.format("<%s任务><%s>FTP数据源端口不通!端口:%s",task.getTaskName(),source.getSname(),source.getPort()));
            }else{
                checkItem.setResultMsg(String.format("<%s任务><%s>FTP数据源网络不通!",task.getTaskName(),source.getSname()));
            }
        }else {
            StringBuffer sb = new StringBuffer();
            sb.append(String.format("<%s任务><%s>FTP数据源连接正常", task.getTaskName(), source.getSname()));
            sb.append(String.format("<br>端口服务正常，端口号:%s", source.getPort()));
            sb.append(String.format("<br>用户名、密码正常登录", source.getPort()));
            if (returnJson.getBooleanValue("isRootLook")) {
                sb.append(String.format("<br>主目录已锁定"));
            } else {
                isAlarm = true;
                sb.append(String.format("<br>主目录未锁定"));
            }

            if (returnJson.getBooleanValue("isPassive")) {
                sb.append(String.format("<br>被动模式"));
            } else {
                sb.append(String.format("<br>非被动模式"));
            }

            int totalFileNum = returnJson.getIntValue("totalFileNum");
            if (totalFileNum > 100000) {
                isAlarm = true;
                sb.append(String.format("<br>根目录下文件个数:%s,大于10万个", totalFileNum));
            } else {
                sb.append(String.format("<br>根目录下文件个数:%s,小于10万个", totalFileNum));
            }

            int maxFloorNum = returnJson.getIntValue("maxFloorNum");
            if (maxFloorNum > 5) {
                isAlarm = true;
                sb.append(String.format("<br>根目录下目录层级大于5级，目录层级:%s", maxFloorNum));
            } else {
                sb.append(String.format("<br>根目录下目录层级小于5级，目录层级:%s", maxFloorNum));
            }

            int topDirNum = returnJson.getIntValue("topDirNum");
            if (topDirNum > 30) {
                isAlarm = true;
                sb.append(String.format("<br>根目录下同级目录大于30个，当前同级目录数量:%s", topDirNum));
            } else {
                sb.append(String.format("<br>根目录下同级目录小于30个，当前同级目录数量:%s", topDirNum));
            }

            String allAuth = returnJson.getString("allAuth");
            if (allAuth.contains("ADD") && allAuth.contains("DEL") && allAuth.contains("READ")) {
                sb.append(String.format("<br>权限正常,已有权限%s", allAuth));
            } else {
                isAlarm = true;
                sb.append(String.format("<br>权限不足,已有权限%s", allAuth));
            }
            checkItem.setResultLevel(isAlarm ? MyLevel.LEVEL_WARN : MyLevel.LEVEL_NORMAL);
            checkItem.setResultMsg(sb.toString());
        }

        return checkItem;
    }

    private CheckItem testSource_db(long checkId, Task task, Source source, DeviceService detectorService) {
        boolean isConnect = testSourceConnect_db(source,detectorService);

        CheckItem checkItem = checkStepCache.createNextItem(checkId);

        if(isConnect){
            checkItem.setResultLevel(MyLevel.LEVEL_NORMAL)
                    .setResultMsg(String.format("探测<%s 任务>%sDB数据源[%s]连接正常",task.getTaskName(),source.isNetAreaIn()?"内网":"外网",source.getSname()));
        }else {
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR)
                    .setResultMsg(String.format("探测<%s 任务>%sDB数据源[%s]连接失败",task.getTaskName(),source.isNetAreaIn()?"内网":"外网",source.getSname()));
        }
        return checkItem;
    }

    private CheckItem testSource_video(long checkId, Task task, Source source, DeviceService detectorService) {
        String msg = String.format("<%s任务><%s>暂时不支持video数据源探测",task.getTaskName(), source.getSname(),source.getSourceType());
        log.error(msg);
        CheckItem checkItem = checkStepCache.createNextItem(checkId);
        checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(msg);
        return checkItem;
    }


    public JSONObject testSourceConnect_ftp(Source source, DeviceService detectorService)throws SystemException {
        //将source转换为ftpconfigdata
        FTPConfigData ftpConfigData = new FTPConfigData();
        ftpConfigData.setIp(source.getIp());
        ftpConfigData.setPort(source.getPort());
        ftpConfigData.setName(source.getName());
        ftpConfigData.setPwd(source.getPwd());
        ftpConfigData.setRootPath(source.getDbName());

        JSONObject returnJson;
        if(source.isNetAreaIn()){
            returnJson = ftpUtil.testFTPSource(ftpConfigData, FTPUtil.default_timeout);
        }else{
            returnJson = DetectorUtil.testFTPSource(detectorService.getIp(),detectorService.getConfigData().getPort(), ftpConfigData);
        }

        return returnJson;
    }

    public boolean testSourceConnect_db(Source source, DeviceService detectorService)throws SystemException {
        try {
            boolean isConnect;
            if (source.isNetAreaIn()) {
                isConnect = dynamicDB.testDBConnect(source);
            } else {
                isConnect = DetectorUtil.testDBConnect(detectorService.getIp(), detectorService.getConfigData().getPort(), source);
            }
            return isConnect;
        }catch(SystemException e){
            log.error("testDBConnect exception ",e);
            return false;
        }

    }
}
