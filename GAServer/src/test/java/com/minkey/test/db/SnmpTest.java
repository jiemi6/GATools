package com.minkey.test.db;

import com.alibaba.fastjson.JSONObject;
import com.minkey.MainRun;
import com.minkey.command.SnmpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnmpTest {
    private final static Logger logger = LoggerFactory.getLogger(MainRun.class);

    public static void main(String[] args) {
        test();

    }

    public static void test(){

        SnmpUtil snmpUtil = new SnmpUtil("127.0.0.1");
        JSONObject jsonObject = snmpUtil.snmpWalk("1.3.6.1.2.1.25.2.3.1");

        logger.info(jsonObject.toJSONString());
    }
}
