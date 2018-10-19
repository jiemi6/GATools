package com.minkey.test.db;


import com.minkey.syslog.SysLogUtil;

import java.util.Date;

public class SysLogTest {

    public static void main(String[] args) {
        startS();

//        send();

    }

     public static void send() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("time：" + new Date().toString().substring(4, 20) + ";");
            buffer.append("userID:" + "uuu1" + ";");
            buffer.append("logType:" + "100" + ";");
            buffer.append("actiom:" + "delete" + ";");
            buffer.append("des:" + "312312323");

            SysLogUtil.sendLog("127.0.0.1", 514, buffer.toString(), 1);

        }

    private static void startS(){
        try {
            SysLogUtil.startServer(514);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}