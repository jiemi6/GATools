package com.minkey.controller;

import com.minkey.contants.Modules;
import com.minkey.db.KnowledgeHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.Check;
import com.minkey.db.dao.Knowledge;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.dto.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 知识点接口
 */
@Slf4j
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeHandler knowledgeHandler;

    @Autowired
    HttpSession session;

    @Autowired
    UserLogHandler userLogHandler;

    @RequestMapping("/insert")
    public String insert(Knowledge knowledge) {
        log.info("start: 执行insert知识点 knowledge={} ",knowledge);

        try{
            User sessionUser = (User)session.getAttribute("user");
            //记录用户日志
            userLogHandler.log(sessionUser,Modules.device,String.format("[%s]新增知识库[%s]",sessionUser.getuName(),knowledge.getKnowledgeDesc()));

            knowledge.setUid(sessionUser.getUid());

            knowledgeHandler.insert(knowledge);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行insert知识点 knowledge={} ",knowledge);
        }
    }

    /**
     * 根据错误id，查询所有可能的知识点
     * @param alarmType
     * @return
     */
    @RequestMapping("/query8AlarmType")
    public String query8AlarmType(Integer currentPage,Integer pageSize, Integer alarmType) {
        log.info("start: 执行查询知识点 alarmType={} ",alarmType);
        if(alarmType == null){
            log.info("knowledgeId不能为空");
            return JSONMessage.createFalied("knowledgeId不能为空").toString();
        }
        try{
            Page<Knowledge> page = new Page(currentPage,pageSize);

            page = knowledgeHandler.query8AlarmType(page,alarmType);

            return JSONMessage.createSuccess().addData(page).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行查询知识点 alarmType={} ",alarmType);
        }
    }


    /**
     * 点赞一次
     * @return
     */
    @RequestMapping("/up")
    public String up(Long knowledgeId) {
        log.info("start: 执行up知识点 ");
        if(knowledgeId == null){
            log.info("knowledgeId不能为空");
            return JSONMessage.createFalied("knowledgeId不能为空").toString();
        }
        try{
            knowledgeHandler.up(knowledgeId);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:执行up知识点  ");
        }
    }
}