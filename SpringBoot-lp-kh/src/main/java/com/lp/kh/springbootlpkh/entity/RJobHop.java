package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * (RJobHop)实体类
 *
 * @author makejava
 * @since 2025-03-19 13:56:26
 */
public class RJobHop implements Serializable {
    private static final long serialVersionUID = -68669558354398066L;

    private String idJobHop;

    private String idJob;

    private String idJobentryCopyFrom;

    private String idJobentryCopyTo;

    private String enabled;

    private String evaluation;

    private String unconditional;


    public String getIdJobHop() {
        return idJobHop;
    }

    public void setIdJobHop(String idJobHop) {
        this.idJobHop = idJobHop;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdJobentryCopyFrom() {
        return idJobentryCopyFrom;
    }

    public void setIdJobentryCopyFrom(String idJobentryCopyFrom) {
        this.idJobentryCopyFrom = idJobentryCopyFrom;
    }

    public String getIdJobentryCopyTo() {
        return idJobentryCopyTo;
    }

    public void setIdJobentryCopyTo(String idJobentryCopyTo) {
        this.idJobentryCopyTo = idJobentryCopyTo;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getUnconditional() {
        return unconditional;
    }

    public void setUnconditional(String unconditional) {
        this.unconditional = unconditional;
    }

}

