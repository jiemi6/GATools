package com.minkey.db.dao;

/**
 * 设备对象，每个机器都是一个设备
 */
public class Device {

    /**
     * 设备id，自增主键
     */
    private long deviceId;

    /**
     * 设备名称，用户自定义
     */
    private String deviceName;

    /**
     * 设备ip
     */
    private String ip;

    /**
     * 设备类型<br>
     * 1 : 探针 <br>
     * 2 : 文件夹 <br>
     * 3 :
     *
     */
    private int deviceType;




}
