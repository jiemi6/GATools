package com.minkey.db;

import com.minkey.contants.AlarmEnum;
import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
            jdbcTemplate.execute("grant select any dictionary to "+dbConfigData.getName());
        } catch (DataAccessException e) {
            log.error("赋予用户权限异常 :"+e.getMessage());
        }

        try {
            result = jdbcTemplate.queryForList("select * from session_privs");
        } catch (Exception e) {
            log.error("查询用户权限异常 :"+e.getMessage());
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
            //查不到时候,会报ORA-00942: 表或视图不存在, 需要执行命令grant select_catalog_role to 用户
            log.error("获取最大连接数 :"+e.getMessage());
            if(e.getMessage().indexOf("表或视图不存在") != -1){
                throw new SystemException(AlarmEnum.db_oracle_noAuth_V);
            }
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
        List<Map<String, Object>> result = null;
        try {
            result = jdbcTemplate.queryForList("select count(*) from v$process");
        } catch (DataAccessException e) {
            //查不到时候,会报ORA-00942: 表或视图不存在, 需要执行命令grant select_catalog_role to 用户
            log.error("获取当前连接数 :"+e.getMessage());
            if(e.getMessage().indexOf("表或视图不存在") != -1){
                throw new SystemException(AlarmEnum.db_oracle_noAuth_V);
            }
        }

        Integer connectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return connectNum;
    }


    public Set<String> getAllTableNames(JdbcTemplate jdbcTemplate) {
        List<Map<String, Object>> result = null;
        try {
            result = jdbcTemplate.queryForList("select table_name from user_tables");
        } catch (DataAccessException e) {
            log.error("获取当前库所有表名 :"+e.getMessage());
        }
        Set<String> tableSet = new HashSet<>();
        String authStr;
        for (Map<String, Object> stringObjectMap : result) {
            authStr = stringObjectMap.values().iterator().next().toString();
            tableSet.add(authStr);
        }

        return tableSet;
    }

    public Set<String> getAllTriggers(JdbcTemplate jdbcTemplate) {
        List<Map<String, Object>> result = null;
        try {
            result = jdbcTemplate.queryForList("SELECT NAME FROM USER_SOURCE WHERE TYPE='TRIGGER' GROUP BY NAME");
        } catch (DataAccessException e) {
            log.error("获取当前库所有表名 :"+e.getMessage());
        }

        return null;
    }

    public boolean tableHasBlobDesc(JdbcTemplate jdbcTemplate, String tableName) {
        List<Map<String, Object>> result = null;
        try {
            result = jdbcTemplate.queryForList("select A.COLUMN_NAME,A.DATA_TYPE  from user_tab_columns A where TABLE_NAME='"+tableName+"'");
        } catch (DataAccessException e) {
            log.error("获取当前库所有表名 :"+e.getMessage());
        }

        for (Map<String, Object> stringObjectMap : result) {
            //获取表字段类型
            String type = (String) stringObjectMap.get("DATA_TYPE");
            if(StringUtils.equalsIgnoreCase(type,"BLOB")
                    || StringUtils.equalsIgnoreCase(type,"CLOB")
                    || StringUtils.equalsIgnoreCase(type,"NCLOB")){
                return true;
            }
        }

        return false;
    }
}
