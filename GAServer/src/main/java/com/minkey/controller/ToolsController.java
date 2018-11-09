package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.command.Telnet;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.OSUtil;
import com.minkey.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工具箱接口
 */
@Slf4j
@RestController
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    DeviceHandler deviceHandler;

    @Autowired
    DeviceServiceHandler deviceServiceHandler;

    @Autowired
    DynamicDB dynamicDB;


    /**
     * ping工具
     * @return
     */
    @RequestMapping("/ping")
    public String ping(Integer netArea,String ip,Long deviceId) {
        log.info("start: 执行ping ip={},netArea={},detectorId={}",ip,netArea,deviceId);

        if(netArea == null){
            netArea = Device.NETAREA_IN;
        }

        if(netArea == Device.NETAREA_OUT && deviceId == null){
            return JSONMessage.createFalied("请选择一个探针").toString();
        }

        if(StringUtils.isEmpty(ip)){
            return JSONMessage.createFalied("ip不能为空").toString();
        }

        if(!StringUtil.isIp(ip)){
            return JSONMessage.createFalied("ip格式不正确").toString();
        }
        try{

            String cmd = "ping "+ip+ " -c 4";
            if(OSUtil.isWindowsOS()){
                cmd = "ping "+ip;
            }

            ResultInfo resultInfo = null;
            if(netArea == Device.NETAREA_IN){
                //内网 直接执行
                resultInfo = LocalExecuter.exec(cmd);
            }else{
                //获取该探针服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("该设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                resultInfo = DetectorUtil.executeSh(configData.getIp(),configData.getPort(),cmd);
            }

            if(resultInfo.isExitStutsOK()){
                return JSONMessage.createSuccess().addData("msg",resultInfo.getOutRes()).toString();
            }else{
                return JSONMessage.createSuccess().addData("msg",resultInfo.getErrRes()).toString();
            }


        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行ping");
        }
    }


    /**
     * telnet工具
     * @return
     */
    @RequestMapping("/telnet")
    public String telnet(Integer netArea,String ip,int port,Long deviceId) {
        log.info("start: 执行telnet ip={},port={},netArea={},detectorId={}",ip,port,netArea,deviceId);

        if(netArea == null){
            netArea = Device.NETAREA_IN;
        }

        try{
            if(StringUtils.isEmpty(ip)){
                return JSONMessage.createFalied("ip不能为空").toString();
            }

            if(!StringUtil.isIp(ip)){
                return JSONMessage.createFalied("ip格式不正确").toString();
            }

            boolean isConnect ;
            if(netArea == Device.NETAREA_IN){
                //内网 直接执行
                isConnect = Telnet.doTelnet(ip,port);
            }else{
                //获取该探针服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("该设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                isConnect = DetectorUtil.telnetCmd(configData.getIp(),configData.getPort(),ip,port);
            }

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行telnet");
        }
    }

    /**
     *测试数据库
     * @param dbConfigData
     * @param deviceId
     * @return
     */
    @RequestMapping("/testDB")
    public String testDB(Integer netArea,DBConfigData dbConfigData,Long deviceId){
        log.info("start: 执行测试数据库连接 netArea={},decetorId={}, dbConfigData={}",netArea,deviceId,dbConfigData);

        if(StringUtils.isEmpty(dbConfigData.getIp())
                || StringUtils.isEmpty(dbConfigData.getPwd())
                || StringUtils.isEmpty(dbConfigData.getName())
                || StringUtils.isEmpty(dbConfigData.getDbName())
                || dbConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect;
            if(netArea == Device.NETAREA_IN){
                //内网直接测试
                isConnect = dynamicDB.testDB(dbConfigData);
            }else{
                //获取该探针服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("该设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                isConnect = DetectorUtil.testDB(configData.getIp(),configData.getPort(),dbConfigData);
            }

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行测试数据库连接 ");
        }
    }

    /**
     * 协助访问其他服务器snmp工具
     * @return
     */
    @RequestMapping("/snmp")
    public String snmp(Integer netArea,String ip,Integer port,String oid,Long deviceId) {
        log.info("start: 执行snmp工具 netArea={},decetorId={}, ip={},port={},oid={}",netArea,deviceId,ip,port,oid);

        if(StringUtils.isEmpty(ip)){
            return JSONMessage.createFalied("ip不能为空").toString();
        }

        if(!StringUtil.isIp(ip)){
            return JSONMessage.createFalied("ip格式不正确").toString();
        }

        try{
            JSONObject jo;
            if(netArea == Device.NETAREA_IN){
                //内网直接测试
                jo = new SnmpUtil(ip).snmpWalk(oid);
            }else{
                //获取该探针服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("该设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                jo = DetectorUtil.snmpWalk(configData.getIp(),configData.getPort(),ip,oid);
            }

            return JSONMessage.createSuccess().addData("msg",jo).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行snmp工具 ");
        }
    }
}