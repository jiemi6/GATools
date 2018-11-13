package com.minkey;


import com.minkey.dto.BaseConfigData;
import com.minkey.util.FTPUtil;
import org.junit.Test;

public class FTPTest {

    @Test
    public void testLocalSH() {
        BaseConfigData baseConfigData = new BaseConfigData();
        baseConfigData.setIp("127.0.0.0");
        baseConfigData.setPort(88);
        baseConfigData.setName("root");
        baseConfigData.setPwd("root");
        new FTPUtil().testFTPConnect(baseConfigData,1000);
    }

}
