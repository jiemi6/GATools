package com.minkey.db.dao;

import java.util.Date;

/**
 * 任务数据源对象
 */
public class TaskSource {

    /**
     * 主键
     */
    private long id;

    /**
     * 来源方主键
     */
    private String targetId;
    /**
     * 任务id
     */
    private String taskId;

    /**
     * 冗余链路id
     */
    private long linkId;

    /**
     * 源数据源
     */
    private String fromResourceId;

    /**
     * 目标数据源
     */
    private String toResourceId;

    /**
     * 创建时间
     */
    private Date createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getFromResourceId() {
        return fromResourceId;
    }

    public void setFromResourceId(String fromResourceId) {
        this.fromResourceId = fromResourceId;
    }

    public String getToResourceId() {
        return toResourceId;
    }

    public void setToResourceId(String toResourceId) {
        this.toResourceId = toResourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "TaskSource{" +
                "id=" + id +
                ", targetId='" + targetId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", linkId=" + linkId +
                ", fromResourceId='" + fromResourceId + '\'' +
                ", toResourceId='" + toResourceId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
