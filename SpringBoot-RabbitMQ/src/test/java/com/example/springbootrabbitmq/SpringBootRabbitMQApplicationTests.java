package com.example.springbootrabbitmq;

import com.example.springbootrabbitmq.entity.CanalEntity;
import com.example.springbootrabbitmq.utils.ParseEntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRabbitMQApplicationTests {

    @Test
    public void contextLoads() {
        byte[] byteData = new byte[]{123,34,100,97,116,97,34,58,91,123,34,105,100,34,58,34,49,51,34,44,34,102,105,108,101,95,110,97,109,101,34,58,34,100,97,106,119,100,106,97,119,34,44,34,102,105,108,101,95,116,121,112,101,34,58,34,100,111,99,120,34,44,34,102,105,108,101,95,115,105,122,101,34,58,34,49,53,57,48,34,44,34,102,105,108,101,95,112,97,116,104,34,58,34,49,34,44,34,105,115,95,100,101,108,101,116,101,100,34,58,34,48,34,125,93,44,34,100,97,116,97,98,97,115,101,34,58,34,100,98,55,34,44,34,101,115,34,58,49,55,51,48,56,56,54,57,48,49,48,48,48,44,34,105,100,34,58,57,44,34,105,115,68,100,108,34,58,102,97,108,115,101,44,34,109,121,115,113,108,84,121,112,101,34,58,123,34,105,100,34,58,34,105,110,116,34,44,34,102,105,108,101,95,110,97,109,101,34,58,34,118,97,114,99,104,97,114,40,50,53,53,41,34,44,34,102,105,108,101,95,116,121,112,101,34,58,34,118,97,114,99,104,97,114,40,53,48,41,34,44,34,102,105,108,101,95,115,105,122,101,34,58,34,98,105,103,105,110,116,34,44,34,102,105,108,101,95,112,97,116,104,34,58,34,116,101,120,116,34,44,34,105,115,95,100,101,108,101,116,101,100,34,58,34,116,105,110,121,105,110,116,40,49,41,34,125,44,34,111,108,100,34,58,110,117,108,108,44,34,112,107,78,97,109,101,115,34,58,91,34,105,100,34,93,44,34,115,113,108,34,58,34,34,44,34,115,113,108,84,121,112,101,34,58,123,34,105,100,34,58,52,44,34,102,105,108,101,95,110,97,109,101,34,58,49,50,44,34,102,105,108,101,95,116,121,112,101,34,58,49,50,44,34,102,105,108,101,95,115,105,122,101,34,58,45,53,44,34,102,105,108,101,95,112,97,116,104,34,58,50,48,48,53,44,34,105,115,95,100,101,108,101,116,101,100,34,58,45,54,125,44,34,116,97,98,108,101,34,58,34,102,105,108,101,95,116,97,98,108,101,34,44,34,116,115,34,58,49,55,51,48,56,56,54,57,48,49,57,52,51,44,34,116,121,112,101,34,58,34,73,78,83,69,82,84,34,125};
        String jsonString = new String(byteData, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode map = null;
        try {
            map = mapper.readTree(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 打印解析后的 JSON 对象
        System.out.println("解析后的 JSON 对象：" + map.toString());


        CanalEntity canalEntity = ParseEntityUtils.parseEntity(byteData, CanalEntity.class);
        System.out.println(canalEntity.toString());


    }

}
