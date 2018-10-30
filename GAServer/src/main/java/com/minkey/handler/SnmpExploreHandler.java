package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.DeviceExplorer;
import com.minkey.dto.RateObj;
import com.minkey.dto.SnmpConfigData;
import org.apache.commons.lang3.StringUtils;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通过snmp获取硬件资源情况
 */
@Component
public class SnmpExploreHandler {

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    /**
     * 设备对应的snmp服务缓存
     * key：设备id
     * value ： 该设备的snmp服务
     */
    private Map<Long,DeviceService> deviceSNMPServiceMap = new HashMap<>();


    public void init(){
        List<DeviceService> deviceServiceList = deviceServiceHandler.query8Type(DeviceService.SERVICETYPE_SNMP);
        if(CollectionUtils.isEmpty(deviceServiceList)){
            //清空探针缓存
            deviceSNMPServiceMap = new HashMap<>();
        }else{
            //重新赋值
            deviceSNMPServiceMap = deviceServiceList.stream().collect(Collectors.toMap(DeviceService::getDeviceId, DeviceService-> DeviceService));
        }
    }


    /**
     * 通过探针获取外网设备的硬件资源
     * @param device
     * @param detectorService
     * @return
     */
    public DeviceExplorer get(Device device, DeviceService detectorService) {
        if(device == null || detectorService == null){
            return null;
        }
//        DetectorUtil.snmpWalk();


        return null;
    }

    /**
     * 直接获取内网的硬件资源
     * @param device
     * @return
     */
    public DeviceExplorer get(Device device) {
        if(device == null){
            return null;
        }

        DeviceService deviceService = deviceSNMPServiceMap.get(device.getDeviceId());
        if(deviceService == null){
            return null;
        }

        SnmpConfigData snmpConfigData = (SnmpConfigData) deviceService.getConfigData();
        //获取该设备的 snmp服务
        SnmpUtil snmpUtil = new SnmpUtil(deviceService.getIp(),
                snmpConfigData.getPort(),
                snmpConfigData.getCommunity(),
                snmpConfigData.getVersion(),
                0,
                1000);

        DeviceExplorer deviceExplorer = new DeviceExplorer();

        deviceExplorer.setCpus(getCpu(snmpUtil));
        deviceExplorer.setDisks(getDisk(deviceExplorer,snmpUtil));


        return deviceExplorer;
    }

    private Map<String,RateObj> getDisk(DeviceExplorer deviceExplorer, SnmpUtil snmpUtil) {
        String oid = "1.3.6.1.2.1.25.2.3.1";
        JSONObject jo = snmpUtil.snmpWalk(oid);

        if(jo == null || jo.size() == 0){
            return null;
        }

        Map<String,RateObj> map = new HashMap<>();
        RateObj rateObj ;
        List<Integer> lastOidList = new ArrayList<>();
        OID tempOid;
        for(String key : jo.keySet()){
            tempOid = new OID(key);
            //先取序列号
            if(tempOid.startsWith(new OID(oid + ".1"))){
                int lastOid = tempOid.removeLast();
                lastOidList.add(lastOid);
            }
        }

        for(Integer lastOid : lastOidList){
            //名称
            String name = jo.getString(oid+".3."+lastOid);
            //分配单元所占字节数
            int danweiByte = jo.getInteger(oid+".4."+lastOid);
            //总区块数
            long totalArea = jo.getLong(oid+".5."+lastOid);
            //已经使用的区块数
            long useArea = jo.getLong(oid+".6."+lastOid);
            //总大小
            long total = totalArea * danweiByte;

            rateObj = RateObj.create8Use(totalArea * danweiByte , useArea *danweiByte);
            //如果是物理内存
            if(StringUtils.equalsIgnoreCase(name , "Physical Memory")){
                deviceExplorer.setMem(rateObj);
            }else if(StringUtils.equalsIgnoreCase(name , "Virtual Memory")){
                //如果是虚拟内存，暂时不获取
            }else if(name.contains(":\\") || name.contains("/")){
                //如果是磁盘存储
                map.put(name,rateObj);
            }
        }
        return map;
    }

    private Map<String,RateObj> getCpu(SnmpUtil snmpUtil) {
        JSONObject jo = snmpUtil.snmpWalk("1.3.6.1.2.1.25.3.3.1.2");

        if(jo == null || jo.size() == 0){
            return null;
        }

        Map<String,RateObj> map = new HashMap<>();
        RateObj rateObj ;
        for(String key : jo.keySet()){
            rateObj = RateObj.create8Use(100,Double.valueOf(jo.getString(key)));
            map.put(key,rateObj);
        }
        return map;
    }


    private Map<String,RateObj> getNetwork(SnmpUtil snmpUtil) {
        String net_in = "1.3.6.1.2.1.2.2.1.16";
        String net_out = "1.3.6.1.2.1.2.2.1.10";


        //Minkey 网络实时流量不知道这么获取
        return null;
    }
}
