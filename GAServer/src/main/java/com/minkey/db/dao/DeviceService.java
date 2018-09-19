package com.minkey.db.dao;

import java.util.Objects;

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
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务类型， 固定几种，直接定义在代码中
     * 1 : 数据库 <br>
     * 2 : ftp <br>
     * 3 : ssh <br>
     */
    private int serviceType;

    /**
     * 服务配置数据， 根据不同的服务，配置数据不一样， 这里存的是json
     */
    private String configData;

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

    public String getConfigData() {
        return configData;
    }

    public void setConfigData(String configData) {
        this.configData = configData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceService that = (DeviceService) o;
        return serviceId == that.serviceId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(serviceId);
    }
}
