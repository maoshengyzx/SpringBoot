package com.example.springbootmschema.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (T01Instance)实体类
 *
 * @author makejava
 * @since 2025-04-12 14:04:16
 */

public class Instance implements Serializable {
    private static final long serialVersionUID = 857459008466677604L;

    private Long instanceId;

    private String instanceCode;

    private String instanceName;

    private String classifierId;

    private Long parentId;

    private String namespace;

    private Long version;

    private Long startTime;

    private String relType;

    private String isValid;

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}

