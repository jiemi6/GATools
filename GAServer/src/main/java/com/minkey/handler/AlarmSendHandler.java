package com.minkey.handler;

import com.alibaba.fastjson.JSONObject;
import com.minkey.cache.DeviceCache;
import com.minkey.contants.AlarmEnum;
import com.minkey.contants.ConfigEnum;
import com.minkey.contants.MyLevel;
import com.minkey.db.AlarmLogHandler;
import com.minkey.db.ConfigHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.AlarmLog;
import com.minkey.util.DateUtil;
import com.minkey.util.SendMailText;
import com.minkey.util.SendSMS;
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

    @Autowired
    SendSMS sendSMS;

    @Autowired
    DeviceCache deviceCache;

    /**
     * 报警发送间隔分钟
     */
    private int jianGeFenzhong = 1;

    /**
     * 发送次数
     */
    private int totalSendTimes = 0;

    public void initConfig(){
        Map<String, Object> configMap = configHandler.query(ConfigEnum.AlarmConfig.getConfigKey());
        if(CollectionUtils.isEmpty(configMap)){
            log.warn("没有报警设置,不做报警");
            alarmConfigData = null;
            return;
        }

        //{"emailConfig":{"isOpen":true,"alarmuser":"280878481@qq.com","alarpwd":"280878481@qq.com","bjtitle":"280878481@qq.com","emailbj":"280878481@qq.com","fwmaildz":"280878481@qq.com","ports":"3306"},"smsConfig":{"fwzxhm":"17520143588","isOpen":false,"fstel":"17520143588"},"baseConfig":{"timeEnd":"05:00:00","timeStart":"01:00:00","alarmtime":"10","maxnum":"1"}}
        alarmConfigData = JSONObject.parseObject((String) configMap.get(ConfigHandler.CONFIGDATAKEY));
    }

    public void updateConfig(final JSONObject configData) {
        alarmConfigData = configData;
    }


    /**
     * 每天凌晨 0：01重置设置
     */
    @Scheduled(cron="0 1 0 * * ?")
    public void reset(){
        //重置发送次数
        totalSendTimes = 0;

    }


    /**
     * 发送报警,一分钟一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void sendAlarm(){
        log.error("开始报警发送");
        try {
            if (alarmConfigData == null) {
                log.error("alarmConfigData == null");
                return;
            }

            JSONObject baseConfig = alarmConfigData.getJSONObject("baseConfig");

            if(!isAlarmTime(baseConfig)){
                log.error("非发送时间段");
                return;
            }

            if (baseConfig.getIntValue("maxnum") < totalSendTimes) {
                log.error("发送次数超限");
                return;
            } else {
                totalSendTimes++;
            }

            if (baseConfig.getIntValue("alarmtime") < 1) {
                log.error("发送次数设置错误[{}]",baseConfig.getIntValue("alarmtime"));
                return;
            }

            if (baseConfig.getIntValue("alarmtime") > jianGeFenzhong) {
                log.error("间隔时间没有达到 ");
                jianGeFenzhong++;
                return;
            } else {
                //清零,并往下执行
                jianGeFenzhong = 1;
            }


            long index = 0;
            Map<String, Object> configIndex = configHandler.query(ConfigEnum.AlarmSendIndex.getConfigKey());
            JSONObject jsonConfig ;
            if(!CollectionUtils.isEmpty(configIndex)){
                String jsonConfigStr = configIndex.get(ConfigHandler.CONFIGDATAKEY).toString();
                jsonConfig = (JSONObject) JSONObject.parse(jsonConfigStr);
                index = jsonConfig.getLong("sendIndex");
            }else{
                jsonConfig = new JSONObject();
            }

            List<AlarmLog> alarmLogs = alarmLogHandler.query4send(index);
            if(CollectionUtils.isEmpty(alarmLogs)){
                return;
            }

            //取出来
            AlarmLog alarmLog = alarmLogs.get(0);

            //更新下标
            jsonConfig.put("sendIndex",alarmLog.getLogId());
            configHandler.insert(ConfigEnum.AlarmSendIndex.getConfigKey(),jsonConfig.toString());


            String alarmObjectName = alarmLog.getBid()+"";
            switch (alarmLog.getbType()){
                case 1 :
                    alarmObjectName= deviceCache.getLink8Id(alarmLog.getBid()).getLinkName();
                    break;
                case 2 :
                    alarmObjectName = taskHandler.query(alarmLog.getBid()).getTaskName();
                    break;
                case 3 :
                    alarmObjectName = deviceCache.getDevice(alarmLog.getBid()).getDeviceName();
                    break;
            }


            if (alarmConfigData.getJSONObject("emailConfig") != null) {
                sendEmail(alarmObjectName,alarmConfigData.getJSONObject("emailConfig"),alarmLog);
            }

            if (alarmConfigData.getJSONObject("smsConfig") != null) {
                sendSMS(alarmObjectName,alarmConfigData.getJSONObject("smsConfig"),alarmLog);
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


    @Autowired
    TaskHandler taskHandler;

    private void sendEmail(String alarmObjectName ,JSONObject emailConfig,AlarmLog alarmLog){
        if(!emailConfig.getBooleanValue("isOpen")){
            return;
        }

        StringBuffer sb = new StringBuffer();

        sb.append("告警代码:").append(alarmLog.getType()).append("说明:").append(AlarmEnum.find8Type(alarmLog.getType()).getDesc()).append("\r\n");
        sb.append("告警类型:").append(AlarmLog.getString8BType(alarmLog.getbType())).append("\r\n");
        sb.append("告警级别:").append(MyLevel.getString8level(alarmLog.getLevel())).append("\r\n");
        sb.append("告警对象:").append(alarmObjectName).append("\r\n");
        sb.append("告警内容:").append(alarmLog.getMsg()).append("\r\n");
        sb.append("告警时间:").append(DateUtil.dateFormatStr(alarmLog.getCreateTime(),DateUtil.format_all)).append("\r\n");

        sendMailText.send(emailConfig,sb.toString());
    }

    private void sendSMS(String alarmObjectName ,JSONObject smsConfig,AlarmLog alarmLog) {
        if(!smsConfig.getBooleanValue("isOpen")){
            return;
        }

        StringBuffer sb = new StringBuffer();

        sb.append("告警代码:").append(alarmLog.getType()).append("说明:").append(AlarmEnum.find8Type(alarmLog.getType()).getDesc()).append(".");
        sb.append("告警类型:").append(AlarmLog.getString8BType(alarmLog.getbType())).append(".");
        sb.append("告警级别:").append(MyLevel.getString8level(alarmLog.getLevel())).append(".");
        sb.append("告警对象:").append(alarmObjectName).append(".");
        sb.append("告警内容:").append(alarmLog.getMsg()).append(".");
        sb.append("告警时间:").append(DateUtil.dateFormatStr(alarmLog.getCreateTime(),DateUtil.format_all));


        sendSMS.send(smsConfig,sb.toString());
    }


}