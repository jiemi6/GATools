package com.minkey.spring;

import com.minkey.cache.DeviceCache;
import com.minkey.controller.LicenseController;
import com.minkey.handler.AlarmHandler;
import com.minkey.handler.AlarmSendHandler;
import com.minkey.handler.DeviceConnectHandler;
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
    AlarmSendHandler alarmSendHandler;

    @Value("${system.debug:false}")
    private boolean isDebug;

    @PostConstruct
    public void initAll(){
        if(!isDebug) {
            try{
                initCom();
            }catch (Exception e){

            }
        }

    }

    @Autowired
    AlarmHandler alarmHandler;

    @Autowired
    DeviceConnectHandler deviceConnectHandler;

    public void initCom(){

        //全局缓存
        deviceCache.init();

        //初始化syslog接受服务器
        sysLogUtil.startAcceptServer(SysLogUtil.SYSLOG_PORT);

        licenseController.init();

        alarmSendHandler.initConfig();

        //先刷新所有的连接
        deviceConnectHandler.reflashConnect();

        //再刷新所有设备的状态
        alarmHandler.scanDeviceStatus();
    }


}
