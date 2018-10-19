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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工具箱接口
 */
@RestController
@RequestMapping("/tools")
public class ToolsController {
    private final static Logger logger = LoggerFactory.getLogger(ToolsController.class);

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
        logger.info("start: 执行ping");

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

            String cmd = "ping "+ip+ " -c 4";
            if(OSUtil.isWindowsOS()){
                cmd = "ping "+ip;
            }

            ResultInfo resultInfo = null;
            if(netArea == Device.NETAREA_IN){
                //内网 直接执行
                resultInfo = LocalExecuter.exec(cmd);
            }else{
//                //获取探针
//                Device device = deviceHandler.query(deviceId);
                //获取该探针的ssh服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_DETECTOR);

                if(ssh == null){
                    return JSONMessage.createFalied("探针没有配置ssh服务，无法执行命令").toString();
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
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行ping");
        }
    }


    /**
     * telnet工具
     * @return
     */
    @RequestMapping("/telnet")
    public String telnet(Integer netArea,String ip,int port,Long deviceId) {
        logger.info("start: 执行telnet");

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
                //获取探针
                Device device = deviceHandler.query(deviceId);
                //获取该探针的ssh服务
                DeviceService ssh = deviceServiceHandler.query8Device(deviceId,DeviceService.SERVICETYPE_SSH);

                if(ssh == null){
                    return JSONMessage.createFalied("探针没有配置ssh服务，无法执行命令").toString();
                }

                BaseConfigData configData = ssh.getConfigData();

                //执行命令
                isConnect = DetectorUtil.telnetCmd(configData.getIp(),configData.getPort(),ip,port);
            }

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行telnet");
        }

    }


    /**
     * ssh工具开关
     * @return
     */
    @RequestMapping("/sshd")
    public String sshd() {
        //调用系统命令进行开关，

        return JSONMessage.createSuccess().toString();

    }

    @RequestMapping("/testDB")
    public String testDB(DBConfigData dbConfigData){
        logger.info("start: 执行测试数据库连接 dbConfigData={}",dbConfigData);

        if(StringUtils.isEmpty(dbConfigData.getIp())
                || StringUtils.isEmpty(dbConfigData.getPwd())
                || StringUtils.isEmpty(dbConfigData.getName())
                || StringUtils.isEmpty(dbConfigData.getDbName())
                || dbConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect = dynamicDB.testDB(dbConfigData);

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 执行测试数据库连接 ");
        }
    }

    /**
     * 设置本平台的snmp服务信息，方便其他软件读取。
     * @return
     */
    @RequestMapping("/snmp")
    public String snmp(String ip,String oid) {

        JSONObject jo = new SnmpUtil(ip).snmpWalk(oid);
        //第三方实现， 默认开启

        return JSONMessage.createSuccess().addData(jo).toString();

    }

    /**
     * 设置本平台的syslog推送目的地，发送给其他服务器的日志收集服务器
     * @return
     */
    @RequestMapping("/syslog")
    public String syslog() {



        return JSONMessage.createSuccess().toString();

    }


}