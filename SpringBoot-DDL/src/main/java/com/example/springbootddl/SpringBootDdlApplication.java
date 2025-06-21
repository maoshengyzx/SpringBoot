package com.example.springbootddl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.class})
@MapperScan("com.example.springbootddl.mapper")
public class SpringBootDdlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDdlApplication.class, args);
	}
}
