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

    public final static int defalut_pingTimes=4;
    public final static double defalut_intervalTime=0.2;
    public final static int defalut_timeout=1;

    /**
     *
     * @param ipAddress
     * @return
     * @throws SystemException
     */
    public static boolean javaPing(String ipAddress) throws SystemException{
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status;
        try {
            status = InetAddress.getByName(ipAddress).isReachable(1000);
        } catch (Exception e) {
            log.debug("Java isReachable "+ipAddress +" Exception,"+e.getMessage());
            return false;
        }
        return status;
    }

    /**
     * 单纯测试是否联通
     * @param ipAddress
     * @return
     */
    public static boolean pingConnect(String ipAddress) {
        int connectedCount = ping(ipAddress,defalut_pingTimes,defalut_intervalTime,defalut_timeout);
        return connectedCount > 0;
    }

    /**
     * 对外暴露的总接口
     * @param ipAddress
     * @param pingTimes
     * @param intervalTime
     * @param timeout
     * @return
     */
    public static int ping(String ipAddress, int pingTimes, double intervalTime,int timeout) {
        //可判断操作系统
        return pingLinux(ipAddress,pingTimes,intervalTime,timeout);
    }

    /**
     * linux操作系统ping
     * @param ipAddress
     * @param pingTimes ping次数
     * @param intervalTime  间隔时间 单位秒
     * @param timeout 超时时间 单位秒
     * @return
     */
    private static int pingLinux(String ipAddress, int pingTimes, double intervalTime,int timeout) {
        BufferedReader in = null;
        // 将要执行的ping命令,此命令是linux格式的命令
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + ipAddress + " -c " + pingTimes + " -i " + intervalTime + " -W "+timeout;
        try {
            // 执行命令并获取输出
            log.debug("linux下执行ping命令:"+pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                return 0;
            }
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int successNum = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                log.debug("ping line :"+line);
                // 如果出现类似=23ms TTL=62这样的字样,
                successNum += getCheckResult4Linux(line);
            }
            log.debug("successNum ="+successNum +"/pingTimes="+ pingTimes);
            // 出现的次数=测试次数则返回真
            return successNum;
        } catch (Exception e) {
            throw new SystemException(e.getMessage(),e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
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
     * windows操作系统ping
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    private static boolean pingWindow(String ipAddress, int pingTimes, int timeOut) {
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
