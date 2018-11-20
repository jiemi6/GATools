package com.minkey.db.analysis;

import com.minkey.util.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 按天统计报警情况
 */
@Component
public class AlarmDayLogHandler {
    private final String tableName = "t_alarmDayLog";
    @Autowired
    JdbcTemplate jdbcTemplate;



    public void insert(AlarmDayLog alarmDayLog) {
        int num = jdbcTemplate.update("insert into "+tableName+" (totalLinkNum,alarmLinkNum,totalDeviceNum,alarmDeviceNum,totalTaskNum,alarmTaskNum,createTime) VALUES (?,?,?,?,?,?,?)"
                ,new Object[]{alarmDayLog.getTotalLinkNum(),alarmDayLog.getAlarmLinkNum(),
                        alarmDayLog.getTotalDeviceNum(),alarmDayLog.getAlarmDeviceNum(),
                        alarmDayLog.getTotalTaskNum(),alarmDayLog.getAlarmTaskNum(),alarmDayLog.getCreateTime()});

    }

    /**
     * 根据年月日查询数据  exp:2018-12-11
     * @param date
     */
    public AlarmDayLog query8day(Date date) {

        String day = DateUtil.dateFormatStr(date,DateUtil.format_day);

        List<AlarmDayLog> alarmDayLogs = jdbcTemplate.query("select * from "+tableName+" where createTime= ?",
                new Object[]{day},new BeanPropertyRowMapper<>(AlarmDayLog.class));
        if(CollectionUtils.isEmpty(alarmDayLogs)){
            return null;
        }
        return alarmDayLogs.get(0);

    }


    public List<AlarmDayLog> query8days(Date startDate, Date endDate) {
        String startDateStr = DateUtil.dateFormatStr(startDate,DateUtil.format_all);
        String endDateStr = DateUtil.dateFormatStr(endDate,DateUtil.format_all);
        List<AlarmDayLog> alarmDayLogs = jdbcTemplate.query("select * from "+tableName+" where createTime BETWEEN ? AND ?",
                new Object[]{startDateStr,endDateStr},new BeanPropertyRowMapper<>(AlarmDayLog.class));

        return alarmDayLogs;
    }
}
