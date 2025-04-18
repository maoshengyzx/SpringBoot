package com.example.springbootmschema;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootmschema.mapper")
public class SpringBootMSchemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMSchemaApplication.class, args);
	}

}
