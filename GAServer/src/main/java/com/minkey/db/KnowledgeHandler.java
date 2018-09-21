package com.minkey.db;

import com.minkey.db.dao.Knowledge;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KnowledgeHandler {
    private final static Logger logger = LoggerFactory.getLogger(KnowledgeHandler.class);

    private final String tableName = "t_knowledge";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void up(Long knowledgeId) {
        int num = jdbcTemplate.update("update "+tableName+" SET upNum = upNum+1 where knowledgeId=?",Integer.class);
        if(num == 0){
            logger.error("点赞知识点失败，knowledgeId={}",knowledgeId);
        }
    }

    public void insert(Knowledge knowledge) {
        int num = jdbcTemplate.update("replace into "+tableName+" (configKey, configData) VALUES (?,?)",new Object[]{knowledge});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }

    public List<Knowledge> query8errorId(long errorId) {
        return jdbcTemplate.queryForList("select * from "+tableName+" where errorId= ? ORDER BY upNum desc ",new Object[]{errorId},Knowledge.class);
    }

}
