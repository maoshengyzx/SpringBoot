package com.example.luatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableRedisRepositories
public class  LuaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuaTestApplication.class, args);
    }

}
