package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.T04DataqualityData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 数据质量问题关联规则数据表(T04DataqualityData)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:08:58
 */
public interface T04DataqualityDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    T04DataqualityData queryById(Long id);

    /**
     * 分页查询
     *
     * @param t04DataqualityData 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    Page<T04DataqualityData> queryByPage(T04DataqualityData t04DataqualityData, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param t04DataqualityData 实例对象
     * @return 实例对象
     */
    T04DataqualityData insert(T04DataqualityData t04DataqualityData);

    /**
     * 修改数据
     *
     * @param t04DataqualityData 实例对象
     * @return 实例对象
     */
    T04DataqualityData update(T04DataqualityData t04DataqualityData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
