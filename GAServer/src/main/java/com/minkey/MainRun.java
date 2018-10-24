package com.minkey;

import com.minkey.handler.DeviceStatusHandler;
import com.minkey.syslog.SysLogUtil;
import com.minkey.util.SpringUtils;
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

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MainRun.class, args);

        SpringUtils.setCtx(ctx);



    }

    public void initSystem(){
        //初始化syslog接受服务器
        SpringUtils.getBean(SysLogUtil.class).startServer(SysLogUtil.SYSLOG_PORT);

        //设备状态扫描器
        SpringUtils.getBean(DeviceStatusHandler.class).init();



    }

}
