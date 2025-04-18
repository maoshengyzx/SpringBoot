package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (RJob)实体类
 *
 * @author makejava
 * @since 2025-03-19 13:56:25
 */
public class RJob implements Serializable {
    private static final long serialVersionUID = 726319985311672130L;

    private String idJob;

    private String idDirectory;

    private String name;

    private String description;

    private String extendedDescription;

    private String jobVersion;

    private String jobStatus;

    private String idDatabaseLog;

    private String tableNameLog;

    private String createdUser;

    private Date createdDate;

    private String modifiedUser;

    private Date modifiedDate;

    private String useBatchId;

    private String passBatchId;

    private String useLogfield;

    private String sharedFile;


    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdDirectory() {
        return idDirectory;
    }

    public void setIdDirectory(String idDirectory) {
        this.idDirectory = idDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtendedDescription() {
        return extendedDescription;
    }

    public void setExtendedDescription(String extendedDescription) {
        this.extendedDescription = extendedDescription;
    }

    public String getJobVersion() {
        return jobVersion;
    }

    public void setJobVersion(String jobVersion) {
        this.jobVersion = jobVersion;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getIdDatabaseLog() {
        return idDatabaseLog;
    }

    public void setIdDatabaseLog(String idDatabaseLog) {
        this.idDatabaseLog = idDatabaseLog;
    }

    public String getTableNameLog() {
        return tableNameLog;
    }

    public void setTableNameLog(String tableNameLog) {
        this.tableNameLog = tableNameLog;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getUseBatchId() {
        return useBatchId;
    }

    public void setUseBatchId(String useBatchId) {
        this.useBatchId = useBatchId;
    }

    public String getPassBatchId() {
        return passBatchId;
    }

    public void setPassBatchId(String passBatchId) {
        this.passBatchId = passBatchId;
    }

    public String getUseLogfield() {
        return useLogfield;
    }

    public void setUseLogfield(String useLogfield) {
        this.useLogfield = useLogfield;
    }

    public String getSharedFile() {
        return sharedFile;
    }

    public void setSharedFile(String sharedFile) {
        this.sharedFile = sharedFile;
    }

}

