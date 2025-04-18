package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.entity.RJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RJob)表服务接口
 *
 * @author makejava
 * @since 2025-03-19 13:56:26
 */
public interface RJobService {

    /**
     * 通过ID查询单条数据
     *
     * @param idJob 主键
     * @return 实例对象
     */
    RJob queryById(String idJob);

    /**
     * 分页查询
     *
     * @param rJob        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<RJob> queryByPage(RJob rJob, Integer pageNum, Integer pageSize, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param rJob 实例对象
     * @return 实例对象
     */
    RJob insert(RJob rJob);

    /**
     * 修改数据
     *
     * @param rJob 实例对象
     * @return 实例对象
     */
    RJob update(RJob rJob);

    /**
     * 通过主键删除数据
     *
     * @param idJob 主键
     * @return 是否成功
     */
    boolean deleteById(String idJob);

    void startJob(String filePath);
}
