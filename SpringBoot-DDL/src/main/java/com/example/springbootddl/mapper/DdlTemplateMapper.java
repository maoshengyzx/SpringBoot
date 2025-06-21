package com.example.springbootddl.mapper;

import com.example.springbootddl.entity.DdlTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ddll模板表(DdlTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-21 10:24:02
 */
public interface DdlTemplateMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DdlTemplate queryById(Long id);


    /**
     * 统计总行数
     *
     * @param ddlTemplate 查询条件
     * @return 总行数
     */
    long count(DdlTemplate ddlTemplate);

    /**
     * 新增数据
     *
     * @param ddlTemplate 实例对象
     * @return 影响行数
     */
    int insert(DdlTemplate ddlTemplate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DdlTemplate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DdlTemplate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DdlTemplate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DdlTemplate> entities);

    /**
     * 修改数据
     *
     * @param ddlTemplate 实例对象
     * @return 影响行数
     */
    int update(DdlTemplate ddlTemplate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<DdlTemplate> listAll();

    DdlTemplate queryByUnique(@Param("version") String version,@Param("dbType") String dbType);
}

