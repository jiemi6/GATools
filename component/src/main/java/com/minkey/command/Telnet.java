package com.minkey.command;


import com.minkey.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Telnet {
    private static final Logger logger = LoggerFactory.getLogger(SnmpUtil.class);


    public static boolean doTelnet(String ipAddress, int port) throws SystemException {
        Socket server = null;
        boolean isConnected = false;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            server.connect(address, 3000);
            return server.isConnected();
        } catch (UnknownHostException e) {
            logger.error("telnet异常，未知对方host",e);
        } catch (IOException e) {
            logger.error("telnet失败",e);
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