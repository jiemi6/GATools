package com.minkey.controller;

import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Link;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.TopologyNode;
import com.minkey.handler.DeviceStatusHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 网络拓扑接口
 */
@RestController
@RequestMapping(value = "/topology")
public class TopologyController {
    private final static Logger logger = LoggerFactory.getLogger(TopologyController.class);

    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DeviceStatusHandler linkCheckHandler;


    @RequestMapping("/queryAll")
    public String queryAll() {
        logger.info("start: 执行查询所有拓扑节点");

        try{
            List<TopologyNode> topologyNodes = new ArrayList<>();
            List<Link> linkList = linkHandler.queryAll();
            if(!CollectionUtils.isEmpty(linkList)){
                linkList.forEach(link -> {
                    topologyNodes.addAll(link.getTopologyNodes());
                });
            }

            return JSONMessage.createSuccess().addData("list",topologyNodes).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行查询所有拓扑节点");
        }
    }

    /**
     * 根据链路id查找该链路拓扑图
     * @param linkId
     * @return
     */
    @RequestMapping("/query8linkId")
    public String query8linkId(Long linkId) {
        logger.info("start: 执行根据链路id查找该链路拓扑图 linkId={} ",linkId);
        if(linkId == null){
            logger.info("linkId不能为空");
            return JSONMessage.createFalied("linkId不能为空").toString();
        }
        try{
            Link link = linkHandler.query(linkId);
            List<TopologyNode> topologyNodes = new ArrayList<>();
            if(link != null){
                topologyNodes = link.getTopologyNodes();
            }

            return JSONMessage.createSuccess().addData("list",topologyNodes).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行根据链路id查找该链路拓扑图 linkId={} ",linkId);
        }
    }


    /**
     * 查询所有设备的连接情况,每隔5秒重复刷新
     * @return
     */
    @RequestMapping("/queryAllConnect")
    public String queryAllConnect() {
        logger.info("start: 查询所有可连接的设备");
        try{
            Set<Long> deviceId = linkCheckHandler.queryAllConnect();
            return JSONMessage.createSuccess().addData("connectIds",deviceId).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 查询所有可连接的设备");
        }
    }



}