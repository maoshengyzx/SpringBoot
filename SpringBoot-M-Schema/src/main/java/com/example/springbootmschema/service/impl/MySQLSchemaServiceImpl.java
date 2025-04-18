package com.example.springbootmschema.service.impl;

import com.example.springbootmschema.mapper.InstanceMapper;
import com.example.springbootmschema.service.MySQLSchemaService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * ClassName: MySQLSchemaServiceImpl
 * Package: com.example.springbootmschema.service.impl
 * Description:
 *
 * @Author ms
 * @Create 2025/4/12 14:08
 * @Version 1.0
 */
@Service
public class MySQLSchemaServiceImpl implements MySQLSchemaService {

    @Resource
    private InstanceMapper instanceMapper;


}
