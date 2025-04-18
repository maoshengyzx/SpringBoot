package com.example.springbootmschema.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.example.springbootmschema.entity.schema.DB;
import com.example.springbootmschema.service.InstanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ClassName: MScheamController
 * Package: com.example.springbootmschema.controller
 * Description:
 *
 * @Author ms
 * @Create 2025/4/12 17:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/mschema")
public class MScheamController {

    private static final Log log = Log.get();

    @Resource
    private InstanceService instanceService;

    @GetMapping("/export")
    public void export(@RequestParam String datasourceId, @RequestParam Long schemaInstanceId) throws IOException {
        DB export = instanceService.export(datasourceId, schemaInstanceId);
//        String json = JSONUtil.toJsonStr(export,);
//        log.info("结果json:{}", json);
        // 保存到文件

        String json = new ObjectMapper().writeValueAsString(export);

        Files.write(Path.of("D:\\schema.json"),json.getBytes(StandardCharsets.UTF_8));
//        FileUtil.writeBytes(json.getBytes(StandardCharsets.UTF_8), "schema_output.json");
//        Files.write(Paths.get("schema_output.json"),
//                json.getBytes(StandardCharsets.UTF_8));
        log.info("导出成功");
    }
}
