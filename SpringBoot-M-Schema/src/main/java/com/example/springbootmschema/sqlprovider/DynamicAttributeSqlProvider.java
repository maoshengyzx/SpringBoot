package com.example.springbootmschema.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamicAttributeSqlProvider {

    public String buildDynamicAttributeQuery(Map<String, Object> params) {
        List<Long> instanceIds = (List<Long>) params.get("instanceIds");
        List<String> attStores = (List<String>) params.get("attStores");
        String tableName = (String) params.get("tableName");
        String classifierId = (String) params.get("classifierId");

        SQL sql = new SQL()
                .SELECT("instance_id");

        // 动态添加属性列
        for (String attStore : attStores) {
            sql.SELECT(attStore);
        }

        sql.FROM(tableName)
                .WHERE("classifier_id = '" + classifierId + "' AND instance_id IN (" +
                        instanceIds.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")");

        return sql.toString();
    }
}