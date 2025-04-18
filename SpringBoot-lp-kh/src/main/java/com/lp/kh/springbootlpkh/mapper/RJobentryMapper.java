package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.RJobentry;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (RJobentry)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-19 13:56:27
 */
public interface RJobentryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentry 主键
     * @return 实例对象
     */
    RJobentry queryById(String idJobentry);

    /**
     * 查询指定行数据
     *
     * @param rJobentry 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<RJobentry> queryAllByLimit(RJobentry rJobentry, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rJobentry 查询条件
     * @return 总行数
     */
    long count(RJobentry rJobentry);

    /**
     * 新增数据
     *
     * @param rJobentry 实例对象
     * @return 影响行数
     */
    int insert(RJobentry rJobentry);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJobentry> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RJobentry> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJobentry> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RJobentry> entities);

    /**
     * 修改数据
     *
     * @param rJobentry 实例对象
     * @return 影响行数
     */
    int update(RJobentry rJobentry);

    /**
     * 通过主键删除数据
     *
     * @param idJobentry 主键
     * @return 影响行数
     */
    int deleteById(String idJobentry);

}

