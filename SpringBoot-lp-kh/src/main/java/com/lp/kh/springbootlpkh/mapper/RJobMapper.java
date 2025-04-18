package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.RJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (RJob)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-19 13:56:25
 */
public interface RJobMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param idJob 主键
     * @return 实例对象
     */
    RJob queryById(String idJob);

    /**
     * 查询指定行数据
     *
     * @param rJob     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<RJob> queryAllByLimit(RJob rJob, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param rJob 查询条件
     * @return 总行数
     */
    long count(RJob rJob);

    /**
     * 新增数据
     *
     * @param rJob 实例对象
     * @return 影响行数
     */
    int insert(RJob rJob);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJob> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RJob> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RJob> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RJob> entities);

    /**
     * 修改数据
     *
     * @param rJob 实例对象
     * @return 影响行数
     */
    int update(RJob rJob);

    /**
     * 通过主键删除数据
     *
     * @param idJob 主键
     * @return 影响行数
     */
    int deleteById(String idJob);

}

