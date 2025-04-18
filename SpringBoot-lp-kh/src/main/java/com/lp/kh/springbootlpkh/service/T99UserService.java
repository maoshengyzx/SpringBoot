package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T99User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户表(T99User)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:09:00
 */
public interface T99UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    T99User queryById(String userId);

    /**
     * 分页查询
     *
     * @param t99User     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<T99User> queryByPage(T99User t99User, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t99User 实例对象
     * @return 实例对象
     */
    T99User insert(T99User t99User);

    /**
     * 修改数据
     *
     * @param t99User 实例对象
     * @return 实例对象
     */
    T99User update(T99User t99User);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);

}
