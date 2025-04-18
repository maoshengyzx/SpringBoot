package com.lp.kh.springbootlpkh.service;

import com.lp.kh.springbootlpkh.vo.QualityReportVO;

import java.util.List;

/**
 * 稽核结果表(T02AuditResult)表服务接口
 *
 * @author maosheng
 * @since 2025-01-03 11:08:54
 */
public interface T02AuditResultService {


    /**
     * 查询质量报告
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 质量报告汇总对象
     */
    QualityReportVO queryQualityReport(String day);

    /**
     * 查看月份内是否有执行记录
     * @param month  日期值， 格式为 yyyy-MM
     * @return    执行记录列表
     */
    List<String> listExecCalendar(String month);
}
