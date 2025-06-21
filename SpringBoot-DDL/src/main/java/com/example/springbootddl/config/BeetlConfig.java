package com.example.springbootddl.config;

import com.example.springbootddl.service.DdlTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FreeMarkerConfig
 * Package: com.example.springbootddl.config
 * Description:
 *
 * @Author ms
 * @Create 2025/6/21 10:52
 * @Version 1.0
 */
@Configuration
@Slf4j
public class BeetlConfig {


    @Bean
    public GroupTemplate groupTemplate() throws Exception {
        StringTemplateResourceLoader loader = new StringTemplateResourceLoader();
        org.beetl.core.Configuration cfg = org.beetl.core.Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(loader, cfg);
        return gt;
    }
}
