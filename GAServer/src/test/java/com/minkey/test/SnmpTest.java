package com.minkey.test;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SnmpTest {
    public static void main(String[] args) {
        test();

    }

    public static void test(){

        SnmpUtil snmpUtil = new SnmpUtil("127.0.0.1");
        JSONObject jsonObject = snmpUtil.snmpWalk("1.3.6.1.2.1.25.2.3.1");

        log.info(jsonObject.toJSONString());
    }
}
