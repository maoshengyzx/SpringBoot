package com.lp.kh.springbootlpkh.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.lp.kh.springbootlpkh.exception.SystemException;
import com.lp.kh.springbootlpkh.mapper.T02AuditResultMapper;
import com.lp.kh.springbootlpkh.service.T02AuditResultService;
import com.lp.kh.springbootlpkh.service.T02ProjectService;
import com.lp.kh.springbootlpkh.service.T02RuleService;
import com.lp.kh.springbootlpkh.service.T99DicService;
import com.lp.kh.springbootlpkh.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 稽核结果表(T02AuditResult)表服务实现类
 *
 * @author maosheng
 * @since 2025-01-03 11:08:54
 */
@Service("t02AuditResultService")
@Slf4j
public class T02AuditResultServiceImpl implements T02AuditResultService {
    private final T02AuditResultMapper t02AuditResultMapper;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final T02ProjectService t02ProjectService;
    private final T02RuleService t02ruleService;
    private final T99DicService t99DicService;

    public T02AuditResultServiceImpl(T02AuditResultMapper t02AuditResultMapper, ThreadPoolExecutor threadPoolExecutor,
                                     T02ProjectService t02ProjectService, T02RuleService t02ruleService, T99DicService t99DicService) {
        this.t02AuditResultMapper = t02AuditResultMapper;
        this.threadPoolExecutor = threadPoolExecutor;
        this.t02ProjectService = t02ProjectService;
        this.t02ruleService = t02ruleService;
        this.t99DicService = t99DicService;
    }

    /**
     * 查询质量报告
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 质量报告VO
     */
    @Override
    public QualityReportVO queryQualityReport(String day) {
        // 1. 获取前五个任务的执行结果
        List<Integer> countResults = executeCountTasks(day);

        QualityReportVO qualityReportVO = new QualityReportVO();
        TotalDataQualityVO totalDataQualityVO = new TotalDataQualityVO();

        // 2. 设置前五个任务的执行结果到totalDataQualityVO对象中
        setTotalDataQualityVO(countResults, totalDataQualityVO);
        qualityReportVO.setTotalDataQualityVO(totalDataQualityVO);

        // 3. 获取第六个任务的执行结果，按照日期分组,折线图数据
        List<ResultDayGroupVO> resultDayGroupVOS = getResultDayGroupCount(day);
        qualityReportVO.setResultDayGroupVOS(resultDayGroupVOS);


        // 4. 获取第七个任务的执行结果，按照维度进行汇总，统计每个维度中出现警告和严重的次数,饼状图数据
        List<DimensionGroupVO> dimensionGroupVOS = getDimensionGroupCount(day);
        qualityReportVO.setDimensionGroupVOS(dimensionGroupVOS);


        // 5. 获取8，9 任务的执行结果，专项和规则一对多，把规则的执行记录汇总到专项上展示,柱状图数据
        List<ProjectDetailsGroupVO> projectDetailsGroupVOS = getProjectDetailsGroupCount(day);
        qualityReportVO.setProjectDetailsGroupVOS(projectDetailsGroupVOS);

        // 6. 获取10 任务的执行结果，按照规则当天执行的规则警告次数和严重次数倒序排序, 只取10条数据
        List<RuleQualityVO> ruleQualityVOS = getRuleQualityVOS(day);
        qualityReportVO.setRuleQualityVOS(ruleQualityVOS);

        log.info("qualityReportVO:{}", qualityReportVO);
        return qualityReportVO;
    }

    /**
     * 查看月份内是否有执行记录
     *
     * @param month 日期值， 格式为 yyyy-MM
     * @return 执行记录列表
     */
    @Override
    public List<String> listExecCalendar(String month) {
        month = StrUtil.isBlank(month) ? DateUtil.format(DateUtil.parseDate(DateUtil.today()), "yyyy-MM") : month;
        return t02AuditResultMapper.listExecCalendar(month);
    }

