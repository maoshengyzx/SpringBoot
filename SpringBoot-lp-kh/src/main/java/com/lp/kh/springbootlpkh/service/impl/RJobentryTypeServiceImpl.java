package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.RJobentryType;
import com.lp.kh.springbootlpkh.mapper.RJobentryTypeMapper;
import com.lp.kh.springbootlpkh.service.RJobentryTypeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (RJobentryType)表服务实现类
 *
 * @author makejava
 * @since 2025-03-19 13:56:29
 */
@Service("rJobentryTypeService")
public class RJobentryTypeServiceImpl implements RJobentryTypeService {
    @Resource
    private RJobentryTypeMapper rJobentryTypeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryType 主键
     * @return 实例对象
     */
    @Override
    public RJobentryType queryById(String idJobentryType) {
        return this.rJobentryTypeMapper.queryById(idJobentryType);
    }

    /**
     * 分页查询
     *
     * @param rJobentryType 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<RJobentryType> queryByPage(RJobentryType rJobentryType, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.rJobentryTypeMapper.count(rJobentryType);
        return new PageImpl<>(this.rJobentryTypeMapper.queryAllByLimit(rJobentryType, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rJobentryType 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentryType insert(RJobentryType rJobentryType) {
        this.rJobentryTypeMapper.insert(rJobentryType);
        return rJobentryType;
    }

    /**
     * 修改数据
     *
     * @param rJobentryType 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentryType update(RJobentryType rJobentryType) {
        this.rJobentryTypeMapper.update(rJobentryType);
        return this.queryById(rJobentryType.getIdJobentryType());
    }

    /**
     * 通过主键删除数据
     *
     * @param idJobentryType 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String idJobentryType) {
        return this.rJobentryTypeMapper.deleteById(idJobentryType) > 0;
    }

    @Override
    public RJobentryType queryByCode(String type) {
        return rJobentryTypeMapper.queryByCode(type);
    }
}
