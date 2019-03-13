package com.minkey.command;

import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Ping {

    /**
     * ping命令默认超时时间
     */
    public static final int DEFALUT_TIMEOUT = 1000;

    private static boolean javaPing(String ipAddress,int timeout) throws SystemException{
        if(timeout <= 0){
            timeout = DEFALUT_TIMEOUT;
        }
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status;
        try {
            status = InetAddress.getByName(ipAddress).isReachable(timeout);
        } catch (Exception e) {
            throw new SystemException(e.getMessage(),e);
        }
        return status;
    }
    /**
     * java测试ping，只能代表是否联通
     * @param ipAddress
     * @return
     * @throws Exception
     */
    public static boolean javaPing(String ipAddress) throws SystemException{
        try {
            return javaPing(ipAddress,DEFALUT_TIMEOUT);
        } catch (SystemException e) {
            log.debug("Java isReachable "+ipAddress +" Exception,"+e.getMessage());
            try {
                log.debug("尝试ping命令");
                //尝试ping命令
                return pingLinux(ipAddress,1,0.1);
            } catch (SystemException e2) {
                log.debug("ping命令执行异常"+e.getMessage());
                return false;
            }
        }
    }


    /**
     * 若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
     *@line "64 bytes from 127.0.0.1: icmp_seq=1 ttl=64 time=0.047 ms"
     */
    private static int getCheckResult4Linux(String line) {
        //计算类似出现=23ms TTL=62字样的次数
        Pattern pattern = Pattern.compile("(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    /**
     * linux操作系统ping
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    public static boolean pingLinux(String ipAddress, int pingTimes, double timeOut) {
        BufferedReader in = null;
        // 将要执行的ping命令,此命令是windows格式的命令
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + ipAddress + " -c " + pingTimes + " -i " + timeOut;
        try {
            // 执行命令并获取输出
            log.debug("执行ping命令:"+pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                log.debug("ping line :"+line);
                connectedCount += getCheckResult4Linux(line);
            }
            log.debug("connectedCount="+connectedCount +"|pingTimes="+ pingTimes);
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception e) {
            throw new SystemException(e.getMessage(),e);
            // 出现异常则返回假
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * windows操作系统ping
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    public static boolean pingWindow(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        // 将要执行的ping命令,此命令是windows格式的命令
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        try {
            // 执行命令并获取输出
            log.debug("执行ping命令:"+pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult4Windows(line);
            }
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception ex) {
            log.error("执行ping异常"+ ex.getMessage());
            // 出现异常则返回假
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) { }
        }
    }


    /**
     *
     若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
     */
    private static int getCheckResult4Windows(String line) {
        //计算类似出现=23ms TTL=62字样的次数
        Pattern pattern = Pattern.compile("(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

}
