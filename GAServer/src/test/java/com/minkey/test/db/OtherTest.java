package com.minkey.test.db;

import com.minkey.MainRun;
import com.minkey.db.TaskLogHandler;
import com.minkey.db.third.task.TaskCollector;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainRun.class)
@Slf4j
public class OtherTest{
    @Autowired
    TaskCollector taskCollector;

    @Autowired
    TaskLogHandler taskLogHandler;
    @Test
    public void test() {
//        taskCollector.getTaskFromOtherDB();
        log.error("xxxx");
        taskLogHandler.querySum(1);
    }
}
