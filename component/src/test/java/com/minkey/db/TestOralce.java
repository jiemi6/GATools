package com.minkey.db;

import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.jdbc.DatabaseDriver;

@Slf4j
public class TestOralce {


    @Test
    public void testOracle() {
        DynamicDB dynamicDB = new DynamicDB();
        String ip = "topwalkhndq.tpddns.cn";
        int port = 1521;
        String dbName = "orcl";
        try {

            DBConfigData dbConfigData = new DBConfigData();
            dbConfigData.setDatabaseDriver(DatabaseDriver.ORACLE);
            dbConfigData.setIp(ip);
            dbConfigData.setPort(port);
            dbConfigData.setDbName(dbName);
            dbConfigData.setName("scott");
            dbConfigData.setPwd("tiger");

//            dynamicDB.testDBConnect(dbConfigData);

            dynamicDB.testDBSource(dbConfigData);


            log.error("ok=" );

        } catch (SystemException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
