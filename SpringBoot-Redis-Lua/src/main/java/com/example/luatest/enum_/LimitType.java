package com.example.luatest.enum_;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum LimitType {
    /**
     * 针对某一个ip进行限流
     */
    IP("IP")
    ;

    LimitType(String type) {
        this.type = type;
    }

    private final String type;

}
