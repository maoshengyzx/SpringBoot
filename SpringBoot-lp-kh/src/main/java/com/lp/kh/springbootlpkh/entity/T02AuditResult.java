package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 稽核结果表(T02AuditResult)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:53
 */
public class T02AuditResult implements Serializable {
    private static final long serialVersionUID = 922787963320175952L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 稽核任务id
     */
    private Long taskId;
    /**
     * 规则id
     */
    private Long ruleId;
    /**
     * 稽核方法运行状态，0：失败，1、成功
     */
    private String status;
    /**
     * 失败原因
     */
    private String failureCause;
    /**
     * 检查结果，1: 正常，3：严重，2：警告
     */
    private String result;
    /**
     * 稽核sql执行结果
     */
    private String execResult;
    /**
     * 扫描数据量
     */
    private Long scanCount;
    /**
     * 结果产生时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }

    public Long getScanCount() {
        return scanCount;
    }

    public void setScanCount(Long scanCount) {
        this.scanCount = scanCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

