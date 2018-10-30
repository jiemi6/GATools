package com.minkey.controller;

import com.minkey.db.LinkHandler;
import com.minkey.db.dao.Link;
import com.minkey.dto.JSONMessage;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 链路接口
 */
@Slf4j
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;

    @RequestMapping("/insert")
    public String insert(Link link) {
        log.info("start: 执行新增链路 link={} ",link);

        if(StringUtils.isEmpty(link.getLinkName())
                || StringUtils.isEmpty(link.getDbConfigData().getIp())
                || StringUtils.isEmpty(link.getDbConfigData().getPwd())
                || StringUtils.isEmpty(link.getDbConfigData().getName())
                || StringUtils.isEmpty(link.getDbConfigData().getDbName())
                || link.getDbConfigData().getPort() <= 0
                || link.getLinkType() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect = dynamicDB.testDB(link.getDbConfigData());
            if(!isConnect){
                return JSONMessage.createFalied("数据库连接失败").toString();
            }

            linkHandler.insert(link);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行新增链路 link={} ",link);
        }
    }

    @RequestMapping("/update")
    public String update(Link link) {
        log.info("start: 执行新增链路 link={} ",link);
        if(link.getLinkId() == 0){
            return JSONMessage.createFalied("修改时id不能为空").toString();
        }

        if(StringUtils.isEmpty(link.getLinkName())
                || StringUtils.isEmpty(link.getDbConfigData().getIp())
                || StringUtils.isEmpty(link.getDbConfigData().getPwd())
                || StringUtils.isEmpty(link.getDbConfigData().getName())
                || StringUtils.isEmpty(link.getDbConfigData().getDbName())
                || link.getDbConfigData().getPort() <= 0
                || link.getLinkType() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect = dynamicDB.testDB(link.getDbConfigData());
            if(!isConnect){
                return JSONMessage.createFalied("数据库连接失败").toString();
            }

            linkHandler.update(link);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行新增链路 link={} ",link);
        }
    }





    @RequestMapping("/query")
    public String query(Long linkId) {
        log.info("start: 执行query设备 linkId={} ",linkId);
        if(linkId == null){
            log.info("linkId不能为空");
            return JSONMessage.createFalied("linkId不能为空").toString();
        }

        try{
            return JSONMessage.createSuccess().addData(linkHandler.query(linkId)).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行query设备 linkId={} ",linkId);
        }
    }

    @RequestMapping("/queryAll")
    public String queryAll() {
        log.info("start: 执行query所有链路");

        try{
            return JSONMessage.createSuccess().addData("list",linkHandler.queryAll()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行query所有设备 ");
        }
    }

    @RequestMapping("/queryCount")
    public String queryCount() {
        log.info("start: 执行count所有设备 ");

        try{
            return JSONMessage.createSuccess().addData("total",linkHandler.queryCount()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行count所有设备 ");
        }
    }

    @RequestMapping("/delete")
    public String delete(Long linkId) {
        log.info("start: 执行删除link, linkId={} ",linkId);
        if(linkId == null){
            return JSONMessage.createFalied("linkId不能为空").toString();
        }
        try{
            linkHandler.del(linkId);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行删除link ");
        }
    }

}