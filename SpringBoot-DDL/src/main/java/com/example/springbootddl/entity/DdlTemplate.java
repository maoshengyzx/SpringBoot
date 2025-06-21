package com.example.springbootddl.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * ddll模板表(DdlTemplate)实体类
 *
 * @author makejava
 * @since 2025-06-21 10:24:03
 */
@Data
public class DdlTemplate implements Serializable {

    private static final long serialVersionUID = 916638723625753280L;

    private Long id;

    private String name;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private String createdBy;

    private String updatedBy;

    private String version;

    private String dbType;

    private String template;

}

