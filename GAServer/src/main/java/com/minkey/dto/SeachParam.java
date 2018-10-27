package com.minkey.dto;

import com.minkey.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class SeachParam {

    /**
     * 关键字
     */
    private String keyWord;

    private String startDate;
    private String endDate;

    /**
     * 级别
     */
    private Integer level;

    private Integer type;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date startDate(){
        if(StringUtils.isEmpty(startDate)){
            return null;
        }
        return DateUtil.strFormatDate(startDate,DateUtil.format_all);
    }

    public Date endDate(){
        if(StringUtils.isEmpty(endDate)){
            return null;
        }
        return DateUtil.strFormatDate(endDate,DateUtil.format_all);
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean hasDataParam(){
        return StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate);
    }

    public String buildDateBetweenSql(){
        if(!hasDataParam()){
            return null;
        }


        if(this.startDate() == null){
            this.setStartDate("2010-10-10");
        }
        if(this.endDate() == null){
            this.setEndDate("2099-10-10");
        }

        return " between '"+this.startDate + "' and '" + this.endDate +"'";
    }
}
