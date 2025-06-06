package com.example.springbootasyncexcel.aop;

import cn.hutool.json.JSONUtil;
import com.example.springbootasyncexcel.annotation.ProcessRunner;
import com.example.springbootasyncexcel.entity.Process;
import com.example.springbootasyncexcel.entity.ProcessDTO;
import com.example.springbootasyncexcel.service.ProcessService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: ProcessRunneerAop
 * Package: com.example.springbootasyncexcel.aop
 * Description:
 *
 * @Author ms
 * @Create 2025/6/5 14:30
 * @Version 1.0
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class ProcessRunnerAop {

    @Resource
    private ProcessService processService;

    @Around("@annotation(processRunner)")
    public Object processRunner(ProceedingJoinPoint proceedingJoinPoint, ProcessRunner processRunner) throws Throwable {
        // 插入流程到数据库表
        Object proceed = proceedingJoinPoint.proceed();
        if (proceed instanceof ProcessDTO processDTO) {
            Process process = new Process();
            process.setProcessId(processDTO.getProcessId());
            process.setProcessName(processRunner.processName());
            process.setDescription(processRunner.description());
            process.setProcessType(processRunner.processType().name());
            process.setStartTime(new Date());
            process.setStatus((byte) 0);
            process.setParams(JSONUtil.toJsonStr(processDTO));
            processService.insert(process);
        }
        return proceed;
    }
}
