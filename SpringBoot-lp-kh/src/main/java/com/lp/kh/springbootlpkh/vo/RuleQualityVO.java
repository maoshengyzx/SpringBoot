package com.lp.kh.springbootlpkh.vo;


import lombok.Data;
import lombok.ToString;

public class RuleQualityVO {
   // @ApiModelProperty("专项名称")
    private String projectName;
   // @ApiModelProperty("管理人名称")
    private String managerName;
  //  @ApiModelProperty("规则名称")
    private String ruleName;
  //  @ApiModelProperty("规则id")
    private Integer ruleId;
//    @ApiModelProperty("严重次数")
    private Integer severeCount;
  //  @ApiModelProperty("一般次数")
    private Integer alertCount;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
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
        return "RuleQualityVO{" +
                "projectName='" + projectName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleId=" + ruleId +
                ", severeCount=" + severeCount +
                ", alertCount=" + alertCount +
                '}';
    }
}