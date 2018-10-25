package com.minkey;

import com.minkey.controller.LicenseController;
import com.minkey.handler.DeviceStatusHandler;
import com.minkey.syslog.SysLogUtil;
import com.minkey.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class MainRun {
    private final static Logger logger = LoggerFactory.getLogger(MainRun.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MainRun.class, args);

        SpringUtils.setCtx(ctx);



    }

    public void initSystem(){
        //初始化syslog接受服务器
        SpringUtils.getBean(SysLogUtil.class).startServer(SysLogUtil.SYSLOG_PORT);

        //设备状态扫描器
        SpringUtils.getBean(DeviceStatusHandler.class).init();

        try {
            SpringUtils.getBean(LicenseController.class).key();
        }catch (Exception e){
            logger.error("获取注册license异常",e);
        }
    }

}
