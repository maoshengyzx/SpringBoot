package com.lp.kh.springbootlpkh.controller;

import com.lp.kh.springbootlpkh.service.T02AuditResultService;
import com.lp.kh.springbootlpkh.utils.AjaxResponseWrapper;
import com.lp.kh.springbootlpkh.vo.QualityReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author maosheng
 */
@RestController
@RequestMapping("/qualityReport")
@Slf4j
public class QualityReportController {

    private final T02AuditResultService t02AuditResultService;

    public QualityReportController(T02AuditResultService t02AuditResultService) {
        this.t02AuditResultService = t02AuditResultService;
    }

    /**
     * 查询质量报告
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return AjaxResponseWrapper
     */
    @GetMapping("/queryQualityReport")
    public AjaxResponseWrapper<QualityReportVO> queryQualityReport(@RequestParam(value = "day", required = false) String day) {
        return AjaxResponseWrapper.data(t02AuditResultService.queryQualityReport(day));
    }


    /**
     * 查看月份内是否有执行记录
     *
     * @param month 日期值， 格式为 yyyy-MM
     * @return AjaxResponseWrapper
     */
    @GetMapping("/listExecCalendar")
    public AjaxResponseWrapper<List<String>> listExecCalendar(@RequestParam(value = "month", required = false) String month) {
        return AjaxResponseWrapper.data(t02AuditResultService.listExecCalendar(month));
    }
}