    /**
     * 统计任务 1,2,3,4,5
     *
     * @return 前五个任务的执行结果
     */
    private List<Integer> executeCountTasks(String day) {
        // 定义统计任务的Supplier列表，1,2,3,4,5 分别对应不同的统计任务
        List<Supplier<Integer>> countSuppliers = Arrays.asList(
                t02ProjectService::getProjectCount,       // 1. 统计所有的专项数量
                t02ruleService::getRuleCount,                  // 2. 统计所有的规则数量
                () -> getCheckCount(day),                        // 3. 统计当天所有的规则运行次数
                () -> getResultCount(2, 1, day),  // 4. 统计当天所有的规则运行警告次数
                () -> getResultCount(3, 1, day)   // 5. 统计当天所有的规则运行严重次数
        );
        // 创建CompletableFuture列表
        List<CompletableFuture<Integer>> futures = countSuppliers.stream()
                .map(supplier -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return supplier.get();
                    } catch (Exception e) {
                        handleException("统计任务出错", e);
                        return null; // 或者返回一个默认值，比如0
                    }
                }, threadPoolExecutor))
                .collect(Collectors.toList());

        // 直接收集所有任务的结果，而不是先等待所有任务完成再获取结果
        CompletableFuture<List<Integer>> allResultsFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(future -> {
                            try {
                                return future.join(); // 使用join而不是get，避免阻塞
                            } catch (CompletionException e) {
                                handleException("获取任务结果时出错", e);
                                return null; // 或者返回一个默认值
                            }
                        })
                        .collect(Collectors.toList()));

        // 尝试获取所有结果，这里仍然会阻塞，但只阻塞一次
        try {
            return allResultsFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            handleException("等待任务结果时出错", e);
            return Collections.emptyList(); // 或者返回部分已完成任务的结果
        } finally {
            log.info("所有任务完成");
        }
    }

    /**
     * 统计选中那天往前30天的规则运行次数, 统计结果按照日期进行分组
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 折线图数据
     */
    private List<ResultDayGroupVO> getResultDayGroupCount(String day) {
        day = StrUtil.isBlank(day) ? DateUtil.today() : day;
        return t02AuditResultMapper.getResultDayGroupCount(day);
    }


    /**
     * 按照维度进行汇总，统计每个维度中出现警告和严重的次数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 维度数据VO列表
     */
    private List<DimensionGroupVO> getDimensionGroupCount(String day) {
        return t99DicService.getDimensionGroupCount(day);
    }

    /**
     * 按照规则当天执行的规则警告次数和严重次数倒序排序
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 规则质量VO列表
     */
    private List<RuleQualityVO> getRuleQualityVOS(String day) {
        return t02ProjectService.getRuleQualityVOS(day);
    }


    /**
     * 专项和规则一对多，把规则的执行记录汇总到专项上展示
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 专项规则明细VO列表
     */
    private List<ProjectDetailsGroupVO> getProjectDetailsGroupCount(String day) {
        return t02ProjectService.getProjectDetailsGroupCount(day);
    }


    /**
     * 设置前五个任务的执行结果到totalDataQualityVO对象中
     *
     * @param countResults       前五个任务的执行结果
     * @param totalDataQualityVO 总数据质量VO对象
     */
    private void setTotalDataQualityVO(List<Integer> countResults, TotalDataQualityVO totalDataQualityVO) {
        if (CollUtil.size(countResults) >= 5) {
            totalDataQualityVO.setProjectCount(countResults.get(0));
            totalDataQualityVO.setRuleCount(countResults.get(1));
            totalDataQualityVO.setCheckCount(countResults.get(2));
            totalDataQualityVO.setAlertCount(countResults.get(3));
            totalDataQualityVO.setSevereCount(countResults.get(4));
        }
    }


    /**
     * 处理异常
     *
     * @param message 错误信息
     * @param e       异常对象
     */
    private void handleException(String message, Exception e) {
        log.error(message, e);
        throw new SystemException(e);
    }


    /**
     * 统计当天规则运行次数根据to2 audit result表中的create time判断进行统计总数
     *
     * @param day 日期值， 格式为 yyyy-MM-dd
     * @return 当天规则运行次数
     */
    private Integer getCheckCount(String day) {
        return t02AuditResultMapper.getCheckCount(day);
    }


    /**
     * 统计当天规则运行次数根据t02 audit result表中的create time还有result字段值判断进行统计,
     * 前提是结果执行成功。
     *
     * @param resultValue 2 或 3      2表示警告 3表示严重
     * @param status      0  或 1     0 表示执行失败 1表示执行成功
     * @param day         日期值， 格式为 yyyy-MM-dd
     * @return 当天规则运行警告或严重次数
     */
    private Integer getResultCount(int resultValue, int status, String day) {
        return t02AuditResultMapper.getResultCount(resultValue, status, day);
    }
}
