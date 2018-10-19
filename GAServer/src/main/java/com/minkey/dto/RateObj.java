package com.minkey.dto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 比率工具对象，专门计算百分比
 */
public class RateObj {

    private double max;

    private double use;

    private double free;

    RateObj() {

    }

    public static RateObj create8Free(double max, double free){
        RateObj rateObj = new RateObj();
        rateObj.setMax(max);
        rateObj.setFree(free);
        rateObj.setUse(max - free);
        return  rateObj;
    }

    public static RateObj create8Use(double max, double use){
        RateObj rateObj = new RateObj();
        rateObj.setMax(max);
        rateObj.setUse(use);
        rateObj.setFree(max - use);
        return  rateObj;
    }

    void setMax(double max) {
        this.max = max;
    }

    void setUse(double use) {
        this.use = use;
    }

    void setFree(double free) {
        this.free = free;
    }

    public double getMax() {
        return max;
    }

    public double getUse() {
        return use;
    }

    public double getFree() {
        return free;
    }

    public double getRate(){
        return use / max ;
    }

    public String getUseRateStr(int scale){
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = getRate() * 100;
        return df.format(accuracy_num)+"%";
    }

    public String getUseRateStr(){
        return getUseRateStr(0);
    }


}
