package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.RJobentryCopy;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (RJobentryCopy)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-19 13:56:28
 */
public interface RJobentryCopyMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryCopy 主键
     * @return 实例对象
     */
    RJobentryCopy queryById(String idJobentryCopy);

    /**
     * 查询指定行数据
     *
     * @param rJobentryCopy 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<RJobentryCopy> queryAllByLimit(RJobentryCopy rJobentryCopy, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rJobentryCopy 查询条件
     * @return 总行数
     */
    long count(RJobentryCopy rJobentryCopy);

    /**
     * 新增数据
     *
     * @param rJobentryCopy 实例对象
     * @return 影响行数
     */
    int insert(RJobentryCopy rJobentryCopy);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJobentryCopy> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RJobentryCopy> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJobentryCopy> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RJobentryCopy> entities);

    /**
     * 修改数据
     *
     * @param rJobentryCopy 实例对象
     * @return 影响行数
     */
    int update(RJobentryCopy rJobentryCopy);

    /**
     * 通过主键删除数据
     *
     * @param idJobentryCopy 主键
     * @return 影响行数
     */
    int deleteById(String idJobentryCopy);

}

