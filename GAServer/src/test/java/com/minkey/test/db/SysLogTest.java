package com.minkey.test.db;

import com.minkey.util.SysLogUtil;

import java.util.Date;

public class SysLogTest {

    public static void main(String[] args) {


        StringBuffer buffer = new StringBuffer();
        buffer.append("time：" + new Date().toString().substring(4, 20) + ";");
        buffer.append("userID:" + "uuu1" + ";");
        buffer.append("logType:" + "100" + ";");
        buffer.append("actiom:" + "delete" + ";");
        buffer.append("des:" + "312312323");

        SysLogUtil.sendLog("172.18.10.123", 514, buffer.toString(), 1);

    }

}