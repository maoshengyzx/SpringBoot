package com.example.luatest.myaspect;


import com.example.luatest.annotition.RateLimiter;
import com.example.luatest.exception.IPException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 */
@Aspect
@Component    //切面类也需要加入到ioc容器
public class RateLimitAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitAspect.class);

    private final  RedisTemplate<String, Object> redisTemplate;


    private final DefaultRedisScript<Long> limitScript;

    public RateLimitAspect(RedisTemplate<String, Object> redisTemplate, DefaultRedisScript<Long> limitScript) {
        this.redisTemplate = redisTemplate;
        this.limitScript = limitScript;
    }

    @Before("@annotation(rateLimiter)")
    public void isAllowed(JoinPoint proceedingJoinPoint, RateLimiter rateLimiter) throws IPException, InstantiationException, IllegalAccessException {
        String ip = null;
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;
                ip = request.getLocalAddr();
                break;
            }
        }
        LOGGER.info("ip:{}", ip);
        if (ip == null) {
            throw new IPException("ip is null");
        }


        //拼接redis建
        String key = rateLimiter.key() + ip;

        // 执行 Lua 脚本进行限流判断
        List<String> keyList = Collections.singletonList(key);
        Long result = redisTemplate
                .execute(limitScript, keyList, Integer.toString(rateLimiter.count())
                        , Integer.toString(rateLimiter.time()));
        LOGGER.info("result:{}", result);
        if (result != null && result > rateLimiter.count()) {
            throw new IPException("IP [" + ip + "] 访问过于频繁，已超出限流次数");
        }
    }
}
