package com.minkey.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.minkey.contants.MyLevel;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 设备硬件资源信息，
 * 包括cpu，内存，磁盘
 */
public class DeviceExplorer {
    /**
     * cpu占用率，list长度为cpu个数，Integer值为单个cpu的占用率
     */
    @JSONField(serialize=false)
    private Map<String,RateObj> cpus;

    /**
     * 磁盘占用
     */
    @JSONField(serialize=false)
    private Map<String,RateObj> disks;

    /**
     * 缓存占用，缓存只有一个
     */
    private RateObj  mem;

    public Map<String, RateObj> getCpus() {
        return cpus;
    }

    public void setCpus(Map<String, RateObj> cpus) {
        this.cpus = cpus;
    }

    public Map<String, RateObj> getDisks() {
        return disks;
    }

    public void setDisks(Map<String, RateObj> disks) {
        this.disks = disks;
    }

    public RateObj getMem() {
        return mem;
    }

    public void setMem(RateObj mem) {
        this.mem = mem;
    }

    public RateObj getCpu(){
        if(MapUtils.isEmpty(cpus)){
            return null;
        }

        RateObj cpuRate = new RateObj();
        cpuRate.setMax(100);
        double use = 0;
        for(Map.Entry<String,RateObj> entry : cpus.entrySet()){
            use +=entry.getValue().getUse();
        }

        //除以总数
        cpuRate.setUse(use/cpus.size());
        return  cpuRate;
    }

    public RateObj getDisk(){
        if(MapUtils.isEmpty(disks)){
            return null;
        }

        RateObj diskRate = new RateObj();
        double max = 0;
        double use = 0;
        for(Map.Entry<String,RateObj> entry : disks.entrySet()){
            max +=entry.getValue().getMax();
            use +=entry.getValue().getUse();
        }
        diskRate.setUse(use/disks.size());
        diskRate.setMax(max/disks.size());
        return  diskRate;
    }

    /**
     * 根据cpu,disk,mem使用比率一起判断级别
     * 只要有一项高 就代表高
     * @return
     */
    public int judgeLevel(){
        RateObj cpu = this.getCpu();
        RateObj disk = this.getDisk();

        if(cpu.judgeLevel() == MyLevel.LEVEL_ERROR
                || disk.judgeLevel() == MyLevel.LEVEL_ERROR
                || mem.judgeLevel() == MyLevel.LEVEL_ERROR){
            return MyLevel.LEVEL_ERROR;
        }else if(cpu.judgeLevel() == MyLevel.LEVEL_WARN
                || disk.judgeLevel() == MyLevel.LEVEL_WARN
                || mem.judgeLevel() == MyLevel.LEVEL_WARN){
            return MyLevel.LEVEL_WARN;
        }else{
            return MyLevel.LEVEL_NORMAL;
        }
    }
}
