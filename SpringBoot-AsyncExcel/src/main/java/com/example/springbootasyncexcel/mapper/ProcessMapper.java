package com.example.springbootasyncexcel.mapper;

import com.example.springbootasyncexcel.entity.Process;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Process)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-05 15:11:23
 */
public interface ProcessMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param processId 主键
     * @return 实例对象
     */
    Process queryById(Long processId);


    /**
     * 统计总行数
     *
     * @param process 查询条件
     * @return 总行数
     */
    long count(Process process);

    /**
     * 新增数据
     *
     * @param process 实例对象
     * @return 影响行数
     */
    int insert(Process process);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Process> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Process> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Process> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Process> entities);

    /**
     * 修改数据
     *
     * @param process 实例对象
     * @return 影响行数
     */
    int update(Process process);

    /**
     * 通过主键删除数据
     *
     * @param processId 主键
     * @return 影响行数
     */
    int deleteById(Long processId);

    Process listByStatus(@Param("status") Integer status);

}

