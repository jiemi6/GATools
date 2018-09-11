package com.minkey.controller;

import com.minkey.db.dao.Topology;
import com.minkey.db.TopologyHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网络拓扑接口
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
     * 根据链路id查找该链路拓扑图
     * @param linkId
     * @return
     */
    @RequestMapping("/query8linkId")
    public String query8linkId(Long linkId) {
        logger.info("start: 执行查询知识点 linkId={} ",linkId);
        if(linkId == null){
            logger.info("topologyId不能为空");
            return JSONMessage.createFalied("topologyId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(topologyHandler.query8LinkId(linkId)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行查询知识点 linkId={} ",linkId);
        }
    }



}