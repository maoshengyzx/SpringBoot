package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T99DeptUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 部门用户关系表(T99DeptUser)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
public interface T99DeptUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    T99DeptUser queryById(String deptId);

    /**
     * 分页查询
     *
     * @param t99DeptUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<T99DeptUser> queryByPage(T99DeptUser t99DeptUser, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t99DeptUser 实例对象
     * @return 实例对象
     */
    T99DeptUser insert(T99DeptUser t99DeptUser);

    /**
     * 修改数据
     *
     * @param t99DeptUser 实例对象
     * @return 实例对象
     */
    T99DeptUser update(T99DeptUser t99DeptUser);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    boolean deleteById(String deptId);

}
