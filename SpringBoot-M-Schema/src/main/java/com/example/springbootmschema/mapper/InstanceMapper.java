package com.example.springbootmschema.mapper;

import com.example.springbootmschema.entity.CodeItem;
import com.example.springbootmschema.entity.Datasource;
import com.example.springbootmschema.entity.Instance;
import com.example.springbootmschema.sqlprovider.DynamicAttributeSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * (Instance)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-12 14:07:18
 */
public interface InstanceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param instanceId 主键
     * @return 实例对象
     */
    Instance queryById(Long instanceId);


    /**
     * 统计总行数
     *
     * @param Instance 查询条件
     * @return 总行数
     */
    long count(Instance Instance);

    /**
     * 新增数据
     *
     * @param Instance 实例对象
     * @return 影响行数
     */
    int insert(Instance Instance);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Instance> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Instance> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Instance> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Instance> entities);

    /**
     * 修改数据
     *
     * @param Instance 实例对象
     * @return 影响行数
     */
    int update(Instance Instance);

    /**
     * 通过主键删除数据
     *
     * @param instanceId 主键
     * @return 影响行数
     */
    int deleteById(Long instanceId);

    /**
     * 通过数据源id查询元数据信息
     *
     * @param datasourceList
     * @return
     */
    List<Instance> selectInstanceByDatasourceIds(@Param("entries") List<Datasource> datasourceList);

    Instance selectInstanceByInstanceIdAndClassiferId(@Param("instanceId") String instanceId, @Param("classifierId") String classiferId, @Param("schemaInstanceId") Long schemaInstanceId);

    String selectStringValueByInstanceId(@Param("instanceId") Long instanceId, @Param("attStore") String attStore);

    Instance selectInstanceByDataSourceIdAndClassiferId(@Param("instanceCode") String instanceCode, @Param("classifierId") String classifierId);

    List<Instance> selectByParentInstanceIds(@Param("parentInstanceIds") List<Long> parentInstanceId, @Param("classifierId") String classifierId);


    // 动态属性查询
    @SelectProvider(type = DynamicAttributeSqlProvider.class, method = "buildDynamicAttributeQuery")
    List<Map<String, Object>> selectDynamicAttributes(@Param("instanceIds") List<Long> instanceIds,
                                                      @Param("attStores") List<String> attStores,
                                                      @Param("tableName") String tableName,
                                                      @Param("classifierId") String classifierId);

    List<CodeItem> selectCodeValueByInstanceId(@Param("instanceId") Long instanceId);
}

