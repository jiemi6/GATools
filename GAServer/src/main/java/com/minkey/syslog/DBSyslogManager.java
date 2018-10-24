package com.minkey.syslog;

import com.minkey.db.SyslogHandler;
import com.minkey.db.dao.Syslog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 临时存储接受的syslog, 并间隔一段时间后批量刷入db
 */
@Component
public class DBSyslogManager {
    private final static Logger logger = LoggerFactory.getLogger(DBSyslogManager.class);

    @Autowired
    SyslogHandler syslogHandler;

    //缓存直接替换指针
    Set<Syslog> logCache = Collections.synchronizedSet(new HashSet<Syslog>());


    /**
     * 增加log，不直接写db
     * @param syslog
     */
    public void addLog(Syslog syslog){
        logCache.add(syslog);
    }


    /**
     * 5秒刷写一次db
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void save2Db() {
        if(CollectionUtils.isEmpty(logCache)){
            return;
        }

        Set<Syslog> dbSet = logCache;
        //指针切换
        logCache = Collections.synchronizedSet(new HashSet<Syslog>());

        try{
            syslogHandler.insertAll(dbSet);
        }catch (Exception e){
            logger.error("存储syslog异常",e);
        }

    }
}
