package com.example.springbootmschema.utils;

import com.example.springbootmschema.entity.Datasource;
import com.example.springbootmschema.entity.schema.DB;

import java.util.Map;

/**
 * ClassName: SchemmaUtils
 * Package: com.example.springbootmschema.utils
 * Description:
 *
 * @Author ms
 * @Create 2025/4/15 13:46
 * @Version 1.0
 */
public class SchemaUtils {


    /**
     * 导出数据库结构
     *
     * @param datasource     数据源对象
     * @param schemaInstanceId 数据库实例id
     * @param tableFeatureMap  表特征信息
     * @param columnFeatureMap 列特征信息
     * @return
     */
    private DB processDatasource(Datasource datasource, Long schemaInstanceId,
                                 Map<String, String> tableFeatureMap,
                                 Map<String, String> columnFeatureMap) {

        return null;
    }
}
