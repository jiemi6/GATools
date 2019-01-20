package com.minkey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class StartUpApplication {
    public static void main(String[] args) {

        SpringApplication.run(StartUpApplication.class, args);




    }
}