package com.minkey.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String format_day = "yyyy-MM-dd";

    public static final String format_time= "HH:mm:ss";

    public static final String format_all = "yyyy-MM-dd HH:mm:ss";


    /**
     * 日期格式化
     * return  格式化字符串如 2017-09-07
     * @param date
     */
    public static String dateFormatStr(Date date,String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        String time = format.format(date);
        return time;
    }

    /**
     * 字符串日期转为Date
     * @param dateStr
     * @throws ParseException
     */
    public static Date strFormatDate(String dateStr,String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        Date lendDate = null;
        try {
            lendDate = format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return lendDate;
    }


    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


}
