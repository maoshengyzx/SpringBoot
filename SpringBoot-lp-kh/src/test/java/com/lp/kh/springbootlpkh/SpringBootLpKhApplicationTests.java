package com.lp.kh.springbootlpkh;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@SpringBootTest
public class SpringBootLpKhApplicationTests {

    @Test
    public void contextLoads() throws IOException {
//        ClassPathResource resource = new ClassPathResource("classpath:");
        URL inputStream = ClassLoader.getSystemClassLoader().getResource("prompts/Qwen1.5-7B-Chat/doc/system/chat-doc.st");
        String path = inputStream.getPath();

        byte[] bytes = FileUtil.readBytes(path);
        System.out.println(bytes.length);
    }




}
