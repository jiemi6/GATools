package com.minkey.contants;

public enum AlarmEnum {
    unKnow(-1,"未知错误"),

    //网络类
    ip_notConnect(1000,"网络不通"),
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
    db_createError(8000, "DB访问异常"),
    db_wrongpwd(8001, "DB账号密码错误"),
    db_databaseName_noexist(8002, "DB名不存在"),


    //ftp相关
    ftp_io_error(9000,"FTP传输异常"),
    ftp_wrongpwd(9001,"FTP账号密码错误"),
    ftp_rootUnLock(9002,"FTP根目录未锁定"),
    ftp_notPassive(9003,"FTP非被动模式"),
    ftp_notAllAuth(9004,"FTP没有读写删全部权限"),
    ftp_fileNum_tooBig(9005,"FTP根目录下文件数量太大"),
    ftp_topDirNum_tooBig(9006,"FTP根目录下文件夹太多"),
    ftp_floorNum_tooBig(9007,"FTP根目录下子目录层级太多"),

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


    public static AlarmEnum find8Type(int alarmType) {
        for (AlarmEnum alarmEnum : AlarmEnum.values()) {
            if(alarmEnum.getAlarmType() == alarmType){
                return alarmEnum;
            }
        }

        return AlarmEnum.unKnow;
    }

    @Override
    public String toString() {
        return "AlarmEnum{" +
                "alarmType=" + alarmType +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }
}