package com.lp.kh.springbootlpkh.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (RJobentry)实体类
 *
 * @author makejava
 * @since 2025-03-19 13:56:27
 */
@Data
public class RJobentry implements Serializable {
    private static final long serialVersionUID = -13569281420020721L;

    private String idJobentry;

    private String idJob;

    private String idJobentryType;

    private String name;

    private String description;


    private RJobentryCopy rJobentryCopy;

    private List<RJobentryAttribute> rJobentryAttributeList;




}

