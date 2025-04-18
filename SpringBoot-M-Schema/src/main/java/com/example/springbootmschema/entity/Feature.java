package com.example.springbootmschema.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Feature)实体类
 *
 * @author makejava
 * @since 2025-04-12 18:09:45
 */
public class Feature implements Serializable {
    private static final long serialVersionUID = -85615504892024145L;

    private String attId;

    private String attCode;

    private String classifierId;

    private String attName;

    private String inheritFlag;

    private String isRead;

    private String isNull;

    private String isCompare;

    private String isShow;

    private String isKey;

    private String isClob;

    private String isHighlight;

    private String isSingleLine;

    private String isLink;

    private String attrType;

    private String defaultValue;

    private String datatypeId;

    private Long length;

    private Long maxLength;

    private Long minLength;

    private String dataPrecision;

    private String combId;

    private String description;

    private String regularPattern;

    private String validateFailureTip;

    private Long attOrder;

    private String attStore;


    public String getAttId() {
        return attId;
    }

    public void setAttId(String attId) {
        this.attId = attId;
    }

    public String getAttCode() {
        return attCode;
    }

    public void setAttCode(String attCode) {
        this.attCode = attCode;
    }

    public String getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
    }

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName;
    }

    public String getInheritFlag() {
        return inheritFlag;
    }

    public void setInheritFlag(String inheritFlag) {
        this.inheritFlag = inheritFlag;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getIsCompare() {
        return isCompare;
    }

    public void setIsCompare(String isCompare) {
        this.isCompare = isCompare;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    public String getIsClob() {
        return isClob;
    }

    public void setIsClob(String isClob) {
        this.isClob = isClob;
    }

    public String getIsHighlight() {
        return isHighlight;
    }

    public void setIsHighlight(String isHighlight) {
        this.isHighlight = isHighlight;
    }

    public String getIsSingleLine() {
        return isSingleLine;
    }

    public void setIsSingleLine(String isSingleLine) {
        this.isSingleLine = isSingleLine;
    }

    public String getIsLink() {
        return isLink;
    }

    public void setIsLink(String isLink) {
        this.isLink = isLink;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDatatypeId() {
        return datatypeId;
    }

    public void setDatatypeId(String datatypeId) {
        this.datatypeId = datatypeId;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public Long getMinLength() {
        return minLength;
    }

    public void setMinLength(Long minLength) {
        this.minLength = minLength;
    }

    public String getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(String dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public String getCombId() {
        return combId;
    }

    public void setCombId(String combId) {
        this.combId = combId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegularPattern() {
        return regularPattern;
    }

    public void setRegularPattern(String regularPattern) {
        this.regularPattern = regularPattern;
    }

    public String getValidateFailureTip() {
        return validateFailureTip;
    }

    public void setValidateFailureTip(String validateFailureTip) {
        this.validateFailureTip = validateFailureTip;
    }

    public Long getAttOrder() {
        return attOrder;
    }

    public void setAttOrder(Long attOrder) {
        this.attOrder = attOrder;
    }

    public String getAttStore() {
        return attStore;
    }

    public void setAttStore(String attStore) {
        this.attStore = attStore;
    }
}

