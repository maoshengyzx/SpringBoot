package com.example.springbootmschema.service;

import com.example.springbootmschema.entity.schema.DB;

import java.util.List;

public interface InstanceService {
   public DB export(String datasourceId, Long schemaInstanceId);

}
