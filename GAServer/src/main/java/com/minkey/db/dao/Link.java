package com.minkey.db.dao;

import com.minkey.dto.JSONMessage;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 链路类，基本单位，链路具有连接关系
 */
public class Link {

    /**
     * 链路id，自增主键
     */
    private long linkId;

    /**
     * 链路名称
     */
    private String linkName;

    /**
     * 链路类型
     */
    private int linkType;

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


    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
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
