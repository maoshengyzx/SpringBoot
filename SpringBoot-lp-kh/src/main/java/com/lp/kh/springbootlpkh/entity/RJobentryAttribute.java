package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * (RJobentryAttribute)实体类
 *
 * @author makejava
 * @since 2025-03-19 13:56:28
 */
public class RJobentryAttribute implements Serializable {
    private static final long serialVersionUID = 324445844144966034L;

    private String idJobentryAttribute;

    private String idJob;

    private String idJobentry;

    private String nr;

    private String code;

    private String valueNum;

    private String valueStr;


    public String getIdJobentryAttribute() {
        return idJobentryAttribute;
    }

    public void setIdJobentryAttribute(String idJobentryAttribute) {
        this.idJobentryAttribute = idJobentryAttribute;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdJobentry() {
        return idJobentry;
    }

    public void setIdJobentry(String idJobentry) {
        this.idJobentry = idJobentry;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValueNum() {
        return valueNum;
    }

    public void setValueNum(String valueNum) {
        this.valueNum = valueNum;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

}

