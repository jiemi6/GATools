package com.minkey.test.db;

import com.minkey.GAToolsRun;
import com.minkey.db.TaskDayLogHandler;
import com.minkey.db.third.task.TaskCollector;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GAToolsRun.class)
@Slf4j
public class OtherTest{
    @Autowired
    TaskCollector taskCollector;

    @Autowired
    TaskDayLogHandler taskDayLogHandler;
    @Test
    public void test() {
//        taskCollector.getTaskFromOtherDB();
        log.error("xxxx");
        taskDayLogHandler.querySum(1,null);
    }
}
