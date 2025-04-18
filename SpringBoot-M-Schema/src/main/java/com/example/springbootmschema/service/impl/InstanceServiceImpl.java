package com.example.springbootmschema.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.log.Log;
import com.example.springbootmschema.entity.CodeItem;
import com.example.springbootmschema.entity.Datasource;
import com.example.springbootmschema.entity.Feature;
import com.example.springbootmschema.entity.Instance;
import com.example.springbootmschema.entity.schema.DB;
import com.example.springbootmschema.mapper.DatasourceMapper;
import com.example.springbootmschema.mapper.FeatureMapper;
import com.example.springbootmschema.mapper.InstanceMapper;
import com.example.springbootmschema.service.InstanceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * ClassName: InstanceServiceImpl
 * Package: com.example.springbootmschema.service.impl
 * Description:
 *
 * @Author ms
 * @Create 2025/4/12 17:37
 * @Version 1.0
 */
@Service
public class InstanceServiceImpl implements InstanceService {

    @Resource
    private InstanceMapper instanceMapper;

    @Resource
    private FeatureMapper featureMapper;

    @Resource
    private DatasourceMapper datasourceMapper;

    private static final List<String> COLUMM_ATTSORE = List.of("string_1", "string_8", "string_9", "string_2", "string_3");

    private static final List<String> TABLE_ATTSORE = List.of("string_1");

    private static final Log log = Log.get();


    @Override
    public DB export(String datasourceId, Long schemaInstanceId) {
        try {
            log.info("开始导出数据库元数据");


            Datasource datasource = datasourceMapper.queryById(datasourceId);


            // 2. 预加载所有特征配置并构建映射关系
            Map<String, Map<String, String>> featureMappings = safelyCall(this::buildFeatureMappings, "构建特征映射");
            Map<String, String> tableFeatureMap = featureMappings.get("Table");
            Map<String, String> columnFeatureMap = featureMappings.get("Column");

            if (tableFeatureMap == null || columnFeatureMap == null) {
                log.error("缺少必要的特征映射配置，Table: {}, Column: {}",
                        tableFeatureMap != null, columnFeatureMap != null);
                return null;
            }

            // 3. 并行处理每个数据源
            return processDatasource(datasource, schemaInstanceId, tableFeatureMap, columnFeatureMap);
        } catch (Exception e) {
            log.error("导出过程中发生未捕获的异常: {}", e.getMessage(), e);
            return null;
        } finally {
            log.info("数据库元数据导出完成");
        }
    }

    /**
     * 构建特征映射关系
     */
    private Map<String, Map<String, String>> buildFeatureMappings() {
        try {
            // 仅查询Table和Column分类的特征
            List<Feature> requiredFeatures = safelyCall(
                    () -> featureMapper.selectFeaturesByClassifierIds(Arrays.asList("Table", "Column")),
                    "查询特征配置");

            if (CollectionUtils.isEmpty(requiredFeatures)) {
                log.error("未找到任何特征配置");
                return Collections.emptyMap();
            }

            return requiredFeatures.stream()
                    .collect(Collectors.groupingBy(
                            Feature::getClassifierId,
                            Collectors.toMap(Feature::getAttId, Feature::getAttStore, (a, b) -> a)
                    ));
        } catch (Exception e) {
            log.error("构建特征映射失败: {}", e.getMessage(), e);
            throw new RuntimeException("构建特征映射失败", e);
        }
    }

