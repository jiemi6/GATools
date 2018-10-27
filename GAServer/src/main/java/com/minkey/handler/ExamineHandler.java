package com.minkey.handler;

import com.minkey.db.CheckItemHandler;
import com.minkey.db.dao.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查log
 */
@Component
public class ExamineHandler {

    @Autowired
    CheckItemHandler checkItemHandler;

    public void doAllInOne(long checkId) {
    }

    public List<CheckItem> getResultList(Long checkId, Integer index) {
        List<CheckItem> checkItems  = checkItemHandler.query(checkId,index);
        return  checkItems;
    }

    public void doDevice(long checkId, Long deviceId) {
    }

    public void doTask(long checkId, Long taskId) {
    }

    public void doLink(long checkId, Long linkId) {
    }
}
