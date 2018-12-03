package com.minkey.db.dao;

import java.util.Date;

/**
 * 知识库中知识点对象
 */
public class Knowledge {

    /**
     * 知识点对象id，自增主键
     */
    private long knowledgeId;
    /**
     * 报警类型，所有的报警都在此检索知识库
     * @see com.minkey.contants.AlarmEnum
     */
    private long alarmType;

    /**
     * 知识点描述
     */
    private String knowledgeDesc;

    /**
     * 点击计数
     */
    private int upNum;

    private long uid;

    private Date createTime;

    public long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public long getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(long alarmType) {
        this.alarmType = alarmType;
    }

    public String getKnowledgeDesc() {
        return knowledgeDesc;
    }

    public void setKnowledgeDesc(String knowledgeDesc) {
        this.knowledgeDesc = knowledgeDesc;
    }

    public int getUpNum() {
        return upNum;
    }

    public void setUpNum(int upNum) {
        this.upNum = upNum;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "knowledgeId=" + knowledgeId +
                ", alarmType=" + alarmType +
                ", knowledgeDesc='" + knowledgeDesc + '\'' +
                ", upNum=" + upNum +
                ", uid=" + uid +
                ", createTime=" + createTime +
                '}';
    }
}
