package com.example.springbootelasticsearch.utils;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * ClassName: MinioUtils
 * Package: com.example.springbootelasticsearch.utils
 * Description:
 * minio 工具类
 *
 * @Author ms
 * @Create 2024/11/3 10:36
 * @Version 1.0
 */
@Component
public class MinioUtils {

    private final MinioClient minioClient;

    public MinioUtils(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 创建桶
     *
     * @param bucketName
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .region("cn-beijing")
                            .build());
        }
    }

    /**
     * 删除桶
     *
     * @param bucketName 桶名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }


    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名
     * @param stream     流
     * @param fileSize   文件大小
     * @param type       文件类型
     * @throws Exception
     */
    public void putObject(String bucketName, String objectName, InputStream stream, Long fileSize, String type) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                stream, fileSize, -1)
                        .contentType(type)
                        .build());
    }


    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 桶名称
     * @param prefix     文件夹名字
     * @return
     */
    @SneakyThrows
    public Boolean folderExists(String bucketName, String prefix) {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName)
                .prefix(prefix).recursive(false).build());
        for (Result<Item> result : results) {
            Item item = result.get();
            if (item.isDir()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建文件夹
     *
     * @param bucketName 桶名称
     * @param path       路径
     */
    @SneakyThrows
    public void createFolder(String bucketName, String path) {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(path)
                .stream(new ByteArrayInputStream(new byte[]{}), 0, -1).build());
    }

    /**
     * 获取文件在minio在服务器上的外链
     *
     * @param bucketName 桶名称
     * @param objectName 文件名
     * @return
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
    }

    /**
     * 从minio下载文件为流
     *
     * @param bucketName  桶名称
     * @param objectName  文件名称
     * @return
     */
    @SneakyThrows
    public InputStream downloadObjectAsStream(String bucketName, String objectName) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();
        return minioClient.getObject(objectArgs);
    }

}
