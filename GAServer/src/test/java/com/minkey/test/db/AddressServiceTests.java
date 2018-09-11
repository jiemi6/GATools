package com.minkey.test.db;

import com.minkey.MainRun;
import com.minkey.scheduled.DeviceExplorerScheduled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainRun.class)
public class AddressServiceTests {
    @Autowired
    DeviceExplorerScheduled   deviceExplorerScheduled;

    @Test
    public void testService() {
        int i = 0;
        while (i < 50){

            i++;
            deviceExplorerScheduled.getDeviceFromSNMP();
        }

}
}