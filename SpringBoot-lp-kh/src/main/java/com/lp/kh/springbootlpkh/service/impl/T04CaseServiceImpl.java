package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T04Case;
import com.lp.kh.springbootlpkh.mapper.T04CaseMapper;
import com.lp.kh.springbootlpkh.service.T04CaseService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 工单表(T04Case)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:58
 */
@Service("t04CaseService")
public class T04CaseServiceImpl implements T04CaseService {
    @Resource
    private T04CaseMapper t04CaseMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param caseId 主键
     * @return 实例对象
     */
    @Override
    public T04Case queryById(String caseId) {
        return this.t04CaseMapper.queryById(caseId);
    }

    /**
     * 分页查询
     *
     * @param t04Case     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<T04Case> queryByPage(T04Case t04Case, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t04CaseMapper.count(t04Case);
        return new PageImpl<>(this.t04CaseMapper.queryAllByLimit(t04Case, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t04Case 实例对象
     * @return 实例对象
     */
    @Override
    public T04Case insert(T04Case t04Case) {
        this.t04CaseMapper.insert(t04Case);
        return t04Case;
    }

    /**
     * 修改数据
     *
     * @param t04Case 实例对象
     * @return 实例对象
     */
    @Override
    public T04Case update(T04Case t04Case) {
        this.t04CaseMapper.update(t04Case);
        return this.queryById(t04Case.getCaseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param caseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String caseId) {
        return this.t04CaseMapper.deleteById(caseId) > 0;
    }
}
