package com.example.springbootelasticsearch.service.impl;

import com.example.springbootelasticsearch.entity.FileTable;
import com.example.springbootelasticsearch.esrepositry.FileTableRepository;
import com.example.springbootelasticsearch.mapper.FileTableMapper;
import com.example.springbootelasticsearch.service.FileTableService;
import com.example.springbootelasticsearch.utils.FileUtils;
import com.example.springbootelasticsearch.utils.MinioUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * (FileTable)表服务实现类
 *
 * @author makejava
 * @since 2024-11-03 11:22:41
 */
@Service("fileTableService")
@AllArgsConstructor
public class FileTableServiceImpl implements FileTableService {

    private final FileTableMapper fileTableMapper;

    private final FileTableRepository fileTableRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    private final MinioUtils minioUtils;


    /**
     * 实例化完成后创建索引
     */
    @PostConstruct
    public void createIndex() {
        IndexOperations operations = elasticsearchOperations.indexOps(FileTable.class);
        if (!operations.exists()) {
            operations.create();
        }
        Document document = operations.createMapping();
        operations.putMapping(document);
    }


    /**
     * 新增数据
     *
     * @param fileTable 实例对象
     * @return 实例对象
     */
    @Override
    public FileTable insert(FileTable fileTable) {
        this.fileTableMapper.insert(fileTable);
        return fileTable;
    }


    /**
     * 获取file的文件内容，上次到es中来做检索
     *
     * @param file       文件对象
     * @param bucketName 桶名称
     */
    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file, String bucketName) {
        minioUtils.createBucket(bucketName);
        // 生产服务器文件名
        String objectName = bucketName + UUID.randomUUID().getLeastSignificantBits() + "_" + file.getOriginalFilename();
        Assert.notNull(file.getOriginalFilename(), "文件名不能为空");
        String fileType = file.getOriginalFilename().split("\\.")[1];
        minioUtils.putObject(bucketName, objectName, file.getInputStream(), file.getSize(), fileType);

        // 插入数据库数据
        String fileUrl = minioUtils.getObjectUrl(bucketName, objectName);
        FileTable fileTable = new FileTable();
        fileTable.setFileName(objectName);
        fileTable.setFilePath(fileUrl);
        fileTable.setIsDeleted(0L);
        fileTable.setFileType(fileType);
        fileTable.setFileSize(file.getSize());
        fileTableMapper.insert(fileTable);

        // 读取文件内容，上传到es，方便后续的检索  可以考虑使用消息队列，提高效率  因为读取文件内容比较耗时
        // 这里为了演示，直接读取文件内容，上传到es
        String fileContent = FileUtils.readFileContent(file.getInputStream(), fileType);
        fileTable.setFileContent(fileContent);
        fileTableRepository.save(fileTable);
    }

    @Override
    public List<FileTable> getInfoHighlight(Long id) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery("手册", "fileName", "fileContent"));
        queryBuilder.withQuery(QueryBuilders.termQuery("id", id));
        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        String[] fieldNames = {"fileName", "fileContent"};
        for (String fieldName : fieldNames) {
            highlightBuilder.field(fieldName);
        }
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        highlightBuilder.order();
        queryBuilder.withHighlightBuilder(highlightBuilder);
//        queryBuilder.withHighlightFields(new HighlightBuilder.Field("fileName"));

        // 也可以添加分页和排序
        SortBuilder<FieldSortBuilder> sortBuilder = new FieldSortBuilder("fileSize").order(SortOrder.DESC);
        queryBuilder.withSort(sortBuilder).withPageable(PageRequest.of(0, 10)); // 表示第一页，每页10条

        NativeSearchQuery nativeSearchQuery = queryBuilder.build();


        // 使用开启异步线程，执行查询
        CompletableFuture<SearchHits<FileTable>> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            return elasticsearchOperations.search(nativeSearchQuery, FileTable.class);
        });
        SearchHits<FileTable> searchHits = null;
        try {
            searchHits = future.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        ArrayList<FileTable> fileTables = new ArrayList<>();

        searchHits.forEach(item -> {
            fileTables.add(item.getContent());
        });

        return fileTables;
    }
}
