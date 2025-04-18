package com.lp.kh.springbootlpkh.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.XmlUtil;
import com.lp.kh.springbootlpkh.entity.RJob;
import com.lp.kh.springbootlpkh.entity.RJobHop;
import com.lp.kh.springbootlpkh.entity.RJobentry;
import com.lp.kh.springbootlpkh.entity.RJobentryCopy;
import com.lp.kh.springbootlpkh.mapper.RJobMapper;
import com.lp.kh.springbootlpkh.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (RJob)表服务实现类
 *
 * @author makejava
 * @since 2025-03-19 13:56:26
 */
@Service("rJobService")
public class RJobServiceImpl implements RJobService {
    @Resource
    private RJobMapper rJobMapper;

    @Resource
    private RJobentryTypeService rJobentryTypeService;

    @Resource
    private RJobentryService rJobentryService;

    @Resource
    private RJobentryCopyService rJOBentryCopyService;

    @Resource
    private RJobHopService rJobHopService;

    @Resource
    private RJobentryAttributeService rJobentryAttributeService;

    /**
     * 通过ID查询单条数据
     *
     * @param idJob 主键
     * @return 实例对象
     */
    @Override
    public RJob queryById(String idJob) {
        return this.rJobMapper.queryById(idJob);
    }

    /**
     * 分页查询
     *
     * @param rJob        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<RJob> queryByPage(RJob rJob, Integer pageNum, Integer pageSize, PageRequest pageRequest) {
        long total = this.rJobMapper.count(rJob);
        return new PageImpl<>(this.rJobMapper.queryAllByLimit(rJob, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param rJob 实例对象
     * @return 实例对象
     */
    @Override
    public RJob insert(RJob rJob) {
        this.rJobMapper.insert(rJob);
        return rJob;
    }

    /**
     * 修改数据
     *
     * @param rJob 实例对象
     * @return 实例对象
     */
    @Override
    public RJob update(RJob rJob) {
        this.rJobMapper.update(rJob);
        return this.queryById(rJob.getIdJob());
    }

