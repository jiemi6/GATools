package com.minkey.db;

import com.minkey.db.dao.CheckItem;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckItemHandler {
    private final static Logger logger = LoggerFactory.getLogger(CheckItemHandler.class);

    private final String tableName = "t_checkItem";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void insert(CheckItem checkItem) {
        int num = jdbcTemplate.update("INSERT into "+tableName+" (checkId,resultMsg,step,totalStep,resultLevel,itemType,errorType) VALUES (?,?,?,?,?,?,?)"
                ,new Object[]{checkItem.getCheckId(),checkItem.getResultMsg(),checkItem.getStep(),checkItem.getTotalStep(),checkItem.getItemType(),checkItem.getResultLevel(),checkItem.getErrorType()});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }

    public List<CheckItem> query(Long checkId, Integer index) {
        return jdbcTemplate.query("select * from "+tableName+" where checkId= ? ORDER BY itemId LIMIT ?,10 ",new Object[]{checkId,index},new BeanPropertyRowMapper<>(CheckItem.class));
    }

}
