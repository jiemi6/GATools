package com.minkey.contants;

public enum AlarmEnum {

    //网络类
    ip_notConnect(1000,"ip网络不通"),
    port_notConnect(1001,"端口不通"),


    //性能类
    shebeixingneng(2000,"设备性能报警"),


    //设备服务类
    shebeifuwu(3000,"设备服务报警"),
    no_snmpservice(3001,"没有配置SNMP服务" ),

    //链路类
    no_TAS(6001, "链路没有配置TAS"),
    no_UAS(6002, "链路没有配置UAS"),

    //任务类
    no_source(7000,"找不到数据源"),
    no_taskProcess(7001, "任务进程不存在"),

    //数据库类
    db_createError(8000, "创建db访问对象异常"),
    db_wrongpwd(8001, "数据库账号密码错误"),


    //探针类,
    no_detector(5000,"没有配置探针");


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
