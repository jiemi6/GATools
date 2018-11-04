package com.minkey.test;

import com.minkey.dto.DBConfigData;
import com.minkey.util.DetectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DetectorTest {

    @Test
    public void test() {
//        log.info(""+DetectorUtil.check("127.0.0.1",1234));
//        log.info(""+DetectorUtil.ping("127.0.0.1",1234,"127.0.0.1"));
//        log.info(""+DetectorUtil.executeSh("127.0.0.1",1234,"ping 127.0.0.1"));


    }

    @Test
    public void snmpWalk(){
        log.info(""+DetectorUtil.snmpWalk("127.0.0.1",1234,"127.0.0.1","1.3.6.1.2.1.25.3.3.1.2"));

    }

    @Test
    public void testDb(){
        DBConfigData dbConfigData = new DBConfigData();
        dbConfigData.setIp("127.0.0.1");
        dbConfigData.setPort(3306);
        dbConfigData.setName("root");
        dbConfigData.setPwd("roo2");
        dbConfigData.setDbName("gatools");
        log.info(""+DetectorUtil.testDB("127.0.0.1",1234,dbConfigData));
    }
}
