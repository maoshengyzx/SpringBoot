package com.example.springbootddl.db_enum.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: MySQLIndexEnum
 * Package: com.example.springbootddl.db_enum
 * Description:
 *
 * @Author ms
 * @Create 2025/6/23 11:30
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum MySQLIndexEnum {

    PRIMARY("PRIMARY", "主键索引", true, false),
    UNIQUE("UNIQUE", "唯一索引", true, false),
    FULLTEXT("FULLTEXT", "全文索引", false, true),

    SPATIAL("SPATIAL", "空间索引", false, true),

    INDEX("INDEX", "普通索引", false, false),

    // 组合索引
    COMBINED("COMBINED", "组合索引", false, false);


    private final String code;
    private final String description;
    private final boolean unique;  // 是否唯一
    private final boolean special; // 是否需要特殊实现方式
}
