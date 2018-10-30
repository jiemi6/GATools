package com.minkey.controller;

import com.minkey.db.KnowledgeHandler;
import com.minkey.db.dao.Knowledge;
import com.minkey.dto.JSONMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 知识点接口
 */
@Slf4j
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeHandler knowledgeHandler;

    @RequestMapping("/insert")
    public String insert(Knowledge knowledge) {
        log.info("start: 执行insert知识点 knowledge={} ",knowledge);

        try{
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
     * @param errorId
     * @return
     */
    @RequestMapping("/query8errorId")
    public String query8errorId(Long errorId) {
        log.info("start: 执行查询知识点 errorId={} ",errorId);
        if(errorId == null){
            log.info("knowledgeId不能为空");
            return JSONMessage.createFalied("knowledgeId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(knowledgeHandler.query8errorId(errorId)).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行查询知识点 errorId={} ",errorId);
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