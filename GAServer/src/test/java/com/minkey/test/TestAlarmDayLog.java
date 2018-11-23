package com.minkey.test;

import com.minkey.GAToolsRun;
import com.minkey.controller.IndexController;
import com.minkey.db.analysis.AlarmDayLogAnalysisTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GAToolsRun.class)
public class TestAlarmDayLog {

    @Autowired
    AlarmDayLogAnalysisTask alarmDayLogAnalysisTask;

    @Autowired
    IndexController indexController;

    @Test
    public void testDayLog(){
        alarmDayLogAnalysisTask.goAnalysis();
    }

    @Test
    public void testIndexLog(){
        indexController.alarmStatistics();
    }

    @Test
    public void testTotalLog(){
        indexController.statistics();
    }

    @Test
    public void alarmRinking(){

        indexController.alarmRinking(1);
    }


}
