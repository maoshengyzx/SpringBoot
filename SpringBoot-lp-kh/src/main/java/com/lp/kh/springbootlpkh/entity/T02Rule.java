package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 专项规则关联表(T02Rule)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:55
 */
public class T02Rule implements Serializable {
    private static final long serialVersionUID = -81997475494096114L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 专项id
     */
    private Long projectId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则描述
     */
    private String remark;
    /**
     * 权重
     */
    private String weight;
    /**
     * 规则维度
     */
    private String ruleDim;
    /**
     * 质量规则分类编号, 对应t02_rule_class表中的class_no
     */
    private String ruleClassNo;
    /**
     * 创建类型，1：模板创建，2：自定义创建
     */
    private String createType;
    /**
     * 数据源id
     */
    private String datasourceId;
    /**
     * 数据源用户
     */
    private String userName;
    /**
     * 稽核的Schema
     */
    private String schemaName;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 执行sql
     */
    private String execSql;
    /**
     * 复核sql， 用于查询异常数据
     */
    private String checkSql;
    /**
     * 用于查询总数
     */
    private String countSql;
    /**
     * 执行模式，online：在线执行，offline：离线执行
     */
    private String execMode;
    /**
     * 影响范围
     */
    private String influenceSphere;
    /**
     * 异常告警方式，1：邮件
     */
    private String alarmType;
    /**
     * 告警级别， 字典配置，1: 正常，2：警告，3：严重，4：错误
     */
    private String alarmLevel;
    /**
     * 正常阈值, 0到100之间取值
     */
    private String normalVal;
    /**
     * 警告阈值, 0到100之间取值
     */
    private String warnVal;
    /**
     * 负责人id
     */
    private String managerId;
    /**
     * 创建人id
     */
    private String creatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改人
     */
    private String lastModifierId;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
    /**
     * 错误结果保留天数
     */
    private Long errorDataRetainDay;
    /**
     * 错误数据保留条数
     */
    private Long errorDataRetainCount;
    /**
     * 是否推送工单，0不推送 1推送
     */
    private String pushTodo;
    /**
     * 是否预处理脚本： 0 是,1不是
     */
    private String preprocess;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRuleDim() {
        return ruleDim;
    }

    public void setRuleDim(String ruleDim) {
        this.ruleDim = ruleDim;
    }

    public String getRuleClassNo() {
        return ruleClassNo;
    }

    public void setRuleClassNo(String ruleClassNo) {
        this.ruleClassNo = ruleClassNo;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getExecSql() {
        return execSql;
    }

    public void setExecSql(String execSql) {
        this.execSql = execSql;
    }

    public String getCheckSql() {
        return checkSql;
    }

    public void setCheckSql(String checkSql) {
        this.checkSql = checkSql;
    }

    public String getCountSql() {
        return countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }

    public String getExecMode() {
        return execMode;
    }

    public void setExecMode(String execMode) {
        this.execMode = execMode;
    }

    public String getInfluenceSphere() {
        return influenceSphere;
    }

    public void setInfluenceSphere(String influenceSphere) {
        this.influenceSphere = influenceSphere;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getNormalVal() {
        return normalVal;
    }

    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    public String getWarnVal() {
        return warnVal;
    }

    public void setWarnVal(String warnVal) {
        this.warnVal = warnVal;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifierId() {
        return lastModifierId;
    }

    public void setLastModifierId(String lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getErrorDataRetainDay() {
        return errorDataRetainDay;
    }

    public void setErrorDataRetainDay(Long errorDataRetainDay) {
        this.errorDataRetainDay = errorDataRetainDay;
    }

    public Long getErrorDataRetainCount() {
        return errorDataRetainCount;
    }

    public void setErrorDataRetainCount(Long errorDataRetainCount) {
        this.errorDataRetainCount = errorDataRetainCount;
    }

    public String getPushTodo() {
        return pushTodo;
    }

    public void setPushTodo(String pushTodo) {
        this.pushTodo = pushTodo;
    }

    public String getPreprocess() {
        return preprocess;
    }

    public void setPreprocess(String preprocess) {
        this.preprocess = preprocess;
    }

}

