package com.minkey.controller;

import com.minkey.cache.DeviceCache;
import com.minkey.contants.Modules;
import com.minkey.db.*;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.Link;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    HttpSession session;
    @Autowired
    DeviceCache deviceCache;

    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    TaskDayLogHandler taskDayLogHandler;

    @RequestMapping("/insert")
    public String insert(Link link) {
        log.info("start: 执行新增链路 link={} ",link);

        if(StringUtils.isEmpty(link.getLinkName())
                || StringUtils.isEmpty(link.getDbConfigData().getIp())
                || StringUtils.isEmpty(link.getDbConfigData().getPwd())
                || StringUtils.isEmpty(link.getDbConfigData().getName())
                || StringUtils.isEmpty(link.getDbConfigData().getDbName())
                || link.getDbConfigData().getPort() <= 0
                || link.getLinkType() == null){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect = dynamicDB.testDBConnect(link.getDbConfigData());
            if(!isConnect){
                return JSONMessage.createFalied("数据库连接失败").toString();
            }

            linkHandler.insert(link);

            User sessionUser = (User) session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser, Modules.link,String.format("%s 新建链路，链路名称=%s ",sessionUser.getuName(),link.getLinkName()));

            //刷新缓存
            deviceCache.refresh();

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
            boolean isConnect = dynamicDB.testDBConnect(link.getDbConfigData());
            if(!isConnect){
                return JSONMessage.createFalied("数据库连接失败").toString();
            }

            linkHandler.update(link);

            User sessionUser = (User) session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser, Modules.link,String.format("%s 修改链路，链路名称=%s ",sessionUser.getuName(),link.getLinkName()));

            //刷新缓存
            deviceCache.refresh();

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


    @RequestMapping("/queryAllIdAndName")
    public String queryAllIdAndName() {
        log.info("start: 查询所有链路id和名称");

        try{
            return JSONMessage.createSuccess().addData("list",linkHandler.queryAllIdAndName()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 查询所有链路id和名称");
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

    @Autowired
    TaskHandler taskHandler;
    @Autowired
    TaskSourceHandler taskSourceHandler;
    @Autowired
    SourceHandler sourceHandler;

    @RequestMapping("/delete")
    public String delete(Long linkId) {
        log.info("start: 执行删除link, linkId={} ",linkId);
        if(linkId == null){
            return JSONMessage.createFalied("linkId不能为空").toString();
        }
        try{

            //删除链路下的所有任务报警
            alarmLogHandler.deleteTask8LinkId(linkId);
            //删除任务每日统计
            taskDayLogHandler.delete8LinkId(linkId);
            //删除链路报警日志
            alarmLogHandler.delete8Id(AlarmLog.BTYPE_LINK,linkId);

            //删除链路的任务
            taskHandler.del8LinkId(linkId);
            //删除链路与数据源的对应关系
            taskSourceHandler.del8LinkId(linkId);
            //删除链路对应的数据源
            sourceHandler.del8LinkId(linkId);
            //真正删除链路
            linkHandler.del(linkId);

            User sessionUser = (User) session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser, Modules.link,String.format("%s 删除链路，链路id=%s ",sessionUser.getuName(),linkId));

            //刷新缓存
            deviceCache.refresh();

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行删除link ");
        }
    }

}