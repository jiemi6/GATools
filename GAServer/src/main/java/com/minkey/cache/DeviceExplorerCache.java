package com.minkey.cache;

import com.minkey.dto.DeviceExplorer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DeviceExplorerCache {

    /**
     * 设备硬件资源map
     * key ：硬件id
     * value： 资源对象
     */
    private Map<Long,DeviceExplorer> deviceExplorerMap = new HashMap<>();

    public void remove(Long deviceId) {
        deviceExplorerMap.remove(deviceId);
    }

    /**
     * 批量获取
     * @param deviceIds 需要获取的硬件id集合
     * @return 得到的具有数据的结果，map的size小于等于参数ids的size
     */
    public Map<Long,DeviceExplorer> getDeviceExplorerMap(Set<Long> deviceIds){
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
    public DeviceExplorer getDeviceExplorer(Long deviceId){
        return deviceExplorerMap.get(deviceId);
    }


    public void putDeviceExplorer(long deviceId, DeviceExplorer deviceExplorer) {
        deviceExplorerMap.put(deviceId,deviceExplorer);
    }

}
