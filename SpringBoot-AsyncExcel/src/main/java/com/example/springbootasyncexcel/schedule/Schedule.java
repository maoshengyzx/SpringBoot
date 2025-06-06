package com.example.springbootasyncexcel.schedule;

import com.example.springbootasyncexcel.service.ProcessService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ClassName: Scheddule
 * Package: com.example.springbootasyncexcel.schedule
 * Description:
 *
 * @Author ms
 * @Create 2025/6/5 15:58
 * @Version 1.0
 */
@Component
@Slf4j
public class Schedule {


    @Resource
    private ProcessService processService;

    /**
     * 定时任务
     * 表示上一次执行完毕后,间隔2秒执行下一次
     */
    @Scheduled(fixedDelay = 2000)
    public void run() {
        log.info("开始执行定时任务");
        processService.run();
    }
}
