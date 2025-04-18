package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.RJobHop;
import com.lp.kh.springbootlpkh.mapper.RJobHopMapper;
import com.lp.kh.springbootlpkh.service.RJobHopService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (RJobHop)表服务实现类
 *
 * @author makejava
 * @since 2025-03-19 13:56:27
 */
@Service("rJobHopService")
public class RJobHopServiceImpl implements RJobHopService {
    @Resource
    private RJobHopMapper rJobHopMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param idJobHop 主键
     * @return 实例对象
     */
    @Override
    public RJobHop queryById(String idJobHop) {
        return this.rJobHopMapper.queryById(idJobHop);
    }

    /**
     * 分页查询
     *
     * @param rJobHop     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<RJobHop> queryByPage(RJobHop rJobHop, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.rJobHopMapper.count(rJobHop);
        return new PageImpl<>(this.rJobHopMapper.queryAllByLimit(rJobHop, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rJobHop 实例对象
     * @return 实例对象
     */
    @Override
    public RJobHop insert(RJobHop rJobHop) {
        this.rJobHopMapper.insert(rJobHop);
        return rJobHop;
    }

    /**
     * 修改数据
     *
     * @param rJobHop 实例对象
     * @return 实例对象
     */
    @Override
    public RJobHop update(RJobHop rJobHop) {
        this.rJobHopMapper.update(rJobHop);
        return this.queryById(rJobHop.getIdJobHop());
    }

    /**
     * 通过主键删除数据
     *
     * @param idJobHop 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String idJobHop) {
        return this.rJobHopMapper.deleteById(idJobHop) > 0;
    }
}
