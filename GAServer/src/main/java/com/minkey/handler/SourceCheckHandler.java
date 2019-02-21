package com.minkey.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.MyLevel;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.DeviceService;
import com.minkey.db.dao.Source;
import com.minkey.db.dao.Task;
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

    public Set<AlarmLog> testSource(Task task, Source source, DeviceService detectorService) throws SystemException{
        Set<AlarmLog> alarmLogs = new HashSet<>();
        AlarmLog alarmLog;
        //如果是外网而且没有探针
        if(!source.isNetAreaIn() && detectorService == null){
            alarmLog = new AlarmLog();
            alarmLog.setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.no_detector)
                    .setMsg(String.format("没有部署探针，无法探测外网资源%s",source.getSname()));
            alarmLogs.add(alarmLog);
            return alarmLogs;
        }

        if(StringUtils.equals(source.getSourceType(),Source.sourceType_db)){
            return testSource_db(task,source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_ftp)){
            return testSource_ftp(task,source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_video)){
            return testSource_video(task,source,detectorService);
        }else{
            //未知数据格式
            String logstr = String.format("未知数据源类型[%]",source.getSourceType());
            log.error(logstr);
            return null;
        }
    }

    public Set<AlarmLog> testSource_ftp(Task task, Source source, DeviceService detectorService)throws SystemException {
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
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(alarmType)
                    .setMsg(AlarmEnum.find8Type(alarmType).getDesc());
            alarmLogs.add(alarmLog);
            return  alarmLogs;
        }

        boolean isRootLock = jsonResult.getBooleanValue("isRootLock");
        if(!isRootLock){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_rootUnLock)
                    .setMsg("FTP根目录未锁定");
            alarmLogs.add(alarmLog);
        }

        boolean isPassive = jsonResult.getBooleanValue("isPassive");
        if(!isPassive){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_notPassive)
                    .setMsg("FTP服务端不是被动模式");
            alarmLogs.add(alarmLog);
        }

        String allAuth = jsonResult.getString("allAuth");
        if(!allAuth.contains("ADD") || !allAuth.contains("DEL") || !allAuth.contains("READ")){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.ftp_notAllAuth)
                    .setMsg("FTP操作权限不足,目前权限为"+allAuth);
            alarmLogs.add(alarmLog);
        }

        int totalFileNum = jsonResult.getIntValue("totalFileNum");
        if(totalFileNum > 100000){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_fileNum_tooBig)
                    .setMsg("FTP根目录下文件总数超过10万");
            alarmLogs.add(alarmLog);
        }

        int topDirNum = jsonResult.getIntValue("topDirNum");
        if(topDirNum > 30 ){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_topDirNum_tooBig)
                    .setMsg("FTP根目录下目录数超过30个");
            alarmLogs.add(alarmLog);
        }

        int floorNum = jsonResult.getIntValue("floorNum");
        if(floorNum > 5){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_WARN)
                    .setType(AlarmEnum.ftp_floorNum_tooBig)
                    .setMsg("FTP根目录下子目录层级超过5层");
            alarmLogs.add(alarmLog);
        }

        return alarmLogs;
    }

    public Set<AlarmLog> testSource_db(Task task, Source source, DeviceService detectorService)throws SystemException {
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
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(alarmType)
                    .setMsg(AlarmEnum.find8Type(alarmType).getDesc());
            alarmLogs.add(alarmLog);
            return alarmLogs;
        }

        int maxConnectNum = jsonResult.getIntValue("maxConnectNum");
        int connectNum = jsonResult.getIntValue("connectNum");
        if(connectNum >= maxConnectNum-1){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_maxConnectNum)
                    .setMsg("DB连接数达到最大连接数"+maxConnectNum);
            alarmLogs.add(alarmLog);
        }

        JSONArray hasBlobTableNames = jsonResult.getJSONArray("hasBlobTableNames");
        if(hasBlobTableNames != null && hasBlobTableNames.size() > 0){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_has_bigColumn)
                    .setMsg("数据库表含有大字段,表名:"+hasBlobTableNames);
            alarmLogs.add(alarmLog);
        }

        JSONArray tableCountMax1W = jsonResult.getJSONArray("tableCountMax1W");
        if(hasBlobTableNames != null && hasBlobTableNames.size() > 0){
            alarmLog = new AlarmLog().setBid(task.getTaskId())
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_has_bigColumn)
                    .setMsg("数据库表行数超过1万,表名:"+tableCountMax1W);
            alarmLogs.add(alarmLog);
        }




        if(source.getDatabaseDriver() == DatabaseDriver.MYSQL){
            alarmLog = judgeMysqlAuth(jsonResult.getJSONArray("authSet"),task.getTaskId());
            if(alarmLog != null){
                alarmLogs.add(alarmLog);
            }
        }else if(source.getDatabaseDriver() == DatabaseDriver.ORACLE){
            alarmLog = judgeOracleAuth(jsonResult.getJSONArray("authSet"),task.getTaskId());
            if(alarmLog != null){
                alarmLogs.add(alarmLog);
            }
        }




        return alarmLogs;

    }

    private AlarmLog judgeOracleAuth(JSONArray authSet, long taskId) {
        AlarmLog alarmLog;

        Set<String> noAuthSet = new HashSet<>();
        if(!authSet.contains("CREATE TABLE")){
            noAuthSet.add("CREATE TABLE");
        }

        if(!authSet.contains("CREATE TRIGGER")){
            noAuthSet.add("CREATE TRIGGER");
        }

        if(noAuthSet.size()>0){
            alarmLog = new AlarmLog().setBid(taskId)
                    .setbType(AlarmLog.BTYPE_TASK)
                    .setLevel(MyLevel.LEVEL_ERROR)
                    .setType(AlarmEnum.db_notAllAuth)
                    .setMsg(String.format("DB权限不足,缺少权限",noAuthSet));
            return alarmLog;
        }

        return null;
    }

    /**
     * 判断mysql是否具备权限
     * @return
     */
    private AlarmLog judgeMysqlAuth(JSONArray authSet,long taskId){
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
                alarmLog = new AlarmLog().setBid(taskId)
                        .setbType(AlarmLog.BTYPE_TASK)
                        .setLevel(MyLevel.LEVEL_ERROR)
                        .setType(AlarmEnum.db_notAllAuth)
                        .setMsg(String.format("DB权限不足,缺少权限",noAuthSet));
                return alarmLog;
            }

        }
        return null;
    }

    public Set<AlarmLog> testSource_video(Task task, Source source, DeviceService detectorService)throws SystemException {
        log.error("暂时不支持video数据源探测");
        return null;
    }

}
