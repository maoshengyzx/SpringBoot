package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.RJobentryType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (RJobentryType)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-19 13:56:29
 */
public interface RJobentryTypeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryType 主键
     * @return 实例对象
     */
    RJobentryType queryById(String idJobentryType);

    /**
     * 查询指定行数据
     *
     * @param rJobentryType 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<RJobentryType> queryAllByLimit(RJobentryType rJobentryType, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rJobentryType 查询条件
     * @return 总行数
     */
    long count(RJobentryType rJobentryType);

    /**
     * 新增数据
     *
     * @param rJobentryType 实例对象
     * @return 影响行数
     */
    int insert(RJobentryType rJobentryType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJobentryType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RJobentryType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJobentryType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RJobentryType> entities);

    /**
     * 修改数据
     *
     * @param rJobentryType 实例对象
     * @return 影响行数
     */
    int update(RJobentryType rJobentryType);

    /**
     * 通过主键删除数据
     *
     * @param idJobentryType 主键
     * @return 影响行数
     */
    int deleteById(String idJobentryType);

    RJobentryType queryByCode(String type);
}

