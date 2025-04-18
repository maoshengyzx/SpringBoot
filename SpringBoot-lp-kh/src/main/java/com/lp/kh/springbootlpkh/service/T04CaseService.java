package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T04Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 工单表(T04Case)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:58
 */
public interface T04CaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param caseId 主键
     * @return 实例对象
     */
    T04Case queryById(String caseId);

    /**
     * 分页查询
     *
     * @param t04Case     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<T04Case> queryByPage(T04Case t04Case, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t04Case 实例对象
     * @return 实例对象
     */
    T04Case insert(T04Case t04Case);

    /**
     * 修改数据
     *
     * @param t04Case 实例对象
     * @return 实例对象
     */
    T04Case update(T04Case t04Case);

    /**
     * 通过主键删除数据
     *
     * @param caseId 主键
     * @return 是否成功
     */
    boolean deleteById(String caseId);

}
