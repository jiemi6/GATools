package com.minkey.db.third.task;

import com.minkey.contants.CommonContants;
import com.minkey.contants.LinkType;
import com.minkey.db.*;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.Source;
import com.minkey.db.dao.Task;
import com.minkey.db.dao.TaskSource;
import com.minkey.dto.DBConfigData;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 从数据交换系统，采集task信息
 * <br><br/>
 * 每天获取一次
 */
@Slf4j
@Component
public class TaskCollector {
    @Autowired
    TaskHandler taskHandler;

    @Autowired
    TaskSourceHandler taskSourceHandler;

    @Autowired
    TaskTriggerHandler taskTriggerHandler;

    @Autowired
    SourceHandler sourceHandler;

    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;


    @Value("${system.debug:false}")
    private boolean isDebug;

    /**
     * 每天凌晨 1：30执行
     */
    @Scheduled(cron="0 30 1 * * ?")
    public void getTaskFromOtherDB(){
        log.warn("开始抓取其他链路数据库信息...");

        List<Link> linkList = null;
        try {
            //查询所有链路
            linkList = linkHandler.queryAll();
            if (CollectionUtils.isEmpty(linkList)) {
                return;
            }
        }catch (Exception e){
            log.error("获取所有的链路异常",e);
            return;
        }

        for(Link link: linkList){
            JdbcTemplate jdbcTemplate;
            try {
                //从链路中获取数据交换系统的数据库配置
                DBConfigData dbConfig = link.getDbConfigData();
                //先从缓存中获取
                jdbcTemplate = dynamicDB.get8dbConfig(dbConfig);
            } catch (Exception e) {
                log.error("从交换系统抓起任务列表异常", e);
                continue;
            }


            if (link.getLinkType() == LinkType.shujujiaohuan) {
                shujujiaohuan(link,jdbcTemplate);
            }
        }

        log.warn("抓取其他链路数据库信息结束...");
    }

    public void shujujiaohuan(Link link, JdbcTemplate jdbcTemplate) {
        try {
            //获取task
            collectorTask(jdbcTemplate, link);
        } catch (Exception e) {
            log.error("从数据交换系统抓取[任务信息]保存到数据库中异常", e);
        }
        try {
            //获取taskSource
            collectorTaskSource(jdbcTemplate, link);
        } catch (Exception e) {
            log.error("从数据交换系统抓取[任务与数据源对应关系]保存到数据库中异常", e);
        }
        try {
            //获取Source
            collectorSource(jdbcTemplate, link);
        } catch (Exception e) {
            log.error("从数据交换系统抓取[数据源信息]保存到数据库中异常", e);
        }

        try {
            //获取任务与触发器的对应关系
            collectorTriggerinfo(jdbcTemplate, link);
        } catch (Exception e) {
            log.error("从数据交换系统抓取[数据源信息]保存到数据库中异常", e);
        }
    }

    private void collectorTriggerinfo(JdbcTemplate jdbcTemplate, Link link) {
        //查询所有触发器
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select id,taskid,resourcesid,tablename,trigname,trigtype,maxnum from tbtriggerinfo ");

        if(CollectionUtils.isEmpty(mapList)){
            taskTriggerHandler.del8LinkId(link.getLinkId());
            return;
        }
        List<Task> taskTriggerList = new ArrayList<>(mapList.size());

    }


    /**
     * 迁移tbtask所有数据到自己的 t_task表中
     * @param jdbcTemplate
     * @param link
     */
    private void collectorTask(JdbcTemplate jdbcTemplate, Link link){
        //查询所有task
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select taskid,name,tbtasktypeid,status from tbtask ");

        //先删除,软删除
        taskHandler.del8LinkId(link.getLinkId());

        if(CollectionUtils.isEmpty(mapList)){
            return;
        }

        mapList.forEach(stringObjectMap -> {
            Task task = new Task();
            task.setTargetTaskId((String)stringObjectMap.get("taskId"));
            task.setTaskName((String) stringObjectMap.get("name"));
            //在tbtask表中的tbtasktypeid字段 01说明是数据库同步，05是FTP文件同步
            String tasktypeid = (String)stringObjectMap.get("tbtasktypeid");
            if(StringUtils.equals(tasktypeid,"01")){
                task.setTaskType(Task.taskType_db);
            }else if(StringUtils.equals(tasktypeid,"05")){
                task.setTaskType(Task.taskType_ftp);
            }else{
                task.setTaskType(Task.taskType_unknow);
            }
            task.setLinkId(link.getLinkId());
            task.setStatus(Integer.valueOf((String)stringObjectMap.get("status")));

            //如果存在,则update
            if(taskHandler.isExist(link.getLinkId(),task.getTargetTaskId())){
                taskHandler.update(task);
            }else{
                //不存在则新增
                taskHandler.insert(task);
            }
        });

    }

