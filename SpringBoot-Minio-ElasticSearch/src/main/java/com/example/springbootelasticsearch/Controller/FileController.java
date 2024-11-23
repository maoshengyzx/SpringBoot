package com.example.springbootelasticsearch.Controller;

import com.example.springbootelasticsearch.entity.FileTable;
import com.example.springbootelasticsearch.service.FileTableService;
import com.example.springbootelasticsearch.utils.MinioUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: Controller
 * Package: com.example.springbootelasticsearch
 * Description:
 * 文件控制层
 *
 * @Author ms
 * @Create 2024/11/3 10:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {


    private final FileTableService fileTableService;

    public FileController(MinioUtils minioUtils, FileTableService fileTableService) {
        this.fileTableService = fileTableService;
    }

    /**
     * 文件上传
     * @param file
     * @param bucketName
     * @return
     */
    @GetMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, String bucketName) {
        fileTableService.uploadFile(file, bucketName);
        return "文件上传成功";
    }

    /**
     * 简单测试一个查询高亮
     *
     * @param id
     * @return
     */
    @GetMapping("/getInfoHighlight")
    public List<FileTable> getInfoHighlight(Long id) {
        return fileTableService.getInfoHighlight(id);
    }

}
