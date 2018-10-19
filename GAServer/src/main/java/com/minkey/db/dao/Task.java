package com.minkey.db.dao;

/**
 * 任务对象，数据来自别的数据库
 */
public class Task {

    /**
     * 任务id，主键,因为数据来源方用的字符串
     */
    private String taskId;

    /**
     * 链路id
     */
    private long linkId;

    /**
     * 任务名称
     */
    private String taskName;


    /**
     * 从数据交换系统获取的数据本身
     */
    private String data;


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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
