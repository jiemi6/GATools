package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.command.Telnet;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.CommonContants;
import com.minkey.db.dao.Device;
import com.minkey.db.dao.DeviceService;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.FTPConfigData;
import com.minkey.dto.SnmpConfigData;
import com.minkey.exception.SystemException;
import com.minkey.executer.SSHExecuter;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 设备服务检查
 *
 */
@Slf4j
@Component
public class DeviceServiceCheckManager {

    @Autowired
    DynamicDB dynamicDB;

    @Autowired
    FTPUtil ftpUtil;


    /**
     * 检查设备服务
     * @param device 入参不能为空,
     * @param deviceService 入参不能为空,
     * @param detectorService  当为外网的时候,入参不能为空,
     * @return json
     */
   public JSONObject checkDeviceService(Device device, DeviceService deviceService, DeviceService detectorService) throws SystemException {
       BaseConfigData baseConfigData;
       JSONObject returnJsonObject = new JSONObject();
       JSONObject testResult;
       String msg;
        boolean isOk;
        switch (deviceService.getServiceType()){
            //如果是探针，调用探针的check接口
            case DeviceService.SERVICETYPE_DETECTOR :
                isOk = DetectorUtil.check(deviceService.getIp(),deviceService.getConfigData().getPort());
                msg = String.format("探针端口%s",deviceService.getConfigData().getPort());
                break;
            case DeviceService.SERVICETYPE_DB:
                DBConfigData dbConfigData = (DBConfigData) deviceService.getConfigData();

                if(device.isNetAreaIn()){
                    testResult = dynamicDB.testDBSource(dbConfigData);
                }else{
                    testResult = DetectorUtil.testDBSource(detectorService.getIp(),detectorService.getConfigData().getPort(),dbConfigData);
                }

                int maxConnectNum = testResult.getIntValue("maxConnectNum");
                int connectNum = testResult.getIntValue("connectNum");
                if(testResult.get("alarmType") == null){
                        isOk = true;
                        msg = String.format("数据库链接正常,端口:%s" +
                                "<br/>用户名密码正确" +
                                "<br/>连接数小于70%,最大连接数:%s,当前连接数:%s" +
                                "<br/>无表死锁",dbConfigData.getPort(),maxConnectNum,connectNum);
                }else{
                    isOk =false;
                    StringBuffer sb = new StringBuffer("数据库资源异常,端口:"+dbConfigData.getPort());

                    if(testResult.getIntValue("alarmType") == AlarmEnum.db_createError.getAlarmType()) {
                        sb.append("<br/>端口不通");
                        msg = sb.toString();
                        break;
                    }

                    if(testResult.getIntValue("alarmType") == AlarmEnum.db_wrongpwd.getAlarmType()){
                        sb.append("<br/>用户名密码错误,用户名:"+dbConfigData.getName()+",密码:"+dbConfigData.getPwd());
                        msg = sb.toString();
                        break;
                    }

                    sb.append("<br/>用户名密码正确");

                    if(testResult.getIntValue("alarmType") == AlarmEnum.db_connect_70.getAlarmType()){
                        sb.append( String.format("<br/>连接数大于70%,最大连接数:%s,当前连接数:%s",dbConfigData.getPort(),maxConnectNum,connectNum));
                    }else{
                        sb.append( String.format("<br/>连接数小于70%,最大连接数:%s,当前连接数:%s",dbConfigData.getPort(),maxConnectNum,connectNum));
                    }

                    msg = sb.toString();
                }

                break;
            case DeviceService.SERVICETYPE_SNMP:
                SnmpConfigData snmpConfigData = (SnmpConfigData) deviceService.getConfigData();
                boolean isConnect ;
                //先检查网络
                if(device.isNetAreaIn()){
                    isConnect = Telnet.doTelnet(snmpConfigData.getIp(),snmpConfigData.getPort());
                }else{
                    isConnect = DetectorUtil.telnetCmd(detectorService.getIp(),detectorService.getConfigData().getPort(),
                            snmpConfigData.getIp(),snmpConfigData.getPort());
                }
                if(isConnect){
                    SnmpUtil snmpUtil =new SnmpUtil(snmpConfigData);
                    if(device.isNetAreaIn()){
                        isOk = snmpUtil.testConnect();
                    }else{
                        isOk = DetectorUtil.testSNMP(detectorService.getIp(),detectorService.getConfigData().getPort(),snmpConfigData);
                    }
                    if(isOk){
                        isOk = true;
                        msg = String.format("SNMP服务端口:%s,团体名:%s",snmpConfigData.getPort(),snmpConfigData.getCommunity());
                    }else{
                        isOk = false;
                        msg = String.format("SNMP服务端口:%s,团体名:%s错误",snmpConfigData.getPort(),snmpConfigData.getCommunity());
                    }
                }else{
                    isOk = false;
                    msg = String.format("SNMP服务端口:%s不通,团体名:%s",snmpConfigData.getPort(),snmpConfigData.getCommunity());
                }
                break;
            case DeviceService.SERVICETYPE_FTP:
                FTPConfigData ftpConfigData = (FTPConfigData) deviceService.getConfigData();
                if(device.isNetAreaIn()){
                    testResult = ftpUtil.testFTPSource(ftpConfigData, CommonContants.DEFAULT_TIMEOUT);
                }else{
                    testResult = DetectorUtil.testFTPSource(detectorService.getIp(),detectorService.getConfigData().getPort(), ftpConfigData);
                }

                if(testResult.get("alarmType") == null){
                    isOk = true;
                    msg = String.format("FTP链接正常,端口:%s,用户名密码正确",ftpConfigData.getPort());
                }else{
                    isOk =false;
                    msg = String.format("FTP链接连接失败,端口:%s",ftpConfigData.getPort());
                }
                break;
            case DeviceService.SERVICETYPE_SSH:
                baseConfigData = deviceService.getConfigData();
                if(device.isNetAreaIn()){
                    testResult = SSHExecuter.testSSH(baseConfigData);
                }else{
                    testResult = DetectorUtil.testSSH(detectorService.getIp(),detectorService.getConfigData().getPort(), baseConfigData);
                }
                if(testResult.getBooleanValue("isConnect")){
                    isOk = true;
                    msg = String.format("SSH端口:%s,用户名密码正确.",baseConfigData.getPort());
                }else{
                    isOk=false;
                    int alarmType = testResult.getIntValue("alarmType");
                    if(alarmType == AlarmEnum.ssh_port_notConnect.getAlarmType()){
                        msg = String.format("SSH端口不通,端口:%s",baseConfigData.getPort());
                    }else if(alarmType == AlarmEnum.ssh_wrongPwd.getAlarmType()) {
                        msg = String.format("SSH用户名密码错误,用户名:%s,密码:", baseConfigData.getName(), baseConfigData.getPwd());
                    }else{
                        msg = String.format("SSH连接错误,端口:%s,用户名:%s,密码:",baseConfigData.getPort(),baseConfigData.getName(), baseConfigData.getPwd());
                    }
                }
                break;
            default:
                isOk =false;
                log.error(String.format("暂不支持该服务类型[%s]",deviceService.getServiceType()));
                msg = String.format("暂不支持该服务类型[%s]",deviceService.getServiceType());
        }

       returnJsonObject.put("isOk",isOk);
       returnJsonObject.put("msg",msg);
       return returnJsonObject;

    }
}
