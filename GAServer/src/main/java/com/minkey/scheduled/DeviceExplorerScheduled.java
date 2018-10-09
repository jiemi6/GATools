package com.minkey.scheduled;

import com.minkey.db.DeviceHandler;
import com.minkey.db.dao.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 设备硬件收集器
 */
@Component
public class DeviceExplorerScheduled {
    private final static Logger logger = LoggerFactory.getLogger(DeviceExplorerScheduled.class);

    @Autowired
    DeviceHandler deviceHandler;

    /**
     * 获取所有设备
     */
    private void getAllDevice(){
        List<Device> deviceList = deviceHandler.queryAll();


        //剔除连接状态异常的，

        //剔除文件夹

        //
    }

    @Async
    public void getDeviceFromSNMP(){

        logger.info("获取设备硬件使用信息");
    }
}
