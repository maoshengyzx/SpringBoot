package com.example.springbootasyncexcel.controller;

import com.example.springbootasyncexcel.model.ProcessManager;
import com.example.springbootasyncexcel.service.ProcessService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ProcessManagerController
 * Package: com.example.springbootasyncexcel.controller
 * Description:
 *
 * @Author ms
 * @Create 2025/6/5 15:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/processManager")
public class ProcessManagerController {


    @Resource
    private ProcessManager processManager;

    @GetMapping("/startProcess")
    public void  startProcess(@RequestParam(value = "processId") Long processId) {
        processManager.startProcess(processId);
    }
}
