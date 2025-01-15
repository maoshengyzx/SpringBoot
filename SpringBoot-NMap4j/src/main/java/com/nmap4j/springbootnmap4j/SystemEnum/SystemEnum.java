package com.nmap4j.springbootnmap4j.SystemEnum;


/**
 * @author ms
 * @Date 2025/1/10 16:37
 */
public enum SystemEnum {

    WINDOWS("Windows"),

    LINUX("Linux");
    private String value;

    SystemEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
