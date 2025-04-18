package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T04Case;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 工单表(T04Case)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
public interface T04CaseMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param caseId 主键
     * @return 实例对象
     */
    T04Case queryById(String caseId);

    /**
     * 查询指定行数据
     *
     * @param t04Case  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<T04Case> queryAllByLimit(T04Case t04Case, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t04Case 查询条件
     * @return 总行数
     */
    long count(T04Case t04Case);

    /**
     * 新增数据
     *
     * @param t04Case 实例对象
     * @return 影响行数
     */
    int insert(T04Case t04Case);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T04Case> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T04Case> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T04Case> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T04Case> entities);

    /**
     * 修改数据
     *
     * @param t04Case 实例对象
     * @return 影响行数
     */
    int update(T04Case t04Case);

    /**
     * 通过主键删除数据
     *
     * @param caseId 主键
     * @return 影响行数
     */
    int deleteById(String caseId);

}

