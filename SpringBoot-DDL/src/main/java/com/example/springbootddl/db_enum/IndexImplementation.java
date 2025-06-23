package com.example.springbootddl.db_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 索引的物理实现方式
 */
@AllArgsConstructor
@Getter
public enum IndexImplementation {

    GIST("GIST", "通用搜索树", false),
    GIN("GIN", "倒排索引", false),
    BRIN("BRIN", "块范围索引", false),
    RTREE("RTREE", "空间树结构", false),
    BITMAP("BITMAP", "位图", false);

    private final String code;
    private final String description;
    private final boolean isDefault; // 是否为默认实现方式


}