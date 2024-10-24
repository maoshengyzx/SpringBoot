package com.example.luatest.annotition;


import com.example.luatest.enum_.LimitType;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Target(ElementType.METHOD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 限流类型
     *
     * @return
     */
    LimitType limitType() default LimitType.IP;

    /**
     * 限流key
     *
     * @return
     */
    String key() default "";

    /**
     * 限流时间
     *
     * @return
     */
    int time() default 60;

    /**
     * 限流次数
     *
     * @return
     */
    int count() default 100;
}
