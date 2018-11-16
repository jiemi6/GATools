package com.minkey.util;


import com.minkey.dto.BaseConfigData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class FTPUtil {
    public static final int default_timeout = 1000;

    public boolean testFTPConnect(BaseConfigData baseConfigData){
        return testFTPConnect(baseConfigData.getIp(),baseConfigData.getPort(),
                baseConfigData.getName(),baseConfigData.getPwd(),default_timeout);
    }

    public boolean testFTPConnect(BaseConfigData baseConfigData,int timeout){
        return testFTPConnect(baseConfigData.getIp(),baseConfigData.getPort(),
                baseConfigData.getName(),baseConfigData.getPwd(),timeout);
    }

    public boolean testFTPConnect(String ip,int port,String user,String pwd,int timeout){
        FTPClient ftp = new FTPClient();
        try {

            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");
            ftp.setConnectTimeout(timeout);

            ftp.connect(ip, port);
            ftp.login(user, pwd);

            return ftp.isConnected();
        } catch (SocketTimeoutException e) {
            log.error(String.format("ftp连接超时%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        } catch (SocketException e) {
            log.error(String.format("ftp连接异常%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        } catch (IOException e) {
            log.error(String.format("ftp-IO异常%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        }finally {
            if(ftp.isConnected()){
                try {
                    ftp.logout();
                    ftp.abort();
                } catch (IOException e) {

                }
            }
        }
        return false;
    }


}
