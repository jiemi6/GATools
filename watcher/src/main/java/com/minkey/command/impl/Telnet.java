package com.minkey.command.impl;


import com.minkey.exception.SystemException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Telnet {

    public static boolean doTelnet(String ipAddress, int port) throws SystemException {
        Socket server = null;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            server.connect(address, 5000);
            return server.isConnected();
        } catch (UnknownHostException e) {
            throw new SystemException("telnet异常，未知对方host",e);
        } catch (IOException e) {
            throw new SystemException("telnet失败",e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                }
            }
        }
    }


}