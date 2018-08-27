package com.minkey.controller;

import com.minkey.dao.Topology;
import com.minkey.db.TopologyHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 知识点接口
 */
@RestController
@RequestMapping("/topology")
public class TopologyController {
    private final static Logger logger = LoggerFactory.getLogger(TopologyController.class);

    @Autowired
    TopologyHandler topologyHandler;

    @RequestMapping("/insert")
    public String insert(Topology topology) {
        logger.info("start: 执行insert知识点 topology={} ",topology);

        try{
            topologyHandler.insert(topology);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行insert知识点 topology={} ",topology);
        }
    }

    /**
     * 根据错误id，查询所有可能的知识点
     * @param errorId
     * @return
     */
    @RequestMapping("/query8errorId")
    public String query8errorId(Long errorId) {
        logger.info("start: 执行查询知识点 errorId={} ",errorId);
        if(errorId == null){
            logger.info("topologyId不能为空");
            return JSONMessage.createFalied("topologyId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(topologyHandler.query8errorId(errorId)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行查询知识点 errorId={} ",errorId);
        }
    }


    /**
     * 点赞一次
     * @return
     */
    @RequestMapping("/up")
    public String up(Long topologyId) {
        logger.info("start: 执行up知识点 ");
        if(topologyId == null){
            logger.info("topologyId不能为空");
            return JSONMessage.createFalied("topologyId不能为空").toString();
        }
        try{
            topologyHandler.up(topologyId);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:执行up知识点  ");
        }
    }
}