package com.minkey.db.dao;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 告警log
 */
public class AlarmLog {

    private long logId;

    /**
     * 根据type不一样，对应相应类型的主键id
     */
    private long bid;

    /**
     * 告警类型
     * 1 ： 链路
     * 2 ： 任务
     * 3 :  设备
     */
    @JSONField(serialize=false)
    private int bType ;

    public static final int BTYPE_LINK = 1;
    public static final int BTYPE_TASK = 2;
    public static final int BTYPE_DEVICE = 3;


    /**
     * 报警类型
     * @see com.minkey.contants.AlarmType
     */
    private int type;

    /**
     * 报警内容
     */
    private String msg;

    /**
     * 告警级别
     */
    private int level;

    private Date createTime;

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public int getbType() {
        return bType;
    }

    public void setbType(int bType) {
        this.bType = bType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
