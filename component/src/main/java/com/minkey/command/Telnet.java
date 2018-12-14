package com.minkey.command;


import com.alibaba.fastjson.util.IOUtils;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.CommonContants;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@Slf4j
public class Telnet {
    public static boolean doTelnet(String ipAddress, int port) throws SystemException {
        Socket server = null;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            server.connect(address, CommonContants.DEFAULT_TIMEOUT);
            return server.isConnected();
        } catch (UnknownHostException e) {
            //ip解析错误。ip填写错误
            throw new SystemException(AlarmEnum.ip_notConnect,String.format("telnet异常,未知对方host.[%s:%s] Msg=%s",ipAddress,port,e.getMessage()));
        } catch (IOException e) {
            //ip和端口连不上都会报这个错
            throw new SystemException(AlarmEnum.port_notConnect,String.format("telnet-IO异常[%s:%s] Msg=%s",ipAddress,port,e.getMessage()));
        } finally {
            IOUtils.close(server);
        }
    }


}