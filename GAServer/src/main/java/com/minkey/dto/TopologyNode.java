package com.minkey.dto;

/**
 * 拓扑图节点
 */
public class TopologyNode {
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

    public TopologyNode() {
        super();
    }

    public long getFromDeviceId() {
        return fromDeviceId;
    }

    public void setFromDeviceId(long fromDeviceId) {
        this.fromDeviceId = fromDeviceId;
    }

    public long getToDeviceId() {
        return toDeviceId;
    }

    public void setToDeviceId(long toDeviceId) {
        this.toDeviceId = toDeviceId;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}