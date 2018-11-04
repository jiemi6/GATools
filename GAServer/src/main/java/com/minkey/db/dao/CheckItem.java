package com.minkey.db.dao;

import com.minkey.contants.MyLevel;

/**
 * 检查细项
 */
public class CheckItem {
    /**
     * 检查项id
     */
    private long itemId;

    /**
     * 检查id
     */
    private long checkId;

    /**
     * 结果信息，代码拼装
     */
    private String resultMsg;

    /**
     * 当前步骤
     */
    private int step;

    /**
     * 总步数
     */
    private int totalStep;

    /**
     * 结果级别
     * @see  com.minkey.contants.MyLevel
     */
    private int resultLevel = MyLevel.LEVEL_NORMAL;

    /**
     * 检查项目类型
     */
    private int itemType;

    /**
     * 错误类型，
     * <br> 0 默认为成功。
     */
    private int errorType = 0;

    public CheckItem(){
    }

    public CheckItem(long checkId, Integer totalStep) {
        this.checkId = checkId;
        this.totalStep = totalStep;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getCheckId() {
        return checkId;
    }

    public void setCheckId(long checkId) {
        this.checkId = checkId;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public int getResultLevel() {
        return resultLevel;
    }

    public void setResultLevel(int resultLevel) {
        this.resultLevel = resultLevel;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTotalStep() {
        return totalStep;
    }

    public void setTotalStep(int totalStep) {
        this.totalStep = totalStep;
    }

    @Override
    public String toString() {
        return "CheckItem{" +
                "itemId=" + itemId +
                ", checkId=" + checkId +
                ", resultMsg='" + resultMsg + '\'' +
                ", step=" + step +
                ", totalStep=" + totalStep +
                ", resultLevel=" + resultLevel +
                ", itemType=" + itemType +
                ", errorType=" + errorType +
                '}';
    }
}
