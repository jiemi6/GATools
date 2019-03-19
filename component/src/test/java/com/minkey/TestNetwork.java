package com.minkey;

import com.minkey.command.Telnet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestNetwork {

    @Test
    public void testTelnet() throws InterruptedException {

        while (true){
            log.error(String.valueOf(Telnet.doTelnet("127.0.0.1",7)));
            Thread.sleep(3000l);
        }

    }


    @Test
    public void testPing(){

        String str= "64 bytes from 127.0.0.1: icmp_seq=1 ttl=64 time=0.047 ms";

        Pattern pattern = Pattern.compile("(time=\\d+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
           log.warn("find");
        }
        log.error("no");
    }
}
