package com.minkey.db;

import com.minkey.db.dao.Link;
import com.minkey.exception.DataException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class LinkHandler {
    private final static Logger logger = LoggerFactory.getLogger(LinkHandler.class);

    private final String tableName = "t_link";
    @Autowired
    JdbcTemplate jdbcTemplate;


    public Integer queryCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" ",Integer.class);
        return count;
    }

    public void insert(Link link) {
        int num = jdbcTemplate.update("INSERT into "+tableName+" ( linkName,linkType,dbConfig,deviceSet) VALUES (?,?,?,?)",
                new Object[]{link.getLinkName(),link.getLinkType(),link.dbConfigStr(),link.deviceIdsStr()});

        if(num == 0){
            throw new DataException("新增失败");
        }
    }

    public void update(Link link) {
        int num = jdbcTemplate.update("replace into "+tableName+" (linkId, linkName,linkType,dbConfig,deviceSet) VALUES (?,?,?,?,?)",
                new Object[]{link.getLinkId(),link.getLinkName(),link.getLinkType(),link.dbConfigStr(),link.deviceIdsStr()});

        if(num == 0){
            throw new DataException("更新失败");
        }
    }

    public Link query(Long linkId) {
        List<Link> linkList= jdbcTemplate.query("select * from "+tableName+" where linkId= ?",
                new Object[]{linkId}, new LinkRowMapper());
        if(CollectionUtils.isEmpty(linkList)){
            return null;
        }
        return linkList.get(0);
    }

    public List<Link> queryAll() {
        return jdbcTemplate.query("select * from "+tableName,new LinkRowMapper());
    }

    public void del(Long linkId) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where linkId= ?",new Object[]{linkId});

        if(num == 0){
            throw new DataException("删除失败");
        }
    }

    class LinkRowMapper implements RowMapper{
        @Override
        public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
            Link link =new Link();
            link.setLinkId(rs.getLong("linkId"));
            link.setLinkName(rs.getString("linkName"));
            link.setLinkType(rs.getInt("linkType"));
            link.setDbConfigStr(rs.getString("dbConfig"));
            link.setDeviceIdsStr(rs.getString("deviceSet"));

            return link;
        }
    }

}
