package com.minkey.db.analysis;

import com.minkey.cache.DeviceCache;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 天统计，凌晨1点统计上一天的数据
 */
@Slf4j
@Component
public class AlarmDayLogAnalysisTask {
    @Autowired
    DeviceCache deviceCache;
    @Autowired
    TaskHandler taskHandler;

    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    AlarmDayLogHandler alarmDayLogHandler;

    /**
     * 凌晨1点开始
     */
    @Scheduled(cron="0 0 1 * * ?")
    public void goAnalysis(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //时分秒清零
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        Date endDate = c.getTime();
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        Date startDate = c.getTime();
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        try {


            //获取所有链路数
            int totalLinkNum = deviceCache.getAllLinkMap().size();
            List<Long> allLinkIds = alarmLogHandler.queryAllBid8btype(AlarmLog.BTYPE_LINK, startDate,endDate);
            int alarmLinkNum = CollectionUtils.isEmpty(allLinkIds) ? 0 : allLinkIds.size();

            //获取所有设备
            int totalDeviceNum = deviceCache.allDevice().size();
            List<Long> allDeviceIds = alarmLogHandler.queryAllBid8btype(AlarmLog.BTYPE_DEVICE, startDate,endDate);
            int alarmDeviceNum = CollectionUtils.isEmpty(allDeviceIds) ? 0 : allDeviceIds.size();

            int totalTaskNum =taskHandler.queryCount();
            int alarmTaskNum = 0;
            //获取所有任务
            List<Task> tasks = taskHandler.queryAll();
            if(!CollectionUtils.isEmpty(tasks)){
                List<Long> alarmTaskIds = alarmLogHandler.queryAllBid8btype(AlarmLog.BTYPE_TASK, startDate,endDate);

                if(!CollectionUtils.isEmpty(alarmTaskIds)){
                    Set<Long> allTaskIds = tasks.stream().map(Task::getTaskId).collect(Collectors.toSet());

                    //取交集
                    alarmTaskIds.removeAll(allTaskIds);
                    allTaskIds.addAll(alarmTaskIds);

                    alarmTaskNum = allTaskIds.size();
                }
            }
            //解决报警数有可能大于总数的bug
            if(alarmDeviceNum > totalDeviceNum){
                alarmDeviceNum = totalDeviceNum;
                log.warn("设备报警数为:%s,总的设备数为:%s",alarmDeviceNum,totalDeviceNum);
            }

            if(alarmTaskNum > totalTaskNum){
                alarmTaskNum = totalTaskNum;
                log.warn("任务报警数为:%s,总的任务数为:%s",alarmTaskNum,totalTaskNum);
            }


            AlarmDayLog alarmDayLog = new AlarmDayLog();
            alarmDayLog.setTotalLinkNum(totalLinkNum);
            alarmDayLog.setAlarmLinkNum(alarmLinkNum);
            alarmDayLog.setTotalDeviceNum(totalDeviceNum);
            alarmDayLog.setAlarmDeviceNum(alarmDeviceNum);
            alarmDayLog.setTotalTaskNum(totalTaskNum);
            alarmDayLog.setAlarmTaskNum(alarmTaskNum);

            //时分秒清零
            c.set(Calendar.HOUR_OF_DAY,0);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.MILLISECOND,0);
            alarmDayLog.setCreateTime(c.getTime());

            alarmDayLogHandler.insert(alarmDayLog);

            log.info("每日["+yesterday+"]统计报警信息:"+alarmDayLog);
        }catch (Exception e){
            log.error("每日["+yesterday+"]统计报警信息异常,"+e.getMessage());
        }
    }


}
