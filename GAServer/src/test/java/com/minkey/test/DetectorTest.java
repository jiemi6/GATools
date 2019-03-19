package com.minkey.test;

import com.minkey.dto.DBConfigData;
import com.minkey.util.DetectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Pattern;

@Slf4j
public class DetectorTest {

    @Test
    public void test() {
//        log.info(""+DetectorUtil.check("127.0.0.1",1234));
//        log.info(""+DetectorUtil.pingConnect("127.0.0.1",1234,"127.0.0.1"));
//        log.info(""+DetectorUtil.executeSh("127.0.0.1",1234,"pingConnect 127.0.0.1"));
        boolean b = Pattern.compile(".*(\\.css| \\.json | \\.png|\\.gif|\\.js|\\.eot|\\.svg|\\.ttf|\\.woff|\\.mp4)").matcher("/sshd/get").matches();
       log.error(b+"");

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

        log.info(""+DetectorUtil.testDBConnect("127.0.0.1",8080,dbConfigData));
    }
}
