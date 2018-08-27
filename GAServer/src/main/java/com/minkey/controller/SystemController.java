package com.minkey.controller;

import com.minkey.db.ConfigHandler;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理接口
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    private final static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    ConfigHandler configHandler;

    /**
     * 系统自检
     * @return
     */
    @RequestMapping("/check")
    public String check() {
        logger.info("start: 执行系统自检");

        try{
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("end:  执行系统自检");
        }
    }

    /**
     * 关机/重启
     * @param reboot 是否为重启
     * @return
     */
    @RequestMapping("/shutDown")
    public String shutDown(Boolean reboot) {


        return JSONMessage.createSuccess().toString();

    }


    /**
     * 备份设置，导出文件
     * @return
     */
    @RequestMapping("/bakConfig")
    public String bakConfig() {
        return JSONMessage.createSuccess().toString();
    }


    /**
     * 导入设置，导入配置文件，还原设置
     * @return
     */
    @RequestMapping("/reloadConfig")
    public String reloadConfig() {
        return JSONMessage.createSuccess().toString();

    }



    /**
     * 重置系统，恢复出厂设置
     * @return
     */
    @RequestMapping("/reset")
    public String reset() {
        return JSONMessage.createSuccess().toString();

    }




    /**
     * 设置网卡信息，ip，网关，子掩码等
     * @return
     */
    @RequestMapping("/setNetWork")
    public String setNetWork() {

        //检查，

        //调用系统命令设置

        return JSONMessage.createSuccess().toString();

    }


}