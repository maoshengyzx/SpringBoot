package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 数据质量问题关联规则数据表(T04DataqualityData)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:58
 */
public class T04DataqualityData implements Serializable {
    private static final long serialVersionUID = -15016043312007386L;

    private Long id;
    /**
     * 数据质量详情id
     */
    private Long dataqualityId;
    /**
     * 稽核执行任务id
     */
    private Long auditTaskId;
    /**
     * 规则id
     */
    private Long ruleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则描述
     */
    private String ruleDesc;
    /**
     * 对象id
     */
    private Long objectId;
    /**
     * 对象代码
     */
    private String objectCode;
    /**
     * 对象名称
     */
    private String objectName;
    /**
     * 对象类型
     */
    private String classifierId;
    /**
     * 对象类型名称
     */
    private String classifierName;
    /**
     * 数据源id
     */
    private String datasourceId;
    /**
     * 数据源名称
     */
    private String datasourceName;
    /**
     * 归属系统id
     */
    private Long systemId;
    /**
     * 所属系统名称
     */
    private String systemName;
    /**
     * 执行时间
     */
    private Date executeTime;
    /**
     * 结果状态
     */
    private String resultStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataqualityId() {
        return dataqualityId;
    }

    public void setDataqualityId(Long dataqualityId) {
        this.dataqualityId = dataqualityId;
    }

    public Long getAuditTaskId() {
        return auditTaskId;
    }

    public void setAuditTaskId(Long auditTaskId) {
        this.auditTaskId = auditTaskId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
    }

    public String getClassifierName() {
        return classifierName;
    }

    public void setClassifierName(String classifierName) {
        this.classifierName = classifierName;
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

}

