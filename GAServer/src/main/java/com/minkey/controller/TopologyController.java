package com.minkey.controller;

import com.minkey.dao.Link;
import com.minkey.db.LinkHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 链路接口
 */
@RestController
@RequestMapping("/link")
public class TopologyController {
    private final static Logger logger = LoggerFactory.getLogger(TopologyController.class);

    @Autowired
    LinkHandler linkHandler;

    @RequestMapping("/insert")
    public String insert(Link link) {
        logger.info("start: 执行insert设备 link={} ",link);

        try{
            linkHandler.insert(link);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行insert设备 link={} ",link);
        }
    }

    @RequestMapping("/query")
    public String query(long linkId) {
        logger.info("start: 执行query设备 linkId={} ",linkId);
        if(linkId == 0){
            logger.info("linkId不能为空");
            return JSONMessage.createFalied("linkId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(linkHandler.query(linkId)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query设备 linkId={} ",linkId);
        }
    }

    @RequestMapping("/queryAll")
    public String queryAll() {
        logger.info("start: 执行query所有设备 ");

        try{
            return JSONMessage.createSuccess().addData(linkHandler.queryAll()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行query所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        logger.info("start: 执行count所有设备 ");

        try{
            return JSONMessage.createSuccess().addData(linkHandler.queryCount()).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行count所有设备 ");
        }
    }
}