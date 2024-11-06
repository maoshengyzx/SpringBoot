package com.example.springbootrabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: CanalEntity
 * Package: com.example.springbootrabbitmq.entity
 * Description:
 * 用户接受canal信息的实体类
 *
 * @Author ms
 * @Create 2024/11/6 12:07
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanalEntity implements Serializable {

    private List<FileTableData> data;
    private String database;
    private long es;
    private int id;
    private boolean isDdl;
    private MysqlType mysqlType;
    private Object old;
    private List<String> pkNames;
    private String sql;
    private SqlType sqlType;
    private String table;
    private long ts;
    private String type;

    // 生成必要的 getter 和 setter 方法

    @Data
    public static class FileTableData {
        private String id;
        private String fileName;
        private String fileType;
        private String fileSize;
        private String filePath;
        private String isDeleted;

    }

    @Data
    public static class MysqlType {
        private String id;
        private String fileName;
        private String fileType;
        private String fileSize;
        private String filePath;
        private String isDeleted;

    }

    @Data
    public static class SqlType {
        private int id;
        private int fileName;
        private int fileType;
        private int fileSize;
        private int filePath;
        private int isDeleted;
    }


}
