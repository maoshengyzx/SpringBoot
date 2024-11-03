package com.example.springbootelasticsearch.mapper;

import com.example.springbootelasticsearch.entity.FileTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * (FileTable)表数据库访问层
 *
 * @author makejava
 * @since 2024-11-03 11:22:40
 */
public interface FileTableMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FileTable queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param fileTable 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<FileTable> queryAllByLimit(FileTable fileTable, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param fileTable 查询条件
     * @return 总行数
     */
    long count(FileTable fileTable);

    /**
     * 新增数据
     *
     * @param fileTable 实例对象
     * @return 影响行数
     */
    int insert(FileTable fileTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FileTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FileTable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FileTable> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<FileTable> entities);

    /**
     * 修改数据
     *
     * @param fileTable 实例对象
     * @return 影响行数
     */
    int update(FileTable fileTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

