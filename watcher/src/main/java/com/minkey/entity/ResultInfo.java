package com.minkey.entity;

/**
 * @author minkey
 * @version V1.0
 */
public class ResultInfo {
    /**
    *返回状态码 （在linux中可以通过 echo $? 可知每步执行令执行的状态码）
     * <br> 0: 正常返回
     */
    private int exitStuts;
    /**
        标准正确输出流内容
     */
    private String outRes;
    /**
        标准错误输出流内容
     */
    private String errRes;

    public ResultInfo(int exitStuts, String outRes, String errRes) {
        super();
        this.exitStuts = exitStuts;
        this.outRes = outRes;
        this.errRes = errRes;
    }

    public ResultInfo() {
        super();
    }

    public int getExitStuts() {
        return exitStuts;
    }

    public void setExitStuts(int exitStuts) {
        this.exitStuts = exitStuts;
    }

    public String getOutRes() {
        return outRes;
    }

    public void setOutRes(String outRes) {
        this.outRes = outRes;
    }

    public String getErrRes() {
        return errRes;
    }

    public void setErrRes(String errRes) {
        this.errRes = errRes;
    }

    /**
     * 当exitStuts=0 && errRes="" &&outREs=""返回true
     *
     * @return
     */
    public boolean isEmptySuccess() {
        if (this.getExitStuts() == 0 && "".equals(this.getErrRes()) && "".equals(this.getOutRes())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ResultInfo [exitStuts=" + exitStuts + ", outRes=" + outRes +  ", errRes=" + errRes + "]";
    }

    public void clear() {
        exitStuts = 0;
        outRes = errRes = null;
    }

}