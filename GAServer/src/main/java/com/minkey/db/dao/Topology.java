package com.minkey.db.dao;

import com.minkey.dto.TopologyNode;

import java.util.List;

/**
 * 拓扑结构，设备与设备之间的连接关系，有方向性
 */
public class Topology {

    /**
     * 自增主键
     */
    private long topologyId;


    /**
     *  拓扑名称
     */
    private String topologyName;


    private List<TopologyNode> topologyNodeList;


}
