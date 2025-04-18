package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * 质量维度细类信息表(T02RuleClass)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:56
 */
public class T02RuleClass implements Serializable {
    private static final long serialVersionUID = 990486020408047300L;
    /**
     * 分类编号
     */
    private String classNo;
    /**
     * 名称
     */
    private String className;
    /**
     * 质量规则维度
     */
    private String ruleDim;
    /**
     * 展示顺序
     */
    private Long showOrder;


    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRuleDim() {
        return ruleDim;
    }

    public void setRuleDim(String ruleDim) {
        this.ruleDim = ruleDim;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

}

