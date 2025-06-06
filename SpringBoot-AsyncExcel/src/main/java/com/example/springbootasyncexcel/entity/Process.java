package com.example.springbootasyncexcel.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Process)实体类
 *
 * @author makejava
 * @since 2025-06-05 15:11:23
 */
@Data
public class Process implements Serializable {
    private static final long serialVersionUID = -44376018570190342L;

    private Long processId;

    private String processName;

    private String processType;

    private Date startTime;

    private Date endTime;

    /**
     * 0 未开始 1 进行中 2 完成 3 失败 4 取消
     */
    private byte status;

    private String description;

    private String url;

    private String params;
}

