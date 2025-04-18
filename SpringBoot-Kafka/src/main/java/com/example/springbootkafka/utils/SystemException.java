package com.example.springbootkafka.utils;

import lombok.EqualsAndHashCode;
import org.slf4j.helpers.MessageFormatter;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = false)
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private Map<String, Object> extras;

    public SystemException addExtras(String key, Object value) {
        if (this.extras == null) {
            this.extras = new HashMap<>(0);
        }
        this.extras.put(key, value);
        return this;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }



    public SystemException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public SystemException(String message) {
        super(message);
        this.code = 10004;
        this.msg = message;
    }

    public SystemException(Exception e) {
        super(e.getMessage());
        this.code = 10004;
        this.msg = getMessage();
    }

    public static SystemException wrap(String messagePattern, Object... arguments) {
        String message = MessageFormatter.arrayFormat(messagePattern, arguments).getMessage();
        return new SystemException(message);
    }

    public static SystemException wrap(int code, String messagePattern, Object... arguments) {
        String message = MessageFormatter.arrayFormat(messagePattern, arguments).getMessage();
        return new SystemException(code, message);
    }

    public static SystemException wrap(Exception e) {
        if (e instanceof SystemException) {
            return (SystemException) e;
        }
        return new SystemException(e);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}