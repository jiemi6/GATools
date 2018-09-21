package com.minkey.dto;

import org.springframework.boot.jdbc.DatabaseDriver;

/**
 * 数据库配置
 */
public class DBConfig {
    /**
     * 数据库类型
     */
    private DatabaseDriver databaseDriver = DatabaseDriver.MYSQL;
    /**
     * 链路数据库名称
     */
    private String dbName;

    /**
     * 链路数据库ip
     */
    private String dbIp;

    /**
     * 链路数据库端口
     */
    private int dbPort;

    /**
     * 链路数据库用户名
     */
    private String dbUserName;

    /**
     * 链路数据库密码
     */
    private String dbPwd;


    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public void setDatabaseDriver(DatabaseDriver databaseDriver) {
        this.databaseDriver = databaseDriver;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }
}
