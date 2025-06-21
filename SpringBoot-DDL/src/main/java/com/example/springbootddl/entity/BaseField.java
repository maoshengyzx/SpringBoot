package com.example.springbootddl.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * ClassName: BaseField
 * Package: com.example.springbootddl.entity.module
 * Description:
 *
 * @Author ms
 * @Create 2025/6/21 14:18
 * @Version 1.0
 */
@Data
@SuperBuilder
public class BaseField {

    /**
     * 字段名称
     */
    protected String code;

    /**
     * 字段注释
     */
    protected String comment;
    /**
     * 字段类型
     */
    protected String type;

    /**
     * 字段长度
     */
    protected Integer len;

    /**
     * 字段精度
     */
    protected Double scale;

    /**
     * 是否可空
     */
    protected Boolean notNull;

    /**
     * 是否自增
     */
    protected Boolean autoIncrement;

    /**
     * 默认值
     */
    protected String defaultValue;
}
