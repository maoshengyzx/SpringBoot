package com.example.luatest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.Resource;
import java.time.Duration;


/**
 * @author Administrator
 */
@Configuration
public class RedisConfig {

    /**
     * RedisTemplate配置
     *
     * @param factory
     * @return
     */
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        // 配置Redis主从结构
//        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
//        sentinelConfiguration.master("mymaster").sentinel("127.0.0.1", 26379)
//                .sentinel("127.0.0.1", 26380);

        template.setConnectionFactory(factory);
//        template.setEnableTransactionSupport(true);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);


        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

    /**
     * Redis Lua 脚本
     *
     * @return
     */
    @Bean
    DefaultRedisScript<Long> limitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/limit.lua")));
        return script;
    }
}
