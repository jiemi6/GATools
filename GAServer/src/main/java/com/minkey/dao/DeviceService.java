package com.minkey.dao;

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
     */
    private int serviceType;

    /**
     * 服务配置数据， 根据不同的服务，配置数据不一样， 这里存的是json
     */
    private String configData;


}
