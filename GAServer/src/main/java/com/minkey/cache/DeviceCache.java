package com.minkey.cache;

import com.minkey.command.Ping;
import com.minkey.db.DeviceHandler;
import com.minkey.db.dao.Device;
import com.minkey.dto.DeviceExplorer;
import com.minkey.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 终端缓存
 */
@Component
public class DeviceCache {
    private final static Logger logger = LoggerFactory.getLogger(DeviceCache.class);

    @Autowired
    static DeviceHandler deviceHandler;

    /**
     * 正常的
     */
    private static Set<Long> okSet = new HashSet<>();

    /**
     * 失败一次后，放入，待检测的
     */
    private static Set<Long> checkSet = new HashSet<>();

    /**
     * 认为连接不上的
     */
    private static Set<Long> disabledSet = new HashSet<>();

    /**
     * 设备硬件资源map
     * key ：硬件id
     * value： 资源对象
     */
    private static Map<Long,DeviceExplorer> deviceExplorerMap = new HashMap<>();

    private static void putOk(Long deviceId){
        okSet.add(deviceId);

        checkSet.remove(deviceId);
        disabledSet.remove(deviceId);
    }

    private static void putCheck(Long deviceId){
        checkSet.add(deviceId);

        okSet.remove(deviceId);
        disabledSet.remove(deviceId);
    }

    private static void putDisabled(Long deviceId){
        disabledSet.add(deviceId);

        checkSet.remove(deviceId);
        okSet.remove(deviceId);
    }

    public static void putConnect(Long deviceId,boolean isConnect){
        if(okSet.contains(deviceId)){
            //ok的不通过一次 就放入 check
            if(!isConnect){
                putCheck(deviceId);
            }
        }else if(checkSet.contains(deviceId)){
            if(isConnect){
                //check 通过就放入ok ，
                putOk(deviceId);
            }else{
                //不通过就放入disabled
                putDisabled(deviceId);
            }

        }else if(disabledSet.contains(deviceId)){
            if(isConnect){
                //测试通过一次,就放入check
                putCheck(deviceId);
            }
        }else{
            //新设备 就默认放入ok
            putOk(deviceId);
        }
    }


    /**
     * 批量获取
     * @param deviceIds 需要获取的硬件id集合
     * @return 得到的具有数据的结果，map的size小于等于参数ids的size
     */
    public static Map<Long,DeviceExplorer> getDeviceExplorerMap(Set<Long> deviceIds){
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
    public static DeviceExplorer getDeviceExplorer(Long deviceId){
        return deviceExplorerMap.get(deviceId);
    }


    public static void putDeviceExplorer(long deviceId, DeviceExplorer deviceExplorer) {
        deviceExplorerMap.put(deviceId,deviceExplorer);
    }
}
