package com.example.springbootddl.controller;

import com.example.springbootddl.service.DdlTemplateService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DdlController
 * Package: com.example.springbootddl.controller
 * Description:
 *
 * @Author ms
 * @Create 2025/6/21 10:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/ddl")
public class DdlController {

    @Resource
    private DdlTemplateService ddlTemplateService;

    /**
     * 单张表的ddl语句创建
     *
     * @return
     */
    @PostMapping("/createDdl")
    public void createDdl(@RequestParam("version") String version,@RequestParam("dbType") String dbType) {
        ddlTemplateService.createDdl(version, dbType);
    }
}