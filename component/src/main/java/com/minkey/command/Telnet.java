package com.minkey.command;


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
        boolean isConnected = false;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            server.connect(address, 3000);
            return server.isConnected();
        } catch (UnknownHostException e) {
            log.error("telnet异常，未知对方host",e);
        } catch (IOException e) {
            log.error("telnet失败",e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                }
            }
            return isConnected;
        }
    }


}