package com.minkey.util;


import com.minkey.dto.BaseConfigData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

@Slf4j
public class FTPUtil {

    public static boolean testFTPConnect(BaseConfigData baseConfigData){
        FTPClient ftp = new FTPClient();
        try {

            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");
            ftp.setConnectTimeout(1000);

            ftp.connect(baseConfigData.getIp(), baseConfigData.getPort());
            ftp.login(baseConfigData.getName(), baseConfigData.getPwd());

            return ftp.isConnected();
        } catch (SocketTimeoutException e) {
            log.error("ftp连接超时",e);
        } catch (SocketException e) {
            log.error("ftp连接异常",e);
        } catch (IOException e) {
            log.error("ftp-io异常",e);
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
