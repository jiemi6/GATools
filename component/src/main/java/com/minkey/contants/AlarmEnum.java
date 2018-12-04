package com.minkey.contants;

public enum AlarmEnum {

    ip_notConnect(1,"ip网络不通"),

    shebeixingneng(2,"设备性能报警"),

    shebeifuwu(3,"设备服务报警"),

    no_source(4,"找不到数据源"),

    no_detector(5,"没有配置探针"),


    port_notConnect(6,"端口不通"),

    no_snmpservice(7,"没有配置SNMP服务" ),

    no_TAS(8, "链路没有配置TAS"),
    no_UAS(9, "链路没有配置UAS"),

    no_taskProcess(10, "任务进程不存在");


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