    /**
     * 处理单个数据源
     */
    private DB processDatasource(Datasource datasource,
                                 Long schemaInstanceId, Map<String, String> tableFeatureMap,
                                 Map<String, String> columnFeatureMap) {
        DB db = new DB();
        db.setDbId(datasource.getDatasourceId());
        db.setSchema(instanceMapper.queryById(schemaInstanceId).getInstanceCode());

        try {
            // 1. 查询Root实例
            Instance rootInstance = safelyCall(
                    () -> instanceMapper.selectInstanceByDataSourceIdAndClassiferId(
                            datasource.getDatasourceId(), "Root"),
                    "查询Root实例");

            if (rootInstance == null) {
                log.warn("数据源[{}]未找到Root实例", datasource.getDatasourceId());
                return db;
            }

            // 2. 查询指定的Schema实例
            Instance schemaInstance = safelyCall(
                    () -> instanceMapper.selectInstanceByInstanceIdAndClassiferId(
                            Convert.toStr(rootInstance.getInstanceId()), "Schema", schemaInstanceId),
                    "查询指定的Schema实例");


            if (Objects.isNull(schemaInstance)) {
                log.warn("数据源[{}]下未找到任何Schema实例", datasource.getDatasourceId());
                return db;
            }


            List<Instance> tableInstances = safelyCall(
                    () -> instanceMapper.selectByParentInstanceIds(List.of(schemaInstance.getInstanceId()), "Table"),
                    "查询表实例");

            if (CollectionUtils.isEmpty(tableInstances)) {
                log.warn("数据源[{}] 数据库[{}]下未找到任何表实例", datasource.getDatasourceId(), schemaInstance.getInstanceId());
                return db;
            }

            List<Long> tableInstanceIds = tableInstances.stream()
                    .map(Instance::getInstanceId)
                    .collect(Collectors.toList());

            List<Instance> columnInstances = safelyCall(
                    () -> instanceMapper.selectByParentInstanceIds(tableInstanceIds, "Column"),
                    "查询列实例");

            if (CollectionUtils.isEmpty(columnInstances)) {
                log.warn("数据源[{}] 数据库[{}] 表[{}]下未找到任何列实例", datasource.getDatasourceId(), schemaInstance.getInstanceId(), tableInstanceIds);
                return db;
            }


            // 4. 动态查询表属性
            List<Map<String, Object>> tableAttributes = safelyCall(
                    () -> instanceMapper.selectDynamicAttributes(
                            tableInstanceIds, TABLE_ATTSORE, "t01_instance", "Table"),
                    "查询表属性");


            // 5. 动态查询列属性
            List<Map<String, Object>> columnAttributes = safelyCall(
                    () -> instanceMapper.selectDynamicAttributes(
                            columnInstances.stream().map(Instance::getInstanceId).collect(Collectors.toList()),
                            COLUMM_ATTSORE, "t01_instance", "Column"),
                    "查询列属性");


            // 6. 处理表结构
            db.setTables(buildTables(
                    schemaInstance,
                    tableInstances,
                    columnInstances,
                    processDynamicAttributes(tableAttributes, tableFeatureMap),
                    processDynamicAttributes(columnAttributes, columnFeatureMap)
            ));

            return db;
        } catch (Exception e) {
            log.error("处理数据源[{}]失败: {}", datasource.getDatasourceId(), e.getMessage(), e);
            throw new RuntimeException("处理数据源失败", e);
        }
    }

    /**
     * 构建表结构
     */
    private Map<String, DB.Table> buildTables(Instance schemaInstances,
                                              List<Instance> tableInstances,
                                              List<Instance> columnInstances,
                                              Map<Long, Map<String, String>> tableAttributes,
                                              Map<Long, Map<String, String>> columnAttributes) {
        try {
            HashMap<String, DB.Table> tableMap = new HashMap<>();

            // 构建实例关系映射
            Map<Long, List<Instance>> tablesBySchema = tableInstances.stream()
                    .collect(Collectors.groupingBy(Instance::getParentId));

            Map<Long, List<Instance>> columnsByTable = columnInstances.stream()
                    .collect(Collectors.groupingBy(Instance::getParentId));

            List<Instance> schemaTables = tablesBySchema.getOrDefault(
                    schemaInstances.getInstanceId(), Collections.emptyList());

            for (Instance table : schemaTables) {
                try {
                    DB.Table dbTable = new DB.Table();

                    // 设置表属性
                    Map<String, String> tableAttrs = tableAttributes.get(table.getInstanceId());
                    if (tableAttrs != null) {
                        dbTable.setTableComment(tableAttrs.get("Table_1")); // 表注释
                        // todo
                        dbTable.setExamples(List.of());
                        // 其他表属性...
                    } else {
//                        log.warn("表[{}]未找到属性配置", dbTable.getTableName());
                    }
                    // 处理表字段
                    List<Instance> tableColumns = columnsByTable.getOrDefault(
                            table.getInstanceId(), Collections.emptyList());
                    dbTable.setFields(buildFields(tableColumns, columnAttributes));

                    tableMap.put(schemaInstances.getInstanceCode() + "." + table.getInstanceCode(), dbTable);

                } catch (Exception e) {
                    log.error("构建表[{}]结构失败: {}",
                            table.getInstanceCode(), e.getMessage(), e);
                }
            }
            return tableMap;
        } catch (Exception e) {
            log.error("构建表结构失败: {}", e.getMessage(), e);
            throw new RuntimeException("构建表结构失败", e);
        }
    }

