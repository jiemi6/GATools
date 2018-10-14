package com.minkey.controller;

import com.minkey.db.dao.Knowledge;
import com.minkey.db.KnowledgeHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 知识点接口
 */
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    private final static Logger logger = LoggerFactory.getLogger(KnowledgeController.class);

    @Autowired
    KnowledgeHandler knowledgeHandler;

    @RequestMapping("/insert")
    public String insert(Knowledge knowledge) {
        logger.info("start: 执行insert知识点 knowledge={} ",knowledge);

        try{
            knowledgeHandler.insert(knowledge);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行insert知识点 knowledge={} ",knowledge);
        }
    }

    /**
     * 根据错误id，查询所有可能的知识点
     * @param errorId
     * @return
     */
    @RequestMapping("/query8errorId")
    public String query8errorId(Long errorId) {
        logger.info("start: 执行查询知识点 errorId={} ",errorId);
        if(errorId == null){
            logger.info("knowledgeId不能为空");
            return JSONMessage.createFalied("knowledgeId不能为空").toString();
        }
        try{
            return JSONMessage.createSuccess().addData(knowledgeHandler.query8errorId(errorId)).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行查询知识点 errorId={} ",errorId);
        }
    }


    /**
     * 点赞一次
     * @return
     */
    @RequestMapping("/up")
    public String up(Long knowledgeId) {
        logger.info("start: 执行up知识点 ");
        if(knowledgeId == null){
            logger.info("knowledgeId不能为空");
            return JSONMessage.createFalied("knowledgeId不能为空").toString();
        }
        try{
            knowledgeHandler.up(knowledgeId);
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end:执行up知识点  ");
        }
    }
}