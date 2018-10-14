package com.minkey.db.dao;

import java.util.List;

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
     * 设备ip，可选，文件夹类型就没有ip
     */
    private String ip;

    /**
     * 设备类型<br>
     * 1 : 探针 <br>
     * 2 : 文件夹 <br>
     * 3 :
     *@see com.minkey.contants.DeviceType
     */
    private int deviceType;


    /**
     * 所属区域
     *
     *             <option value="路由接入区">路由接入区</option>
     *             <option selected="" value=" 边界保护区">&nbsp;边界保护区</option>
     *             <option value="应用服务器">应用服务器</option>
     *             <option value="安全隔离区">安全隔离区</option>
     *             <option value="安全检测与管理区">安全检测与管理区</option>
     */
    private int area;

    /**
     * 网络区域： 内网/外网
     */
    private int netArea;

    public static final int NETAREA_IN = 1;
    public static final int NETAREA_OUT = 2;

    /**
     * 图标id
     */
    private int icon;

    /**
     * 设备对应的服务
     */
    List<DeviceService> deviceServiceList;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getNetArea() {
        return netArea;
    }

    public void setNetArea(int netArea) {
        this.netArea = netArea;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<DeviceService> getDeviceServiceList() {
        return deviceServiceList;
    }

    public void setDeviceServiceList(List<DeviceService> deviceServiceList) {
        this.deviceServiceList = deviceServiceList;
    }

}
