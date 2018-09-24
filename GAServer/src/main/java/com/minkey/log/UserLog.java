package com.minkey.log;

import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户日志
 */
@Component
public class UserLog {

    @Autowired
    UserLogHandler userLogHandler;

    public void log(User user, String log){
        //当前时间
        long time = System.currentTimeMillis();


        userLogHandler.insert(user.getuName(),user.getLoginIp(),log);

    }
}
