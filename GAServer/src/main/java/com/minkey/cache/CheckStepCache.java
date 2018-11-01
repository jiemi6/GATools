package com.minkey.cache;

import com.minkey.db.dao.CheckItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CheckStepCache {

    private Map<Long,Integer> stepCache = new HashMap<>();
    private Map<Long,Integer> totalStepCache = new HashMap<>();

    private final Object lock= new Object();

    public Integer now(Long checkId){
        synchronized (lock){
            if(stepCache.containsKey(checkId)){
                return stepCache.get(checkId);
            }else{
                return null;
            }
        }
    }


    public Integer next(Long checkId){
        synchronized (lock){
            if(stepCache.containsKey(checkId)){
                Integer step = stepCache.get(checkId);
                step ++;
                stepCache.put(checkId,step);
                return step;
            }else{
                return null;
            }
        }
    }

    public void create(Long checkId,Integer totalStep){
        synchronized (lock) {
            stepCache.put(checkId,0);
            totalStepCache.put(checkId,totalStep);
        }
    }

    public Integer total(Long checkId){
        return totalStepCache.get(checkId);
    }

    public CheckItem createNextItem(Long checkId){
        return new CheckItem(checkId,totalStepCache.get(checkId));
    }
}
