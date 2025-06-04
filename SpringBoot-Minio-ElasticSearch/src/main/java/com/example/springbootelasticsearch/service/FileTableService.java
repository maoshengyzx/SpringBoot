package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.common.Page;
import com.example.springbootelasticsearch.entity.FileTable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (FileTable)表服务接口
 *
 * @author makejava
 * @since 2024-11-03 11:22:41
 */
public interface FileTableService {

    /**
     * 新增数据
     *
     * @param fileTable 实例对象
     * @return 实例对象
     */
    FileTable insert(FileTable fileTable);


    void uploadFile(MultipartFile file, String bucketName);

    /**
     * 查询高亮
     * @param id
     * @return
     */
    List<FileTable> getInfoHighlight(Long id);


    Page<FileTable> listInfo(Page<FileTable> page);
}
