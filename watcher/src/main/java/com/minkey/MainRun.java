package com.minkey;


import com.jcraft.jsch.JSchException;
import com.minkey.command.LocalICommandHandler;
import com.minkey.commands.Ping;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import com.minkey.executer.SSHExecuter;

import java.io.IOException;

public class MainRun {


    public static void main(String[] args) throws IOException, JSchException {
        //        String command = "top -bcn 1 ";
//        String command = "ping -c 4 192.168.10.1 ";
//        String command = "ls -l ";

        String command = "ping 192.168.1.10";
//        Telnet.doTelnet("192.168.1.11",122);
//        testLocalSH(command);
        testLocalSH2(command);
    }

    public static void testLocalSH2(String command) {
        try {
            LocalICommandHandler localICommandHandler = new LocalICommandHandler(new Ping());
            ResultInfo resultInfo = localICommandHandler.exec();
            System.out.println(resultInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testLocalSH(String command) {
        try {
            ResultInfo resInfo = LocalExecuter.exec("ping -c 4 192.168.10.1");
            System.out.println(resInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSSH2(String command) {
        String host = "192.168.101.162";
        int port = 22;
        String user = "root";
        String password = "sinosun";

        try {
            //使用目标服务器机上的用户名和密码登陆
            SSHExecuter helper = new SSHExecuter(host, port, user, password);
            try {
                ResultInfo resInfo = helper.sendCmd(command);
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
