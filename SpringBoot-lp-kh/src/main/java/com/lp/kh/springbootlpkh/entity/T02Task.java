package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 稽核执行任务表(T02Task)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
public class T02Task implements Serializable {
    private static final long serialVersionUID = -67114031404622838L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 调度作业的id
     */
    private Long jobId;
    /**
     * 调度表达式
     */
    private String cron;
    /**
     * 执行sql
     */
    private String execSql;
    /**
     * 复核sql
     */
    private String checkSql;
    /**
     * 用于查询总数
     */
    private String countSql;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 任务执行状态，1：准备就绪，2：执行中。3：执行成功，4：执行失败
     */
    private String status;
    /**
     * 运行时间，毫秒数
     */
    private Long runTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

}

