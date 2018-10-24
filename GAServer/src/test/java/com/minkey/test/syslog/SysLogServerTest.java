package com.minkey.test.syslog;


import com.minkey.syslog.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SysLogServerTest {

    private final static Logger logger = LoggerFactory.getLogger(SysLogServerTest.class);


    public static void main(String[] args) {
        new SysLogServerTest().startS();

    }

//    @Test
    public void startS(){
        try {
            SysLogUtil SysLogUtil = new SysLogUtil();
            SysLogUtil.startServer(514);
            logger.error("start");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}