package com.minkey.db;

import com.minkey.dao.Link;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinkHandler {
    private final static Logger logger = LoggerFactory.getLogger(LinkHandler.class);

    private final String tableName = "t_link";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public long queryCount() {
        Long count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Long.class);
        return count;
    }

    public void insert(Link link) {
        int num = jdbcTemplate.update("replace into "+tableName+" (configKey, configData) VALUES (?,?)",new Object[]{link});

        if(num == 0){
            throw new DataException("插入配置失败");
        }
    }

    public Link query(Long linkId) {
            return jdbcTemplate.queryForObject("select configKey, configData from "+tableName+" where linkId= ?",new Object[]{linkId},Link.class);
    }

    public List<Link> queryAll() {
        return jdbcTemplate.queryForList("select configKey, configData from "+tableName,Link.class);
    }
}
