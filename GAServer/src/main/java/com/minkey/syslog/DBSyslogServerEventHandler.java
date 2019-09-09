package com.minkey.syslog;

import com.minkey.db.dao.Syslog;
import com.minkey.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;

import java.util.Date;

@Slf4j
public class DBSyslogServerEventHandler implements SyslogServerEventHandlerIF {
    @Override
    public void event(SyslogServerIF syslogServer, SyslogServerEventIF event) {
//        SyslogServerEvent syslogServerEvent = (SyslogServerEvent)event;

        try {
            Syslog syslog = new Syslog();
            syslog.setCreateTime(event.getDate() == null ? new Date() : event.getDate());
            syslog.setHost(event.getHost());
            syslog.setLevel(event.getLevel());
            syslog.setMsg(event.getMessage());
            syslog.setFacility(event.getFacility());

            //先扔到缓存中去
            SpringUtils.getBean(DBSyslogManager.class).addLog(syslog);
        }catch (Exception e){
            log.error(String.format("syslog接收:%s,格式不正确,保存异常,",event2String(event)),e);
        }
    }

    private String event2String(SyslogServerEventIF event){
        return String.format("%s[Date=%s,Host=%s,Facility=%s,Level=%s,Message=%s]",event.getClass().getSimpleName(),
                event.getDate(),event.getHost(),event.getFacility(),event.getLevel(),event.getMessage());

    }
}
