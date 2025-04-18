package com.example.springbootmschema.entity;

public class DynamicAttributeResult {
    private Long instanceId;
    private String attStore;
    private String attValue;


    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getAttStore() {
        return attStore;
    }

    public void setAttStore(String attStore) {
        this.attStore = attStore;
    }

    public String getAttValue() {
        return attValue;
    }

    public void setAttValue(String attValue) {
        this.attValue = attValue;
    }
}