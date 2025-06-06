package com.example.springbootasyncexcel.controller;

import com.example.springbootasyncexcel.entity.Process;
import com.example.springbootasyncexcel.entity.ProcessDTO;
import com.example.springbootasyncexcel.model.ProcessManager;
import com.example.springbootasyncexcel.service.ProcessService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * (Process)表控制层
 *
 * @author makejava
 * @since 2025-06-05 15:11:23
 */
@RestController
@RequestMapping("process")
public class ProcessController {
    /**
     * 服务对象
     */
    @Resource
    private ProcessService processService;


    @GetMapping("/startProcess")
    public ProcessDTO startProcess (){
       return processService.startProcess();
    }
}

