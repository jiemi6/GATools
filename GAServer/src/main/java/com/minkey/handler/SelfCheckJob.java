package com.minkey.handler;

import com.minkey.cache.DeviceCache;
import com.minkey.contants.MyLevel;
import com.minkey.db.CheckHandler;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.dao.CheckItem;
import com.minkey.dto.RateObj;
import com.minkey.util.DiskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 本系统基本信息自检
 */
@Slf4j
@Component
public class SelfCheckJob {
    /**
     * 检查总共4步
     */
    private final int totalStep = 4 ;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CheckHandler checkHandler;
    @Autowired
    CheckItemHandler checkItemHandler;

    @Autowired
    DeviceCache deviceCache;

    public void check(long checkId) {
        //异步执行check
        startCheck(checkId);

    }

    @Async
    public void startCheck(long checkId){
        CheckItem checkItem;

        //Minkey pingConnect 网关
        checkItem = new CheckItem(checkId,totalStep);
        checkItem.setResultLevel(MyLevel.LEVEL_NORMAL);
        checkItem.setResultMsg("pingConnect 网关成功");
        checkItemHandler.insert(checkItem);


        if(checkId != -1) {
            try {
                //test本地数据库
                jdbcTemplate.execute(DatabaseDriver.MYSQL.getValidationQuery());
                checkItem = new CheckItem(checkId,totalStep);
                checkItem.setResultLevel(MyLevel.LEVEL_NORMAL);
                checkItem.setResultMsg(String.format("连接本地数据库成功."));
                checkItemHandler.insert(checkItem);
            } catch (Exception e) {
                checkItem = new CheckItem(checkId,totalStep);
                checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
                checkItem.setResultMsg("连接本地数据库失败" + e.getMessage());
                checkItemHandler.insert(checkItem);
            }
        }

        //test 本地硬盘大小
        RateObj rateObj = DiskUtils.LocalDriver();
        checkItem = new CheckItem(checkId,totalStep);
        checkItem.setResultLevel(rateObj.judgeLevel());
        checkItem.setResultMsg("磁盘已经使用"+rateObj.getUseRateStr()+",剩余"+DiskUtils.FormetFileSize(Double.valueOf(rateObj.getFree()).longValue()));
        checkItemHandler.insert(checkItem);

        try {
            deviceCache.initDB2Cache();
            checkItem = new CheckItem(checkId,totalStep);
            checkItem.setResultLevel(MyLevel.LEVEL_NORMAL);
            checkItem.setResultMsg("初始化内存数据成功");
            checkItemHandler.insert(checkItem);
        }catch (Exception e){
            checkItem = new CheckItem(checkId,totalStep);
            checkItem.setResultLevel(MyLevel.LEVEL_ERROR);
            checkItem.setResultMsg("初始化内存数据失败" + e.getMessage());
            checkItemHandler.insert(checkItem);
        }
    }



    public List<CheckItem> getResultList(Long checkId, Integer index) {
        List<CheckItem> checkItems  = checkItemHandler.query(checkId,index);
        return  checkItems;
    }
}
