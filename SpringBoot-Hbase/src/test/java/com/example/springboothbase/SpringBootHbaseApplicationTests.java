package com.example.springboothbase;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class SpringBootHbaseApplicationTests {

    private Set<String> headerCache = new HashSet<>();

    private Admin admin;

    @Test
    void contextLoads() throws IOException {
        // 这个地方虽然会显示连接成功，但当去拿去hbase的元数据时会出现网络错误，原因是使用 docker 部署hbase，hbase的ip地址变为容器id，所以域名解析器解析不到ip地址, 这里有两种解决办法
        // 1. 在 hosts 文件中 配置域名解析 配置为 ip地址 容器id  示例：xxx.xxx.xx.xx c360bd9ce150。 注意这个 ip 地址必须是宿主机的ip地址，不是容器的ip地址
        // 2. 自定义域名解析
        this.admin = testHbaseConnection("192.168.10.3:2181");
        log.info("hbase连接成功");
        NamespaceDescriptor[] descriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor namespace : descriptors) {
            log.info("namespace: {}", namespace.getName());
            for (TableDescriptor tableDescriptor : admin.listTableDescriptorsByNamespace(namespace.getName().getBytes())) {
                log.info("table: {}", tableDescriptor.getTableName().getQualifierAsString());
                ColumnFamilyDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
                for (ColumnFamilyDescriptor columnFamily : columnFamilies) {
                    log.info("columnFamily: {}", columnFamily.getNameAsString());
                }
            }
        }
    }



    public static Admin testHbaseConnection(String zookeeperQuorum) {
        org.apache.hadoop.hbase.client.Connection connection = null;
        try {
            connection = getHbaseConnection(zookeeperQuorum);
            Admin admin = connection.getAdmin();
            // do nothing
            log.info("hbase连接成功");
            return admin;
        } catch (Exception e) {
            log.error("", e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("无法连接数据源，连接信息错误", e);
        } finally {

        }
    }


    public static org.apache.hadoop.hbase.client.Connection getHbaseConnection(String zookeeperQuorum) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<Connection> futureTask = new FutureTask<>(new Callable<Connection>() {
            @Override
            public org.apache.hadoop.hbase.client.Connection call() throws Exception {
                Configuration configuration = HBaseConfiguration.create();
                configuration.set("hbase.zookeeper.quorum", zookeeperQuorum);
                configuration.setInt("zookeeper.recovery.retry", 1);
                configuration.set("hbase.client.retries.number", "1");
                configuration.set("hbase.rpc.timeout", "5000");
                configuration.set("hbase.client.operation.timeout", "5000");
                configuration.set("hbase.client.scanner.timeout.period", "10000");
//                HBaseAdmin.checkHBaseAvailable(configuration);
                HBaseAdmin.available(configuration);
                return ConnectionFactory.createConnection(configuration);
            }
        });
        // 设置一个线程异步执行任务
        new Thread(futureTask).start();
        // 异步请求设置超时
        return futureTask.get(10, TimeUnit.SECONDS);
    }
}
