package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T02Rule;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 专项规则关联表(T02Rule)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:55
 */
public interface T02RuleMapper {

    /**
     * 统计 t02_rule 表中所有的规则数量
     * @return 规则数量
     */
    int getRuleCount();
}

