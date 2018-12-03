package com.minkey.contants;

public enum AlarmEnum {

    wangluobutong(1,"网络不通"),

    shebeixingneng(2,"设备性能报警"),

    shebeifuwu(3,"设备服务报警"),

    no_source(4,"找不到数据源"),

    no_detector(5,"没有配置探针");


    private int alarmType;
    private String desc;

    AlarmEnum(int alarmType , String desc) {
        this.alarmType = alarmType;
        this.desc = desc;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public String getDesc() {
        return desc;
    }
}
