package com.example.luatest.exception;

import lombok.Getter;

/**
 * @author Administrator
 */
@Getter
public class IPException extends Exception{

    private final String message;

    public IPException(String message) {
        this.message = message;
    }

}
