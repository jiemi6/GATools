package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 探针工具类
 * 访问探针http接口
 */
public class DetectorUtil {

    public static boolean check(String detectorIp,int detectorPort){
        String url = String.format("http://%s:%s",detectorIp,detectorPort);
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

    public static boolean ping(String detectorIp,int detectorPort,String ip){
        String url = String.format("http://%s:%s/ping",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(1);
        param.put("ip",ip);
        String returnStr = HttpClient.postRequest(url,param);
        if(StringUtils.isEmpty(returnStr)){
            return false;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return false;
        }
        if(jsonMessage.isSuccess()){
            return jsonMessage.getData().getBoolean("isConnect");
        }
        return false;
    }


    public static ResultInfo executeSh(String detectorIp,int detectorPort, String cmdStr){
        String url = String.format("http://%s:%s/executeSh",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(1);
        param.put("cmdStr",cmdStr);
        String returnStr = HttpClient.postRequest(url,param);
        if(StringUtils.isEmpty(returnStr)){
            return null;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return null;
        }

        return JSONObject.toJavaObject(jsonMessage.getData(),ResultInfo.class);
    }

    public static boolean telnetCmd(String detectorIp,int detectorPort, String ip,int port) {
        String url = String.format("http://%s:%s/telnetCmd",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(2);
        param.put("ip",ip);
        param.put("port",""+port);
        String returnStr = HttpClient.postRequest(url,param);
        if(StringUtils.isEmpty(returnStr)){
            return false;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return false;
        }
        if(jsonMessage.isSuccess()){
            return jsonMessage.getData().getBoolean("isConnect");
        }
        return false;

    }

    public static JSONObject snmpGet(String detectorIp,int detectorPort,
                                     String ip,
                                         Integer port,
                                         Integer version,
                                         String community,
                                         Integer retry,
                                         Long timeout,
                                         String oid) {

        String url = String.format("http://%s:%s/snmpGet",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",ip);
        param.put("port",port.toString());
        param.put("version",version.toString());
        param.put("community",community);
        param.put("retry",retry.toString());
        param.put("timeout",timeout.toString());
        param.put("oid",oid);
        String returnStr = HttpClient.postRequest(url,param);
        if(StringUtils.isEmpty(returnStr)){
            return null;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return null;
        }

        return jsonMessage.getData();
    }

    public static JSONObject snmpWalk(String detectorIp,int detectorPort,
                                      String ip,
                                      Integer port) {


        return null;
    }

    public static JSONObject snmpWalk(String detectorIp,int detectorPort,
                                      String ip,
                                         Integer port,
                                         Integer version,
                                         String community,
                                         Integer retry,
                                         Long timeout,
                                         String oid) {
        String url = String.format("http://%s:%s/snmpWalk",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",ip);
        param.put("port",port.toString());
        param.put("version",version.toString());
        param.put("community",community);
        param.put("retry",retry.toString());
        param.put("timeout",timeout.toString());
        param.put("oid",oid);
        String returnStr = HttpClient.postRequest(url,param);
        if(StringUtils.isEmpty(returnStr)){
            return null;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return null;
        }

        return jsonMessage.getData();
    }

    public static boolean testDB(String detectorIp, int detectorPort, DBConfigData dbConfigData) {
        String url = String.format("http://%s:%s/testDB",detectorIp,detectorPort);
        Map<String,String> param = new HashMap<>(10);
        param.put("ip",dbConfigData.getIp());
        param.put("port",""+dbConfigData.getPort());
        param.put("dbName",dbConfigData.getDbName());
        param.put("name",dbConfigData.getName());
        param.put("pwd",dbConfigData.getPwd());
        param.put("databaseDriverId",dbConfigData.getDatabaseDriver().getId());
        String returnStr = HttpClient.postRequest(url,param);
        if(StringUtils.isEmpty(returnStr)){
            return false;
        }
        JSONMessage jsonMessage = JSONMessage.string2Obj(returnStr);
        if(jsonMessage == null){
            return false;
        }

        if(jsonMessage.isSuccess()){
            return jsonMessage.getData().getBoolean("isConnect");
        }
        return false;
    }
}
