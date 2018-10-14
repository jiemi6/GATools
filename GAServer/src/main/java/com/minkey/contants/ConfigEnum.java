package com.minkey.contants;

public enum  ConfigEnum {

    SystemRegister("SystemInfo","系统注册信息"),

    AutoCheckTimes("AutoCheckTimes","系统自检时间"),

    LicenseData("LicenseData","证书相关数据"),

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
