package com.minkey.cache;

import com.minkey.command.Ping;
import com.minkey.command.Telnet;
import com.minkey.db.dao.Device;
import com.minkey.dto.DeviceExplorer;
import com.minkey.exception.SystemException;
import com.minkey.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 终端连通性缓存
 */
@Component
public class DeviceConnectCache {
    private final static Logger logger = LoggerFactory.getLogger(DeviceConnectCache.class);

    /**
     * 正常的
     */
    private Set<Long> okSet = new HashSet<>();

    /**
     * 失败一次后，放入，待检测的
     */
    private Set<Long> checkSet = new HashSet<>();

    /**
     * 失败三次之后放入，认为连接不上的
     */
    private Set<Long> disabledSet = new HashSet<>();


    public void putOk(Long deviceId){
        okSet.add(deviceId);

        checkSet.remove(deviceId);
        disabledSet.remove(deviceId);
    }

    public void putCheck(Long deviceId){
        checkSet.add(deviceId);

        okSet.remove(deviceId);
        disabledSet.remove(deviceId);
    }

    public void putDisabled(Long deviceId){
        disabledSet.add(deviceId);

        checkSet.remove(deviceId);
        okSet.remove(deviceId);
    }

    /**
     * 每5秒刷新终端数据
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void refresh(){

        //获取所有机器的ip，采用snmp方式访问

//        logger.info("测试下 schedule！");

    }

    private boolean testConnect(Device device){
        if(device == null){
            throw new SystemException("设备不能为空");
        }
        if(device.getDeviceType() == Device.DeviceType.FOLDER.type){
            return false;
        }

        if(StringUtils.isEmpty(device.getIp())){
            throw new SystemException("设备ip不能为空");
        }

        return Ping.javaPing(device.getIp());
    }


}
