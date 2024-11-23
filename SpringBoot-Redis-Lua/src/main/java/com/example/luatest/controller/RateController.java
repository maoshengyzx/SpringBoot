package com.example.luatest.controller;

import com.example.luatest.annotition.RateLimiter;
import com.example.luatest.enum_.LimitType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/rate")
public class RateController {


    @RateLimiter(count = 100, time = 60, limitType = LimitType.IP)
    @GetMapping("/someMethod")
    @Cacheable("someMethod")
    public void someMethod(HttpServletRequest request) {
        // 方法的具体逻辑
    }
}
