package com.minkey.command.impl;


import com.minkey.exception.SysException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Telnet {

    public static void doTelnet(String ipAddress, int port) throws SysException {
        Socket server = null;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            server.connect(address, 5000);
        } catch (UnknownHostException e) {
            throw new SysException("telnet异常，未知对方host",e);
        } catch (IOException e) {
            throw new SysException("telnet失败",e);
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