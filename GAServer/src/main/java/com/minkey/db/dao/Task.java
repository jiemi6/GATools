package com.minkey.db.dao;

/**
 * 任务对象，数据来自别的数据库
 */
public class Task {
    /**
     * 自增主键
     */
    private long id;

    /**
     *
     * 任务id，来自不同数据库，可能重复，不能做主键
     */
    private String targetId;

    /**
     * 链路id
     */
    private long linkId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * -100表示已删除，0表示新增,(2,-3,3)停止,(22,23,26)启动中，(1,4,13,25,28,29)运行，(24,27)停止中，其他表示异常
     */
    private int status;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
