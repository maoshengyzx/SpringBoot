package com.example.springbootajcaptcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springbootajcaptcha","com.anji.captcha"})
public class SpringBootAjCaptchaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAjCaptchaApplication.class, args);
    }

}
