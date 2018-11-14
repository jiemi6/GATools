package com.minkey.handler;

import com.minkey.cache.CheckStepCache;
import com.minkey.cache.DeviceCache;
import com.minkey.contants.MyLevel;
import com.minkey.db.*;
import com.minkey.db.dao.*;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void doTask(long checkId, Long taskId) {
        //需要检查任务数据源 和 数据存放地 两边的情况
        CheckItem checkItem;

        Task task = taskHandler.query(taskId);
        if(task == null){
            return;
        }

        String taskTargetId = task.getTargetTaskId();

        TaskSource taskSource = taskSourceHandler.query(task.getLinkId(),taskTargetId);

        if(taskSource == null){
            checkItem = new CheckItem(checkId,1);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR).setResultMsg(String.format("没有找到任务%s的数据源信息",task.getTaskName()));
            checkItemHandler.insert(checkItem);
            //没有找到数据源配置
            return;
        }

        int totalStep = 2+1+1;
        checkStepCache.create(checkId,totalStep);
        checkItem = checkStepCache.createNextItem(checkId);

        DeviceService detectorService = deviceCache.getDetectorService8linkId(task.getLinkId());

        String fromSourceId = taskSource.getFromResourceId();
        String toSourceId = taskSource.getToResourceId();

        Source fromSource = sourceHandler.query(task.getLinkId(),fromSourceId);
        testSource(fromSource,detectorService);

        Source toSource = sourceHandler.query(task.getLinkId(),toSourceId);
        testSource(toSource,detectorService);

        //检查任务进程是否存在

    }

    private boolean testSource(Source source, DeviceService detectorService){
        if(StringUtils.equals(source.getSourceType(),Source.sourceType_db)){
            testSource_db(source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_ftp)){
            testSource_ftp(source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_video)){
            testSource_video(source,detectorService);
        }else{
            //未知数据格式
            log.error("未知数据源类型="+source.getSourceType());
        }

        return false;
    }

    private void testSource_ftp(Source source, DeviceService detectorService) {
        boolean isConnect = ftpUtil.testFTPConnect(source,FTPUtil.default_timeout);
        if(isConnect){

        }else {

        }

    }

    private void testSource_db(Source source, DeviceService detectorService) {
        boolean isConnect = dynamicDB.testDB(source);
        if(isConnect){

        }else{

        }
    }

    private void testSource_video(Source source, DeviceService detectorService) {

    }

}
