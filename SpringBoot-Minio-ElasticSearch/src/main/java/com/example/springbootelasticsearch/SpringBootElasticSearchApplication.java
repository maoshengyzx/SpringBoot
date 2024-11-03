package com.example.springbootelasticsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan("com.example.springbootelasticsearch.mapper")
public class SpringBootElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticSearchApplication.class, args);
    }

}
