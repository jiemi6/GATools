package com.minkey.db.dao;

import com.minkey.contants.MyLevel;

/**
 * 任务对象，数据来自别的数据库
 */
public class Task {
    /**
     * 自增主键
     */
    private long taskId;

    /**
     *
     * 任务id，来自不同数据库，可能重复，不能做主键
     */
    private String targetTaskId;

    /**
     * 链路id
     */
    private long linkId;

    /**
     * 任务类型
     */
    private int taskType;

    public static final int taskType_unknow = -1;
    public static final int taskType_db = 1;
    public static final int taskType_ftp = 2;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * -100表示已删除，0表示新增,(2,-3,3)停止,(22,23,26)启动中，(1,4,13,25,28,29)运行，(24,27)停止中，其他表示异常
     */
    private int status;

    /**
     * 任务当前级别
     */
    private int level = MyLevel.LEVEL_NORMAL;

    public String getTargetTaskId() {
        return targetTaskId;
    }

    public void setTargetTaskId(String targetTaskId) {
        this.targetTaskId = targetTaskId;
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

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", targetTaskId='" + targetTaskId + '\'' +
                ", linkId=" + linkId +
                ", taskType=" + taskType +
                ", taskName='" + taskName + '\'' +
                ", status=" + status +
                ", level=" + level +
                '}';
    }

}
