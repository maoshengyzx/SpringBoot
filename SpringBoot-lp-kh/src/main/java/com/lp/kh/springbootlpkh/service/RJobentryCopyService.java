package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.RJobentryCopy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RJobentryCopy)表服务接口
 *
 * @author makejava
 * @since 2025-03-19 13:56:29
 */
public interface RJobentryCopyService {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryCopy 主键
     * @return 实例对象
     */
    RJobentryCopy queryById(String idJobentryCopy);

    /**
     * 分页查询
     *
     * @param rJobentryCopy 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<RJobentryCopy> queryByPage(RJobentryCopy rJobentryCopy, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rJobentryCopy 实例对象
     * @return 实例对象
     */
    RJobentryCopy insert(RJobentryCopy rJobentryCopy);

    /**
     * 修改数据
     *
     * @param rJobentryCopy 实例对象
     * @return 实例对象
     */
    RJobentryCopy update(RJobentryCopy rJobentryCopy);

    /**
     * 通过主键删除数据
     *
     * @param idJobentryCopy 主键
     * @return 是否成功
     */
    boolean deleteById(String idJobentryCopy);

}
