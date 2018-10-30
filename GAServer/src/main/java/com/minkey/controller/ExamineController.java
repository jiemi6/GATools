package com.minkey.controller;

import com.minkey.db.CheckHandler;
import com.minkey.db.dao.Check;
import com.minkey.db.dao.CheckItem;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.handler.ExamineHandler;
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
        //存入数据库，获取id
        long checkId = checkHandler.insert(check);

        try{
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
        //存入数据库，获取id
        long checkId = checkHandler.insert(check);

        try{
            //开始检查
            examineHandler.doLink(checkId,linkId);
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
        //存入数据库，获取id
        long checkId = checkHandler.insert(check);

        try{
            //开始检查
            examineHandler.doDevice(checkId,deviceId);
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
        //存入数据库，获取id
        long checkId = checkHandler.insert(check);

        try{
            //开始检查
            examineHandler.doTask(checkId,taskId);
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
            List<CheckItem> checkItems = examineHandler.getResultList(checkId,index);

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
            List<CheckItem> checkItems = examineHandler.getResultList(checkId);

            //Minkey 处理成文件返回

            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行体检信息");
        }
    }


}