package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.entity.T04DataqualityData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 数据质量问题关联规则数据表(T04DataqualityData)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:58
 */
public interface T04DataqualityDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    T04DataqualityData queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param t04DataqualityData 查询条件
     * @param pageable           分页对象
     * @return 对象列表
     */
    List<T04DataqualityData> queryAllByLimit(T04DataqualityData t04DataqualityData, Pageable pageable);

    /**
     * 统计总行数
     *
     * @param t04DataqualityData 查询条件
     * @return 总行数
     */
    long count(T04DataqualityData t04DataqualityData);

    /**
     * 新增数据
     *
     * @param t04DataqualityData 实例对象
     * @return 影响行数
     */
    int insert(T04DataqualityData t04DataqualityData);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<T04DataqualityData> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<T04DataqualityData> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<T04DataqualityData> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<T04DataqualityData> entities);

    /**
     * 修改数据
     *
     * @param t04DataqualityData 实例对象
     * @return 影响行数
     */
    int update(T04DataqualityData t04DataqualityData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

