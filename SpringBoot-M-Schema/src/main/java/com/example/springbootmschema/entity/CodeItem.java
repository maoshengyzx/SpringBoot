package com.example.springbootmschema.entity;

/**
 * ClassName: CodeItem
 * Package: com.example.springbootmschema.entity
 * Description:
 *
 * @Author ms
 * @Create 2025/4/15 9:32
 * @Version 1.0
 */
public class CodeItem {

    private Long instanceId;

    private String codeValue;

    private String codeNum;

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }
}
