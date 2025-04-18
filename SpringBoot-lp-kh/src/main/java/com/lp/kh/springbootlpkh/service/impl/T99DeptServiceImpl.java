package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T99Dept;
import com.lp.kh.springbootlpkh.mapper.T99DeptMapper;
import com.lp.kh.springbootlpkh.service.T99DeptService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 部门表(T99Dept)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
@Service("t99DeptService")
public class T99DeptServiceImpl implements T99DeptService {
    @Resource
    private T99DeptMapper t99DeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public T99Dept queryById(String deptId) {
        return this.t99DeptMapper.queryById(deptId);
    }

    /**
     * 分页查询
     *
     * @param t99Dept     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<T99Dept> queryByPage(T99Dept t99Dept, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t99DeptMapper.count(t99Dept);
        return new PageImpl<>(this.t99DeptMapper.queryAllByLimit(t99Dept, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t99Dept 实例对象
     * @return 实例对象
     */
    @Override
    public T99Dept insert(T99Dept t99Dept) {
        this.t99DeptMapper.insert(t99Dept);
        return t99Dept;
    }

    /**
     * 修改数据
     *
     * @param t99Dept 实例对象
     * @return 实例对象
     */
    @Override
    public T99Dept update(T99Dept t99Dept) {
        this.t99DeptMapper.update(t99Dept);
        return this.queryById(t99Dept.getDeptId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deptId) {
        return this.t99DeptMapper.deleteById(deptId) > 0;
    }
}
