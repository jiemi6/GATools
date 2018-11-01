package com.minkey.db.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.TopologyNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
     *
     * @see com.minkey.contants.LinkType
     */
    private Integer linkType;

    /**
     * 链路包含拓扑节点
     */
    private ArrayList<TopologyNode> topologyNodes;

    /**
     * 链路对应的数据库信息
     */
    private DBConfigData dbConfigData;

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

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public List<TopologyNode> getTopologyNodes() {
        return topologyNodes;
    }

    public void setTopologyNodes(ArrayList<TopologyNode> topologyNodes) {
        this.topologyNodes = topologyNodes;
    }

    public DBConfigData getDbConfigData() {
        return dbConfigData;
    }

    public void setDbConfigData(DBConfigData dbConfigData) {
        this.dbConfigData = dbConfigData;
    }
    public String dbConfigDataStr() {
        return JSONObject.toJSON(dbConfigData).toString();
    }

    public void setDbConfigDataStr(String dbConfig) {
        this.dbConfigData = JSONObject.parseObject(dbConfig,DBConfigData.class);
    }

    public void setTopologyNodesStr(String deviceIdsStr) {
        if(StringUtils.isEmpty(deviceIdsStr)){
            return;
        }
        ArrayList<TopologyNode> ids = new ArrayList<>();
        ids.addAll(JSONObject.parseArray(deviceIdsStr,TopologyNode.class));
        this.topologyNodes = ids;
    }

    public String topologyNodesStr() {
        return JSONArray.toJSON(topologyNodes).toString();
    }

    public Set<Long> getDeviceIds() {
        if(CollectionUtils.isEmpty(topologyNodes)){
            return null;
        }

        Set<Long> deviceIds = new HashSet<>();
        topologyNodes.forEach(topologyNode -> {
            deviceIds.addAll(topologyNode.allDeviceId());
        });
        return deviceIds;
    }


    @Override
    public String toString() {
        return "Link{" +
                "linkId=" + linkId +
                ", linkName='" + linkName + '\'' +
                ", linkType=" + linkType +
                ", topologyNodes=" + topologyNodes +
                ", dbConfigData=" + dbConfigData +
                '}';
    }
}
