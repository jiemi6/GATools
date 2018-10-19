package com.minkey;

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
}
