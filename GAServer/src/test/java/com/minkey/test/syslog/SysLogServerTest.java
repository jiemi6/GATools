package com.minkey.test.syslog;


import com.minkey.syslog.SysLogUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SysLogServerTest {

    public static void main(String[] args) {
        new SysLogServerTest().startS();

    }

//    @Test
    public void startS(){
        try {
            SysLogUtil SysLogUtil = new SysLogUtil();
            SysLogUtil.startAcceptServer(514);
            log.error("start");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}