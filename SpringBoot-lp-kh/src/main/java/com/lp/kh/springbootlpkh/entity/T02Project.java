package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 质量专项表(T02Project)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:54
 */
public class T02Project implements Serializable {
    private static final long serialVersionUID = -83272692159216249L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 专项编号
     */
    private String projectNo;
    /**
     * 专项名称
     */
    private String projectName;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 截止时间
     */
    private Date endTime;
    /**
     * 项目状态，手动维护, 0：终止，1：运行
     */
    private String status;
    /**
     * 专项描述
     */
    private String remark;
    /**
     * 负责人id
     */
    private String managerId;
    /**
     * 创建人id
     */
    private String creatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改人
     */
    private String lastModifierId;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
    /**
     * 专项评分
     */
    private Long score;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifierId() {
        return lastModifierId;
    }

    public void setLastModifierId(String lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}

