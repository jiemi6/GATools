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
     * 发生时间
     */
    private Date createTime;
    /**
     * 日志内容
     */
    private String msg;

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
}
