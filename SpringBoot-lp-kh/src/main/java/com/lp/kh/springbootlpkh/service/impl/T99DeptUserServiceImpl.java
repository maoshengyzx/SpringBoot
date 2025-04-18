package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.entity.T99DeptUser;
import com.lp.kh.springbootlpkh.mapper.T99DeptUserMapper;
import com.lp.kh.springbootlpkh.service.T99DeptUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 部门用户关系表(T99DeptUser)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
@Service("t99DeptUserService")
public class T99DeptUserServiceImpl implements T99DeptUserService {
    @Resource
    private T99DeptUserMapper t99DeptUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public T99DeptUser queryById(String deptId) {
        return this.t99DeptUserMapper.queryById(deptId);
    }

    /**
     * 分页查询
     *
     * @param t99DeptUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<T99DeptUser> queryByPage(T99DeptUser t99DeptUser, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.t99DeptUserMapper.count(t99DeptUser);
        return new PageImpl<>(this.t99DeptUserMapper.queryAllByLimit(t99DeptUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param t99DeptUser 实例对象
     * @return 实例对象
     */
    @Override
    public T99DeptUser insert(T99DeptUser t99DeptUser) {
        this.t99DeptUserMapper.insert(t99DeptUser);
        return t99DeptUser;
    }

    /**
     * 修改数据
     *
     * @param t99DeptUser 实例对象
     * @return 实例对象
     */
    @Override
    public T99DeptUser update(T99DeptUser t99DeptUser) {
        this.t99DeptUserMapper.update(t99DeptUser);
        return this.queryById(t99DeptUser.getDeptId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deptId) {
        return this.t99DeptUserMapper.deleteById(deptId) > 0;
    }
}
