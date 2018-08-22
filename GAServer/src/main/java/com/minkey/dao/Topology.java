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
     * 所属于链路
     */
    private long linkId;

    /**
     * 起始设备id
     */
    private long fromDeviceId;

    /**
     * 方向，true为正向，false为反向
     */
    private boolean direction;

    /**
     * 截止设备id；
     */
    private long toDeviceId;


}