    /**
     * 构建字段列表
     */
    private Map<String, DB.Table.Field> buildFields(List<Instance> columns,
                                                    Map<Long, Map<String, String>> columnAttributes) {
        return columns.stream().map(column -> {
            try {
                DB.Table.Field field = new DB.Table.Field();
                field.setFieldName(column.getInstanceCode());

                Map<String, String> colAttrs = columnAttributes.get(column.getInstanceId());
                if (colAttrs != null) {
                    field.setFieldType(colAttrs.get("Column_1"));  // 字段类型
                    field.setIsPrimaryKey("y".equalsIgnoreCase(colAttrs.get("Column_8"))); // 是否主键
                    field.setIsNullable("y".equalsIgnoreCase(colAttrs.get("Column_9"))); // 是否可为空
                    field.setDefaultValue(colAttrs.get("Column_2")); // 默认值
                    field.setFieldComment(colAttrs.get("Column_3")); // 字段注释
                    // 取码值信息，如果码值信息不存在取示例数据
                    List<CodeItem> codeItems = instanceMapper.selectCodeValueByInstanceId(column.getInstanceId());
                    if (!CollectionUtils.isEmpty(codeItems)) {
                        field.setExamples(codeItems.stream().map(CodeItem::getCodeNum).collect(Collectors.toList()));
                    } else {
                        // todo
                        field.setExamples(List.of());
                    }
                } else {
                    log.warn("列[{}]未找到属性配置", column.getInstanceCode());
                }
                field.setAutoIncrement(false);

                return field;
            } catch (Exception e) {
                log.error("构建字段[{}]失败: {}", column.getInstanceCode(), e.getMessage(), e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toMap(DB.Table.Field::getFieldName, field -> field, (a, b) -> a));
    }

    /**
     * 处理动态属性结果
     */
    private Map<Long, Map<String, String>> processDynamicAttributes(
            List<Map<String, Object>> attributeResults,
            Map<String, String> featureMap) {
        try {
            if (CollectionUtils.isEmpty(attributeResults)) {
                return Collections.emptyMap();
            }

            // 构建反向映射 (attStore -> attId)
            Map<String, String> storeToAttId = featureMap.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getValue,
                            Map.Entry::getKey,
                            (a, b) -> {
                                log.warn("发现重复的attStore值: {}", a);
                                return a;
                            }));

            return attributeResults.stream().collect(Collectors.toMap(
                            m -> {
                                try {
                                    Long instanceId = (Long) m.get("instance_id");
                                    if (instanceId == null) {
                                        log.warn("发现空的instance_id: {}", m);
                                    }
                                    return instanceId;
                                } catch (Exception e) {
                                    log.error("解析instance_id失败: {}", e.getMessage(), e);
                                    return -1L; // 使用无效ID作为标记
                                }
                            },
                            m -> {
                                Map<String, String> attrs = new HashMap<>();
                                m.forEach((key, value) -> {
                                    try {
                                        if (!"instance_id".equals(key) && value != null) {
                                            String attId = storeToAttId.get(key);
                                            if (attId != null) {
                                                attrs.put(attId, value.toString());
                                            } else {
                                                log.debug("未找到attStore {} 对应的attId", key);
                                            }
                                        }
                                    } catch (Exception e) {
                                        log.error("处理属性键值对[{}, {}]失败: {}",
                                                key, value, e.getMessage(), e);
                                    }
                                });
                                return attrs;
                            },
                            (a, b) -> {
                                log.warn("发现重复的instance_id，合并属性");
                                a.putAll(b);
                                return a;
                            }
                    )).entrySet().stream()
                    .filter(e -> e.getKey() != -1L)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (Exception e) {
            log.error("处理动态属性失败: {}", e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    /**
     * 安全执行方法，带有日志记录
     */
    private <T> T safelyCall(Supplier<T> supplier, String operation) {
        try {
            log.debug("执行操作: {}", operation);
            return supplier.get();
        } catch (Exception e) {
            log.error("操作[{}]执行失败: {}", operation, e.getMessage(), e);
            throw new RuntimeException(operation + "失败", e);
        }
    }
}
