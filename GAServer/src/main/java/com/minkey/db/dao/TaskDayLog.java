package com.minkey.db.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务对象执行统计日志，来自别交换系统数据库
 */
public class TaskDayLog implements Serializable{

    /**
     * logid，主键,数据库生成
     */
    private String logId;
    /**
     * 数据来源方logid
     */
    private long targetLogId;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 冗余链路id，区分链路
     */
    private long linkId;

    /**
     * 成功数
     */
    private long successNum;
    /**
     * 成功流量
     */
    private long successFlow;
     /**
     * 失败数量
     */
    private long errorNum;
    /**
     * 失败流量
     */
    private long errorFlow;

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

    public long getTargetLogId() {
        return targetLogId;
    }

    public void setTargetLogId(long targetLogId) {
        this.targetLogId = targetLogId;
    }

    public long getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(long successNum) {
        this.successNum = successNum;
    }

    public long getSuccessFlow() {
        return successFlow;
    }

    public void setSuccessFlow(long successFlow) {
        this.successFlow = successFlow;
    }

    public long getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(long errorNum) {
        this.errorNum = errorNum;
    }

    public long getErrorFlow() {
        return errorFlow;
    }

    public void setErrorFlow(long errorFlow) {
        this.errorFlow = errorFlow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "TaskDayLog{" +
                "logId='" + logId + '\'' +
                ", targetLogId=" + targetLogId +
                ", taskId='" + taskId + '\'' +
                ", linkId=" + linkId +
                ", successNum=" + successNum +
                ", successFlow=" + successFlow +
                ", errorNum=" + errorNum +
                ", errorFlow=" + errorFlow +
                ", createTime=" + createTime +
                '}';
    }
}
