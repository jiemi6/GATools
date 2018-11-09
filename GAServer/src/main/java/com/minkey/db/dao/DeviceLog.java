package com.minkey.db.dao;

import com.minkey.contants.MyLevel;

import java.util.Date;

/**
 * 设备log
 */
public class DeviceLog {
    private long deviceLogId;

    private long deviceId;

    private String ip;

    private int level = MyLevel.LEVEL_NORMAL;

    /**
     * 日志类型
     * 1 ：连通性
     * 2 ：性能
     * 3 ：机器上的任务
     */
    private int type = TYPE_CONNECT;

    public static final int TYPE_CONNECT =1;
    public static final int TYPE_PERFORMANCE =2;
    public static final int TYPE_TASK =3;

    /**
     * 报警内容
     */
    private String msg;

    /**
     * 报警时间
     */
    private Date createTime;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getDeviceLogId() {
        return deviceLogId;
    }

    public void setDeviceLogId(long deviceLogId) {
        this.deviceLogId = deviceLogId;
    }

    @Override
    public String toString() {
        return "DeviceLog{" +
                "deviceLogId=" + deviceLogId +
                ", deviceId=" + deviceId +
                ", ip='" + ip + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", msg='" + msg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
