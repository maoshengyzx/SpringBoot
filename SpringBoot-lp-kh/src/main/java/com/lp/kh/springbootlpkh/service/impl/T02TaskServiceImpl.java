package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T02Task;
import com.lp.kh.springbootlpkh.mapper.T02TaskMapper;
import com.lp.kh.springbootlpkh.service.T02TaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 稽核执行任务表(T02Task)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
@Service("t02TaskService")
public class T02TaskServiceImpl implements T02TaskService {
    @Resource
    private T02TaskMapper t02TaskMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public T02Task queryById(Long id) {
        return this.t02TaskMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param t02Task     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<T02Task> queryByPage(T02Task t02Task, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t02TaskMapper.count(t02Task);
        return new PageImpl<>(this.t02TaskMapper.queryAllByLimit(t02Task, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t02Task 实例对象
     * @return 实例对象
     */
    @Override
    public T02Task insert(T02Task t02Task) {
        this.t02TaskMapper.insert(t02Task);
        return t02Task;
    }

    /**
     * 修改数据
     *
     * @param t02Task 实例对象
     * @return 实例对象
     */
    @Override
    public T02Task update(T02Task t02Task) {
        this.t02TaskMapper.update(t02Task);
        return this.queryById(t02Task.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.t02TaskMapper.deleteById(id) > 0;
    }
}
