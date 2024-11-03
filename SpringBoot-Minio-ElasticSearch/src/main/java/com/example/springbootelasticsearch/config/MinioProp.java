package com.example.springbootelasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ClassName: MinioConfig
 * Package: com.example.springbootelasticsearch.config
 * Description:
 * minio 属性配置
 *
 * @Author ms
 * @Create 2024/11/3 10:31
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProp {
    // minio 连接地址
    private String endpoint;

    // 公钥
    private String accessKey;

    // 私钥
    private String secretKey;
}
