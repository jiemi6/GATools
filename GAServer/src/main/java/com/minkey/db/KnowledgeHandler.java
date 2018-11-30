package com.minkey.db;

import com.minkey.db.dao.Knowledge;
import com.minkey.dto.Page;
import com.minkey.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class KnowledgeHandler {
    private final String tableName = "t_knowledge";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void up(Long knowledgeId) {
        int num = jdbcTemplate.update("update "+tableName+" SET upNum = upNum+1 where knowledgeId=? ",new Object[]{knowledgeId});
    }


    public void insert(Knowledge knowledge) {
        int num = jdbcTemplate.update("replace into "+tableName+" (alarmType, knowledgeDesc, uid) VALUES (?,?,?)",
                new Object[]{knowledge.getAlarmType(),knowledge.getKnowledgeDesc(),knowledge.getUid()});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }

    public Page<Knowledge> query8AlarmType(Page<Knowledge> page, int alarmType) {

        List<Knowledge> list = jdbcTemplate.query("select * from "+tableName+" where alarmType= ? ORDER BY upNum desc limit ?,?",
                new Object[]{alarmType,page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(Knowledge.class));

        page.setData(list);

        page.setTotal(jdbcTemplate.queryForObject("select count(*) from "+tableName + " where alarmType= ? ",new Object[]{alarmType} ,Integer.class));

        return page;
    }

}
