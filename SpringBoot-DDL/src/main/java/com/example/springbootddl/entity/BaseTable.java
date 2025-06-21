package com.example.springbootddl.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * ClassName: BaseTable
 * Package: com.example.springbootddl.entity.module
 * Description:
 *
 * @Author ms
 * @Create 2025/6/21 14:18
 * @Version 1.0
 */
@Data
@SuperBuilder
public class BaseTable {
    /**
     * 表名
     */
    protected String tableCode;

    /**
     * 表注释
     */
    protected String tableComment;

}
