package com.minkey.dto;

/**
 * 比率工具对象，专门计算百分比
 */
public class RateObj {

    private Double max;

    private Double use;

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getUse() {
        return use;
    }

    public void setUse(Double use) {
        this.use = use;
    }

    public Double getRate(){
        //Minkey 高精度下是否会失真
        return  use/max;
    }
}
