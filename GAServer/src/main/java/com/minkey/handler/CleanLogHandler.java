package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.ConfigHandler;
import com.minkey.db.SyslogHandler;
import com.minkey.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 清理历史log
 */
@Slf4j
@Component
public class CleanLogHandler {

    @Autowired
    ConfigHandler configHandler;

    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    CheckItemHandler checkItemHandler;

    @Autowired
    SyslogHandler syslogHandler;

    /**
     * 每天清理一次,凌晨3点执行
     */
    @Scheduled(cron="0 0 3 * * ?")
    public void cleanLog(){
        try {
            Map<String, Object> configMap = configHandler.query(ConfigEnum.LogOverDay.getConfigKey());
            if (CollectionUtils.isEmpty(configMap)) {
                return;
            }
            JSONObject configData = JSONObject.parseObject((String) configMap.get(ConfigHandler.CONFIGDATAKEY));

            int LogOverDay = configData.getIntValue("LogOverDay");

            Calendar date = Calendar.getInstance();
            date.setTime(new Date());

            //往前倒推保留天数
            date.add(Calendar.DAY_OF_YEAR, 0 - LogOverDay);
            String deleteDayStr = DateUtil.dateFormatStr(date.getTime(),DateUtil.format_day);

            //删除日期小于这天的log
            alarmLogHandler.clean(deleteDayStr);
            checkItemHandler.clean(deleteDayStr);
            syslogHandler.clean(deleteDayStr);

        }catch (Exception e){
            log.error("定时清理log异常",e);
        }
    }
}