package com.minkey.dao;

/**
 * 拓扑结构，设备与设备之间的连接关系，有方向性
 */
public class Topology {

    /**
     * 自增主键
     */
    private long topologyId;

    /**
     * 问题id
     */
    private long errorId;

    /**
     *  知识点具体内容
     */
    private String topologyDesc;

    /**
     * 点赞次数
     */
    private int upNum;



}
