package com.minkey.commands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Telnet {

    public static void doTelnet(String ipAddress, int port) {
        Socket server = null;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            server.connect(address, 5000);
        } catch (UnknownHostException e) {
            System.out.println("telnet失败");
        } catch (IOException e) {
            System.out.println("telnet失败");
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