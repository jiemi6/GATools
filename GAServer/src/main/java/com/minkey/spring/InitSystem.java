package com.minkey.spring;

import com.minkey.cache.DeviceCache;
import com.minkey.controller.LicenseController;
import com.minkey.syslog.SysLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class InitSystem {

    @Autowired
    SysLogUtil sysLogUtil;

    @Autowired
    DeviceCache deviceCache;

    @Autowired
    LicenseController licenseController;

    @PostConstruct
    public void initAll(){
        initCom();


    }


    public void initCom(){

        //全局缓存
        deviceCache.init();

        //初始化syslog接受服务器
        sysLogUtil.startServer(SysLogUtil.SYSLOG_PORT);

        licenseController.init();
    }


}
