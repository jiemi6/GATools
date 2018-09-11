package com.minkey.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 设备硬件收集器
 */
@Component
public class DeviceExplorerScheduled {
    private final static Logger logger = LoggerFactory.getLogger(DeviceExplorerScheduled.class);

    /**
     * 获取所有设备
     */
    private void getAllDevice(){

        //剔除连接状态异常的，

        //剔除文件夹

        //
    }

    @Async
    public void getDeviceFromSNMP(){

        logger.info("获取设备硬件使用信息");
    }
}