    /**
     * 通过主键删除数据
     *
     * @param idJob 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String idJob) {
        return this.rJobMapper.deleteById(idJob) > 0;
    }

    @Override
    @Transactional
    public void startJob(String filePath) {
        File file = FileUtil.file(filePath);

        Document document = XmlUtil.readXML(file);
        // 构建rJob对象
        RJob rJob = buildRJob(document);

        // 构建 r_job_hop 对象
        List<RJobHop> rJobHopList = buildRJobHop(document, rJob.getIdJob());

        // 构建r_jobentry 对象
        List<RJobentry> rJobentryList = buildRJobentry(document, rJob.getIdJob());


        // 批量插入rJob对象
        rJobMapper.insert(rJob);
        // 批量插入rJobHop对象
        if (CollUtil.isNotEmpty(rJobHopList)) {
            rJobHopList.forEach(item -> {
                rJobHopService.insert(item);
            });
        }
        // 批量插入rJobentry对象
        if (CollUtil.isNotEmpty(rJobentryList)) {
            rJobentryList.forEach(item -> {
                rJobentryService.insert(item);
            });
        }

        // 批量插入rJobentryCopy对象
        if (CollUtil.isNotEmpty(rJobentryList)) {
            rJobentryList.forEach(item -> {
                rJOBentryCopyService.insert(item.getRJobentryCopy());
            });
        }

        // 批量插入rJobentryAttribute对象
        if (CollUtil.isNotEmpty(rJobentryList)) {
            rJobentryList.forEach(item -> {
                rJobentryAttributeService.insertBatch(item.getRJobentryAttributeList());
            });
        }


    }


    public RJob buildRJob(Document document) {
        // 获取job节点
        Element rootElement = XmlUtil.getRootElement(document);

        // 获取job节点的属性
        String jobName = rootElement.getAttribute("name");
        String description = rootElement.getAttribute("description");
        String extendedDescription = rootElement.getAttribute("extended_description");
        String jobVersion = rootElement.getAttribute("job_version");
        String jobStatus = rootElement.getAttribute("job_status");
        String idDirectory = rootElement.getAttribute("directory");
        String createdUser = rootElement.getAttribute("created_user");
        String createdDate = rootElement.getAttribute("created_date");
        String modifiedUser = rootElement.getAttribute("modified_user");
        String modifiedDate = rootElement.getAttribute("modified_date");
        String passBatchId = rootElement.getAttribute("pass_batchid");
        String idJob = IdUtil.fastSimpleUUID();
        RJob rJob = new RJob();
        rJob.setIdJob(idJob);
        rJob.setName(jobName);
        rJob.setDescription(description);
        rJob.setExtendedDescription(extendedDescription);
        rJob.setJobVersion(jobVersion);
        rJob.setJobStatus(jobStatus);
        rJob.setIdDirectory(idDirectory);
        rJob.setCreatedUser(createdUser);
        rJob.setCreatedDate(DateUtil.parseDate(createdDate));
        rJob.setModifiedUser(modifiedUser);
        rJob.setModifiedDate(DateUtil.parseDate(modifiedDate));
        rJob.setPassBatchId(passBatchId);
        return rJob;
    }

    public List<RJobHop> buildRJobHop(Document document, String idJob) {
        // 获取job节点
        Element rootElement = XmlUtil.getRootElement(document);
        // 获取hops节点的属性
        Element hopsElement = XmlUtil.getElement(rootElement, "hops");

        List<Element> hopElements = XmlUtil.getElements(hopsElement, "hop");

        return hopElements.stream().map(hopElement -> {
            RJobHop rJobHop = new RJobHop();
            rJobHop.setIdJobHop(IdUtil.fastSimpleUUID());
            rJobHop.setIdJob(idJob);
            rJobHop.setIdJobentryCopyFrom(XmlUtil.getElement(hopElement, "form").getNodeValue());
            rJobHop.setIdJobentryCopyTo(XmlUtil.getElement(hopElement, "to").getNodeValue());
            rJobHop.setEnabled(XmlUtil.getElement(hopElement, "enabled").getNodeValue());
            rJobHop.setEvaluation(XmlUtil.getElement(hopElement, "evaluation").getNodeValue());
            rJobHop.setUnconditional(XmlUtil.getElement(hopElement, "unconditional").getNodeValue());
            return rJobHop;
        }).collect(Collectors.toList());
    }


    public List<RJobentry> buildRJobentry(Document document, String idJob) {
        // 获取job节点
        Element rootElement = XmlUtil.getRootElement(document);

        // 获取entries节点的属性
        Element entriesElement = XmlUtil.getElement(rootElement, "entries");
        List<Element> entryElements = XmlUtil.getElements(entriesElement, "entry");
        return entryElements.stream().map(entryElement -> {
            RJobentry rJobentry = new RJobentry();
            rJobentry.setIdJobentry(IdUtil.fastSimpleUUID());
            rJobentry.setIdJob(idJob);
            rJobentry.setIdJobentryType(rJobentryTypeService.queryByCode(XmlUtil.getElement(entryElement, "type").getNodeValue())
                    .getIdJobentryType());
            rJobentry.setName(XmlUtil.getElement(entryElement, "name").getNodeValue());
            rJobentry.setDescription(XmlUtil.getElement(entryElement, "description").getNodeValue());
            // 构建r_jobentry_attribute 对象，暂无数据
            rJobentry.setRJobentryAttributeList(CollUtil.newArrayList());

            // 构建 r_jobentry_copy 对象
            rJobentry.setRJobentryCopy(buildRJobentryCopy(entryElement, idJob, rJobentry.getIdJobentry(), rJobentry.getIdJobentryType()));
            return rJobentry;
        }).collect(Collectors.toList());
    }

    private RJobentryCopy buildRJobentryCopy(Element entryElement, String idJob, String idJobentry, String idJobentryType) {
        RJobentryCopy rJobentryCopy = new RJobentryCopy();
        rJobentryCopy.setIdJobentryCopy(IdUtil.fastSimpleUUID());
        rJobentryCopy.setIdJob(idJob);
        rJobentryCopy.setIdJobentry(idJobentry);
        rJobentryCopy.setIdJobentryType(idJobentryType);
        rJobentryCopy.setNr(XmlUtil.getElement(entryElement, "nr").getNodeValue());
        rJobentryCopy.setGuiLocationX(XmlUtil.getElement(entryElement, "xloc").getNodeValue());
        rJobentryCopy.setGuiLocationY(XmlUtil.getElement(entryElement, "yloc").getNodeValue());
        rJobentryCopy.setGuiDraw(XmlUtil.getElement(entryElement, "draw").getNodeValue());
        rJobentryCopy.setParallel(XmlUtil.getElement(entryElement, "parallel").getNodeValue());
        return rJobentryCopy;
    }


}
