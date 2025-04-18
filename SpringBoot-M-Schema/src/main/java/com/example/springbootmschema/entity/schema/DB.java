package com.example.springbootmschema.entity.schema;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * ClassName: DB
 * Package: com.example.springbootmschema.entity.schema
 * Description:
 *
 * @Author ms
 * @Create 2025/4/12 18:24
 * @Version 1.0
 */

public class DB {

    private String dbId;

    private String schema;

    private Map<String, Table> tables;

    private List<String> foreignKeys;

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void setTables(Map<String, Table> tables) {
        this.tables = tables;
    }

    public List<String> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<String> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }


    public static class Table {
//        /**
//         * 表名称
//         */
//        private String tableName;

        /**
         * 表注释
         */
        private String tableComment;

        /**
         * 样例数据   优先取码值，其次取数据
         */
        private List<String> examples;


        private Map<String, Field> fields;

        public String getTableComment() {
            return tableComment;
        }

        public void setTableComment(String tableComment) {
            this.tableComment = tableComment;
        }

        public List<String> getExamples() {
            return examples;
        }

        public void setExamples(List<String> examples) {
            this.examples = examples;
        }

        public Map<String, Field> getFields() {
            return fields;
        }

        public void setFields(Map<String, Field> fields) {
            this.fields = fields;
        }


        public static class Field {
            private String fieldName;
            private String fieldType;
            private Boolean isPrimaryKey;
            private Boolean isNullable;
            private String defaultValue;

            private Boolean autoIncrement;

            private String fieldComment;

            /**
             * 样例数据   优先取码值，其次取数据
             */
            private List<String> examples;


            public String getFieldName() {
                return fieldName;
            }

            public void setFieldName(String fieldName) {
                this.fieldName = fieldName;
            }

            public String getFieldType() {
                return fieldType;
            }

            public void setFieldType(String fieldType) {
                this.fieldType = fieldType;
            }

            public Boolean getIsPrimaryKey() {
                return isPrimaryKey;
            }

            public void setIsPrimaryKey(Boolean isPrimaryKey) {
                this.isPrimaryKey = isPrimaryKey;
            }

            public Boolean getIsNullable() {
                return isNullable;
            }

            public void setIsNullable(Boolean isNullable) {
                this.isNullable = isNullable;
            }

            public String getDefaultValue() {
                return defaultValue;
            }

            public void setDefaultValue(String defaultValue) {
                this.defaultValue = defaultValue;
            }

            public Boolean getAutoIncrement() {
                return autoIncrement;
            }

            public void setAutoIncrement(Boolean autoIncrement) {
                this.autoIncrement = autoIncrement;
            }

            public String getFieldComment() {
                return fieldComment;
            }

            public void setFieldComment(String fieldComment) {
                this.fieldComment = fieldComment;
            }

            public List<String> getExamples() {
                return examples;
            }

            public void setExamples(List<String> examples) {
                this.examples = examples;
            }
        }
    }
}
