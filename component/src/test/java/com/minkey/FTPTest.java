package com.minkey;


import com.minkey.dto.FTPConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FTPTest {

    @Test
    public void testLocalSH() {
        FTPConfigData ftpConfigData = new FTPConfigData();
        ftpConfigData.setIp("10.0.0.158");
        ftpConfigData.setPort(21);
        ftpConfigData.setName("ftpuser");
        ftpConfigData.setPwd("ftpuser");
        ftpConfigData.setRootPath("/home/ftpuser");
        try {
            new FTPUtil().testFTPSource(ftpConfigData, 1000);
        }catch (SystemException e){
            log.error(e.getMessage(),e);
        }
    }

}
