package com.example.luatest;

import com.example.luatest.domain.Person;
import com.example.luatest.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * ClassName: LuaTestApplicationTest
 * Package: com.example.luatest
 * Description:
 *
 * @Author ms
 * @Create 2024/11/7 23:05
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LuaTestApplicationTest {


    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private PersonRepository personRepository;

    @Test
    public void test() {
//        redisTemplate.multi();
//        redisTemplate.opsForValue().set("key", "value");
//        redisTemplate.exec();
//        redisTemplate.executePipelined();
    }


    @Test
    public void testPerson() {
        Person person = new Person();
        person.setName("ms");
        person.setAge(18);
        personRepository.save(person);

        System.out.println("-----------------");

        System.out.println(personRepository.findById(person.getId()));

    }


}
