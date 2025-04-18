package com.example.springbootmschema.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (T99Datasource)实体类
 *
 * @author makejava
 * @since 2025-04-12 17:44:59
 */

public class Datasource implements Serializable {
    private static final long serialVersionUID = 526371999637265529L;

    private String datasourceId;

    private String datasourceName;

    private String description;

    private String modeId;

    private String toolkitVersion;

    private String type;

    private String creatorId;

    private Date createTime;

    private String lastModifierId;

    private Date lastModifyTime;

    private String authMode;

    private String agentNodes;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getToolkitVersion() {
        return toolkitVersion;
    }

    public void setToolkitVersion(String toolkitVersion) {
        this.toolkitVersion = toolkitVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAuthMode() {
        return authMode;
    }

    public void setAuthMode(String authMode) {
        this.authMode = authMode;
    }

    public String getAgentNodes() {
        return agentNodes;
    }

    public void setAgentNodes(String agentNodes) {
        this.agentNodes = agentNodes;
    }
}

