package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.vo.ProjectDetailsGroupVO;
import com.lp.kh.springbootlpkh.vo.RuleQualityVO;

import java.util.List;

/**
 * 质量专项表(T02Project)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:54
 */
public interface T02ProjectMapper {


    /**
     * 统计  t02_project 表中 所以专项数量
     *
     * @return 专项数量
     */
    int getProjectCount();

    /**
     * 按照专项名称分组统计当天执行的规则数, 检核总数, 严重数, 一般数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 专项分组数据，柱状图
     */
    List<ProjectDetailsGroupVO> getProjectDetailsGroupCount(String day);

    /**
     * 按照规则当天执行的规则警告次数和严重次数倒序排序
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 数据质量运行异常top10
     */
    List<RuleQualityVO> getRuleQualityVOS(String day);
}

