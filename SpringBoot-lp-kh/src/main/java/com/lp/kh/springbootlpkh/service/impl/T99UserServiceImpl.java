package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T99User;
import com.lp.kh.springbootlpkh.mapper.T99UserMapper;
import com.lp.kh.springbootlpkh.service.T99UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户表(T99User)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:09:01
 */
@Service("t99UserService")
public class T99UserServiceImpl implements T99UserService {
    @Resource
    private T99UserMapper t99UserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public T99User queryById(String userId) {
        return this.t99UserMapper.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param t99User     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<T99User> queryByPage(T99User t99User, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t99UserMapper.count(t99User);
        return new PageImpl<>(this.t99UserMapper.queryAllByLimit(t99User, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t99User 实例对象
     * @return 实例对象
     */
    @Override
    public T99User insert(T99User t99User) {
        this.t99UserMapper.insert(t99User);
        return t99User;
    }

    /**
     * 修改数据
     *
     * @param t99User 实例对象
     * @return 实例对象
     */
    @Override
    public T99User update(T99User t99User) {
        this.t99UserMapper.update(t99User);
        return this.queryById(t99User.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return this.t99UserMapper.deleteById(userId) > 0;
    }
}
