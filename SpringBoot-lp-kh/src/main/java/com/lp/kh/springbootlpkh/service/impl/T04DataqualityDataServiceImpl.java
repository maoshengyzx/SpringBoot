package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T04DataqualityData;
import com.lp.kh.springbootlpkh.mapper.T04DataqualityDataMapper;
import com.lp.kh.springbootlpkh.service.T04DataqualityDataService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 数据质量问题关联规则数据表(T04DataqualityData)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:58
 */
@Service("t04DataqualityDataService")
public class T04DataqualityDataServiceImpl implements T04DataqualityDataService {
    @Resource
    private T04DataqualityDataMapper t04DataqualityDataMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public T04DataqualityData queryById(Long id) {
        return this.t04DataqualityDataMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param t04DataqualityData 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @Override
    public Page<T04DataqualityData> queryByPage(T04DataqualityData t04DataqualityData, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t04DataqualityDataMapper.count(t04DataqualityData);
        return new PageImpl<>(this.t04DataqualityDataMapper.queryAllByLimit(t04DataqualityData, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t04DataqualityData 实例对象
     * @return 实例对象
     */
    @Override
    public T04DataqualityData insert(T04DataqualityData t04DataqualityData) {
        this.t04DataqualityDataMapper.insert(t04DataqualityData);
        return t04DataqualityData;
    }

    /**
     * 修改数据
     *
     * @param t04DataqualityData 实例对象
     * @return 实例对象
     */
    @Override
    public T04DataqualityData update(T04DataqualityData t04DataqualityData) {
        this.t04DataqualityDataMapper.update(t04DataqualityData);
        return this.queryById(t04DataqualityData.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.t04DataqualityDataMapper.deleteById(id) > 0;
    }
}
