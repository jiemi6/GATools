package com.minkey.contants;

public enum  ConfigEnum {

    SystemRegister("SystemRegister","系统注册信息"),

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
