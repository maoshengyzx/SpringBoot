package com.example.springbootasyncexcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.*;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.example.springbootasyncexcel.mapper")
@EnableScheduling
public class SpringBootAsyncExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAsyncExcelApplication.class, args);
    }

}
