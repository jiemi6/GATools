package com.minkey.db.dao;

import com.alibaba.fastjson.JSONObject;
import com.minkey.dto.DBConfig;

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


    private DBConfig dbConfig;

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

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
    public String dbConfigStr() {
        return JSONObject.toJSON(dbConfig).toString();
    }

    public void setDbConfigStr(String dbConfig) {
        this.dbConfig = JSONObject.parseObject(dbConfig,DBConfig.class);
    }
}
