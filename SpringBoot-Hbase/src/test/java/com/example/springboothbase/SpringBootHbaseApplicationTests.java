package com.example.springboothbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.*;

@Slf4j
@SpringBootTest
class SpringBootHbaseApplicationTests {

    @Test
    void contextLoads() throws IOException {
        Admin admin = testHbaseConnection("192.168.10.3:2181");
        log.info("hbase连接成功");
        NamespaceDescriptor[] descriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor descriptor : descriptors) {
            log.info(descriptor.getName());
        }
    }


    public static Admin testHbaseConnection(String zookeeperQuorum) {
//		assert.requireNonNull(zookeeperQuorum, "zookeeper连接地址不能为空");
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
//			throw SystemException.wrap("无法连接数据源，{}", ObjectUtil.isEmpty(ExceptionUtil.getCauseMessage(e)) ? "连接信息错误" : e.getMessage());
            throw new RuntimeException("无法连接数据源，连接信息错误", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
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
                return ConnectionFactory.createConnection(configuration);
            }
        });
        // 设置一个线程异步执行任务
        new Thread(futureTask).start();
        // 异步请求设置超时
        return futureTask.get(10, TimeUnit.SECONDS);
    }
}
