package com.minkey.controller;

import com.minkey.db.TaskLogHandler;
import com.minkey.db.dao.TaskLog;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计分析
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    private final static Logger logger = LoggerFactory.getLogger(AnalysisController.class);

    @Autowired
    TaskLogHandler taskLogHandler;

    /**
     * 任务统计分析 分页数据
     * @return
     */
    @RequestMapping("/task")
    public String task(Long linkId,Integer currentPage,Integer pageSize) {
        logger.info("start: 执行分页查询任务统计分析数据列表 currentPage={} ,pageSize={}" , currentPage,pageSize);
        if(linkId == null || linkId <=0
                || currentPage == null || currentPage <=0
                || pageSize == null || pageSize <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            Page<TaskLog> page = new Page(currentPage,pageSize);

            Page<TaskLog> logs = taskLogHandler.query8page(linkId,page);
            return JSONMessage.createSuccess().addData(logs).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行分页查询任务统计分析数据列表 ");
        }
    }

    /**
     * 任务统计分析 总计
     * @return
     */
    @RequestMapping("/taskCount")
    public String taskCount(Long linkId) {
        logger.info("start: 执行查询任务统计分析数据总计");
        if(linkId == null || linkId <=0){
            return JSONMessage.createFalied("参数错误").toString();
        }
        try{

            TaskLog allSum = taskLogHandler.querySum(linkId);
            return JSONMessage.createSuccess().addData("sum",allSum).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:  执行分页查询任务统计分析数据列表 ");
        }
    }


}