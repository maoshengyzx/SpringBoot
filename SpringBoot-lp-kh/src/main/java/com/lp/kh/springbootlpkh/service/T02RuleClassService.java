package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T02RuleClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 质量维度细类信息表(T02RuleClass)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
public interface T02RuleClassService {

    /**
     * 通过ID查询单条数据
     *
     * @param classNo 主键
     * @return 实例对象
     */
    T02RuleClass queryById(String classNo);

    /**
     * 分页查询
     *
     * @param t02RuleClass 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<T02RuleClass> queryByPage(T02RuleClass t02RuleClass, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t02RuleClass 实例对象
     * @return 实例对象
     */
    T02RuleClass insert(T02RuleClass t02RuleClass);

    /**
     * 修改数据
     *
     * @param t02RuleClass 实例对象
     * @return 实例对象
     */
    T02RuleClass update(T02RuleClass t02RuleClass);

    /**
     * 通过主键删除数据
     *
     * @param classNo 主键
     * @return 是否成功
     */
    boolean deleteById(String classNo);

}
