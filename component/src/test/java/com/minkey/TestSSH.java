package com.minkey;


import com.jcraft.jsch.JSchException;
import com.minkey.contants.CommonContants;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import com.minkey.executer.SSHExecuter;
import org.junit.Test;

public class TestSSH {

    @Test
    public void testLocalSH() {
        try {
            ResultInfo resInfo = LocalExecuter.exec("ping 192.168.1.1 -t");
            System.out.println(resInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSSH2() {
        String host = "192.168.101.162";
        int port = 22;
        String user = "root";
        String password = "sinosun";
        //        String command = "top -bcn 1 ";
//        String command = "ping -c 4 192.168.10.1 ";
        String command = "ls -l ";
        try {
            //使用目标服务器机上的用户名和密码登陆
            SSHExecuter helper = new SSHExecuter(host, port, user, password, CommonContants.DEFAULT_TIMEOUT);
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
