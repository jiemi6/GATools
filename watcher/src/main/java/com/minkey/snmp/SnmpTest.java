package com.minkey.snmp;

import java.util.ArrayList;
import java.util.List;


public class SnmpTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SnmpTest test = new SnmpTest();

        test.testGet();
//        test.testWalk();

    }

        String ip = "127.0.0.1";
//        String ip = "192.168.10.162";
        String community = "public";
    public void testGet() {
//        String oidval = "1.3.6.1.2.1.1.6.0";
        String oidval = "1.3.6.1.2.1.25.3.3.1.2.1";
//        String oidval = "1.3.6.1.4.1.2021.4.5.0";
        SnmpData.snmpGet(ip, community, oidval);
    }


    public void testGetList() {

        List<String> oidList = new ArrayList<String>();
        oidList.add("1.3.6.1.2.1.1.5.0");
        oidList.add("1.3.6.1.2.1.1.7.0");
        SnmpData.snmpGetList(ip, community, oidList);
    }


    public void testGetAsyList() {

        List<String> oidList = new ArrayList<String>();
        oidList.add("1.3.6.1.2.1");
        oidList.add("1.3.6.1.2.12");
        SnmpData.snmpAsynGetList(ip, community, oidList);
        System.out.println("i am first!");
    }


    public void testWalk() {

//        String targetOid = "1.3.6.1.2.1.1.5.0";
        String targetOid = ". 1.3.6.1.2.1.25.3.3.1.2";
//        String targetOid = "1.3.6.1.2.1.25.4.2.1.2";
        SnmpData.snmpWalk(ip, community, targetOid);
    }


    public void testAsyWalk() {

        // 异步采集数据
        SnmpData.snmpAsynWalk(ip, community, "1.3.6.1.2.1.25.4.2.1.2");
    }


    public void testSetPDU() throws Exception {

        SnmpData.setPDU(ip, community, "1.3.6.1.2.1.1.6.0", "jianghuiwen");
    }


    public void testVersion() {
        System.out.println(org.snmp4j.version.VersionInfo.getVersion());
    }
}
