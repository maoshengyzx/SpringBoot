package com.example.springbootddl.entity.module.oracle;

import com.example.springbootddl.entity.BaseField;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * ClassName: OracleFieldVO
 * Package: com.example.springbootddl.entity.module.oracle
 * Description:
 *
 * @Author ms
 * @Create 2025/6/21 14:22
 * @Version 1.0
 */
@Data
@SuperBuilder
public class OracleField extends BaseField {

    /**
     * 主键
     */
    private String primaryKey;
}
