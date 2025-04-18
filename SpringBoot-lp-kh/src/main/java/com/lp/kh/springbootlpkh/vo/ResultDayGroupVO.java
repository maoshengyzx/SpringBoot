package com.lp.kh.springbootlpkh.vo;

import lombok.ToString;

public class ResultDayGroupVO {
    //@ApiModelProperty("日期天")
    private String resultDay;
    // @ApiModelProperty("严重数")
    private Integer severeCount;
    //@ApiModelProperty("一般数")
    private Integer alertCount;
    // @ApiModelProperty("总检查数")
    private Integer checkCount;


    public String getResultDay() {
        return resultDay;
    }

    public void setResultDay(String resultDay) {
        this.resultDay = resultDay;
    }

    public Integer getSevereCount() {
        return severeCount;
    }

    public void setSevereCount(Integer severeCount) {
        this.severeCount = severeCount;
    }

    public Integer getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(Integer alertCount) {
        this.alertCount = alertCount;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }


    @Override
    public String toString() {
        return "ResultDayGroupVO{" +
                "resultDay='" + resultDay + '\'' +
                ", severeCount=" + severeCount +
                ", alertCount=" + alertCount +
                ", checkCount=" + checkCount +
                '}';
    }
}