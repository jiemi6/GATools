package com.minkey.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.MyLevel;
import com.minkey.db.dao.*;
import com.minkey.dto.FTPConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class SourceCheckHandler {

    @Autowired
    private FTPUtil ftpUtil;
    @Autowired
    private DynamicDB dynamicDB;

    @Autowired
    DeviceCache deviceCache;


    public Set<AlarmLog> testSource(Task task, Source source, DeviceService detectorService) throws SystemException{
        Set<AlarmLog> alarmLogs = new HashSet<>();
        Link link = deviceCache.getLink8Id(task.getLinkId());
        AlarmLog alarmLog;
        //如果是外网而且没有探针
        if(!source.isNetAreaIn() && detectorService == null){
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.no_detector)
                    .setMsg(String.format("<%s链路>没有部署探针，无法探测外网资源<%s>",link.getLinkName(),source.getSname()));
            alarmLogs.add(alarmLog);
            return alarmLogs;
        }

        if(StringUtils.equals(source.getSourceType(),Source.sourceType_db)){
            return testSource_db(task,source,detectorService,link);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_ftp)){
            return testSource_ftp(task,source,detectorService,link);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_video)){
            return testSource_video(task,source,detectorService,link);
        }else{
            //未知数据格式
            String logstr = String.format("<%s链路>存在未知数据源类型<%>",link.getLinkName(),source.getSourceType());
            log.error(logstr);
            return null;
        }
    }

    public Set<AlarmLog> testSource_ftp(Task task, Source source, DeviceService detectorService, Link link)throws SystemException {
        //将source转换为ftpconfigdata
        FTPConfigData ftpConfigData = new FTPConfigData();
        ftpConfigData.setIp(source.getIp());
        ftpConfigData.setPort(source.getPort());
        ftpConfigData.setName(source.getName());
        ftpConfigData.setPwd(source.getPwd());
        ftpConfigData.setRootPath(source.getDbName());

        AlarmLog alarmLog;
        Set<AlarmLog> alarmLogs = new HashSet<>();
        JSONObject jsonResult;
        if(source.isNetAreaIn()){
            jsonResult = ftpUtil.testFTPSource(ftpConfigData, FTPUtil.default_timeout);
        }else{
            jsonResult = DetectorUtil.testFTPSource(detectorService.getIp(),detectorService.getConfigData().getPort(), ftpConfigData);
        }

        Integer alarmType = jsonResult.getInteger("alarmType");
        //如果不为空,证明报错,没有正常连上
        if(alarmType != null) {
            String msg = String.format("<%s链路>FTP资源<%s>连接失败:<%>",link.getLinkName(),task.getTaskName(),AlarmEnum.find8Type(alarmType).getDesc());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(alarmType)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
            return  alarmLogs;
        }

        boolean isRootLock = jsonResult.getBooleanValue("isRootLock");
        if(!isRootLock){
            String msg = String.format("<%s链路>FTP资源<%s>根目录未锁定",link.getLinkName(),task.getTaskName());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_rootUnLock)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        boolean isPassive = jsonResult.getBooleanValue("isPassive");
        if(!isPassive){
            String msg = String.format("<%s链路>FTP资源<%s>服务端不是被动模式",link.getLinkName(),task.getTaskName());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_notPassive)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        String allAuth = jsonResult.getString("allAuth");
        if(!allAuth.contains("ADD") || !allAuth.contains("DEL") || !allAuth.contains("READ")){
            String msg = String.format("<%s链路>FTP资源<%s>权限不足,已有权限:%s",link.getLinkName(),task.getTaskName(),allAuth);
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.ftp_notAllAuth)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        int totalFileNum = jsonResult.getIntValue("totalFileNum");
        if(totalFileNum > 100000){
            String msg = String.format("<%s链路>FTP资源<%s>根目录下文件总数超过10万",link.getLinkName(),task.getTaskName());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_fileNum_tooBig)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        int topDirNum = jsonResult.getIntValue("topDirNum");
        if(topDirNum > 30 ){
            String msg = String.format("<%s链路>FTP资源<%s>根目录下目录数超过30个",link.getLinkName(),task.getTaskName());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_topDirNum_tooBig)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        int floorNum = jsonResult.getIntValue("floorNum");
        if(floorNum > 5){
            String msg = String.format("<%s链路>FTP资源<%s>根目录下子目录层级超过5层",link.getLinkName(),task.getTaskName());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_floorNum_tooBig)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        return alarmLogs;
    }

    public Set<AlarmLog> testSource_db(Task task, Source source, DeviceService detectorService, Link link)throws SystemException {
        JSONObject jsonResult;
        AlarmLog alarmLog;
        Set<AlarmLog> alarmLogs = new HashSet<>();
        if(source.isNetAreaIn()){
            jsonResult = dynamicDB.testDBSource(source);
        }else{
            jsonResult = DetectorUtil.testDBSource(detectorService.getIp(),detectorService.getConfigData().getPort(),source);
        }

        Integer alarmType = jsonResult.getInteger("alarmType");
        //如果不为空,证明报错,没有正常连上
        if(alarmType != null){
            String msg = String.format("<%s链路>DB资源<%s>连接失败",link.getLinkName(),task.getTaskName(),AlarmEnum.find8Type(alarmType).getDesc());
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(alarmType)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
            return alarmLogs;
        }

        int maxConnectNum = jsonResult.getIntValue("maxConnectNum");
        int connectNum = jsonResult.getIntValue("connectNum");
        if(connectNum >= maxConnectNum-1){
            String msg = String.format("<%s链路>DB资源<%s>连接数达到最大连接数,最大连接数:%s,当前连接数:%s",link.getLinkName(),task.getTaskName(),maxConnectNum,connectNum);
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_maxConnectNum)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        JSONArray hasBlobTableNames = jsonResult.getJSONArray("hasBlobTableNames");
        if(hasBlobTableNames != null && hasBlobTableNames.size() > 0){
            String msg = String.format("<%s链路>DB资源<%s>数据库表含有大字段,表名:%s",link.getLinkName(),task.getTaskName(),hasBlobTableNames);
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_has_bigColumn)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        JSONArray tableCountMax1W = jsonResult.getJSONArray("tableCountMax1W");
        if(hasBlobTableNames != null && hasBlobTableNames.size() > 0){
            String msg = String.format("<%s链路>DB资源<%s>数据库表行数超过1万,表名:",link.getLinkName(),task.getTaskName(),tableCountMax1W);
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_has_bigColumn)
                    .setMsg(msg);
            alarmLogs.add(alarmLog);
        }

        if(source.getDatabaseDriver() == DatabaseDriver.MYSQL){
            alarmLog = judgeMysqlAuth(jsonResult.getJSONArray("authSet"),task,link);
            if(alarmLog != null){
                alarmLogs.add(alarmLog);
            }
        }else if(source.getDatabaseDriver() == DatabaseDriver.ORACLE){
            alarmLog = judgeOracleAuth(jsonResult.getJSONArray("authSet"),task,link);
            if(alarmLog != null){
                alarmLogs.add(alarmLog);
            }
        }

        return alarmLogs;

    }

    private AlarmLog judgeOracleAuth(JSONArray authSet, Task task, Link link) {
        AlarmLog alarmLog;

        Set<String> noAuthSet = new HashSet<>();
        if(!authSet.contains("CREATE TABLE")){
            noAuthSet.add("CREATE TABLE");
        }

        if(!authSet.contains("CREATE TRIGGER")){
            noAuthSet.add("CREATE TRIGGER");
        }

        if(noAuthSet.size()>0){
            String msg = String.format("<%s链路>DB资源<%s>权限不足,缺少权限:",link.getLinkName(),task.getTaskName(),noAuthSet);
            alarmLog = new AlarmLog()
                    .setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_notAllAuth)
                    .setMsg(msg);
            return alarmLog;
        }

        return null;
    }

    /**
     * 判断mysql是否具备权限
     * @return
     */
    private AlarmLog judgeMysqlAuth(JSONArray authSet,Task task, Link link) {
        AlarmLog alarmLog;
        //如果没有all
        if(!authSet.contains("ALL")){
            Set<String> noAuthSet = new HashSet<>();
            if(!authSet.contains("CREATE")){
                noAuthSet.add("CREATE");
            }
            if(!authSet.contains("SELECT")){
                noAuthSet.add("SELECT");
            }
            if(!authSet.contains("DELETE")){
                noAuthSet.add("DELETE");
            }
            if(!authSet.contains("UPDATE")){
                noAuthSet.add("UPDATE");
            }
            if(!authSet.contains("INSERT")){
                noAuthSet.add("INSERT");
            }
            if(!authSet.contains("TRIGGER")){
                noAuthSet.add("TRIGGER");
            }
            if(noAuthSet.size()>0){
                String msg = String.format("<%s链路>DB资源<%s>权限不足,缺少权限:",link.getLinkName(),task.getTaskName(),noAuthSet);
                alarmLog = new AlarmLog()
                        .setBid(task.getTaskId())
                        .setbType(AlarmLog.BTYPE_TASK)
                        .setLevel(MyLevel.LEVEL_ERROR)
                        .setType(AlarmEnum.db_notAllAuth)
                        .setMsg(msg);
                return alarmLog;
            }

        }
        return null;
    }

    public Set<AlarmLog> testSource_video(Task task, Source source, DeviceService detectorService, Link link)throws SystemException {
        log.error("暂时不支持video数据源探测");
        return null;
    }

}
