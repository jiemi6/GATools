package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.Telnet;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.CommonContants;
import com.minkey.db.MysqlAdpter;
import com.minkey.db.OracleAdpter;
import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    MysqlAdpter mysqlAdpter;

    @Autowired
    OracleAdpter oracleAdpter = new OracleAdpter();

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
            jdbcUrl = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            jdbcUrl = "jdbc:oracle:thin:@//"+ip+":"+port+"/"+dbName;
//        }else if(databaseDriver == DatabaseDriver.SQLSERVER){
//            jdbcUrl = "jdbcUrl:jdbc:sqlserver://"+ip+":"+port+";databasename="+dbName;
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
            throw build(e,databaseDriver);
        }

    }

    /**
     * 根据错误类型构造返回的异常
     * @param databaseDriver
     * @return
     */
    private SystemException build(Exception e, DatabaseDriver databaseDriver){
        if(databaseDriver == DatabaseDriver.MYSQL){
            String exceptionStr = e.getCause().getCause().toString();
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
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            String exceptionStr = e.getCause().toString();
            //ORA-01017: invalid username/password; logon denied
            if(exceptionStr.contains("01017")){
                //账号密码错误
                return new SystemException(AlarmEnum.db_wrongpwd);
            }else if (exceptionStr.contains("12514")){
                //Listener refused the connection with the following error:
                //ORA-12514, TNS:listener does not currently know of service requested in connect descriptor
                //The Connection descriptor used by the client was:
                //topwalkhndq.tpddns.cn:1521/1orcl
                return new SystemException(AlarmEnum.db_databaseName_noexist);
            }else if (exceptionStr.contains("12514")){

            }

        }

        return new SystemException(AlarmEnum.db_createError.getAlarmType(),"数据库连接异常,"+e.getMessage());
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
            throw new SystemException(AlarmEnum.port_notConnect,String.format("测试数据库%s是否联通异常,msg=%s",dbConfigData.toString(),e.getMessage()));
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

            int maxConnectNum = maxConnectNum(jdbcTemplate, dbConfigData);
            int connectNum = connectNum(jdbcTemplate, dbConfigData);
            resultJson.put("maxConnectNum",maxConnectNum);
            resultJson.put("connectNum",connectNum);
            int connectRadio = Math.round(connectNum * 100 /maxConnectNum);
            if(connectRadio > 70){
                resultJson.put("alarmType",AlarmEnum.db_connect_70.getAlarmType());
            }
            //获取所有表名
            Set<String> allTableName = getAllTableNames(jdbcTemplate, dbConfigData);
            //包含所有含有blob字段的表
            Set<String> hasBlobTableNames = new HashSet<>();
            //此针对数据交换系统的动作表格式为（topwalk_dtp_任务编号_tb）topwalk_dtp_825746866_tb，
            // 每个触发任务会在业务服务器上建立以此触发任务任务编号命名的动作表，
            // 查询select count（*） from 动作表名；若动作表的数据总数积压超过有1万条数据则报警处理
            Set<String> tableCountMax1W = new HashSet<>();
            if(CollectionUtils.isNotEmpty(allTableName)){
                for (String tableName : allTableName) {
                    //是否有blob字段
                    if(tableHasBlobDesc(jdbcTemplate,tableName, dbConfigData)){
                        hasBlobTableNames.add(tableName);
                    }

                    //过滤掉 topwalk_dtp_nodepoint_tb 这样的表名,截取第三段,看是不是数字,
                    if(tableName.startsWith("topwalk_dtp_") && NumberUtils.isNumber(tableName.split("_")[2])){
                        //判断数据库表大小是否超过1w
                        int tableCount = getTableCount(jdbcTemplate,tableName);
                        if(tableCount > 10000){
                            tableCountMax1W.add(tableName);
                        }
                    }

                    //如果有任务触发器这张表, 则查询触发器
                    if(tableName.equalsIgnoreCase("tbtriggerinfo")){
                        Set<String> allTriggers =getAllTriggers(jdbcTemplate, dbConfigData);
                        //所有触发器名称集合
                        resultJson.put("allTriggers",allTriggers);
                    }
                }
            }
            resultJson.put("hasBlobTableNames",hasBlobTableNames);
            resultJson.put("tableCountMax1W",tableCountMax1W);



        }catch (SystemException  e){
            resultJson.put("alarmType",e.getErrorCode());
        }catch (Exception e){
            log.error(String.format("测试数据库%s资源异常,msg=%s",dbConfigData.toString(),e.getMessage()));
            resultJson.put("alarmType",AlarmEnum.port_notConnect.getAlarmType());
        }

        return resultJson;
    }

    private int getTableCount(JdbcTemplate jdbcTemplate, String tableName) {
        List<Map<String, Object>> result =jdbcTemplate.queryForList("select count(*) FROM "+tableName);

        int tableCount = Integer.parseInt(result.get(0).values().toArray()[1].toString());

        return  tableCount;
    }


    /**
     * 根据查到的权限语句结果,分析具有的权限
     * @param jdbcTemplate
     * @param dbConfigData
     * @return
     */
    private Set<String> getUserAuth(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData){
        DatabaseDriver databaseDriver = dbConfigData.getDatabaseDriver();
        if(databaseDriver == DatabaseDriver.MYSQL){
            return mysqlAdpter.getUserAuth(jdbcTemplate,dbConfigData);
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            return oracleAdpter.getUserAuth(jdbcTemplate,dbConfigData);
        }
        return null;
    }





    /**
     * 获取最大连接数
     * @return
     */
    private int maxConnectNum(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData){
        DatabaseDriver databaseDriver = dbConfigData.getDatabaseDriver();
        if(databaseDriver == DatabaseDriver.MYSQL){
            return mysqlAdpter.maxConnectNum(jdbcTemplate);
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            return oracleAdpter.maxConnectNum(jdbcTemplate);
        }
        return 0;
    }

    /**
     * 获取当前连接数
     * @param jdbcTemplate
     * @param dbConfigData
     * @return
     */
    private int connectNum(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData){
        DatabaseDriver databaseDriver = dbConfigData.getDatabaseDriver();
        if(databaseDriver == DatabaseDriver.MYSQL){
            return mysqlAdpter.connectNum(jdbcTemplate);
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            return oracleAdpter.connectNum(jdbcTemplate);
        }
        return 0;
    }


    /**
     * 获取该库的所有表名
     * @param jdbcTemplate
     * @param dbConfigData
     * @return
     */
    private Set<String> getAllTableNames(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData) {
        DatabaseDriver databaseDriver = dbConfigData.getDatabaseDriver();
        if(databaseDriver == DatabaseDriver.MYSQL){
            return mysqlAdpter.getAllTableNames(jdbcTemplate);
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            return oracleAdpter.getAllTableNames(jdbcTemplate);
        }
        return new HashSet<>();
    }

    /**
     * 获取所有触发任务的表的触发器
     * @param jdbcTemplate
     * @param dbConfigData
     * @return
     */
    private Set<String> getAllTriggers(JdbcTemplate jdbcTemplate, DBConfigData dbConfigData) {
        DatabaseDriver databaseDriver = dbConfigData.getDatabaseDriver();
        if(databaseDriver == DatabaseDriver.MYSQL){
            return mysqlAdpter.getAllTriggers(jdbcTemplate);
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            return oracleAdpter.getAllTriggers(jdbcTemplate);
        }
        return new HashSet<>();
    }


    /**
     * 获取表的字段描述,判断是否有BLOB /CLOB大字段
     * @param jdbcTemplate
     * @param dbConfigData
     * @return
     */
    private boolean tableHasBlobDesc(JdbcTemplate jdbcTemplate, String tableName, DBConfigData dbConfigData){
        DatabaseDriver databaseDriver = dbConfigData.getDatabaseDriver();
        if(databaseDriver == DatabaseDriver.MYSQL){
            return mysqlAdpter.tableHasBlobDesc(jdbcTemplate,tableName);
        }else if(databaseDriver == DatabaseDriver.ORACLE){
            return oracleAdpter.tableHasBlobDesc(jdbcTemplate,tableName);
        }
        return false;
    }
}
