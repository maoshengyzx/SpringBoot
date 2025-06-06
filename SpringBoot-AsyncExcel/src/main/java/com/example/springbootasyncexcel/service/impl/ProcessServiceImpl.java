package com.example.springbootasyncexcel.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.example.springbootasyncexcel.annotation.ProcessHandle;
import com.example.springbootasyncexcel.annotation.ProcessRunner;
import com.example.springbootasyncexcel.entity.Process;
import com.example.springbootasyncexcel.entity.ProcessDTO;
import com.example.springbootasyncexcel.mapper.ProcessMapper;
import com.example.springbootasyncexcel.model.ProcessManager;
import com.example.springbootasyncexcel.model.ProcessType;
import com.example.springbootasyncexcel.service.ProcessService;
import com.example.springbootasyncexcel.utils.MinioUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * (Process)表服务实现类
 *
 * @author makejava
 * @since 2025-06-05 15:11:23
 */
@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {
    @Resource
    private ProcessMapper processMapper;

    @Resource
    private MinioUtils minioUtils;




    /**
     * 通过ID查询单条数据
     *
     * @param processId 主键
     * @return 实例对象
     */
    @Override
    public Process queryById(Long processId) {
        return this.processMapper.queryById(processId);
    }


    /**
     * 新增数据
     *
     * @param process 实例对象
     * @return 实例对象
     */
    @Override
    public Process insert(Process process) {
        this.processMapper.insert(process);
        return process;
    }

    /**
     * 修改数据
     *
     * @param process 实例对象
     * @return 实例对象
     */
    @Override
    public Process update(Process process) {
        this.processMapper.update(process);
        return this.queryById(process.getProcessId());
    }


    @Override
    public void run() {
        // 查询出之前未开始的任务
        Process process = processMapper.listByStatus(0);
        if (process == null) {
            return;
        }
        // 开始执行任务
        HashMap<String, Object> map = new HashMap<>();
        map.put("processId", process.getProcessId());
        HttpUtil.get("http://127.0.0.1:8080/processManager/startProcess?processId=" + process.getProcessId());
    }

    @Override
    @ProcessRunner(processName = "异步导出excel", processType = ProcessType.EXCEL_TYPE)
    public ProcessDTO startProcess() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", 1L);
        Process process = new Process();
        process.setProcessName("异步导出excel");
        hashMap.put("bean", process);
        return ProcessDTO.createProcessDTO(IdUtil.getSnowflakeNextId(), hashMap);
    }

    @Override
    @ProcessHandle(value = "异步导出excel", processType = ProcessType.EXCEL_TYPE)
    public String downloadExcel(Long processId, Map<String, Object> data) {
        // 这里模拟下载excel文件，我就直接找一个本地excel文件，不使用poi来生成excel文件了
        log.info("userId:{}", data.get("userId"));
        log.info("bean:{}", data.get("bean"));
        File file = FileUtil.file("C:\\Users\\Administrator\\Downloads\\1.xlsx");
        ProcessManager.handleProcess(processId, true);
        try {
            minioUtils.putObject("test", file.getName(), FileUtil.getInputStream(file), FileUtil.size(file), FileUtil.getType(file));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return minioUtils.getObjectUrl("test", file.getName());
    }


}
