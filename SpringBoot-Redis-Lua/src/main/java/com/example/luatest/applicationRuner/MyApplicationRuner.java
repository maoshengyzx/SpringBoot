package com.example.luatest.applicationRuner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 应用启动后执行的逻辑
 * @author Administrator
 */
@Component
public class MyApplicationRuner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationRuner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Application started with arguments: {}", args);
        LOGGER.info("ApplicationRuner");
    }

    @PostConstruct
    public void init() {
        LOGGER.info("ApplicationRuner init");
    }

}
