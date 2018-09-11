package com.minkey.cache;

import com.minkey.dto.DeviceExplorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 终端硬件资源缓存，
 * 主要包括 cpu，内存， 硬盘占用率，
 */
@Component
public class DeviceExplorerCache {
    private final static Logger logger = LoggerFactory.getLogger(DeviceExplorerCache.class);


    private Map<Long,DeviceExplorer> deviceExplorerMap = new HashMap<>();


    /**
     * 批量获取
     * @param deviceIds 需要获取的硬件id集合
     * @return 得到的具有数据的结果，map的size小于等于参数ids的size
     */
    public Map<Long,DeviceExplorer> getList(Set<Long> deviceIds){
        Map<Long,DeviceExplorer> returnMap = new HashMap<>(deviceIds.size());
        deviceIds.forEach(aLong -> {
            returnMap.put(aLong, deviceExplorerMap.get(aLong));
        });

        return returnMap;
    }

    /**
     * 获取硬件资源，有可能返回空
     * @param deviceId
     * @return 有可能返回null；
     */
    public DeviceExplorer get(Long deviceId){
        return deviceExplorerMap.get(deviceId);
    }


    /**
     * 设置缓存值，采集到的值随时更新到此缓存中
     * @param deviceId
     * @param deviceExplorer
     */
    public void set(Long deviceId,DeviceExplorer deviceExplorer){
        deviceExplorerMap.put(deviceId,deviceExplorer);
    }


    /**
     * 每5秒刷新终端数据
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void refresh(){

        //获取所有机器的ip，采用snmp方式访问



    }


}
