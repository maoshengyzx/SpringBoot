package com.lp.kh.springbootlpkh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan("com.lp.kh.springbootlpkh.mapper")
public class SpringBootLpKhApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLpKhApplication.class, args);
    }


    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                5,
                10,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(64),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
