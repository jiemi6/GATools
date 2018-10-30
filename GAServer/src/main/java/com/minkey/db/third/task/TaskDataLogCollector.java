package com.minkey.db.third.task;

import com.minkey.contants.LinkType;
import com.minkey.db.LinkHandler;
import com.minkey.db.TaskDataLogHandler;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.TaskDataLog;
import com.minkey.dto.DBConfigData;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 从数据交换系统，采集任务执行日志
 * <br><br/>
 * 每个小时获取一次
 */
@Slf4j
@Component
public class TaskDataLogCollector {
    @Autowired
    TaskDataLogHandler taskDataHandler;


    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;


    @Value("${system.debug}")
    private boolean isDebug;

    /**
     * 每一个小时执行一次，0分0秒开始执行
     */
//    @Scheduled(cron="0 0 * * * ?")
    public void getTaskLogFromOtherDB(){
        if(isDebug){
            log.error("debug，测试抓取任务执行日志调度.");
//            return;
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
                //暂时是由数据交换系统
                shujujiaohuan(link);
            }
        });
    }

    private void shujujiaohuan(Link link){
        List<TaskDataLog> tasks = null;
        try {
            //从链路中获取数据交换系统的数据库配置
            DBConfigData dbConfig = link.getDbConfigData();

            long maxLoggerId = taskDataHandler.queryMaxId(link.getLinkId());

            tasks = queryAllTaskLog(dbConfig, link, maxLoggerId);

        } catch (Exception e) {
            log.error("从交换系统抓起任务执行日志异常", e);
        }

        if (!CollectionUtils.isEmpty(tasks)) {
            try {
                //把链路存到数据库中。
                taskDataHandler.insertAll(tasks);
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
    private List<TaskDataLog> queryAllTaskLog(DBConfigData dbConfig, Link link, long maxLoggerId){
        //先从缓存中获取
        JdbcTemplate jdbcTemplate = dynamicDB.get8dbConfig(dbConfig);

        String sql = "select dayloggerid,taskid,totalSuccessNum,totalSuccessFlow,totalErrorFlow,totalErrorNum,insertTimes from tbdaylogger where dayloggerid > "+maxLoggerId;
        //查询所有task
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList(sql);

        if(CollectionUtils.isEmpty(mapList)){
            return null;
        }

        List<TaskDataLog> tasks = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {

//            tasks.add(taskLog);
        });
        return tasks;
    }


    /**
     * 要访问的表的建表语句
     CREATE TABLE `tbdatalogger` (
     `dataloggerid`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
     `level`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `datetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
     `uname`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `module`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `program`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
     `ttaskid`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `sourceresourceid`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `subsourcetb`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
     `targetsourceid`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `subtargettb`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
     `status`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `datanum`  double NULL DEFAULT NULL ,
     `descr`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
     `opertime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
     `alarmdisposeid`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `tberrordictid`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `datasize`  double NULL DEFAULT NULL ,
     `sfile`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     `tfile`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
     PRIMARY KEY (`dataloggerid`),
     UNIQUE INDEX `dataloggerid` (`dataloggerid`) USING BTREE ,
     INDEX `IDX_LEVEL` (`level`) USING BTREE ,
     INDEX `IDX_DATETIME` (`datetime`) USING BTREE ,
     INDEX `IDX_TASKID` (`ttaskid`) USING BTREE ,
     INDEX `IDX_DATANUM` (`datanum`) USING BTREE ,
     INDEX `IDX_DATASIZE` (`datasize`) USING BTREE ,
     INDEX `IDX_SSOURCE` (`sourceresourceid`) USING BTREE ,
     INDEX `IDX_TSOURCE` (`targetsourceid`) USING BTREE
     )
     ENGINE=InnoDB
     DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
     ROW_FORMAT=DYNAMIC
     ;
     */



}
