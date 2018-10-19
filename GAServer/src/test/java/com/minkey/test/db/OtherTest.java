package com.minkey.test.db;

import com.minkey.MainRun;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherTest{
    private final static Logger logger = LoggerFactory.getLogger(MainRun.class);


    public static void main(String[] args) {

        logger.error(String.format("sdff %sds%s","'xxx'",2));

        String[] sd = new String[3];
        sd[0] = "dddd";
        sd[1] = "dddd";
        sd[2] = "dddd";

        logger.error(StringUtils.join(sd,","));


    }
}
