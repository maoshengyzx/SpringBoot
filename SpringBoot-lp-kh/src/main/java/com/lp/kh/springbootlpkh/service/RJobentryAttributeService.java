package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.RJobentryAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (RJobentryAttribute)表服务接口
 *
 * @author makejava
 * @since 2025-03-19 13:56:28
 */
public interface RJobentryAttributeService {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryAttribute 主键
     * @return 实例对象
     */
    RJobentryAttribute queryById(String idJobentryAttribute);

    /**
     * 分页查询
     *
     * @param rJobentryAttribute 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    Page<RJobentryAttribute> queryByPage(RJobentryAttribute rJobentryAttribute, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rJobentryAttribute 实例对象
     * @return 实例对象
     */
    RJobentryAttribute insert(RJobentryAttribute rJobentryAttribute);

    /**
     * 修改数据
     *
     * @param rJobentryAttribute 实例对象
     * @return 实例对象
     */
    RJobentryAttribute update(RJobentryAttribute rJobentryAttribute);

    /**
     * 通过主键删除数据
     *
     * @param idJobentryAttribute 主键
     * @return 是否成功
     */
    boolean deleteById(String idJobentryAttribute);

    void insertBatch(List<RJobentryAttribute> rJobentryAttributeList);
}
