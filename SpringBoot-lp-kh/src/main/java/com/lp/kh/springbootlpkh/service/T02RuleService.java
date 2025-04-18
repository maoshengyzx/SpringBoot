package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T02Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 专项规则关联表(T02Rule)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:56
 */
public interface T02RuleService {


    /**
     * 统计 t02_rule 表中所有的规则数量
     * @return  规则数量
     */
    Integer getRuleCount();

}
