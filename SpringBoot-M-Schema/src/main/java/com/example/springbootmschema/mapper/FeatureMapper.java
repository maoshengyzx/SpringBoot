package com.example.springbootmschema.mapper;

import com.example.springbootmschema.entity.Feature;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Feature)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-12 18:09:45
 */
public interface FeatureMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param attId 主键
     * @return 实例对象
     */
    Feature queryById(String attId);

    /**
     * 查询指定行数据
     *
     * @param feature  查询条件
     * @return 对象列表
     */
    List<Feature> queryAllByLimit(Feature feature);

    /**
     * 统计总行数
     *
     * @param feature 查询条件
     * @return 总行数
     */
    long count(Feature feature);

    /**
     * 新增数据
     *
     * @param feature 实例对象
     * @return 影响行数
     */
    int insert(Feature feature);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Feature> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Feature> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Feature> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Feature> entities);

    /**
     * 修改数据
     *
     * @param feature 实例对象
     * @return 影响行数
     */
    int update(Feature feature);

    /**
     * 通过主键删除数据
     *
     * @param attId 主键
     * @return 影响行数
     */
    int deleteById(String attId);

    List<Feature> selectFeatureByClassifierId(@Param("classifierId") String classifierId);

    List<Feature> selectFeaturesByClassifierIds(@Param("classifierIds") List<String> classifierIds);
}

