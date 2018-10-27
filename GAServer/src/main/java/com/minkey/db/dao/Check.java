package com.minkey.db.dao;

import java.util.Date;

/**
 * 每一次检查发起的对象
 */
public class Check {

    private long checkId;

    /**
     * 检查名称，代码拼装
     */
    private String checkName;

    /**
     * 检查类型
     */
    private int checkType = CHECKTYPE_SYSTEMSELF;

    /**
     * 系统一键自检
     */
    public final static int CHECKTYPE_ALLINONE = 1;
    /**
     * 设备体检
     */
    public final static int CHECKTYPE_DEVICE = 2;
    /**
     * 任务体检
     */
    public final static int CHECKTYPE_TASK = 3;
    /**
     * 链体检路
     */
    public final static int CHECKTYPE_LINK = 4;
    /**
     * 本系统环境自检
     */
    public final static int CHECKTYPE_SYSTEMSELF = 5;


    /**
     * 触发人，系统自动触发时为 -1
     */
    private long uid;

    private Date createTime;

    public long getCheckId() {
        return checkId;
    }

    public void setCheckId(long checkId) {
        this.checkId = checkId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(int checkType) {
        this.checkType = checkType;
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
}
