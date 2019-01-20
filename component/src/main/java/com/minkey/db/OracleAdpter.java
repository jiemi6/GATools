package com.minkey.db;

import com.minkey.dto.DBConfigData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 适配orcale 数据库的操作
 */
@Slf4j
@Component
public class OracleAdpter {

    public Set<String> getUserAuth(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData) {

        List<Map<String, Object>> result = null;
        try {
            jdbcTemplate.execute("grant select  any dictionary to "+dbConfigData.getName());
            result = jdbcTemplate.queryForList("select * from session_privs");
        } catch (DataAccessException e) {
            log.error("查询用户权限异常"+e.getMessage());
            return new HashSet<>();
        }

        if(CollectionUtils.isEmpty(result)){
            //什么权限都没有
            return new HashSet<>();
        }

        Set<String> authSet = new HashSet<>();
        String authStr;
        for (Map<String, Object> stringObjectMap : result) {
            authStr = stringObjectMap.values().iterator().next().toString();
            authSet.add(authStr);
        }

        return authSet;
    }

    /**
     * 获取最大连接数
     * @return
     */
    public int maxConnectNum(JdbcTemplate jdbcTemplate){
        List<Map<String, Object>> result = null;
        try {
            result = jdbcTemplate.queryForList("select value from v$parameter where name = 'processes'");
        } catch (DataAccessException e) {
            //查不到时候,会报表或者视图不存在, 需要执行命令grant select_catalog_role to 用户
            e.printStackTrace();
        }

        if(CollectionUtils.isEmpty(result)){
            //没查到
            return -1;
        }



        Integer maxConnectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return maxConnectNum;
    }


    /**
     * 获取当前连接数
     * @param jdbcTemplate
     * @return
     */
    public int connectNum(JdbcTemplate jdbcTemplate){
        List<Map<String, Object>> result =jdbcTemplate.queryForList("select count(*) from v$process");

        Integer connectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return connectNum;
    }
    public Set<String> getAllTableNames(JdbcTemplate jdbcTemplate) {


        return null;
    }

    public Set<String> getAllTriggers(JdbcTemplate jdbcTemplate) {


        return null;
    }

    public boolean tableHasBlobDesc(JdbcTemplate jdbcTemplate, String tableName) {



        return false;
    }
}
