package com.minkey.entity;

public class ConnectInfo {

    private String host = "127.0.0.1";

    private int port = 22;

    private String user = "root";

    private String pwd = "root";

    private int timeout = 3000;

    public ConnectInfo(String host, String user, String pwd){
        this.host = host;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
        this.timeout = timeout;
    }

    public ConnectInfo(String host, int port, String user, String pwd, int timeout){
        this.host = host;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getPwd() {
        return pwd;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getHost() {
        return host;
    }


}
