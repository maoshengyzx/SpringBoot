package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T99User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户表(T99User)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:09:00
 */
public interface T99UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    T99User queryById(String userId);

    /**
     * 查询指定行数据
     *
     * @param t99User  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<T99User> queryAllByLimit(T99User t99User, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t99User 查询条件
     * @return 总行数
     */
    long count(T99User t99User);

    /**
     * 新增数据
     *
     * @param t99User 实例对象
     * @return 影响行数
     */
    int insert(T99User t99User);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T99User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T99User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T99User> entities);

    /**
     * 修改数据
     *
     * @param t99User 实例对象
     * @return 影响行数
     */
    int update(T99User t99User);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

}

