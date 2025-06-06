package com.example.springbootasyncexcel.service;

import com.example.springbootasyncexcel.entity.Process;
import com.example.springbootasyncexcel.entity.ProcessDTO;

import java.util.Map;

/**
 * (Process)表服务接口
 *
 * @author makejava
 * @since 2025-06-05 15:11:23
 */
public interface ProcessService {

    /**
     * 通过ID查询单条数据
     *
     * @param processId 主键
     * @return 实例对象
     */
    Process queryById(Long processId);


    /**
     * 新增数据
     *
     * @param process 实例对象
     * @return 实例对象
     */
    Process insert(Process process);

    /**
     * 修改数据
     *
     * @param process 实例对象
     * @return 实例对象
     */
    Process update(Process process);


    void run();

    ProcessDTO startProcess();

    String downloadExcel(Long processId, Map<String, Object> params);


}
