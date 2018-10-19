package com.minkey.manager;

import org.springframework.stereotype.Component;

/**
 * 检查log
 */
@Component
public class ExamineLog {


    /**
     * 设备体检
     * @param uid
     * @param log
     */
    public void device(Long uid,String log){
        //当前时间
        long time = System.currentTimeMillis();


    }


    /**
     * 链路体检
     * @param uid
     * @param log
     */
    public void link(Long uid,String log){
        //当前时间
        long time = System.currentTimeMillis();


    }

    /**
     * 一键体检All
     * @param uid
     * @param log
     */
    public void all(Long uid,String log){
        //当前时间
        long time = System.currentTimeMillis();


    }
}
