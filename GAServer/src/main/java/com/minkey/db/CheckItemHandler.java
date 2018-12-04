package com.minkey.db;

import com.minkey.cache.CheckStepCache;
import com.minkey.db.dao.CheckItem;
import com.minkey.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckItemHandler {
    private final String tableName = "t_checkItem";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CheckStepCache checkStepCache;

    
    public void insert(CheckItem checkItem) {
        int num = jdbcTemplate.update("INSERT into "+tableName+" (checkId,resultMsg,step,totalStep,itemType,resultLevel,errorType) VALUES (?,?,?,?,?,?,?)"
                ,new Object[]{checkItem.getCheckId(),checkItem.getResultMsg(),checkItem.getStep(),checkItem.getTotalStep(),checkItem.getItemType(),checkItem.getResultLevel(),checkItem.getErrorType()});

        if(num == 0){
            throw new SystemException("新增失败");
        }
    }

    public List<CheckItem> query(Long checkId, Integer indexItemId) {
        return jdbcTemplate.query("select * from "+tableName+" where checkId= ? AND itemId > ? ORDER BY itemId LIMIT 0,5 ",new Object[]{checkId,indexItemId},new BeanPropertyRowMapper<>(CheckItem.class));
    }

    public List<CheckItem> query8CheckId(Long checkId) {
        return jdbcTemplate.query("select * from "+tableName+" where checkId= ? ORDER BY itemId ",new Object[]{checkId},new BeanPropertyRowMapper<>(CheckItem.class));
    }

    public List<CheckItem> getLast10() {
        return jdbcTemplate.query("select * from "+tableName+" ORDER BY createTime limit 0,10",new BeanPropertyRowMapper<>(CheckItem.class));
    }
}
