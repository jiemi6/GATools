package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.Telnet;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.CommonContants;
import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Slf4j
@Component
public class DynamicDB {
    public static final int default_timeout = CommonContants.DEFAULT_TIMEOUT;


    /**
     * 所有系统需要访问的数据库
     * <br>key : ip:port/dbName <br/>
     */
    private Map<String,JdbcTemplate> jdbcTemplateMap = new HashMap();

    private String createKey(String ip, int port,String dbName) {
        return ip+":"+port+"/"+dbName;
    }

    private JdbcTemplate getJdbcTemplate(DatabaseDriver databaseDriver, String ip,int port,String dbName, String userName , String password) throws SystemException{
        //先测网络
        boolean isConnect;
        try {
            isConnect = Telnet.doTelnet(ip,port);
        } catch (SystemException e) {
            throw new SystemException(AlarmEnum.port_notConnect,String.format("数据库%s",e.getMessage()));
        }
        if(!isConnect){
            throw new SystemException(AlarmEnum.port_notConnect,String.format("Telnet数据库网络不通[%s:%s]",ip,port));
        }


        JdbcTemplate jdbcTemplate ;
        String jdbcUrl ;
        if(databaseDriver == DatabaseDriver.MYSQL){
            jdbcUrl = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            jdbcUrl = "jdbc:oracle:thin:@//"+ip+":"+port+"/"+dbName;
        }else if(databaseDriver == DatabaseDriver.SQLSERVER){
            jdbcUrl = "jdbcUrl:jdbc:sqlserver://"+ip+":"+port+";databasename="+dbName;
        }else {
            throw new SystemException("暂不支持数据库类型="+databaseDriver);
        }

        DataSource dataSource = null;
        try{
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .driverClassName(databaseDriver.getDriverClassName())
                    .username(userName)
                    .password(password);
            dataSource = dataSourceBuilder.build();

            dataSource.setLoginTimeout(1);
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.setQueryTimeout(default_timeout);

            //测试语句执行
            jdbcTemplate.execute(databaseDriver.getValidationQuery());

            return jdbcTemplate;
        }catch (Exception e ){
            String exceptionStr = e.getCause().getCause().toString();
            throw build(exceptionStr,databaseDriver);
        }

    }

    /**
     * 根据错误类型构造返回的异常
     * @param exceptionStr
     * @param databaseDriver
     * @return
     */
    private SystemException build(String exceptionStr, DatabaseDriver databaseDriver){
        if(databaseDriver == DatabaseDriver.MYSQL){
            // Access denied for user 'root'@'localhost' (using password: YES)
            if(exceptionStr.contains("using password: YES")){
                //账号密码错误
                return new SystemException(AlarmEnum.db_wrongpwd);
            }else if (exceptionStr.contains("Unknown database")){
                //Unknown database 'smz'  数据库名称错误,不存在
                return new SystemException(AlarmEnum.db_databaseName_noexist);
            }else if(exceptionStr.contains("Connection refused")){
                //java.net.ConnectException: Connection refused: connect
                return new SystemException(AlarmEnum.port_notConnect);
            }
        }

        return new SystemException(AlarmEnum.db_createError.getAlarmType(),"数据库连接异常,"+exceptionStr);
    }

    /**
     * 测试数据库是否连接正常
     * @param dbConfigData
     * @return
     * @throws SystemException
     */
    public boolean testDBConnect(DBConfigData dbConfigData) throws SystemException{
        try{
            //获取jdbc操作模板
            JdbcTemplate jdbcTemplate = get8dbConfig(dbConfigData);

            jdbcTemplate.execute(dbConfigData.getDatabaseDriver().getValidationQuery());
            return true;
        }catch (SystemException  e){
            throw e;
        }catch (Exception e){
            throw new SystemException(AlarmEnum.port_notConnect,String.format("测试数据库%s失败,msg=:",dbConfigData.toString(),e.getMessage()));
        }
    }

    /**
     * 获取数据库连接
     * @param dbConfigData
     * @return
     */
    public JdbcTemplate get8dbConfig(DBConfigData dbConfigData) throws SystemException{
        String key = createKey(dbConfigData.getIp(), dbConfigData.getPort(), dbConfigData.getName());
        //先从缓存中获取
        JdbcTemplate jdbcTemplate = jdbcTemplateMap.get(key);
        //没有就新建
        if (jdbcTemplate == null) {
            jdbcTemplate = getJdbcTemplate(dbConfigData.getDatabaseDriver(),dbConfigData.getIp(),dbConfigData.getPort()
                    ,dbConfigData.getDbName(),dbConfigData.getName(),dbConfigData.getPwd());
            //放回缓存
            jdbcTemplateMap.put(key,jdbcTemplate);
        }

        return jdbcTemplate;
    }

    public JSONObject testDBSource(DBConfigData dbConfigData){
        JSONObject resultJson = new JSONObject();
        try{
            //获取jdbc操作模板
            JdbcTemplate jdbcTemplate = get8dbConfig(dbConfigData);

            Set<String> authSet = getUserAuth(jdbcTemplate,dbConfigData);
            resultJson.put("authSet",authSet);

            int maxConnectNum = maxConnectNum(jdbcTemplate);
            int connectNum = connectNum(jdbcTemplate);
            resultJson.put("maxConnectNum",maxConnectNum);
            resultJson.put("connectNum",connectNum);

        }catch (SystemException  e){
            log.debug(String.format("测试数据库%s失败,msg=:",dbConfigData.toString(),e.getMessage()));
            resultJson.put("alarmType",e.getErrorCode());
        }catch (Exception e){
            log.debug(String.format("测试数据库%s失败,msg=:",dbConfigData.toString(),e.getMessage()));
            resultJson.put("alarmType",AlarmEnum.port_notConnect.getAlarmType());
        }

        return resultJson;
    }

    /**
     * 根据查到的权限语句结果,分析具有的权限
     * @param jdbcTemplate
     * @param dbConfigData
     * @return
     */
    private Set<String> getUserAuth(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData){
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
    private int maxConnectNum(JdbcTemplate jdbcTemplate){
        List<Map<String, Object>> result =jdbcTemplate.queryForList("show variables like '%max_connections%'");

        Integer maxConnectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return maxConnectNum;
    }

    /**
     * 获取当前连接数
     * @param jdbcTemplate
     * @return
     */
    private int connectNum(JdbcTemplate jdbcTemplate){
        List<Map<String, Object>> result =jdbcTemplate.queryForList("show global status like 'Threads_connected%'");

        Integer connectNum = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return connectNum;
    }
}
