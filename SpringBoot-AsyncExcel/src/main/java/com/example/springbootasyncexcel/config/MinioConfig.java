package com.example.springbootasyncexcel.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MinioConfig
 * Package: com.example.springbootelasticsearch.config
 * Description:
 *
 * @Author ms
 * @Create 2024/11/3 10:33
 * @Version 1.0
 */
@Configuration
public class MinioConfig {

    private final MinioProp minioProp;

    public MinioConfig(MinioProp minioProp) {
        this.minioProp = minioProp;
    }

    /**
     * minio客户端
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProp.getEndpoint())
                .credentials(minioProp.getAccessKey(), minioProp.getSecretKey())
                .build();
    }
}
