package com.minkey.controller;

import com.minkey.db.dao.Link;
import com.minkey.db.LinkHandler;
import com.minkey.dto.JSONMessage;
import com.minkey.exception.DataException;
import com.minkey.util.DynamicDB;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 链路接口
 */
@RestController
@RequestMapping("/link")
public class LinkController {
    private final static Logger logger = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    LinkHandler linkHandler;

    @Autowired
    DynamicDB dynamicDB;

    @RequestMapping("/insert")
    public String insert(Link link) {
        logger.info("start: 执行新增链路 link={} ",link);

        if(StringUtils.isEmpty(link.getLinkName())
                || StringUtils.isEmpty(link.getDbConfig().getDbIp())
                || StringUtils.isEmpty(link.getDbConfig().getDbPwd())
                || StringUtils.isEmpty(link.getDbConfig().getDbUserName())
                || StringUtils.isEmpty(link.getDbConfig().getDbName())
                || link.getDbConfig().getDbPort() <= 0
                || link.getLinkType() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            testDB(link);

            linkHandler.insert(link);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end: 执行新增链路 link={} ",link);
        }
    }

    private void testDB(Link link){
        try{
            String jdbcUrl = "jdbc:mysql://"+link.getDbConfig().getDbIp()+":"+link.getDbConfig().getDbPort()+"/"+link.getDbConfig().getDbName()+"?useUnicode=true&characterEncoding=utf-8";
            //先检查数据库是否正确
            JdbcTemplate jdbcTemplate = dynamicDB.getJdbcTemplate(jdbcUrl,DatabaseDriver.MYSQL,link.getDbConfig().getDbUserName(),link.getDbConfig().getDbPwd());

        }catch (Exception e){
            throw new DataException("尝试连接数据库失败",e);
        }
    }

    @RequestMapping("/query")
    public String query(Long linkId) {
        logger.info("start: 执行query设备 linkId={} ",linkId);
        if(linkId == null){
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