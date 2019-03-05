package com.minkey.contants;

public enum  ConfigEnum {

    SystemRegister("SystemInfo","系统注册信息"),

    AlarmConfig("AlarmSet","报警设置"),

    Syslog2other("Syslog2other","syslog转发给其他服务器配置"),

    Sshd("Sshd","os有关sshd配置"),

    Snmp("Snmp","os有关Snmp配置"),

    AutoCheckTimes("AutoCheckTimes","系统自检时间"),

    NewWork("NewWork","本系统网卡设置"),

    LicenseData("LicenseData","证书相关数据"),

    AlarmSendIndex("AlarmSendIndex","报警发送的下标记录"),








    LogOverDay("LogOverDay","日志过期天数，最大保存日志天数");

    private String configKey;
    private String desc;

    ConfigEnum(String configKey ,String desc) {
        this.configKey = configKey;
        this.desc = desc;
    }

    public String getConfigKey() {
        return configKey;
    }

    public String getDesc() {
        return desc;
    }
}
