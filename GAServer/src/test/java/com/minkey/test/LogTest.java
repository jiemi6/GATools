package com.minkey.test;

import com.minkey.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: mijj
 * @Date: 2019-07-19 13:48
 */
@Slf4j
public class LogTest {

    @Test
    public void testClean(){
        int LogOverDay = 120;

        Calendar date = Calendar.getInstance();
        Date newData = DateUtil.strFormatDate("2019-2-2","yyyy-MM-dd");
        date.setTime(newData);

        int today = date.get(Calendar.DATE);
        date.add(Calendar.DAY_OF_YEAR, 0-LogOverDay);
//        date.add(Calendar.DATE, 0-LogOverDay);
//        date.set(Calendar.DAY_OF_YEAR, today - LogOverDay);
        String deleteDayStr = DateUtil.dateFormatStr(date.getTime(),DateUtil.format_day);

        log.error("---------"+deleteDayStr);
    }
}
