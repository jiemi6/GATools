package com.minkey.spring;

import com.minkey.cache.DeviceCache;
import com.minkey.controller.LicenseController;
import com.minkey.syslog.SysLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired

    @Value("${system.debug:false}")
    private boolean isDebug;

    @PostConstruct
    public void initAll(){
        if(!isDebug) {
            initCom();
        }

    }


    public void initCom(){

        //全局缓存
        deviceCache.init();

        //初始化syslog接受服务器
        sysLogUtil.startAcceptServer(SysLogUtil.SYSLOG_PORT);

        licenseController.init();
    }


}
