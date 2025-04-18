package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T99Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 部门表(T99Dept)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
public interface T99DeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    T99Dept queryById(String deptId);

    /**
     * 分页查询
     *
     * @param t99Dept     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<T99Dept> queryByPage(T99Dept t99Dept, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t99Dept 实例对象
     * @return 实例对象
     */
    T99Dept insert(T99Dept t99Dept);

    /**
     * 修改数据
     *
     * @param t99Dept 实例对象
     * @return 实例对象
     */
    T99Dept update(T99Dept t99Dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    boolean deleteById(String deptId);

}
