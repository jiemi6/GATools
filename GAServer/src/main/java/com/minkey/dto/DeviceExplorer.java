package com.minkey.dto;

import java.util.Map;

/**
 * 设备硬件资源信息，
 * 包括cpu，内存，磁盘
 */
public class DeviceExplorer {
    /**
     * cpu占用率，list长度为cpu个数，Integer值为单个cpu的占用率
     */
    private Map<String,RateObj> cpu;

    /**
     * 磁盘占用
     */
    private Map<String,RateObj> disk;

    /**
     * 缓存占用，缓存只有一个
     */
    private RateObj  mem;



}
