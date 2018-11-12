package com.minkey.db.dao;

import java.util.Date;

/**
 * 任务对象扫描日志，由本系统发起对任务进行扫描
 */
public class TaskLog {

    /**
     * logid，主键,数据库生成
     */
    private String logId;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 冗余链路id，区分链路
     */
    private Long linkId;

    /**
     * 日志内容
     */
    private String msg;

    /**
     * 告警级别
     */
    private int level;

    /**
     * 错误类型，
     * <br> 0 默认为成功。
     */
    private int errorType = 0;

    /**
     * 记录时间
     */
    private Date createTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "TaskLog{" +
                "logId='" + logId + '\'' +
                ", taskId=" + taskId +
                ", linkId=" + linkId +
                ", msg='" + msg + '\'' +
                ", level=" + level +
                ", errorType=" + errorType +
                ", createTime=" + createTime +
                '}';
    }

}
