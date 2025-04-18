package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.RJobentry;
import com.lp.kh.springbootlpkh.mapper.RJobentryMapper;
import com.lp.kh.springbootlpkh.service.RJobentryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (RJobentry)表服务实现类
 *
 * @author makejava
 * @since 2025-03-19 13:56:27
 */
@Service("rJobentryService")
public class RJobentryServiceImpl implements RJobentryService {
    @Resource
    private RJobentryMapper rJobentryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentry 主键
     * @return 实例对象
     */
    @Override
    public RJobentry queryById(String idJobentry) {
        return this.rJobentryMapper.queryById(idJobentry);
    }

    /**
     * 分页查询
     *
     * @param rJobentry   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<RJobentry> queryByPage(RJobentry rJobentry, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.rJobentryMapper.count(rJobentry);
        return new PageImpl<>(this.rJobentryMapper.queryAllByLimit(rJobentry, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rJobentry 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentry insert(RJobentry rJobentry) {
        this.rJobentryMapper.insert(rJobentry);
        return rJobentry;
    }

    /**
     * 修改数据
     *
     * @param rJobentry 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentry update(RJobentry rJobentry) {
        this.rJobentryMapper.update(rJobentry);
        return this.queryById(rJobentry.getIdJobentry());
    }

    /**
     * 通过主键删除数据
     *
     * @param idJobentry 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String idJobentry) {
        return this.rJobentryMapper.deleteById(idJobentry) > 0;
    }
}
