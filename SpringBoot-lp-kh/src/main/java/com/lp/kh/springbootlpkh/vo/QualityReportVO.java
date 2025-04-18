package com.lp.kh.springbootlpkh.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

// 汇总返回对象
public class QualityReportVO {
    // 数据质量概览 当天汇总 概览
    private TotalDataQualityVO totalDataQualityVO;
    //按维度汇总对象 饼状图
    private List<DimensionGroupVO> dimensionGroupVOS;
    // 按专项分组 柱状图
    private List<ProjectDetailsGroupVO> projectDetailsGroupVOS;
    //统计折现图
    private List<ResultDayGroupVO> resultDayGroupVOS;
    //数据质量运行top 10
    private List<RuleQualityVO> ruleQualityVOS;


    public TotalDataQualityVO getTotalDataQualityVO() {
        return totalDataQualityVO;
    }

    public void setTotalDataQualityVO(TotalDataQualityVO totalDataQualityVO) {
        this.totalDataQualityVO = totalDataQualityVO;
    }

    public List<DimensionGroupVO> getDimensionGroupVOS() {
        return dimensionGroupVOS;
    }

    public void setDimensionGroupVOS(List<DimensionGroupVO> dimensionGroupVOS) {
        this.dimensionGroupVOS = dimensionGroupVOS;
    }

    public List<ProjectDetailsGroupVO> getProjectDetailsGroupVOS() {
        return projectDetailsGroupVOS;
    }

    public void setProjectDetailsGroupVOS(List<ProjectDetailsGroupVO> projectDetailsGroupVOS) {
        this.projectDetailsGroupVOS = projectDetailsGroupVOS;
    }

    public List<ResultDayGroupVO> getResultDayGroupVOS() {
        return resultDayGroupVOS;
    }

    public void setResultDayGroupVOS(List<ResultDayGroupVO> resultDayGroupVOS) {
        this.resultDayGroupVOS = resultDayGroupVOS;
    }

    public List<RuleQualityVO> getRuleQualityVOS() {
        return ruleQualityVOS;
    }

    public void setRuleQualityVOS(List<RuleQualityVO> ruleQualityVOS) {
        this.ruleQualityVOS = ruleQualityVOS;
    }

    @Override
    public String toString() {
        return "QualityReportVO{" +
                "totalDataQualityVO=" + totalDataQualityVO +
                ", dimensionGroupVOS=" + dimensionGroupVOS +
                ", projectDetailsGroupVOS=" + projectDetailsGroupVOS +
                ", resultDayGroupVOS=" + resultDayGroupVOS +
                ", ruleQualityVOS=" + ruleQualityVOS +
                '}';
    }
}