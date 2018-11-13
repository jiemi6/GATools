package com.minkey.snmp;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SnmpTest {
    /**
     * @param args
     */
    public static void main(String[] args) {

        SnmpTest snmpTest = new SnmpTest();

        snmpTest.testGetList();
//        snmpTest.testWalk();

    }

        String ip = "127.0.0.1";
//        String ip = "topwalkhndq.tpddns.cn";
        String community = "public";


    public void testWalk() {
        //cpu
//        String oidval = "1.3.6.1.2.1.25.3.3.1.2";
        //mem
//        String oidval = "1.3.6.1.2.1.25.2.3.1";

        String oidval = "1.3.6.1.2.1.25.2.1.4";
//        CommunityTarget target = SnmpUtil.createDefault(ip);
//        JSONObject jo = SnmpUtil.snmpGet(target, oidval);
//        System.out.println(jo);

        SnmpData.snmpWalk(ip,community,oidval);

    }


    public void testGetList() {
//        SnmpUtil snmpUtil = new SnmpUtil("119.130.206.18");
        SnmpUtil snmpUtil = new SnmpUtil("127.0.0.1");
//        JSONObject jo = snmpUtil.snmpWalk("1.3.6.1.2.1.2.2.1.16");
        JSONObject jo = snmpUtil.snmpWalk("1.3.6.1.2.1.2.2.1.10");

        log.error(jo.toJSONString());

    }



    public void testVersion() {
        System.out.println(org.snmp4j.version.VersionInfo.getVersion());
    }
}
