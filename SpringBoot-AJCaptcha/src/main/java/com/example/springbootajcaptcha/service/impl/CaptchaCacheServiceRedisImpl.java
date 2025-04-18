package com.example.springbootajcaptcha.service.impl;

import com.anji.captcha.service.CaptchaCacheService;
import com.google.auto.service.AutoService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {

    @Override
    public String type() {
        return "redis";
    }

    private static final String LUA_SCRIPT = "local key = KEYS[1] " +
            "local incrementValue = tonumber(ARGV[1]) " +
            "if redis.call('EXISTS', key) == 1 then " +
            "    return redis.call('INCRBY', key, incrementValue) " +
            "else " +
            "    return incrementValue " +
            "end";

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value, long expiresInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

	@Override
	public Long increment(String key, long val) {
        // 执行 Lua 脚本
        RedisScript<Long> script = new DefaultRedisScript<>(LUA_SCRIPT, Long.class);
        // 执行 Lua 脚本
        return stringRedisTemplate.execute(
                script,
                Collections.singletonList(key),
                String.valueOf(val)
        );
	}

    public void setExpire(String key, long l) {
        stringRedisTemplate.expire(key, l, TimeUnit.SECONDS);
    }
}