package com.minkey.db.dao;

import java.util.Date;

public class Syslog {

    /**
     * 来自机器ip，跟设备IP进行匹配
     */
    private String host;
    /**
     * 级别
     */
    private int level;
    /**
     * 日志内容
     */
    private String msg;
    /**
     * 设备
     */
    private int facility;
    /**
     * 发生时间
     */
    private Date createTime;

    public void setHost(String host) {
        this.host = host;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getHost() {
        return host;
    }

    public int getLevel() {
        return level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    public int getFacility() {
        return facility;
    }

    @Override
    public String toString() {
        return "Syslog{" +
                "host='" + host + '\'' +
                ", level=" + level +
                ", msg='" + msg + '\'' +
                ", facility=" + facility +
                ", createTime=" + createTime +
                '}';
    }
}
