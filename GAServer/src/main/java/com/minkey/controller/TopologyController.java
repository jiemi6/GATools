package com.minkey.controller;

import com.minkey.cache.DeviceCache;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Link;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.TopologyNode;
import com.minkey.handler.DeviceConnectHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 网络拓扑接口
 */
@Slf4j
@RestController
@RequestMapping(value = "/topology")
public class TopologyController {
    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DeviceConnectHandler linkCheckHandler;

    @Autowired
    DeviceConnectCache deviceConnectCache;

    @Autowired
    DeviceCache deviceCache;


    @Deprecated
//    @RequestMapping("/queryAll")
    public String queryAll() {
        log.debug("start: 执行查询所有拓扑节点");

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
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行查询所有拓扑节点");
        }
    }

    /**
     * 根据链路id查找该链路拓扑图
     * @param linkId
     * @return
     */
    @RequestMapping("/query8linkId")
    public String query8linkId(Long linkId) {
        log.debug("start: 执行根据链路id查找该链路拓扑图 linkId={} ",linkId);
        if(linkId == null){
            log.info("linkId不能为空");
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
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行根据链路id查找该链路拓扑图 linkId={} ",linkId);
        }
    }


    /**
     * 查询所有设备的连接情况,每隔5秒重复刷新
     * @return
     */
    @RequestMapping("/queryAllConnect")
    public String queryAllConnect() {
        log.debug("start: 查询所有可连接的设备集合");
        try{
            Set<Long> deviceId = deviceConnectCache.getOkSet();
            return JSONMessage.createSuccess().addData("connectIds",deviceId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 查询所有可连接的设备集合");
        }
    }

    /**
     * 查询所有设备允许时的级别状态
     * @return
     */
    @RequestMapping("/queryAllLevel")
    public String queryAllLevel() {
        log.debug("start: 查询所有设备状态级别");
        try{
            Map<Long, Integer> deviceId = deviceCache.getAllDeviceLevel();
            return JSONMessage.createSuccess().addData(deviceId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 查询所有设备状态级别");
        }
    }



}