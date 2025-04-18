package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.RJobentryAttribute;
import com.lp.kh.springbootlpkh.mapper.RJobentryAttributeMapper;
import com.lp.kh.springbootlpkh.service.RJobentryAttributeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RJobentryAttribute)表服务实现类
 *
 * @author makejava
 * @since 2025-03-19 13:56:28
 */
@Service("rJobentryAttributeService")
public class RJobentryAttributeServiceImpl implements RJobentryAttributeService {
    @Resource
    private RJobentryAttributeMapper rJobentryAttributeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryAttribute 主键
     * @return 实例对象
     */
    @Override
    public RJobentryAttribute queryById(String idJobentryAttribute) {
        return this.rJobentryAttributeMapper.queryById(idJobentryAttribute);
    }

    /**
     * 分页查询
     *
     * @param rJobentryAttribute 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @Override
    public Page<RJobentryAttribute> queryByPage(RJobentryAttribute rJobentryAttribute, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.rJobentryAttributeMapper.count(rJobentryAttribute);
        return new PageImpl<>(this.rJobentryAttributeMapper.queryAllByLimit(rJobentryAttribute, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rJobentryAttribute 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentryAttribute insert(RJobentryAttribute rJobentryAttribute) {
        this.rJobentryAttributeMapper.insert(rJobentryAttribute);
        return rJobentryAttribute;
    }

    /**
     * 修改数据
     *
     * @param rJobentryAttribute 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentryAttribute update(RJobentryAttribute rJobentryAttribute) {
        this.rJobentryAttributeMapper.update(rJobentryAttribute);
        return this.queryById(rJobentryAttribute.getIdJobentryAttribute());
    }

    /**
     * 通过主键删除数据
     *
     * @param idJobentryAttribute 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String idJobentryAttribute) {
        return this.rJobentryAttributeMapper.deleteById(idJobentryAttribute) > 0;
    }

    @Override
    public void insertBatch(List<RJobentryAttribute> rJobentryAttributeList) {
        rJobentryAttributeMapper.insertBatch(rJobentryAttributeList);
    }
}
