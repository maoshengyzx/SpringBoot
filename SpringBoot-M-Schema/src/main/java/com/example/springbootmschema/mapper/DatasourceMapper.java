package com.example.springbootmschema.mapper;

import com.example.springbootmschema.entity.Datasource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (T99Datasource)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-12 17:44:59
 */
public interface DatasourceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param datasourceId 主键
     * @return 实例对象
     */
    Datasource queryById(String datasourceId);


    /**
     * 统计总行数
     *
     * @param datasource 查询条件
     * @return 总行数
     */
    long count(Datasource datasource);

    /**
     * 新增数据
     *
     * @param datasource 实例对象
     * @return 影响行数
     */
    int insert(Datasource datasource);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99Datasource> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Datasource> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99Datasource> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Datasource> entities);

    /**
     * 修改数据
     *
     * @param datasource 实例对象
     * @return 影响行数
     */
    int update(Datasource datasource);

    /**
     * 通过主键删除数据
     *
     * @param datasourceId 主键
     * @return 影响行数
     */
    int deleteById(String datasourceId);

    /**
     * 查询所有的数据源信息
     * @return
     */
    List<Datasource> listAll();
}

