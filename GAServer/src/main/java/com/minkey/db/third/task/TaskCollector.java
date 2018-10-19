package com.minkey.db.third.task;

import com.minkey.db.LinkHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.Task;
import com.minkey.dto.DBConfigData;
import com.minkey.util.DynamicDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;


    @Value("${system.debug}")
    private boolean isDebug;

    /**
     * 每天凌晨 1：30执行
     */
//    @Scheduled(cron="0 30 1 * ? *")
    public void getAllTask(){
        if(isDebug){
            logger.error("测试抓取任务列表调度.");
            return;
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

        linkList.forEach(link -> {
            List<Task> tasks = null;
            try {
                //从链路中获取数据交换系统的数据库配置
                DBConfigData dbConfig = link.getDbConfigData();

                tasks = queryAllTask(dbConfig);

            }catch (Exception e){
                logger.error("从交换系统抓起任务列表异常",e);
            }

            if(!CollectionUtils.isEmpty(tasks)){
                try {
                    //把链路存到数据库中。
                    taskHandler.repleaceAll(tasks);
                }catch (Exception e){
                    logger.error("把抓取过来的任务保存到数据库中异常",e);
                }
            }
        });
    }

    /**
     * 从数据交换系统获取任务列表
     * @param dbConfig
     * @return
     */
    private List<Task> queryAllTask(DBConfigData dbConfig){
        //先从缓存中获取
        JdbcTemplate jdbcTemplate = dynamicDB.get(dbConfig.getIp(),dbConfig.getPort(),dbConfig.getName());
        //没有就新建
        if(jdbcTemplate == null){
            String url = "jdbc:mysql://"+dbConfig.getIp()+":"+dbConfig.getPort()+"/"+dbConfig.getName()+"?useUnicode=true&characterEncoding=utf-8";
            jdbcTemplate = dynamicDB.getJdbcTemplate(url,dbConfig.getDatabaseDriver(),dbConfig.getName(),dbConfig.getPwd());
            //放回缓存
            dynamicDB.putIn(dbConfig.getIp(),dbConfig.getPort(),dbConfig.getName(),jdbcTemplate);
        }

        //查询所有task
        List<Map<String, Object>>  mapList= jdbcTemplate.queryForList("select taskid,name, from tbtask");

        if(CollectionUtils.isEmpty(mapList)){
            return null;
        }

        List<Task> tasks = new ArrayList<>(mapList.size());

        mapList.forEach(stringObjectMap -> {
            Task task = new Task();
            task.setTaskId((String)stringObjectMap.get("task"));
            task.setTaskName((String) stringObjectMap.get("name"));

            tasks.add(task);
        });
        return tasks;
    }


    /**
     * 要访问的表的建表语句
     * CREATE TABLE `tbtask` (
     *   `taskid` varchar(15) NOT NULL COMMENT 'ID',
     *   `bussid` varchar(15) DEFAULT NULL,
     *   `name` varchar(30) NOT NULL COMMENT '任务名称',
     *   `synchronouscycle` varchar(20) DEFAULT NULL,
     *   `transfers` varchar(20) DEFAULT NULL COMMENT '同步条数',
     *   `timecontrol` varchar(1) DEFAULT NULL,
     *   `issavesourdata` varchar(5) NOT NULL COMMENT '源端数据保留，0为否1为是',
     *   `isvirusscan` varchar(1) DEFAULT NULL COMMENT '是否杀毒：0为否1为是；',
     *   `fileuplimit` int(11) DEFAULT NULL COMMENT '带宽分配',
     *   `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     *   `suffixcheck` varchar(200) DEFAULT NULL,
     *   `is2way` varchar(2) DEFAULT NULL,
     *   `status` varchar(5) DEFAULT NULL COMMENT '-100表示已删除，0表示新增,(2,-3,3)停止,(22,23,26)启动中，(1,4,13,25,28,29)运行，(24,27)停止中，其他表示异常',
     *   `createdatetime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
     *   `istaskhighconf` varchar(2) DEFAULT NULL,
     *   `priority` varchar(5) DEFAULT NULL,
     *   `stardatetime` datetime DEFAULT NULL,
     *   `enddatetime` datetime DEFAULT NULL,
     *   `flux` int(11) DEFAULT NULL,
     *   `runcount` int(11) DEFAULT NULL,
     *   `tbmguserid` varchar(15) DEFAULT NULL COMMENT '负载均衡类型为1时，负载状态下节点异常是否允许切换，y允许，n不允许',
     *   `tbtasktypeid` varchar(2) DEFAULT NULL COMMENT '任务类型',
     *   `tbtaskgroupid` varchar(15) DEFAULT '1',
     *   `tbdirectionid` varchar(2) DEFAULT NULL COMMENT '方向',
     *   `tbactionid` varchar(2) DEFAULT NULL COMMENT '采集类型',
     *   `exceptionnum` int(11) DEFAULT NULL COMMENT '异常连接次数',
     *   `exceptiontime` int(11) DEFAULT NULL COMMENT '异常时间',
     *   `triggertype` varchar(6) DEFAULT NULL COMMENT '触发类型',
     *   `pkimpact` varchar(1) DEFAULT NULL COMMENT '主键冲突策略',
     *   `no_traffic_alarm` varchar(10) DEFAULT NULL,
     *   `runtime_memory` varchar(10) DEFAULT NULL,
     *   `bakfilepath` varchar(100) DEFAULT NULL,
     *   PRIMARY KEY (`taskid`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */

}
