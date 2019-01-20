package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.ConfigHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.util.DateUtil;
import com.minkey.util.SendMailText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 告警处理，告警数据来源
 */
@Slf4j
@Component
public class AlarmSendHandler {
    private JSONObject alarmConfigData;

    @Autowired
    ConfigHandler configHandler;

    @Autowired
    AlarmLogHandler alarmLogHandler;

    @Autowired
    SendMailText sendMailText;

    /**
     * 报警发送间隔分钟
     */
    private int jianGeFenzhong = 0;

    /**
     * 发送次数
     */
    private int totalSendTimes = 0;

    public void init(){
        Map<String, Object> configMap = configHandler.query(ConfigEnum.AlarmConfig.getConfigKey());
        if(CollectionUtils.isEmpty(configMap)){
            log.warn("没有报警设置,不做报警");
            return;
        }

        //{"emailConfig":"{\"fwmaildz\":\"123456\",\"ports\":\"20\",\"emailbj\":\"123456@qq.com\",\"alarmuser\":\"user\",\"alarpwd\":\"123456\",\"bjtitle\":\"标题\"}","smsConfig":"{\"fwzxhm\":\"123\",\"fstel\":\"13000000000\"}","baseConfig":"{\"alarmtime\":\"88888\",\"maxnum\":\"123\",\"timeStart\":\"00:05:05\",\"timeEnd\":\"00:04:04\"}"}
        alarmConfigData = JSONObject.parseObject((String) configMap.get(ConfigHandler.CONFIGDATAKEY));
    }

    /**
     * 发送报警,一分钟一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void sendAlarm(){
        try {
            if (alarmConfigData == null) {
                return;
            }

            JSONObject baseConfig = alarmConfigData.getJSONObject("baseConfig");

            if(!isAlarmTime(baseConfig)){
                return;
            }

            if (baseConfig.getIntValue("maxnum") < totalSendTimes) {
                return;
            } else {
                totalSendTimes++;
            }

            if (baseConfig.getIntValue("alarmtime") < 1) {
                return;
            }

            if (baseConfig.getIntValue("alarmtime") > jianGeFenzhong) {
                jianGeFenzhong++;
                return;
            } else {
                //清零,并往下执行
                jianGeFenzhong = 0;
            }

            if (alarmConfigData.getJSONObject("emailConfig") != null) {
                sendEmail(alarmConfigData.getJSONObject("emailConfig"));
            }
        }catch (Exception e){
            log.error("调度发送报警异常:"+e.getMessage());
        }

    }

    private boolean isAlarmTime(JSONObject baseConfig ){
        String timeStart = baseConfig.getString("timeStart");
        String timeEnd = baseConfig.getString("timeEnd");

        Date now = new Date();
        String day = DateUtil.dateFormatStr(now ,DateUtil.format_day);

        Date startDate = DateUtil.strFormatDate(day+" "+timeStart ,DateUtil.format_all);
        Date endDate = DateUtil.strFormatDate(day+" "+timeEnd ,DateUtil.format_all);

        return now.after(startDate) && now.before(endDate);
    }


    private void sendEmail(JSONObject emailConfig){
        long index = 0;
        Map<String, Object> configIndex = configHandler.query(ConfigEnum.EmailIndex.getConfigKey());
        if(!CollectionUtils.isEmpty(configIndex)){
            index = Long.valueOf(configIndex.get("sendIndex").toString());
        }

        List<AlarmLog> alarmLogs = alarmLogHandler.query4email(index);
        if(CollectionUtils.isEmpty(alarmLogs)){
            return;
        }

        String context = "emailsccc";

        sendMailText.send(emailConfig,context);
    }
}