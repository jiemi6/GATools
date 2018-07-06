package com.minkey;


import com.jcraft.jsch.JSchException;
import com.minkey.ping.Ping;
import com.minkey.executer.LocalExecuter;
import com.minkey.executer.SSHExecuter;
import com.minkey.entity.SSHResInfo;

import java.io.IOException;

public class MainTest {


    public static void main(String[] args) throws IOException, JSchException {
//        Telnet.doTelnet("192.168.1.11",122);
        testLocalSH();
//        testLocalSH2();
    }

    public static void testLocalSH2() {
        try {
            Ping.ping02("192.168.1.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testLocalSH() {
        try {
            SSHResInfo resInfo = LocalExecuter.exec("192.168.1.1");
            System.out.println(resInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSSH2() {
        String host = "192.168.101.162";
        int port = 22;
        String user = "root";
        String password = "sinosun";
        //        String command = "top -bcn 1 ";
//        String command = "ping -c 4 192.168.10.1 ";
        String command = "ls -l ";
        try {
            //使用目标服务器机上的用户名和密码登陆
            SSHExecuter helper = new SSHExecuter(host, port, user, password);
            try {
                SSHResInfo resInfo = helper.sendCmd(command);
                System.out.println(resInfo.toString());
                //System.out.println(helper.deleteRemoteFIleOrDir(command));
                //System.out.println(helper.detectedFileExist(command));
                helper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }


}
