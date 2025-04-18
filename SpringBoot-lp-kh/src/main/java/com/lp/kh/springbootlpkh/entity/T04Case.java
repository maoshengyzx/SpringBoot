package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 工单表(T04Case)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
public class T04Case implements Serializable {
    private static final long serialVersionUID = -69882673400213905L;

    private String caseId;
    /**
     * 工单编号
     */
    private String caseNumber;
    /**
     * 工单名称
     */
    private String caseName;
    /**
     * 工单类型
     */
    private String caseType;
    /**
     * 工单内容
     */
    private String content;
    /**
     * 工单期望结束时间
     */
    private Date endTime;
    /**
     * 工单状态（0：进行中，1：已关闭，2：待发起）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 关闭时间
     */
    private Date closeTime;
    /**
     * 流程实例id
     */
    private String processId;
    /**
     * 关联业务id
     */
    private String businessId;
    /**
     * 紧急程度,0一般 1紧急
     */
    private String emergencyLevel;

    private String attachmentId;
    /**
     * 创建类型，1手动创建 2系统创建
     */
    private String createType;


    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setEmergencyLevel(String emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

}

