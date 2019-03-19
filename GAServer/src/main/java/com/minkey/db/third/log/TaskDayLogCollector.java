package com.minkey.db.third.log;

import com.minkey.contants.LinkType;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskDayLogHandler;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.TaskDayLog;
import com.minkey.dto.DBConfigData;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 从数据交换系统，采集task执行日志信息
 * <br><br/>
 * 每个小时获取一次
 */
@Slf4j
@Component
public class TaskDayLogCollector {
    @Autowired
    TaskDayLogHandler taskDayLogHandler;


    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;


    @Value("${system.debug:false}")
    private boolean isDebug;

    /**
     * 每一个小时执行一次，0分0秒开始执行
     */
    @Scheduled(cron="0 0 * * * ?")
    public void getTaskLogFromOtherDB(){
        if(isDebug){
            log.error("debug，测试抓取任务执行日志daylog调度.");
        }

        List<Link> linkList = null;
        try {
            //查询所有链路
            linkList = linkHandler.queryAll();
        }catch (Exception e){
            log.error("获取所有的链路异常",e);
            return;
        }

        if (CollectionUtils.isEmpty(linkList)) {
            return;
        }

        linkList.forEach(link -> {
            if (link.getLinkType() == LinkType.shujujiaohuan) {
                //目前只做了数据交换
                shujujiaohuan(link);
            }
        });
    }


    private void shujujiaohuan(Link link){
        List<TaskDayLog> tasks = null;
        try {
            //从链路中获取数据交换系统的数据库配置
            DBConfigData dbConfig = link.getDbConfigData();

            long maxLoggerId = taskDayLogHandler.queryMaxId(link.getLinkId());

            tasks = queryAllTaskLog(dbConfig, link, maxLoggerId);

        } catch (Exception e) {
            log.error("从交换系统抓起任务执行日志异常", e);
        }

        if (!CollectionUtils.isEmpty(tasks)) {
            try {
                //把链路存到数据库中。
                taskDayLogHandler.insertAll(tasks);
            } catch (Exception e) {
                log.error("把抓取过来的任务执行日志保存到数据库中异常", e);
            }
        }
    }

    /**
     * 从数据交换系统获取任务日志列表
     * @param dbConfig
     * @param link
     * @return
     */
    private List<TaskDayLog> queryAllTaskLog(DBConfigData dbConfig, Link link, long maxLoggerId){
        //先从缓存中获取
        JdbcTemplate jdbcTemplate = dynamicDB.get8dbConfig(dbConfig);

        String sql = "select dayloggerid,taskid,totalSuccessNum,totalSuccessFlow,totalErrorFlow,totalErrorNum,insertTimes from tbdaylogger where dayloggerid > "+maxLoggerId;
        //查询所有task
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList(sql);

        if(CollectionUtils.isEmpty(mapList)){
            return null;
        }

        List<TaskDayLog> tasks = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {
            TaskDayLog taskDayLog = new TaskDayLog();
            taskDayLog.setTargetLogId(Long.valueOf(stringObjectMap.get("dayloggerid").toString()));
            taskDayLog.setTargetTaskId( stringObjectMap.get("taskid").toString());
            taskDayLog.setLinkId(link.getLinkId());
            taskDayLog.setSuccessNum(Long.valueOf(stringObjectMap.get("dayloggerid").toString()));
            taskDayLog.setSuccessNum(Long.valueOf(stringObjectMap.get("totalSuccessNum").toString()));
            taskDayLog.setSuccessFlow(Long.valueOf(stringObjectMap.get("totalSuccessFlow").toString()));
            taskDayLog.setErrorNum(Long.valueOf(stringObjectMap.get("totalErrorNum").toString()));
            taskDayLog.setErrorFlow(Long.valueOf(stringObjectMap.get("totalErrorFlow").toString()));
            taskDayLog.setCreateTime((Date) stringObjectMap.get("insertTimes"));
            tasks.add(taskDayLog);
        });
        return tasks;
    }


    /**
     * 要访问的表的建表语句
     CREATE TABLE `tbdaylogger` (
     `dayloggerid`  int(11) NOT NULL AUTO_INCREMENT ,
     `taskid`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
     `totalSuccessNum`  bigint(100) NOT NULL ,
     `totalSuccessFlow`  bigint(100) NOT NULL ,
     `totalErrorFlow`  bigint(100) NOT NULL ,
     `totalErrorNum`  bigint(100) NOT NULL ,
     `insertTimes`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
     `tname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名称' ,
     `isDelete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     PRIMARY KEY (`dayloggerid`)
     )
     ENGINE=InnoDB
     DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
     AUTO_INCREMENT=5
     ROW_FORMAT=DYNAMIC
     ;
     */

}
