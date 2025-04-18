package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * (RJobentryType)实体类
 *
 * @author makejava
 * @since 2025-03-19 13:56:29
 */
public class RJobentryType implements Serializable {
    private static final long serialVersionUID = 642419505724715924L;

    private String idJobentryType;

    private String code;

    private String description;


    public String getIdJobentryType() {
        return idJobentryType;
    }

    public void setIdJobentryType(String idJobentryType) {
        this.idJobentryType = idJobentryType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

