package com.example.springbootkafka.entry;

import lombok.Data;

/**
 * ClassName: Test
 * Package: com.example.springbootkafka.entry
 * Description:
 *
 * @Author ms
 * @Create 2025/4/18 10:09
 * @Version 1.0
 */

public class Test {

    private String name;

    private String age;

    private String sex;

    private String priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
