package com.minkey.test.db;

import com.minkey.MainRun;
import com.minkey.db.third.task.TaskCollector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainRun.class)
public class OtherTest{
    private final static Logger logger = LoggerFactory.getLogger(MainRun.class);

    @Autowired
    TaskCollector taskCollector;
    @Test
    public void test() {
        taskCollector.getTaskFromOtherDB();


    }
}
