package com.minkey.db.analysis;

import java.util.Date;

/**
 * 告警天统计log
 */
public class AlarmDayLog {
    private long dayLogId;
    /**
     * 插入时间，为数据所在天
     */
    private Date createTime;

    private int totalLinkNum;
    private int alarmLinkNum;
    private int totalDeviceNum;
    private int alarmDeviceNum;
    private int totalTaskNum;
    private int alarmTaskNum;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTotalLinkNum() {
        return totalLinkNum;
    }

    public void setTotalLinkNum(int totalLinkNum) {
        this.totalLinkNum = totalLinkNum;
    }

    public int getAlarmLinkNum() {
        return alarmLinkNum;
    }

    public void setAlarmLinkNum(int alarmLinkNum) {
        this.alarmLinkNum = alarmLinkNum;
    }

    public int getTotalDeviceNum() {
        return totalDeviceNum;
    }

    public void setTotalDeviceNum(int totalDeviceNum) {
        this.totalDeviceNum = totalDeviceNum;
    }

    public int getAlarmDeviceNum() {
        return alarmDeviceNum;
    }

    public void setAlarmDeviceNum(int alarmDeviceNum) {
        this.alarmDeviceNum = alarmDeviceNum;
    }

    public int getTotalTaskNum() {
        return totalTaskNum;
    }

    public void setTotalTaskNum(int totalTaskNum) {
        this.totalTaskNum = totalTaskNum;
    }

    public int getAlarmTaskNum() {
        return alarmTaskNum;
    }

    public void setAlarmTaskNum(int alarmTaskNum) {
        this.alarmTaskNum = alarmTaskNum;
    }

    public long getDayLogId() {
        return dayLogId;
    }

    public void setDayLogId(long dayLogId) {
        this.dayLogId = dayLogId;
    }

    @Override
    public String toString() {
        return "AlarmDayLog{" +
                "dayLogId=" + dayLogId +
                ", createTime=" + createTime +
                ", totalLinkNum=" + totalLinkNum +
                ", alarmLinkNum=" + alarmLinkNum +
                ", totalDeviceNum=" + totalDeviceNum +
                ", alarmDeviceNum=" + alarmDeviceNum +
                ", totalTaskNum=" + totalTaskNum +
                ", alarmTaskNum=" + alarmTaskNum +
                '}';
    }
}
