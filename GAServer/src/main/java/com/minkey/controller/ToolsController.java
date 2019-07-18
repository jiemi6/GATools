package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceConnectCache;
import com.minkey.command.SnmpUtil;
import com.minkey.command.Telnet;
import com.minkey.contants.CommonContants;
import com.minkey.db.DeviceHandler;
import com.minkey.db.DeviceServiceHandler;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.FTPConfigData;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
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

    @Autowired
    DeviceConnectCache deviceConnectCache;


    /**
     * ping工具
     * @return
     */
    @RequestMapping("/ping")
    public String ping(Integer netArea,String ip,Long deviceId) {
        log.debug("start: 执行ping ip={},netArea={},deviceId={}",ip,netArea,deviceId);

        if(netArea == null){
            netArea = CommonContants.NETAREA_IN;
        }

        if(netArea == CommonContants.NETAREA_OUT && deviceId == null){
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
//            if(OSUtil.isWindowsOS()){
//                cmd = "ping "+ip;
//            }

            ResultInfo resultInfo = null;
            if(netArea == CommonContants.NETAREA_IN){
                //内网 直接执行
                resultInfo = LocalExecuter.exec(cmd);
            }else{
                if (deviceId == null){
                    return JSONMessage.createFalied("请选择一个在线的探针").toString();
                }
                //探针不在线，无法执行命令
                if(!deviceConnectCache.isOk(deviceId)){
                    return JSONMessage.createFalied("探针不在线，无法执行命令").toString();
                }
                //获取该探针服务
                DeviceService detectorService = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(detectorService == null){
                    return JSONMessage.createFalied("该探针设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = detectorService.getConfigData();

                //执行命令
                resultInfo = DetectorUtil.executeSh(configData.getIp(),configData.getPort(),cmd);
            }

            if(resultInfo == null){
                return JSONMessage.createSuccess().addData("msg",String.format("ping目标设备%s失败",ip)).toString();
            }

            if(resultInfo.isExitStutsOK()){
                return JSONMessage.createSuccess().addData("msg",resultInfo.getOutRes()).toString();
            }else{
                return JSONMessage.createSuccess().addData("msg",resultInfo.getOutRes()).toString();
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行ping");
        }
    }


    /**
     * telnet工具
     * @return
     */
    @RequestMapping("/telnet")
    public String telnet(Integer netArea,String ip,int port,Long deviceId) {
        log.debug("start: 执行telnet ip={},port={},netArea={},detectorId={}",ip,port,netArea,deviceId);

        if(netArea == null){
            netArea = CommonContants.NETAREA_IN;
        }

        if(StringUtils.isEmpty(ip)){
            return JSONMessage.createFalied("ip不能为空").toString();
        }

        if(!StringUtil.isIp(ip)){
            return JSONMessage.createFalied("ip格式不正确").toString();
        }

        boolean isConnect = false;
        try{
            if(netArea == CommonContants.NETAREA_IN){
                //内网 直接执行
                isConnect = Telnet.doTelnet(ip,port);
            }else{
                if (deviceId == null){
                    return JSONMessage.createFalied("请选择一个在线的探针").toString();
                }
                //探针不在线，无法执行命令
                if(!deviceConnectCache.isOk(deviceId)){
                    return JSONMessage.createFalied("探针不在线，无法执行命令").toString();
                }
                //获取该探针服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("该探针设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                isConnect = DetectorUtil.telnetCmd(configData.getIp(),configData.getPort(),ip,port);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行telnet");
            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
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
        log.debug("start: 执行测试数据库连接 netArea={},deviceId={}, dbConfigData={}",netArea,deviceId,dbConfigData);

        if(StringUtils.isEmpty(dbConfigData.getIp())
                || StringUtils.isEmpty(dbConfigData.getPwd())
                || StringUtils.isEmpty(dbConfigData.getName())
                || StringUtils.isEmpty(dbConfigData.getDbName())
                || dbConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }


        try{
            boolean isConnect;
            if(netArea == CommonContants.NETAREA_IN){
                //内网直接测试
                isConnect = dynamicDB.testDBConnect(dbConfigData);
            }else{
                if (deviceId == null){
                    return JSONMessage.createFalied("请选择一个在线的探针").toString();
                }
                //探针不在线，无法执行命令
                if(!deviceConnectCache.isOk(deviceId)){
                    return JSONMessage.createFalied("探针不在线，无法执行命令").toString();
                }
                //获取该探针服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("该探针设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                isConnect = DetectorUtil.testDBConnect(configData.getIp(),configData.getPort(),dbConfigData);
            }

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行测试数据库连接 ");
        }
    }

    /**
     * 协助访问其他服务器snmp工具
     * @return
     */
    @RequestMapping("/snmp")
    public String snmp(Integer netArea,String ip,Integer port,String oid,Long deviceId) {
        log.debug("start: 执行snmp工具 netArea={},deviceId={}, ip={},port={},oid={}",netArea,deviceId,ip,port,oid);

        if(StringUtils.isEmpty(ip)){
            return JSONMessage.createFalied("ip不能为空").toString();
        }

        if(!StringUtil.isIp(ip)){
            return JSONMessage.createFalied("ip格式不正确").toString();
        }

        try{
            JSONObject jo;
            if(netArea == CommonContants.NETAREA_IN){
                //内网直接测试
                jo = new SnmpUtil(ip).snmpWalk(oid);
            }else{
                if (deviceId == null){
                    return JSONMessage.createFalied("请选择一个在线的探针").toString();
                }

                //探针不在线，无法执行命令
                if(!deviceConnectCache.isOk(deviceId)){
                    return JSONMessage.createFalied("探针不在线，无法执行命令").toString();
                }

                //获取该探针服务
                DeviceService detectorService = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(detectorService == null){
                    return JSONMessage.createFalied("该探针设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = detectorService.getConfigData();

                //执行命令
                jo = DetectorUtil.snmpWalk(configData.getIp(),configData.getPort(),ip,oid);
            }

            return JSONMessage.createSuccess().addData("msg",jo).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行snmp工具 ");
        }
    }

    @Autowired
    FTPUtil ftpUtil;
    /**
     * 协助访问其他服务器ftp工具
     * @return
     */
    @RequestMapping("/ftp")
    public String ftp(Integer netArea, Long deviceId, FTPConfigData ftpConfigData) {
        log.debug("start: 执行ftp工具 netArea={},deviceId={}, ftpConfigData={}",netArea,deviceId,ftpConfigData);

        if(StringUtils.isEmpty(ftpConfigData.getIp())){
            return JSONMessage.createFalied("ip不能为空").toString();
        }

        if(!StringUtil.isIp(ftpConfigData.getIp())){
            return JSONMessage.createFalied("ip格式不正确").toString();
        }

        try{
            boolean isConnect;
            if(netArea == CommonContants.NETAREA_IN){
                //内网直接测试
                isConnect = ftpUtil.testFTPConnect(ftpConfigData,CommonContants.DEFAULT_TIMEOUT);
            }else{
                if (deviceId == null){
                    return JSONMessage.createFalied("请选择一个在线的探针").toString();
                }

                //探针不在线，无法执行命令
                if(!deviceConnectCache.isOk(deviceId)){
                    return JSONMessage.createFalied("探针不在线，无法执行命令").toString();
                }

                //获取该探针服务
                DeviceService detectorService = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(detectorService == null){
                    return JSONMessage.createFalied("该探针设备没有探针配置服务，无法执行命令").toString();
                }

                BaseConfigData configData = detectorService.getConfigData();

                //执行命令
                isConnect = DetectorUtil.testFTPConnect(configData.getIp(),configData.getPort(),ftpConfigData);
            }

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行ftp工具 ");
        }
    }



}