package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import com.minkey.dto.DBConfigData;
import com.minkey.entity.ResultInfo;

/**
 * 探针工具类
 * 访问探针http接口
 */
public class DetectorUtil {

    public static boolean check(String detectorIp,int detectorPort){


        return true;
    }

    public static boolean ping(String detectorIp,int detectorPort,String ip){


        return true;
    }


    public static ResultInfo executeSh(String detectorIp,int detectorPort, String cmdStr){


        return null;
    }

    public static boolean telnetCmd(String detectorIp,int detectorPort, String ip,int port) {


        return true;
    }

    public static JSONObject snmpGet(String detectorIp,int detectorPort,
                                     String ip,
                                         Integer port,
                                         Integer version,
                                         String community,
                                         Integer retry,
                                         Long timeout,
                                         String oid) {


        return null;
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


        return null;
    }

    public static boolean testDB(String detectorIp, int detectorPort, DBConfigData dbConfigData) {

        return false;
    }
}
