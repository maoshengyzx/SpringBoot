package com.example.springbootddl.service;

import com.example.springbootddl.entity.DdlTemplate;

import java.util.List;

/**
 * ddll模板表(DdlTemplate)表服务接口
 *
 * @author makejava
 * @since 2025-06-21 10:24:03
 */
public interface DdlTemplateService {


    void createDdl(String version, String dbType);


    DdlTemplate queryByUnique(String version,String dbType);
}
