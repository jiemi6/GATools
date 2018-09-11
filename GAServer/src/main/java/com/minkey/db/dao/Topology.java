package com.minkey.db.dao;

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


    private class TopologyNode {
        /**
         * 划线起始设备
         */
        private long fromDeviceId;

        /**
         * 划线截至设备
         */
        private long toDeviceId;

        /**
         * 方向： 默认1：正向； -1 反向
         */
        private int direction = 1;



    }
}
