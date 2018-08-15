package com.minkey.command.impl;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.ICommand;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ping implements ICommand {

    @Override
    public String commandStr() throws SystemException {
        //linux 下必须带上-c 要不然不反回
        return "ping -c 4 192.168.10.1";

    }

    @Override
    public JSONObject result2JSON(ResultInfo resultInfo) throws SystemException {
        JSONObject jo = new JSONObject();
        jo.put("ok","c成功了");
        return jo;
    }

    /**
     * java测试ping，只能代表是否联通
     * @param ipAddress
     * @return
     * @throws Exception
     */
    public static boolean javaPing(String ipAddress) throws Exception {
        //超时应该在3钞以上
        int timeOut = 3000;
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        return status;
    }

    /**
     *
     若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
     */
    private int getCheckResult(String line) {
        // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    /**
     * windows操作系统ping
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    public boolean pingWindow(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        // 将要执行的ping命令,此命令是windows格式的命令
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        try {   // 执行命令并获取输出
            System.out.println(pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult(line);
            }
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception ex) {
            ex.printStackTrace();
            // 出现异常则返回假
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
