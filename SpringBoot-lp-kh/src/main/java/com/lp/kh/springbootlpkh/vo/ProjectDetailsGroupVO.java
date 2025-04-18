package com.lp.kh.springbootlpkh.vo;


import lombok.Data;
import lombok.ToString;

public class ProjectDetailsGroupVO {
    //@ApiModelProperty("专项名称")
    private String projectName;
    //@ApiModelProperty("当天执行的规则数")
    private Integer ruleCount;
   // @ApiModelProperty("当天检核总数")
    private Integer checkCount;
   // @ApiModelProperty("当天严重数")
    private Integer severeCount;
   // @ApiModelProperty("当天一般")
    private Integer alertCount;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
        return "ProjectDetailsGroupVO{" +
                "projectName='" + projectName + '\'' +
                ", ruleCount=" + ruleCount +
                ", checkCount=" + checkCount +
                ", severeCount=" + severeCount +
                ", alertCount=" + alertCount +
                '}';
    }
}