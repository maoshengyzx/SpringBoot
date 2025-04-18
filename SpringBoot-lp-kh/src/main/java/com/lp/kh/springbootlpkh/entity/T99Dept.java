package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 部门表(T99Dept)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
public class T99Dept implements Serializable {
    private static final long serialVersionUID = -83614688052031886L;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门编号
     */
    private String deptCode;
    /**
     * 部门英文名
     */
    private String deptEname;
    /**
     * 上级部门ID
     */
    private String parentDeptId;
    /**
     * 部门层级
     */
    private Long deptLevel;
    /**
     * 是否虚拟，N：否，Y：是
     */
    private String isVirtual;
    /**
     * 废除字段，是否有效, N：否，Y：是
     */
    private String isValid;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建人ID
     */
    private String creatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人ID
     */
    private String lastModifierId;
    /**
     * 修改时间
     */
    private Date lastModifyTime;
    /**
     * 所领导id
     */
    private String leaderId;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptEname() {
        return deptEname;
    }

    public void setDeptEname(String deptEname) {
        this.deptEname = deptEname;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public Long getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Long deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

}

