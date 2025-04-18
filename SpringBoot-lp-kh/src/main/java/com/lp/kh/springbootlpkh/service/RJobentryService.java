package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.RJobentry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RJobentry)表服务接口
 *
 * @author makejava
 * @since 2025-03-19 13:56:27
 */
public interface RJobentryService {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentry 主键
     * @return 实例对象
     */
    RJobentry queryById(String idJobentry);

    /**
     * 分页查询
     *
     * @param rJobentry   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<RJobentry> queryByPage(RJobentry rJobentry, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rJobentry 实例对象
     * @return 实例对象
     */
    RJobentry insert(RJobentry rJobentry);

    /**
     * 修改数据
     *
     * @param rJobentry 实例对象
     * @return 实例对象
     */
    RJobentry update(RJobentry rJobentry);

    /**
     * 通过主键删除数据
     *
     * @param idJobentry 主键
     * @return 是否成功
     */
    boolean deleteById(String idJobentry);

}
