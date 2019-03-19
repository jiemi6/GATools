package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.contants.CommonContants;
import com.minkey.dto.*;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 探针工具类
 * 访问探针http接口
 */
@Slf4j
public class DetectorUtil {

    public static boolean check(String detectorIp,int detectorPort){
        String url = String.format("http://%s:%s/check",detectorIp,detectorPort);
        String returnStr = HttpClient.postRequest(url,null);
        if(StringUtils.isEmpty(returnStr)){
            return false;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return false;
        }
        return jsonMessage.isSuccess();
    }

    public static JSONObject ping(String detectorIp, int detectorPort, String ip) throws SystemException{
        String url = String.format("http://%s:%s/pingConnect",detectorIp,detectorPort);
        Integer pingTimes = 4;
        Double intervalTime = 0.2;
        Integer timeout = 2;
        Map<String,String> param = new HashMap<>(1);
        param.put("ip",ip);
        param.put("pingTimes",pingTimes.toString());
        param.put("intervalTime",intervalTime.toString());
        param.put("timeout",timeout.toString());
        String returnStr = HttpClient.postRequest(url,param);

        return getReturnJson(returnStr);
    }

    public static boolean pingConnect(String detectorIp, int detectorPort, String ip){
        String url = String.format("http://%s:%s/pingConnect",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(1);
        param.put("ip",ip);
        String returnStr = HttpClient.postRequest(url,param);

        try {
            return getReturnJson(returnStr).getBoolean("isConnect");
        } catch (SystemException e) {
            log.debug("探针执行ping报错,"+e.getMessage());
            return false;
        }
    }


    public static ResultInfo executeSh(String detectorIp,int detectorPort, String cmdStr) throws SystemException{
        String url = String.format("http://%s:%s/executeSh",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(1);
        param.put("cmdStr",cmdStr);
        String returnStr = HttpClient.postRequest(url,param);

        return JSONObject.toJavaObject(getReturnJson(returnStr),ResultInfo.class);
    }

    public static boolean telnetCmd(String detectorIp,int detectorPort, String ip,int port) {
        String url = String.format("http://%s:%s/telnetCmd",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(2);
        param.put("ip",ip);
        param.put("port",""+port);
        String returnStr = HttpClient.postRequest(url,param);

        try {
            return getReturnJson(returnStr).getBoolean("isConnect");
        } catch (SystemException e) {
            log.debug("探针执行telnetCmd报错,"+e.getMessage());
            return false;
        }

    }

    public static JSONObject snmpGet(String detectorIp,int detectorPort,SnmpConfigData snmpConfigData,String oid) throws SystemException{
         String ip = snmpConfigData.getIp();
         Integer port = snmpConfigData.getPort();
         Integer version = snmpConfigData.getVersion();
         String community =snmpConfigData.getCommunity();

        String url = String.format("http://%s:%s/snmpGet",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",ip);
        param.put("port",port.toString());
        param.put("version",version.toString());
        param.put("community",community);
        param.put("retry","1");
        param.put("timeout", String.valueOf(CommonContants.DEFAULT_TIMEOUT));
        param.put("oid",oid);
        String returnStr = HttpClient.postRequest(url,param);

        return getReturnJson(returnStr);
    }

    public static JSONObject snmpWalk(String detectorIp,int detectorPort,
                                      String ip,
                                      String oid) throws SystemException{
        return snmpWalk(detectorIp,detectorPort,ip,SnmpUtil.DEFAULT_PORT, SnmpUtil.DEFAULT_VERSION,SnmpUtil.DEFAULT_COMMUNITY,SnmpUtil.DEFAULT_RETRY,SnmpUtil.DEFAULT_TIMEOUT,oid);
    }

    public static JSONObject snmpWalk(String detectorIp,int detectorPort,
                                      String ip,
                                         Integer port,
                                         Integer version,
                                         String community,
                                         Integer retry,
                                         Long timeout,
                                         String oid) throws SystemException{
        String url = String.format("http://%s:%s/snmp/walk",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",ip);
        param.put("port",port.toString());
        param.put("version",version.toString());
        param.put("community",community);
        param.put("retry",retry.toString());
        param.put("timeout",timeout.toString());
        param.put("oid",oid);
        String returnStr = HttpClient.postRequest(url,param);

        return getReturnJson(returnStr);
    }

    public static boolean testSNMP(String detectorIp, int detectorPort, SnmpConfigData snmpConfigData) {
        String url = String.format("http://%s:%s/test/testSNMP",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",snmpConfigData.getIp());
        param.put("port",""+snmpConfigData.getPort());
        param.put("community",snmpConfigData.getCommunity());
        param.put("version",String.valueOf(snmpConfigData.getVersion()));
        String returnStr = HttpClient.postRequest(url,param);

        try {
            return getReturnJson(returnStr).getBoolean("isConnect");
        } catch (SystemException e) {
            log.debug("探针执行testSNMP报错,"+e.getMessage());
            return false;
        }
    }

    public static boolean testDBConnect(String detectorIp, int detectorPort, DBConfigData dbConfigData) {
        String url = String.format("http://%s:%s/test/testDBConnect",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",dbConfigData.getIp());
        param.put("port",""+dbConfigData.getPort());
        param.put("dbName",dbConfigData.getDbName());
        param.put("name",dbConfigData.getName());
        param.put("pwd",dbConfigData.getPwd());
        param.put("databaseDriverId",dbConfigData.getDatabaseDriver().getId());
        String returnStr = HttpClient.postRequest(url,param);

        try {
            return getReturnJson(returnStr).getBoolean("isConnect");
        } catch (SystemException e) {
            log.debug("探针执行testDBConnect报错,"+e.getMessage());
            return false;
        }
    }

    public static boolean testFTPConnect(String detectorIp, int detectorPort, FTPConfigData ftpConfigData) {
        String url = String.format("http://%s:%s/test/testFTPConnect",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",ftpConfigData.getIp());
        param.put("port",""+ftpConfigData.getPort());
        param.put("rootPath",ftpConfigData.getRootPath());
        param.put("name",ftpConfigData.getName());
        param.put("pwd",ftpConfigData.getPwd());
        String returnStr = HttpClient.postRequest(url,param);

        try {
            return getReturnJson(returnStr).getBoolean("isConnect");
        } catch (SystemException e) {
            log.debug("探针执行testFTPConnect报错,"+e.getMessage());
            return false;
        }
    }

    public static JSONObject testDBSource(String detectorIp, int detectorPort, DBConfigData dbConfigData) throws SystemException{
        String url = String.format("http://%s:%s/test/testDBSource",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",dbConfigData.getIp());
        param.put("port",""+dbConfigData.getPort());
        param.put("dbName",dbConfigData.getDbName());
        param.put("name",dbConfigData.getName());
        param.put("pwd",dbConfigData.getPwd());
        param.put("databaseDriverId",dbConfigData.getDatabaseDriver().getId());
        String returnStr = HttpClient.postRequest(url,param);

        return getReturnJson(returnStr);
    }


    public static JSONObject testFTPSource(String detectorIp, int detectorPort, FTPConfigData ftpConfigData) throws SystemException{
        String url = String.format("http://%s:%s/test/testFTPSource",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",ftpConfigData.getIp());
        param.put("port",""+ftpConfigData.getPort());
        param.put("rootPath",ftpConfigData.getRootPath());
        param.put("name",ftpConfigData.getName());
        param.put("pwd",ftpConfigData.getPwd());
        String returnStr = HttpClient.postRequest(url,param);

        return getReturnJson(returnStr);
    }


    public static boolean testSSH(String detectorIp, int detectorPort, BaseConfigData baseConfigData) {
        String url = String.format("http://%s:%s/test/testSSH",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",baseConfigData.getIp());
        param.put("port",""+baseConfigData.getPort());
        param.put("name",baseConfigData.getName());
        param.put("pwd",baseConfigData.getPwd());
        String returnStr = HttpClient.postRequest(url,param);
        try {
            return getReturnJson(returnStr).getBoolean("isConnect");
        } catch (SystemException e) {
            log.debug("探针执行testSSH报错,"+e.getMessage());
            return false;
        }
    }

    private static JSONObject getReturnJson(String returnStr) throws SystemException{
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            throw new SystemException("探针返回的数据为空。");
        }

        if(jsonMessage.isSuccess()){
            return jsonMessage.getData();
        }else{
            throw new SystemException(jsonMessage.getCode(),jsonMessage.getMsg());
        }
    }
}
