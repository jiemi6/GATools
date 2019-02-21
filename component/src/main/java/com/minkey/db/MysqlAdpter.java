package com.minkey.db;

import com.minkey.dto.DBConfigData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 适配mysql 数据库的操作
 */
@Component
public class MysqlAdpter {

    public Set<String> getUserAuth(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData){
        List<Map<String, Object>> result =jdbcTemplate.queryForList("show grants for ?",new Object[]{dbConfigData.getName()});

        if(CollectionUtils.isEmpty(result)){
            //什么权限都没有
            return null;
        }

        Set<String> authSet = new HashSet<>();
        String authStr;
        for (Map<String, Object> stringObjectMap : result) {
            authStr = stringObjectMap.values().iterator().next().toString();

            //全库权限设置
            if(authStr.contains("ON *.* TO")){
                if(authStr.contains("ALL")){
                    //全部权限
                    authSet.add("ALL");
                }else{
                    authSet.addAll(getAuthDetail(authStr));
                }

                //本库权限设置
            }else if(authStr.contains("ON `"+dbConfigData.getDbName()+"`.* TO")) {
                if(authStr.contains("ALL")){
                    //全部权限
                    authSet.add("ALL");
                }else{
                    authSet.addAll(getAuthDetail(authStr));
                }

                //某表权限设置
            }else if(authStr.contains("ON `"+dbConfigData.getDbName()+"`.")){
                //暂时不处理
            }

        }

        return authSet;
    }

    private Set<String> getAuthDetail(String authStr){
        Set<String> authSet = new HashSet<>();
        if(authStr.contains("CREATE")){
            //创建表
            authSet.add("CREATE");
        }

        if(authStr.contains("INSERT")){
            //增加表数据权限
            authSet.add("INSERT");
        }
        if(authStr.contains("DELETE")){
            //删除表数据权限
            authSet.add("DELETE");
        }
        if(authStr.contains("SELECT")){
            //查询表数据权限
            authSet.add("SELECT");
        }
        if(authStr.contains("UPDATE")){
            //修改表数据权限
            authSet.add("UPDATE");
        }
        if(authStr.contains("TRIGGER")){
            //触发器的权限
            authSet.add("TRIGGER");
        }
        return authSet;
    }



    /**
     * 获取最大连接数
     * @return
     */
    public int maxConnectNum(JdbcTemplate jdbcTemplate){
        List<Map<String, Object>> result =jdbcTemplate.queryForList("show variables like '%max_connections%'");

        Integer maxConnectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return maxConnectNum;
    }


    /**
     * 获取当前连接数
     * @param jdbcTemplate
     * @return
     */
    public int connectNum(JdbcTemplate jdbcTemplate){
        List<Map<String, Object>> result =jdbcTemplate.queryForList("show global status like 'Threads_connected%'");

        Integer connectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return connectNum;
    }

    /**
     * 获取该库的所有表名
     * @param jdbcTemplate
     * @return
     */
    public Set<String> getAllTableNames(JdbcTemplate jdbcTemplate) {
        List<Map<String, Object>> result =jdbcTemplate.queryForList("show tables");

        if(CollectionUtils.isEmpty(result)){
            return null;
        }

        Set<String> allTableNames = new HashSet<>(result.size());
        for (Map<String, Object> stringObjectMap : result) {
            //获取表名
            String tableName = (String) stringObjectMap.values().iterator().next();
            allTableNames.add(tableName);
        }

        return allTableNames;
    }

    /**
     * 获取所有触发任务的表的触发器
     * @param jdbcTemplate
     * @return
     */
    public Set<String> getAllTriggers(JdbcTemplate jdbcTemplate) {
        //任务对应的 触发器
        List<Map<String, Object>> result =jdbcTemplate.queryForList("select * from tbtriggerinfo;");

        if(CollectionUtils.isEmpty(result)){
            return null;
        }

        Set<String> allTriggers = new HashSet<>(result.size());
        for (Map<String, Object> stringObjectMap : result) {
//            String taskId = (String) stringObjectMap.get("taskId");
            //触发器名称:格式为T_任务编号_0_I(此为触发任务新增数据触发器)、T_任务编号_1_U(此为触发任务更新数据触发器)、T_任务编号_2_D(此为触发任务删除数据触发器)
            String trigname = (String) stringObjectMap.get("trigname");
            allTriggers.add(trigname);
        }

        return allTriggers;
    }


    /**
     * 获取表的字段描述,判断是否有BLOB /CLOB大字段
     * @param jdbcTemplate
     * @return
     */
    public boolean tableHasBlobDesc(JdbcTemplate jdbcTemplate, String tableName){
        //如果没权限, 返回list 可能为空
        List<Map<String, Object>> result =jdbcTemplate.queryForList("DESC  "+tableName);

        if(CollectionUtils.isEmpty(result)){
            return false;
        }

        for (Map<String, Object> stringObjectMap : result) {
            //获取表字段类型
            String type = (String) stringObjectMap.get("Type");
            if(StringUtils.equalsIgnoreCase(type,"BLOB")
                    || StringUtils.equalsIgnoreCase(type,"CLOB")
                    || StringUtils.equalsIgnoreCase(type,"NCLOB")){
                return true;
            }
        }
        return false;
    }
}
