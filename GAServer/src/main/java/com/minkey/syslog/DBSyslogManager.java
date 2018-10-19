package com.minkey.syslog;

import java.util.HashSet;
import java.util.Set;

/**
 * 临时存储接受的syslog, 并间隔一段时间后批量刷入db
 */
public class DBSyslogManager {

    /**
     * 间隔刷新时间为5秒
     */
    private final long times = 5000l;


    //Minkey  缓存直接替换指针
    Set logCache = new HashSet();


    /**
     * 增加log，不直接写db
     */
    public void addLog(){

    }

}
