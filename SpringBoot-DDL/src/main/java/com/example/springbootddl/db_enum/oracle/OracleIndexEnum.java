package com.example.springbootddl.db_enum.oracle;

import lombok.AllArgsConstructor;
import lombok.Getter;

/****
 * Created by <NAME> on 2021/07/16.
 */
@AllArgsConstructor
@Getter
public enum OracleIndexEnum {

    // 主键索引
    PRIMARY("PRIMARY", "主键索引", true, false),
    // 唯一索引
    UNIQUE("UNIQUE", "唯一索引", true, false),

    // 普通索引
    INDEX("INDEX", "普通索引", false, false),

    // 组合索引
    COMBINED("COMBINED", "组合索引", false, false),

    // 位图索引
    BITMAP("BITMAP", "位图索引", false, false),

    // 函数索引
    FUNCTION_BASED("FUNCTION_BASED", "函数索引", false, false),

    // 反向键索引
    REVERSE("REVERSE", "反向键索引", false, false);

    private final String code;
    private final String description;
    private final boolean unique;  // 是否唯一
    private final boolean special; // 是否需要特殊实现方式
}
