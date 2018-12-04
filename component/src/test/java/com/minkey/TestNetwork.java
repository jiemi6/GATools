package com.minkey;

import com.minkey.command.Telnet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestNetwork {

    @Test
    public void testTelnet() throws InterruptedException {

        while (true){
            log.error(String.valueOf(Telnet.doTelnet("127.0.0.1",8080)));
            Thread.sleep(3000l);
        }

    }
}
