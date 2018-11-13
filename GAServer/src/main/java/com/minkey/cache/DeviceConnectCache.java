package com.minkey.cache;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 终端连接状态缓存
 */
@Component
public class DeviceConnectCache {
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

    public Set<Long> getOkSet() {
        return okSet;
    }

    /**
     * 是否ok
     * @param deviceId
     * @return
     */
    public boolean isOk(long deviceId){
        return okSet.contains(deviceId);
    }

    public void cleanAll() {
        okSet = new HashSet<>();
        checkSet = new HashSet<>();
        disabledSet = new HashSet<>();
    }

}
