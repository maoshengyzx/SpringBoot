package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.RJobentryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RJobentryType)表服务接口
 *
 * @author makejava
 * @since 2025-03-19 13:56:29
 */
public interface RJobentryTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryType 主键
     * @return 实例对象
     */
    RJobentryType queryById(String idJobentryType);

    /**
     * 分页查询
     *
     * @param rJobentryType 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<RJobentryType> queryByPage(RJobentryType rJobentryType, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rJobentryType 实例对象
     * @return 实例对象
     */
    RJobentryType insert(RJobentryType rJobentryType);

    /**
     * 修改数据
     *
     * @param rJobentryType 实例对象
     * @return 实例对象
     */
    RJobentryType update(RJobentryType rJobentryType);

    /**
     * 通过主键删除数据
     *
     * @param idJobentryType 主键
     * @return 是否成功
     */
    boolean deleteById(String idJobentryType);

    RJobentryType queryByCode(String type);
}
