package com.lp.kh.springbootlpkh.service.impl;

import com.lp.kh.springbootlpkh.mapper.T99DicMapper;
import com.lp.kh.springbootlpkh.service.T99DicService;
import com.lp.kh.springbootlpkh.vo.DimensionGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典表(T99Dic)表服务实现类
 *
 * @author makejava
 * @since 2025-01-03 11:09:00
 */
@Service("t99DicService")
public class T99DicServiceImpl implements T99DicService {
    @Resource
    private T99DicMapper t99DicMapper;


    /**
     * 按照维度进行汇总，统计每个维度中出现警告和严重的次数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 维度分组数据，饼状图
     */
    @Override
    public List<DimensionGroupVO> getDimensionGroupCount(String day) {
        return t99DicMapper.getDimensionGroupCount(day);
    }
}
