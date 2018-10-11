package com.minkey.db.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.dto.DBConfigData;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

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
     * 链路包含的所有的设备
     */
    private Set<Long> deviceSet;

    /**
     * 链路对应的数据库信息
     */
    private DBConfigData dbConfig;

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

    public DBConfigData getDbConfig() {
        return dbConfig;
    }

    public Set<Long> getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(Set<Long> deviceSet) {
        this.deviceSet = deviceSet;
    }

    public void setDbConfig(DBConfigData dbConfig) {
        this.dbConfig = dbConfig;
    }
    public String dbConfigStr() {
        return JSONObject.toJSON(dbConfig).toString();
    }

    public void setDbConfigStr(String dbConfig) {
        this.dbConfig = JSONObject.parseObject(dbConfig,DBConfigData.class);
    }

    public void setDeviceIdsStr(String deviceIdsStr) {
        if(StringUtils.isEmpty(deviceIdsStr)){
            return;
        }
        Set<Long> ids = new HashSet<>();
        ids.addAll(JSONObject.parseArray(deviceIdsStr,Long.class));
        this.deviceSet = ids;
    }

    public String deviceIdsStr() {

        return JSONArray.toJSON(deviceSet).toString();
    }
}
