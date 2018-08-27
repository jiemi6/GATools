package com.minkey.db;

import com.minkey.dao.Topology;
import com.minkey.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopologyHandler {
    private final static Logger logger = LoggerFactory.getLogger(TopologyHandler.class);

    private final String tableName = "t_topology";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void up(Long topologyId) {
        int num = jdbcTemplate.update("update "+tableName+" where topologyId=?",Long.class);
        if(num == 0){
            logger.error("点赞知识点失败，topologyId={}",topologyId);
        }
    }

    public void insert(Topology topology) {
        int num = jdbcTemplate.update("replace into "+tableName+" (configKey, configData) VALUES (?,?)",new Object[]{topology});

        if(num == 0){
            throw new DataException("插入配置失败");
        }
    }

    public List<Topology> query8errorId(long errorId) {
            return jdbcTemplate.queryForList("select * from "+tableName+" where errorId= ? ORDER BY upNum desc",new Object[]{errorId},Topology.class);
    }

}