    /**
     * 迁移tbtaskdbsource所有数据到自己的 t_tasksource表中
     * @param jdbcTemplate
     * @param link
     *
     * `taskdbsourceid`  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
    `taskid`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务ID' ,
    `createdatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `sresourcesid`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源id' ,
    `tresourcesid`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目的ID' ,
     */
    private void collectorTaskSource(JdbcTemplate jdbcTemplate, Link link){
        //查询所有taskSource
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select * from tbtaskdbsource");

        if(CollectionUtils.isEmpty(mapList)){
            taskSourceHandler.del8LinkId(link.getLinkId());
            return;
        }

        List<TaskSource> tasks = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {
            TaskSource taskSource = new TaskSource();
            taskSource.setTaskId((String)stringObjectMap.get("taskid"));
            taskSource.setTargetId((String) stringObjectMap.get("taskdbsourceid"));
            taskSource.setCreateTime((Date)stringObjectMap.get("createdatetime"));
            taskSource.setFromResourceId((String) stringObjectMap.get("sresourcesid"));
            taskSource.setToResourceId((String)stringObjectMap.get("tresourcesid"));
            taskSource.setLinkId(link.getLinkId());
            tasks.add(taskSource);
        });

        taskSourceHandler.del8LinkId(link.getLinkId());
        if(!CollectionUtils.isEmpty(tasks)){
            //把链路存到数据库中。
            taskSourceHandler.insertAll(tasks);
        }

    }

    private void collectorSource(JdbcTemplate jdbcTemplate, Link link){
        //查询所有source
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select * from  tbresources ");

        if(CollectionUtils.isEmpty(mapList)){
            sourceHandler.del8LinkId(link.getLinkId());
            return;
        }

        List<Map<String, Object>>  drivers = jdbcTemplate.queryForList("select * from  tbdatabase_driver");
        Map<String,String> driverMap = new HashMap<>(drivers.size());
        drivers.forEach(dbMap ->{
            driverMap.put((String)dbMap.get("id"),(String)dbMap.get("db_version"));
        });

        List<Map<String, Object>>  sourceType = jdbcTemplate.queryForList("select * from  tbresourcetype");
        Map<String,String> sourceTypeMap = new HashMap<>(drivers.size());
        sourceType.forEach(dbSourceType ->{
            sourceTypeMap.put((String)dbSourceType.get("resourcetypeid"),(String)dbSourceType.get("name"));

        });

        List<Source> sources = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {
            Source source = new Source();
            source.setTargetId((String) stringObjectMap.get("resourcesid"));
            source.setSname((String)stringObjectMap.get("name"));
            source.setIp((String) stringObjectMap.get("ip"));
            source.setPort((Integer)stringObjectMap.get("port"));
            //field方向，0内1外
            String field = (String)stringObjectMap.get("field");
            if(StringUtils.equals(field,"1")){
                source.setNetArea(CommonContants.NETAREA_OUT);
            }else if(StringUtils.equals(field,"0")){
                source.setNetArea(CommonContants.NETAREA_IN);
            }else{
                source.setNetArea(CommonContants.NETAREA_IN);
            }
            source.setDbName((String) stringObjectMap.get("dbname"));
            source.setName((String) stringObjectMap.get("username"));
            source.setPwd((String) stringObjectMap.get("password"));
            source.setSourceType(sourceTypeMap.get((String)stringObjectMap.get("tbresourcetypeid")));
            source.setDbVersion(driverMap.get((String)stringObjectMap.get("db_driver_id")));
            source.setCreateTime((Date)stringObjectMap.get("createdate"));
            source.setLinkId(link.getLinkId());
            sources.add(source);
        });

        sourceHandler.del8LinkId(link.getLinkId());
        if(!CollectionUtils.isEmpty(sources)){
            //把链路存到数据库中。
            sourceHandler.insertAll(sources);
        }

    }
}
