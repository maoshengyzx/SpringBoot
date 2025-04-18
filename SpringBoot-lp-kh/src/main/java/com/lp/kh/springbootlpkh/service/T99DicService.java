package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.vo.DimensionGroupVO;

import java.util.List;

/**
 * 字典表(T99Dic)表服务接口
 *
 * @author makejava
 * @since 2025-01-03 11:09:00
 */
public interface T99DicService {


    /**
     * 按照维度进行汇总，统计每个维度中出现警告和严重的次数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 维度分组数据，饼状图
     */
    List<DimensionGroupVO> getDimensionGroupCount(String day);
}
