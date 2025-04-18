package com.example.luatest.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * ClassName: Person
 * Package: com.example.luatest.domain
 * Description:
 *
 * @Author ms
 * @Create 2024/11/30 10:01
 * @Version 1.0
 */
@RedisHash(value = "person", timeToLive = 60)
@Data
public class Person {

    @Id
    private String id;

    private String name;

    private Integer age;


}
