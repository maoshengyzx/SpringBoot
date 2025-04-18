package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T99DeptUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 部门用户关系表(T99DeptUser)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
public interface T99DeptUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    T99DeptUser queryById(String deptId);

    /**
     * 查询指定行数据
     *
     * @param t99DeptUser 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<T99DeptUser> queryAllByLimit(T99DeptUser t99DeptUser, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t99DeptUser 查询条件
     * @return 总行数
     */
    long count(T99DeptUser t99DeptUser);

    /**
     * 新增数据
     *
     * @param t99DeptUser 实例对象
     * @return 影响行数
     */
    int insert(T99DeptUser t99DeptUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99DeptUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T99DeptUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99DeptUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T99DeptUser> entities);

    /**
     * 修改数据
     *
     * @param t99DeptUser 实例对象
     * @return 影响行数
     */
    int update(T99DeptUser t99DeptUser);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 影响行数
     */
    int deleteById(String deptId);

}

