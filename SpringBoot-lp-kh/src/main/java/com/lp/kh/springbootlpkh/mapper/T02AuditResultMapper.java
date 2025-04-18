package com.lp.kh.springbootlpkh.mapper;

import com.lp.kh.springbootlpkh.vo.ResultDayGroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 稽核结果表(T02AuditResult)表数据库访问层
 *
 * @author makejava
 * @since 2025-01-03 11:08:52
 */
public interface T02AuditResultMapper {

    /**
     * 统计当天规则运行次数根据to2 audit result表中的create time判断进行统计总数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 当天规则运行次数
     */
    int getCheckCount(String day);

    /**
     * 统计当天规则运行次数根据t02 audit result表中的create time还有result字段值判断进行统计, 前提是结果执行成功。
     *
     * @param resultValue 2 或 3      2表示警告 3表示严重
     * @param status      0  或 1     0 表示执行失败 1表示执行成功
     * @param day         日期值， 格式为 yyyy-MM-dd
     * @return 当天规则运行警告或严重次数
     */
    int getResultCount(@Param("resultValue") int resultValue, @Param("status") int status, @Param("day") String day);

    /**
     * 统计选中那天往前30天的规则运行次数, 统计结果按照日期进行分组
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 折线图数据
     */
    List<ResultDayGroupVO> getResultDayGroupCount(String day);

    /**
     * 查看月份内是否有执行记录
     *
     * @param month 日期值， 格式为 yyyy-MM
     * @return 执行记录列表
     */
    List<String> listExecCalendar(String month);
}

