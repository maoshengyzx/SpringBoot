package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T02RuleClass;
import com.lp.kh.springbootlpkh.mapper.T02RuleClassMapper;
import com.lp.kh.springbootlpkh.service.T02RuleClassService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 质量维度细类信息表(T02RuleClass)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:57
 */
@Service("t02RuleClassService")
public class T02RuleClassServiceImpl implements T02RuleClassService {
    @Resource
    private T02RuleClassMapper t02RuleClassMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param classNo 主键
     * @return 实例对象
     */
    @Override
    public T02RuleClass queryById(String classNo) {
        return this.t02RuleClassMapper.queryById(classNo);
    }

    /**
     * 分页查询
     *
     * @param t02RuleClass 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<T02RuleClass> queryByPage(T02RuleClass t02RuleClass, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t02RuleClassMapper.count(t02RuleClass);
        return new PageImpl<>(this.t02RuleClassMapper.queryAllByLimit(t02RuleClass, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t02RuleClass 实例对象
     * @return 实例对象
     */
    @Override
    public T02RuleClass insert(T02RuleClass t02RuleClass) {
        this.t02RuleClassMapper.insert(t02RuleClass);
        return t02RuleClass;
    }

    /**
     * 修改数据
     *
     * @param t02RuleClass 实例对象
     * @return 实例对象
     */
    @Override
    public T02RuleClass update(T02RuleClass t02RuleClass) {
        this.t02RuleClassMapper.update(t02RuleClass);
        return this.queryById(t02RuleClass.getClassNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param classNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String classNo) {
        return this.t02RuleClassMapper.deleteById(classNo) > 0;
    }
}
