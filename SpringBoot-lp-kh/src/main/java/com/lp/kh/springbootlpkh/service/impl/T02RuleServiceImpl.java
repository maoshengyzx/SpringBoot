package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T02Rule;
import com.lp.kh.springbootlpkh.mapper.T02RuleMapper;
import com.lp.kh.springbootlpkh.service.T02RuleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 专项规则关联表(T02Rule)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:56
 */
@Service("t02RuleService")
public class T02RuleServiceImpl implements T02RuleService {
    @Resource
    private T02RuleMapper t02RuleMapper;



    /**
     * 统计 t02_rule 表中所有的规则数量
     * @return 规则数量
     */
    @Override
    public Integer getRuleCount() {
        return t02RuleMapper.getRuleCount();
    }
}
