package com.example.springbootelasticsearch.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * ClassName: Page
 * Package: com.example.springbootelasticsearch.common
 * Description:
 *
 * @Author ms
 * @Create 2025/5/10 14:41
 * @Version 1.0
 */
@Data
@Builder
public class Page<T> implements java.io.Serializable{


    private Integer pageNum;

    private Integer pageSize;

    private Integer total;

    private List<T> records;

    private boolean hasPrevious;


    public static <T> Page<T> of(Integer pageNum, Integer pageSize) {
        return Page.<T>builder().pageNum(pageNum).pageSize(pageSize).build();
    }
}
