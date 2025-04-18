package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.RJobentryCopy;
import com.lp.kh.springbootlpkh.mapper.RJobentryCopyMapper;
import com.lp.kh.springbootlpkh.service.RJobentryCopyService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (RJobentryCopy)表服务实现类
 *
 * @author makejava
 * @since 2025-03-19 13:56:29
 */
@Service("rJobentryCopyService")
public class RJobentryCopyServiceImpl implements RJobentryCopyService {
    @Resource
    private RJobentryCopyMapper rJobentryCopyMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param idJobentryCopy 主键
     * @return 实例对象
     */
    @Override
    public RJobentryCopy queryById(String idJobentryCopy) {
        return this.rJobentryCopyMapper.queryById(idJobentryCopy);
    }

    /**
     * 分页查询
     *
     * @param rJobentryCopy 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<RJobentryCopy> queryByPage(RJobentryCopy rJobentryCopy, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.rJobentryCopyMapper.count(rJobentryCopy);
        return new PageImpl<>(this.rJobentryCopyMapper.queryAllByLimit(rJobentryCopy, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rJobentryCopy 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentryCopy insert(RJobentryCopy rJobentryCopy) {
        this.rJobentryCopyMapper.insert(rJobentryCopy);
        return rJobentryCopy;
    }

    /**
     * 修改数据
     *
     * @param rJobentryCopy 实例对象
     * @return 实例对象
     */
    @Override
    public RJobentryCopy update(RJobentryCopy rJobentryCopy) {
        this.rJobentryCopyMapper.update(rJobentryCopy);
        return this.queryById(rJobentryCopy.getIdJobentryCopy());
    }

    /**
     * 通过主键删除数据
     *
     * @param idJobentryCopy 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String idJobentryCopy) {
        return this.rJobentryCopyMapper.deleteById(idJobentryCopy) > 0;
    }
}
