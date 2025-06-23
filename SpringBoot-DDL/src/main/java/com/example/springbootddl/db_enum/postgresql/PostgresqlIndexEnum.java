package com.example.springbootddl.db_enum.postgresql;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: PostgresqlIndexEnum
 * Package: com.example.springbootddl.db_enum
 * Description:
 *
 * @Author ms
 * @Create 2025/6/23 11:38
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum PostgresqlIndexEnum {

    PRIMARY("PRIMARY", "主键索引", true, false),
    UNIQUE("UNIQUE", "唯一索引", true, false),

    INDEX("INDEX", "普通索引", false, false),

    // 全文索引
    GIN("GIN", "全文索引", false, true),

    // GIST 空间索引
    GIST("GIST", "空间索引", false, true),

    // 函数索引
    FUNCTION_BASED("FUNCTION_BASED", "函数索引", false, false);

    private final String code;
    private final String description;
    private final boolean unique;  // 是否唯一
    private final boolean special; // 是否需要特殊实现方式

}
