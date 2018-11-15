package com.minkey.controller;

import com.minkey.cache.DeviceCache;
import com.minkey.contants.MyLevel;
import com.minkey.db.CheckHandler;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.JSONMessage;
import com.minkey.handler.ExamineHandler;
import com.minkey.handler.TaskExamineHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 体检
 */
@Slf4j
@RestController
@RequestMapping("/examine")
public class ExamineController {
    @Autowired
    HttpSession session;

    @Autowired
    CheckHandler checkHandler;

    @Autowired
    ExamineHandler examineHandler;

    @Autowired
    TaskExamineHandler taskExamineHandler;

    @Autowired
    CheckItemHandler checkItemHandler;
    @Autowired
    DeviceCache deviceCache;
    @Autowired
    TaskHandler taskHandler;

    /**
     * 一键体检
     * @return
     */
    @RequestMapping("/allInOne")
    public String allInOne() {
        log.info("start: 执行一键体检");

        User user = (User) session.getAttribute("user");

        Check check = new Check();
        check.setCheckName(user.getuName()+"发起一键体检");
        check.setCheckType(Check.CHECKTYPE_ALLINONE);
        check.setUid(user.getUid());

        try{
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);
            //开始检查
            examineHandler.doAllInOne(checkId);
            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行系统自检");
        }
    }

    /**
     * 链路体检
     * @return
     */
    @RequestMapping("/link")
    public String link(Long linkId) {
        log.info("start: 执行链路体检 linkId={} ",linkId);
        if(linkId == null){
            return JSONMessage.createFalied("linkId不能为空").toString();
        }

        User user = (User) session.getAttribute("user");

        Check check = new Check();
        check.setCheckName(user.getuName()+"发起链路体检");
        check.setCheckType(Check.CHECKTYPE_ALLINONE);
        check.setUid(user.getUid());

        try{
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);

            Link link = deviceCache.getLink8Id(linkId);
            if(link == null){
                log.error("发起单个链路体检，链路不存在 linkId = {}" ,linkId);
                //不存在就只有一步
                CheckItem checkItem = new CheckItem(checkId,1);
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
                checkItem.setResultMsg(String.format("链路不存在，链路id=%s",linkId));
                checkItemHandler.insert(checkItem);
            }else {
                //开始检查
                examineHandler.doLinkAynsc(checkId,link);
            }
            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行链路体检");
        }
    }

    /**
     * 设备体检
     * @return
     */
    @RequestMapping("/device")
    public String device(Long deviceId) {
        log.info("start: 执行设备体检 deviceId={} ",deviceId);
        if(deviceId == null){
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }

        User user = (User) session.getAttribute("user");

        Check check = new Check();
        check.setCheckName(user.getuName()+"发起设备体检");
        check.setCheckType(Check.CHECKTYPE_DEVICE);
        check.setUid(user.getUid());

        try{
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);

            Device device = deviceCache.getDevice(deviceId);
            if(device == null){
                log.error("发起单个设备体检，体检设备不存在 deviceId = {}" ,deviceId);
                //不存在就只有一步
                CheckItem checkItem = new CheckItem(checkId,1);
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
                checkItem.setResultMsg(String.format("设备不存在，设备id=%s",deviceId));
                checkItemHandler.insert(checkItem);
            }else{
                //开始检查
                examineHandler.doDeviceAsync(checkId,device);
            }
            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行设备体检");
        }
    }

    /**
     * 任务体检
     * @return
     */
    @RequestMapping("/task")
    public String task(Long taskId) {
        log.info("start: 执行任务体检 taskId={} ",taskId);
        if(taskId == null){
            return JSONMessage.createFalied("taskId不能为空").toString();
        }

        User user = (User) session.getAttribute("user");

        Check check = new Check();
        check.setCheckName(user.getuName()+"发起链路体检");
        check.setCheckType(Check.CHECKTYPE_ALLINONE);
        check.setUid(user.getUid());

        try{
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);


            Task task = taskHandler.query(taskId);
            if(task == null){
                log.error("发起单个任务体检，体检任务不存在 deviceId = {}" ,taskId);
                //不存在就只有一步
                CheckItem checkItem = new CheckItem(checkId,1);
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
                checkItem.setResultMsg(String.format("任务不存在，任务id=%s",taskId));
                checkItemHandler.insert(checkItem);
            }else{
                //开始检查
                taskExamineHandler.doTaskAsync(checkId,task);
            }


            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行任务体检");
        }
    }

    /**
     * 体检信息获取，页面不断扫描此接口获取数据
     * @return
     */
    @RequestMapping("/checkResult")
    public String checkResult(Long checkId,Integer index) {
        log.info("start: 获取体检信息 checkId={}，index={}",checkId,index);
        if(checkId == null || checkId <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(index == null || index <= 0){
            index = 0;
        }
        try{
            List<CheckItem> checkItems = checkItemHandler.query(checkId,index);

            return JSONMessage.createSuccess().addData("checkItems",checkItems).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行体检信息");
        }
    }

    /**
     * 下载体检结果文件报告
     * @return
     */
    @RequestMapping("/download")
    public String download(Long checkId) {
        log.info("start: 下载体检结果文件报告 checkId={}",checkId);
        if(checkId == null || checkId <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            List<CheckItem> checkItems = checkItemHandler.queryAll(checkId);

            //Minkey 下载体检结果文件

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行体检信息");
        }
    }


}