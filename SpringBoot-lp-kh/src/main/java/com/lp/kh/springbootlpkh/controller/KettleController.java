package com.lp.kh.springbootlpkh.controller;

import com.lp.kh.springbootlpkh.service.RJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: KettleController
 * Package: com.lp.kh.springbootlpkh.controller
 * Description:
 *
 * @Author ms
 * @Create 2025/3/19 13:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/kettle")
public class KettleController {

    @Resource
    private RJobService rJobService;


    @GetMapping("/test")
    public void test(String filePath){
        rJobService.startJob(filePath);
    }
}
