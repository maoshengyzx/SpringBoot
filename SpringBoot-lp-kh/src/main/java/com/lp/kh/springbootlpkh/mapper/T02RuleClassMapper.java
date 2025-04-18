package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T02RuleClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 质量维度细类信息表(T02RuleClass)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:56
 */
public interface T02RuleClassMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param classNo 主键
     * @return 实例对象
     */
    T02RuleClass queryById(String classNo);

    /**
     * 查询指定行数据
     *
     * @param t02RuleClass 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<T02RuleClass> queryAllByLimit(T02RuleClass t02RuleClass, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t02RuleClass 查询条件
     * @return 总行数
     */
    long count(T02RuleClass t02RuleClass);

    /**
     * 新增数据
     *
     * @param t02RuleClass 实例对象
     * @return 影响行数
     */
    int insert(T02RuleClass t02RuleClass);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T02RuleClass> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T02RuleClass> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T02RuleClass> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T02RuleClass> entities);

    /**
     * 修改数据
     *
     * @param t02RuleClass 实例对象
     * @return 影响行数
     */
    int update(T02RuleClass t02RuleClass);

    /**
     * 通过主键删除数据
     *
     * @param classNo 主键
     * @return 影响行数
     */
    int deleteById(String classNo);

}

