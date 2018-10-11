package com.minkey.dto;

import org.springframework.boot.jdbc.DatabaseDriver;

/**
 * 数据库配置
 */
public class DBConfigData extends BaseConfigData {
    /**
     * 数据库类型
     */
    private DatabaseDriver databaseDriver = DatabaseDriver.MYSQL;
    /**
     * 链路数据库名称
     */
    private String dbName;




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


}
