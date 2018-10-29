package com.minkey.db.third.task;

import com.minkey.contants.LinkType;
import com.minkey.db.LinkHandler;
import com.minkey.db.SourceHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.TaskSourceHandler;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.Source;
import com.minkey.db.dao.Task;
import com.minkey.db.dao.TaskSource;
import com.minkey.dto.DBConfigData;
import com.minkey.util.DynamicDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Component
public class TaskCollector {
    private final static Logger logger = LoggerFactory.getLogger(TaskCollector.class);

    @Autowired
    TaskHandler taskHandler;

    @Autowired
    TaskSourceHandler taskSourceHandler;

    @Autowired
    SourceHandler sourceHandler;

    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;


    @Value("${system.debug}")
    private boolean isDebug;

    /**
     * 每天凌晨 1：30执行
     */
    @Scheduled(cron="0 30 1 * * ?")
    public void getTaskFromOtherDB(){
        if(isDebug){
            logger.error("测试抓取任务列表调度.");
//            return;
        }
        List<Link> linkList = null;
        try {
            //查询所有链路
            linkList = linkHandler.queryAll();
            if (CollectionUtils.isEmpty(linkList)) {
                return;
            }
        }catch (Exception e){
            logger.error("获取所有的链路异常",e);
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
                logger.error("从交换系统抓起任务列表异常", e);
                continue;
            }


            if (link.getLinkType() == LinkType.shujujiaohuan) {
                shujujiaohuan(link,jdbcTemplate);
            }

        }
    }

    private void shujujiaohuan(Link link, JdbcTemplate jdbcTemplate) {
        try {
            //获取task
            collectorTask(jdbcTemplate, link);
        } catch (Exception e) {
            logger.error("从数据交换系统抓取任务保存到数据库中异常", e);
        }
        try {
            //获取taskSource
            collectorTaskSource(jdbcTemplate, link);
        } catch (Exception e) {
            logger.error("从数据交换系统抓取任务与数据源对应关系保存到数据库中异常", e);
        }
        try {
            //获取Source
            collectorSource(jdbcTemplate, link);
        } catch (Exception e) {
            logger.error("从数据交换系统抓取数据源保存到数据库中异常", e);
        }
    }


    /**
     * 迁移tbtask所有数据到自己的 t_task表中
     * @param jdbcTemplate
     * @param link
     */
    private void collectorTask(JdbcTemplate jdbcTemplate, Link link){
            //查询所有task
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select taskid,name,status from tbtask WHERE status <> '-100'");

        if(CollectionUtils.isEmpty(mapList)){
            taskHandler.del(link.getLinkId());
            return;
        }

        List<Task> tasks = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {
            Task task = new Task();
            task.setTargetId((String)stringObjectMap.get("taskId"));
            task.setTaskName((String) stringObjectMap.get("name"));
            task.setLinkId(link.getLinkId());
            task.setStatus(Integer.valueOf((String)stringObjectMap.get("status")));
            tasks.add(task);
        });

        taskHandler.del(link.getLinkId());
        if(!CollectionUtils.isEmpty(tasks)){
            //把链路存到数据库中。
            taskHandler.insertAll(tasks);
        }

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
            taskHandler.del(link.getLinkId());
            return;
        }

        List<TaskSource> tasks = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {
            TaskSource task = new TaskSource();
            task.setTaskId((String)stringObjectMap.get("taskid"));
            task.setTargetId((String) stringObjectMap.get("taskdbsourceid"));
            task.setCreateTime((Date)stringObjectMap.get("createdatetime"));
            task.setFromResourceId((String) stringObjectMap.get("sresourcesid"));
            task.setToResourceId((String)stringObjectMap.get("tresourcesid"));
            task.setLinkId(link.getLinkId());
            tasks.add(task);
        });

        taskSourceHandler.del(link.getLinkId());
        if(!CollectionUtils.isEmpty(tasks)){
            //把链路存到数据库中。
            taskSourceHandler.insertAll(tasks);
        }

    }

    private void collectorSource(JdbcTemplate jdbcTemplate, Link link){
        //查询所有source
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select * from  tbresources ");

        if(CollectionUtils.isEmpty(mapList)){
            taskHandler.del(link.getLinkId());
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
            Source task = new Source();
            task.setTargetId((String) stringObjectMap.get("resourcesid"));
            task.setSname((String)stringObjectMap.get("name"));
            task.setIp((String) stringObjectMap.get("ip"));
            task.setPort((Integer)stringObjectMap.get("port"));
            task.setDbName((String) stringObjectMap.get("dbname"));
            task.setName((String) stringObjectMap.get("username"));
            task.setPwd((String) stringObjectMap.get("password"));
            task.setSourceType(sourceTypeMap.get((String)stringObjectMap.get("tbresourcetypeid")));
            task.setDbVersion(driverMap.get((String)stringObjectMap.get("db_driver_id")));
            task.setCreateTime((Date)stringObjectMap.get("createdate"));
            task.setLinkId(link.getLinkId());
            sources.add(task);
        });

        sourceHandler.del(link.getLinkId());
        if(!CollectionUtils.isEmpty(sources)){
            //把链路存到数据库中。
            sourceHandler.insertAll(sources);
        }

    }
}
