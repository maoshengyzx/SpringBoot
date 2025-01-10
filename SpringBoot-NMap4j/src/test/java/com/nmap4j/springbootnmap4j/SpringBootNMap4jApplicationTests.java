package com.nmap4j.springbootnmap4j;

import cn.hutool.core.io.FileUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringBootNMap4jApplicationTests {

    @Test
    public void contextLoads() throws DocumentException {
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = reader.read(FileUtil.file("D:\\GitHub\\SpringBoot\\result.xml"));
        org.dom4j.Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        Element element = rootElement.element("host");
        Element ports = element.element("ports");

        List<Element> port = ports.elements("port");


        for (Element port1 : port) {
            Element service = port1.element("service");
            String product = service.attributeValue("product");
            String version = service.attributeValue("version");
            System.out.println(product + " " + version);
        }

    }

}
