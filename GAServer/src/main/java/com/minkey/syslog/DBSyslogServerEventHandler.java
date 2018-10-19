package com.minkey.syslog;

import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;


public class DBSyslogServerEventHandler implements SyslogServerEventHandlerIF {
    @Override
    public void event(SyslogServerIF syslogServer, SyslogServerEventIF event) {
        //Minkey 存储到db中
        event.getDate();
        event.getHost();
        event.getLevel();
        event.getMessage();

    }
}
