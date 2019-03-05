package com.minkey.syslog;

import com.minkey.db.SyslogHandler;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.productivity.java.syslog4j.impl.net.udp.UDPNetSyslogConfig;
import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerConfigIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;

@Slf4j
@Component
public class SysLogUtil {
    /**
     * syslog默认端口
     */
    public static final int SYSLOG_PORT = 514;

    private SyslogServerIF serverIF;

    @Autowired
    SyslogHandler syslogHandler;

    /**
     * 发送syslog
     *
     * @param host
     * @param port
     * @param msg
     * @param level
     */
    public void sendLog(String host, int port, String msg, int level) throws SystemException {
        try {
            UDPNetSyslogConfig config = new UDPNetSyslogConfig();
            //设置syslog服务器端地址
            config.setHost(host);
            //设置syslog接收端口，默认514
            config.setPort(port);
            //向多个多个ip发送日志不执行shutdown会导致同一个实例无法发送到多个地址
            Syslog.shutdown();
            //获取syslog的操作类，使用udp协议。syslog支持"udp", "tcp", "unix_syslog", "unix_socket"协议
            SyslogIF syslog = Syslog.createInstance("udp", config);
            syslog.log(level, URLDecoder.decode(msg, "utf-8"));

            log.debug("syslog Server:" + host + ":" + port);
            /* 发送信息到服务器，2表示日志级别 范围为0~7的数字编码，表示了事件的严重程度。0最高，7最低
            * syslog为每个事件赋予几个不同的优先级：
            0 LOG_EMERG：紧急情况，需要立即通知技术人员。
            1 LOG_ALERT：应该被立即改正的问题，如系统数据库被破坏，ISP连接丢失。
            2 LOG_CRIT：重要情况，如硬盘错误，备用连接丢失。
            3 LOG_ERR：错误，不是非常紧急，在一定时间内修复即可。
            4 LOG_WARNING：警告信息，不是错误，比如系统磁盘使用了85%等。
            5 LOG_NOTICE：不是错误情况，也不需要立即处理。
            6 LOG_INFO：情报信息，正常的系统消息，比如骚扰报告，带宽数据等，不需要处理。
            7 LOG_DEBUG：包含详细的开发情报的信息，通常只在调试一个程序时使用。
            */
        } catch (Exception e) {
           throw new SystemException("send syslog Exception",e);
        }
    }


    /**
     * 启动syslog接收服务
     * @param port
     * @throws SystemException
     */
    public void startAcceptServer(int port) throws SystemException{
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    serverIF = SyslogServer.getThreadedInstance("udp");
                    SyslogServerConfigIF config = serverIF.getConfig();
                    //        config.setHost("192.168.1.114");
                    config.setPort(port);

                    //不打印到控制台
//                    config.addEventHandler(new PrintStreamSyslogServerEventHandler(System.out));
                    config.addEventHandler(new DBSyslogServerEventHandler());

                    serverIF.initialize("udp",config);
                    serverIF.run();
                    log.warn("启动syslog接收服务成功");
                }
            },"Syslog-Receive").start();

        } catch (Exception e) {
            log.warn("启动syslog接收服务异常",e);
            throw new SystemException("启动 syslog 服务器异常"+e.getMessage());
        }
    }


    /**
     * 关闭syslog接收服务器
     */
    public void shutdown(){
        try{
            serverIF.shutdown();
        } catch (Exception e) {
            log.error("shutdown syslog server Exception",e);
        }
    }


    /**
     * 开启syslog代发功能
     * @param ip
     * @param port
     */
    public void startSyslog2other(String ip, String port) {
        //Minkey 只发送收集过来的所有syslog
    }

    /**
     * 关闭syslog代发功能
     */
    public void closeSyslog2other() {



    }
}