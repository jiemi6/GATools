package com.minkey.syslog;

import com.minkey.db.dao.Syslog;
import com.minkey.util.SpringUtils;
import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;

import java.util.Date;


public class DBSyslogServerEventHandler implements SyslogServerEventHandlerIF {
    @Override
    public void event(SyslogServerIF syslogServer, SyslogServerEventIF event) {
        Syslog syslog = new Syslog();
        syslog.setCreateTime(event.getDate() == null ? new Date() : event.getDate());
        syslog.setHost(event.getHost());
        syslog.setLevel(event.getLevel());
        syslog.setMsg(event.getMessage());
        syslog.setFacility(event.getFacility());

        //先扔到缓存中去
        SpringUtils.getBean(DBSyslogManager.class).addLog(syslog);
    }
}
