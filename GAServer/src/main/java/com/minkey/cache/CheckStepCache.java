package com.minkey.cache;

import com.minkey.db.dao.CheckItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CheckStepCache {

    private Map<Long,Integer> totalStepCache = new HashMap<>();

    private final Object lock= new Object();

    public void create(long checkId,int totalStep){
        synchronized (lock) {
            Integer step = totalStepCache.get(checkId);
            if(step == null){
                step = totalStep;
            }else{
                step = step +totalStep;
            }
            totalStepCache.put(checkId,step);
        }
    }

    public Integer total(long checkId){
        return totalStepCache.get(checkId);
    }

    public CheckItem createNextItem(long checkId){
        return new CheckItem(checkId,totalStepCache.get(checkId));
    }
}
