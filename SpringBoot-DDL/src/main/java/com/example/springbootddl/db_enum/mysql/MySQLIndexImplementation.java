package com.example.springbootddl.db_enum.mysql;

import lombok.AllArgsConstructor;

/**
 * ClassName: MySQLIndexImplementation
 * Package: com.example.springbootddl.db_enum.mysql
 * Description:
 *
 * @Author ms
 * @Create 2025/6/23 11:43
 * @Version 1.0
 */
@AllArgsConstructor
public enum MySQLIndexImplementation {

    BTREE("BTREE", "B+树结构", true),
    HASH("HASH", "哈希表", false);


    private final String code;
    private final String description;
    private final boolean isDefault; // 是否为默认实现方式
}
