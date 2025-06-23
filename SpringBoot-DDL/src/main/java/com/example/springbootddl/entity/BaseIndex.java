package com.example.springbootddl.entity;

import com.example.springbootddl.db_enum.IndexImplementation;
import com.example.springbootddl.db_enum.mysql.MySQLIndexImplementation;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * ClassName: BaseIndex
 * Package: com.example.springbootddl.entity
 * Description:
 * 索引类
 *
 * @Author ms
 * @Create 2025/6/23 9:16
 * @Version 1.0
 */
@Data
@SuperBuilder
public class BaseIndex {

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引类型
     */
    private String indexType;


    /***
     * 索引实现方式
     */
    private String indexImplementation;


    /**
     * 索引列,  多个列用逗号分隔
     */
    private String indexColumns;

    /**
     * 索引注释
     */
    private String indexComment;

}
