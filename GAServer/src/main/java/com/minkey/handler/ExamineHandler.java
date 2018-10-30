package com.minkey.handler;

import com.minkey.cache.DeviceConnectCache;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.dao.CheckItem;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查log
 */
@Slf4j
@Component
public class ExamineHandler {
    @Autowired
    CheckItemHandler checkItemHandler;

    @Autowired
    DeviceConnectCache deviceConnectCache;

    @Autowired
    DeviceStatusHandler deviceStatusHandler;

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    public void doAllInOne(long checkId) {
    }

    public List<CheckItem> getResultList(Long checkId, Integer index) {
        List<CheckItem> checkItems  = checkItemHandler.query(checkId,index);
        return  checkItems;
    }

    public List<CheckItem> getResultList(Long checkId) {
        List<CheckItem> checkItems  = checkItemHandler.queryAll(checkId);
        return  checkItems;
    }

    /**
     * 单个设备体检
     * @param checkId
     * @param deviceId
     */
    @Async
    public void doDevice(long checkId, Long deviceId) {
        Device device = deviceStatusHandler.getDevice8Id(deviceId);
        if(device == null){
            log.error("发起单个设备体检，体检设备不存在 deviceId = {}" ,deviceId);
            return ;
        }

        List<DeviceService> deviceServiceList = deviceServiceHandler.query8Device(deviceId);
        CheckItem checkItem;
        //默认就只有一步 就是检查连接
        int totalStep = 1;
        //检查网络联通性
        boolean isConnect = deviceConnectCache.isOk(deviceId);
        if(isConnect){
            //网络联通性 + 硬件情况 + 所有服务个数
            totalStep = 1 + 1 + (deviceServiceList == null ? 0 : deviceServiceList.size());
            checkItem = new CheckItem(1,totalStep);
            checkItem.setCheckId(checkId);
            checkItem.setResultLevel(CheckItem.RESULTLEVEL_NORMAL);
            checkItem.setResultMsg("设备%s网络网络状态正常");
            checkItemHandler.insert(checkItem);
        }else{
            checkItem = new CheckItem(1,totalStep);
            checkItem.setCheckId(checkId);
            checkItem.setResultLevel(CheckItem.RESULTLEVEL_ERROR);
            checkItem.setResultMsg("设备%s无法联通，请检查网络状态");
            checkItemHandler.insert(checkItem);
            return;
        }
        //检查硬件情况
        deviceStatusHandler.getDeviceExplorer(deviceId);

        //检查该设备所有服务


    }

    public void doTask(long checkId, Long taskId) {
    }

    public void doLink(long checkId, Long linkId) {
    }
}
