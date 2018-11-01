package com.minkey.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private List<TopologyNode> child;

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

    public List<TopologyNode> getChild() {
        return child;
    }

    public void setChild(List<TopologyNode> child) {
        this.child = child;
    }

    public Set<Long> allDeviceId(){
        Set<Long> all = new HashSet<>(2);
        all.add(fromDeviceId);
        all.add(toDeviceId);
        if(child != null && child.size() > 0 ){
            child.forEach(topologyNode -> {
                all.addAll(topologyNode.allDeviceId());
            });
        }
        return all;
    }
}