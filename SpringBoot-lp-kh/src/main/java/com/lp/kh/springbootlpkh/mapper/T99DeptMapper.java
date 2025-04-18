package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T99Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 部门表(T99Dept)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
public interface T99DeptMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    T99Dept queryById(String deptId);

    /**
     * 查询指定行数据
     *
     * @param t99Dept  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<T99Dept> queryAllByLimit(T99Dept t99Dept, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t99Dept 查询条件
     * @return 总行数
     */
    long count(T99Dept t99Dept);

    /**
     * 新增数据
     *
     * @param t99Dept 实例对象
     * @return 影响行数
     */
    int insert(T99Dept t99Dept);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99Dept> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T99Dept> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99Dept> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T99Dept> entities);

    /**
     * 修改数据
     *
     * @param t99Dept 实例对象
     * @return 影响行数
     */
    int update(T99Dept t99Dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 影响行数
     */
    int deleteById(String deptId);

}

