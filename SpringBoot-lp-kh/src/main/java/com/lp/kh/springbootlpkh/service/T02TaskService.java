package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T02Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 稽核执行任务表(T02Task)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
public interface T02TaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    T02Task queryById(Long id);

    /**
     * 分页查询
     *
     * @param t02Task     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<T02Task> queryByPage(T02Task t02Task, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t02Task 实例对象
     * @return 实例对象
     */
    T02Task insert(T02Task t02Task);

    /**
     * 修改数据
     *
     * @param t02Task 实例对象
     * @return 实例对象
     */
    T02Task update(T02Task t02Task);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
