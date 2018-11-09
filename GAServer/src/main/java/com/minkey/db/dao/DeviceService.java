package com.minkey.db.dao;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.FTPConfigData;
import com.minkey.dto.SnmpConfigData;
import org.apache.commons.lang3.StringUtils;

/**
 *  设备所包含的服务，一个设备上可以跑多个服务
 */
public class DeviceService {

    /**
     * 服务id，自增主键
     */
    private long serviceId;

    /**
     * 所对应的设备id
     */
    private long deviceId;

    /**
     * 冗余ip，服务的ip就是设备的ip
     */
    private String ip;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务类型， 固定几种，直接定义在代码中
     * 1 : 数据库 <br>
     * 2 : ftp <br>
     * 3 : ssh <br>
     * 4 : 探针Http <br>
     * 5 : snmp服务
     */
    private int serviceType;

    public static final int SERVICETYPE_DB = 1;
    public static final int SERVICETYPE_FTP = 2;
    public static final int SERVICETYPE_SSH = 3;
    public static final int SERVICETYPE_DETECTOR = 4;
    public static final int SERVICETYPE_SNMP = 5;

    /**
     * 服务配置数据， 根据不同的服务，配置数据不一样， 这里存的是json
     */
    private BaseConfigData configData;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public BaseConfigData getConfigData() {
        return configData;
    }

    public void setConfigData(BaseConfigData configData) {
        this.configData = configData;
    }

    /**
     * 获取str时候，从data转换
     * @return
     */
    public String configDataStr() {
        if(configData == null){
            return null;
        }
        return JSONObject.toJSONString(configData);
    }

    /**
     * 专门提供给前端放入字符串作为数据
     */
    @JSONField(serialize=false)
    private String configDataStr;
    public void setConfigDataStr(String configDataStr) {
        this.configDataStr = configDataStr;
    }

    public String getConfigDataStr() {
        return configDataStr;
    }


    @Override
    public String toString() {
        return "DeviceService{" +
                "serviceId=" + serviceId +
                ", deviceId=" + deviceId +
                ", ip='" + ip + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceType=" + serviceType +
                ", configData=" + configData +
                '}';
    }


    public static final BaseConfigData conventConfigData8str(int serviceType,String configDataStr){
        if(StringUtils.isEmpty(configDataStr)){
            return null;
        }

        switch (serviceType){
            case DeviceService.SERVICETYPE_DB :
                return JSONObject.parseObject(configDataStr,DBConfigData.class);
            case DeviceService.SERVICETYPE_SNMP :
                return JSONObject.parseObject(configDataStr,SnmpConfigData.class);
            case DeviceService.SERVICETYPE_FTP :
                return JSONObject.parseObject(configDataStr,FTPConfigData.class);
            default:
                return JSONObject.parseObject(configDataStr,BaseConfigData.class);
        }
    }


}
