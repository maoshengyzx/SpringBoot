package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.mapper.T02ProjectMapper;
import com.lp.kh.springbootlpkh.service.T02ProjectService;
import com.lp.kh.springbootlpkh.vo.ProjectDetailsGroupVO;
import com.lp.kh.springbootlpkh.vo.RuleQualityVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 质量专项表(T02Project)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:08:55
 */
@Service("t02ProjectService")
public class T02ProjectServiceImpl implements T02ProjectService {
    @Resource
    private T02ProjectMapper t02ProjectMapper;


    /**
     * 统计  t02_project 表中 所以专项数量
     *
     * @return 专项数量
     */
    @Override
    public Integer getProjectCount() {
        return t02ProjectMapper.getProjectCount();
    }

    /**
     * 按照专项名称分组统计当天执行的规则数, 检核总数, 严重数, 一般数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 专项分组数据，柱状图
     */
    @Override
    public List<ProjectDetailsGroupVO> getProjectDetailsGroupCount(String day) {
        return t02ProjectMapper.getProjectDetailsGroupCount(day);
    }

    /**
     * 按照规则当天执行的规则警告次数和严重次数倒序排序
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 数据质量运行异常top10
     */
    @Override
    public List<RuleQualityVO> getRuleQualityVOS(String day) {
        return t02ProjectMapper.getRuleQualityVOS(day);
    }
}
