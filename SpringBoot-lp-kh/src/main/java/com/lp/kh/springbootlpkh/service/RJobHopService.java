package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.RJobHop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RJobHop)表服务接口
 *
 * @author makejava
 * @since 2025-03-19 13:56:27
 */
public interface RJobHopService {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobHop 主键
     * @return 实例对象
     */
    RJobHop queryById(String idJobHop);

    /**
     * 分页查询
     *
     * @param rJobHop     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<RJobHop> queryByPage(RJobHop rJobHop, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rJobHop 实例对象
     * @return 实例对象
     */
    RJobHop insert(RJobHop rJobHop);

    /**
     * 修改数据
     *
     * @param rJobHop 实例对象
     * @return 实例对象
     */
    RJobHop update(RJobHop rJobHop);

    /**
     * 通过主键删除数据
     *
     * @param idJobHop 主键
     * @return 是否成功
     */
    boolean deleteById(String idJobHop);

}
