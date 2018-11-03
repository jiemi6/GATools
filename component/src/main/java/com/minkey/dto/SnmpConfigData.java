package com.minkey.dto;

import com.minkey.command.SnmpUtil;

/**
 * snmp数据体
 */
public class SnmpConfigData extends BaseConfigData {
    /**
     * 团体名称
     */
    private String community =  SnmpUtil.DEFAULT_COMMUNITY;

    private int version = SnmpUtil.DEFAULT_VERSION;

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SnmpConfigData{" +
                "community='" + community + '\'' +
                ", version=" + version +
                "} " + super.toString();
    }
}
