package com.lp.kh.springbootlpkh.vo;


import lombok.Data;
import lombok.ToString;

public class TotalDataQualityVO {
    //@ApiModelProperty("专项数")
    private Integer  projectCount;
    //@ApiModelProperty("规则数")
    private Integer ruleCount;
    //@ApiModelProperty("当天检核数")
    private Integer checkCount;
    //@ApiModelProperty("当天检核严重数")
    private Integer severeCount;
    //@ApiModelProperty("当天检核警告数")
    private Integer alertCount;


    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getRuleCount() {
        return ruleCount;
    }

    public void setRuleCount(Integer ruleCount) {
        this.ruleCount = ruleCount;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
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

    @Override
    public String toString() {
        return "TotalDataQualityVO{" +
                "projectCount=" + projectCount +
                ", ruleCount=" + ruleCount +
                ", checkCount=" + checkCount +
                ", severeCount=" + severeCount +
                ", alertCount=" + alertCount +
                '}';
    }
}