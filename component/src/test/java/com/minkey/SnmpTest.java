package com.minkey;

import com.minkey.command.SnmpUtil;
import com.minkey.snmp.SnmpData;

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
        String oidval = "1.3.6.1.2.1.1.1";
//        String oidval = "1.3.6.1.2.1.25.3.3.1";
//        String oidval = "1.3.6.1.4.1.2021.11.9.0";
//        CommunityTarget target = SnmpUtil.createDefault(ip);
//        JSONObject jo = SnmpUtil.snmpGet(target, oidval);
//        System.out.println(jo);

        SnmpData.snmpWalk(ip,community,oidval);

    }


    public void testGetList() {

        List<String> oidList = new ArrayList<String>();
        oidList.add("1.3.6.1.2.1.1.5.0");
        oidList.add("1.3.6.1.2.1.1.7.0");
        new SnmpUtil(ip).snmpGetList((String[]) oidList.toArray());
    }



    public void testVersion() {
        System.out.println(org.snmp4j.version.VersionInfo.getVersion());
    }
}
