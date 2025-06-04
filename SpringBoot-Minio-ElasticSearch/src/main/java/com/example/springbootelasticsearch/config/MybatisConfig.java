package com.example.springbootelasticsearch.config;

import com.example.springbootelasticsearch.Interceptor.LoggingInterceptor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * ClassName: MybatisConfig
 * Package: com.example.springbootelasticsearch.config
 * Description:
 *
 * @Author ms
 * @Create 2025/5/19 13:41
 * @Version 1.0
 */
@Configuration
@MapperScan("com.example.springbootelasticsearch.mapper")
public class MybatisConfig {

    /**
     * 配置mybatis
     */
    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

//    @Bean
//    public DatabaseIdProvider databaseIdProvider() {
//        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
//        Properties properties = new Properties();
//
//        // 根据不同的数据库产品名称设置对应的 databaseId
//        properties.setProperty("SQL Server", "sqlserver");
//        properties.setProperty("DB2", "db2");
//        properties.setProperty("Oracle", "oracle");
//        properties.setProperty("MySQL", "mysql");
//        // 如果需要支持更多数据库，请在此添加
//
//        databaseIdProvider.setProperties(properties);
//        return databaseIdProvider;
//    }
}
