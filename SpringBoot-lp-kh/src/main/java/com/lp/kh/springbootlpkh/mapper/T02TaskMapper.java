package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T02Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 稽核执行任务表(T02Task)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
public interface T02TaskMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    T02Task queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param t02Task  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<T02Task> queryAllByLimit(T02Task t02Task, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t02Task 查询条件
     * @return 总行数
     */
    long count(T02Task t02Task);

    /**
     * 新增数据
     *
     * @param t02Task 实例对象
     * @return 影响行数
     */
    int insert(T02Task t02Task);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T02Task> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T02Task> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T02Task> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T02Task> entities);

    /**
     * 修改数据
     *
     * @param t02Task 实例对象
     * @return 影响行数
     */
    int update(T02Task t02Task);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

