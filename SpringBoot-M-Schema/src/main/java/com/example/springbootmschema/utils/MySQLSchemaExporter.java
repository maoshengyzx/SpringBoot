package com.example.springbootmschema.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class MySQLSchemaExporter {

    private static final Log log = LogFactory.getLog(MySQLSchemaExporter.class);

    @Resource
    private DataSourceProperties dataSourceProperties;


    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public void writeScheamFile() {
        // 数据库配置
        Map<String, String> config = new HashMap<>();
        config.put("url", dataSourceProperties.getUrl());
        config.put("username", dataSourceProperties.getUsername());
        config.put("password", dataSourceProperties.getPassword());

        try {
            Map<String, Object> schema = exportSchema(config);
            String json = mapper.writeValueAsString(schema);

            // 保存到文件
            Files.write(Paths.get("schema_output.json"),
                    json.getBytes(StandardCharsets.UTF_8));

            System.out.println("✅ Schema exported successfully");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public JSONObject exportSchema(Map<String, String> config)
            throws SQLException {

        log.info("url:" + config.get("url"));
        log.info("username  :" + config.get("username"));
        log.info("password  :" + config.get("password"));
        String url = config.get("url");
        String username = config.get("username");
        String password = config.get("password");

        try (Connection conn = DriverManager.getConnection(
                url, username, password
        )) {
            return exportSchema(conn);
        }
           /* DatabaseMetaData meta = conn.getMetaData();

            // 获取所有表
            try (ResultSet tablesRs = meta.getTables(
                    null, null, "%", new String[]{"TABLE"})) {
                jsonObject.set("【DB_ID】", meta.getDatabaseProductName());
                jsonObject.set("【Schema】", meta.getDatabaseProductName());

                while (tablesRs.next()) {
                    JSONObject tableJson = new JSONObject();

                    String tableName = tablesRs.getString("TABLE_NAME");
                    tableJson.set("【Table】", tableName);

                    // 获取列信息
                    List<Map<String, String>> columns = new ArrayList<>();
                    try (ResultSet columnsRs = meta.getColumns(
                            null, null, tableName, null)) {

                        while (columnsRs.next()) {
                            JSONArray columnJson = new JSONArray();
                            columnJson.set("name", columnsRs.getString("COLUMN_NAME"));
                            column.put("type", columnsRs.getString("TYPE_NAME"));
                            column.put("size", columnsRs.getString("COLUMN_SIZE"));
                            column.put("nullable",
                                    columnsRs.getString("IS_NULLABLE"));
                            columns.add(column);
                        }
                    }

                    // 获取主键
                    List<String> primaryKeys = new ArrayList<>();
                    try (ResultSet pkRs = meta.getPrimaryKeys(
                            null, null, tableName)) {

                        while (pkRs.next()) {
                            primaryKeys.add(pkRs.getString("COLUMN_NAME"));
                        }
                    }

                    // 获取外键
                    List<Map<String, String>> foreignKeys = new ArrayList<>();
                    try (ResultSet fkRs = meta.getImportedKeys(
                            null, null, tableName)) {

                        while (fkRs.next()) {
                            Map<String, String> fk = new HashMap<>();
                            fk.put("column", fkRs.getString("FKCOLUMN_NAME"));
                            fk.put("references",
                                    fkRs.getString("PKTABLE_NAME") + "." +
                                            fkRs.getString("PKCOLUMN_NAME"));
                            foreignKeys.add(fk);
                        }
                    }

                    tableInfo.put("columns", columns);
                    tableInfo.put("primary_keys", primaryKeys);
                    tableInfo.put("foreign_keys", foreignKeys);
                    tables.add(tableInfo);
                }
            }
        }

        result.put("database", config.get("url").split("/")[3]);
        result.put("tables", tables);
        return jsonObject;*/
    }

    public JSONObject exportSchema(Connection conn) throws SQLException {
        JSONObject result = new JSONObject();

        // 设置DB_ID
        result.set("DB_ID", conn.getCatalog());

        // 准备Schema部分
        JSONObject schema = new JSONObject();
        result.set("Schema", schema);

        DatabaseMetaData meta = conn.getMetaData();

        // 处理所有表
        JSONArray tables = new JSONArray();
        try (ResultSet tablesRs = meta.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"})) {
            while (tablesRs.next()) {
                String tableName = tablesRs.getString("TABLE_NAME");
                tables.add(processTable(meta, conn, tableName));
            }
        }
        schema.set("tables", tables);

        // 处理外键关系
        JSONArray foreignKeys = processForeignKeys(meta,conn);
        if (foreignKeys.size() > 0) {
            result.set("Foreign_keys", foreignKeys);
        }

        return result;
    }

    private JSONObject processTable(DatabaseMetaData meta, Connection conn, String tableName)
            throws SQLException {
        JSONObject tableJson = new JSONObject();
        tableJson.set("Table", tableName);

        JSONArray columns = new JSONArray();
        try (ResultSet columnsRs = meta.getColumns(conn.getCatalog(), null, tableName, null)) {
            while (columnsRs.next()) {
                JSONObject column = new JSONObject();
                String colName = columnsRs.getString("COLUMN_NAME");

                // 基础列信息
                column.set("name", colName);
                column.set("type", columnsRs.getString("TYPE_NAME"));
                column.set("size", columnsRs.getInt("COLUMN_SIZE"));
                column.set("nullable", "YES".equals(columnsRs.getString("IS_NULLABLE")));

                // 主键标记
                if (isPrimaryKey(meta, tableName, colName,conn)) {
                    column.set("primary_key", true);
                }

                // 列注释
                String remarks = columnsRs.getString("REMARKS");
                if (remarks != null && !remarks.isEmpty()) {
                    column.set("description", remarks);
                }

                // 示例数据
                column.set("examples", getExampleValues(conn, tableName, colName, 3));

                columns.add(column);
            }
        }
        tableJson.set("columns", columns);

        return tableJson;
    }

    private boolean isPrimaryKey(DatabaseMetaData meta, String tableName, String columnName, Connection conn)
            throws SQLException {
        try (ResultSet pkRs = meta.getPrimaryKeys(conn.getCatalog(), null, tableName)) {
            while (pkRs.next()) {
                if (columnName.equals(pkRs.getString("COLUMN_NAME"))) {
                    return true;
                }
            }
        }
        return false;
    }

    private JSONArray processForeignKeys(DatabaseMetaData meta, Connection conn) throws SQLException {
        JSONArray foreignKeys = new JSONArray();
        try (ResultSet tablesRs = meta.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"})) {
            while (tablesRs.next()) {
                String tableName = tablesRs.getString("TABLE_NAME");
                try (ResultSet fkRs = meta.getImportedKeys(null, null, tableName)) {
                    while (fkRs.next()) {
                        JSONObject fk = new JSONObject();
                        fk.set("source_table", tableName);
                        fk.set("source_column", fkRs.getString("FKCOLUMN_NAME"));
                        fk.set("target_table", fkRs.getString("PKTABLE_NAME"));
                        fk.set("target_column", fkRs.getString("PKCOLUMN_NAME"));
                        foreignKeys.add(fk);
                    }
                }
            }
        }
        return foreignKeys;
    }

    private JSONArray getExampleValues(Connection conn, String tableName, String columnName, int count)
            throws SQLException {
        JSONArray examples = new JSONArray();
        String sql = String.format("SELECT DISTINCT %s FROM %s LIMIT ?", columnName, tableName);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, count);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object value = rs.getObject(1);
                    if (value != null) {
                        examples.add(value.toString());
                    }
                }
            }
        }

        return examples;
    }

    public void export() {

    }
}