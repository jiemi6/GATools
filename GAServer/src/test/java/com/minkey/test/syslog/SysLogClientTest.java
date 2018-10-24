package com.minkey.test.syslog;


import com.minkey.syslog.SysLogUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;



public class SysLogClientTest {
    private final static Logger logger = LoggerFactory.getLogger(SysLogClientTest.class);


    @Test
    public void send() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("time：" + new Date().toString().substring(4, 20) + ";");
        buffer.append("userID:" + "uuu1" + ";");
        buffer.append("logType:" + "100" + ";");
        buffer.append("actiom:" + "delete" + ";");
        buffer.append("des:" + "312312323");
        SysLogUtil SysLogUtil = new SysLogUtil();
        while(true){

            SysLogUtil.sendLog("127.0.0.1", 514, buffer.toString(), 1);
        }

    }


}