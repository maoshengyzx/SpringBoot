package com.example.springbootelasticsearch.utils;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: FileUtils
 * Package: com.example.springbootelasticsearch.utils
 * Description:
 * 文件操作工具类
 * 用于读取文件中的内容，比如pdf、doc、docx、text
 *
 * @Author ms
 * @Create 2024/11/3 11:53
 * @Version 1.0
 */
public class FileUtils {

    private static final List<String> FILE_TYPE;


    static {
        FILE_TYPE = Arrays.asList("pdf", "doc", "docx", "text");
    }


    @SneakyThrows
    public static String readFileContent(InputStream inputStream, String fileType) {
        if (!FILE_TYPE.contains(fileType)) {
            return null;
        }
        // 使用PdfBox读取pdf文件内容
        if ("pdf".equalsIgnoreCase(fileType)) {
            return readPdfContent(inputStream);
        } else if ("doc".equalsIgnoreCase(fileType) || "docx".equalsIgnoreCase(fileType)) {
            return readDocOrDocxContent(inputStream);
        } else if ("tex".equalsIgnoreCase(fileType)) {
            return readTextContent(inputStream);
        }

        return null;
    }


    @SneakyThrows
    private static String readPdfContent(InputStream inputStream) {
        // 加载PDF文档
        PDDocument pdDocument = PDDocument.load(inputStream);

        // 创建PDFTextStripper对象, 提取文本
        PDFTextStripper textStripper = new PDFTextStripper();

        // 提取文本
        String content = textStripper.getText(pdDocument);

        // 关闭PDF文档
        pdDocument.close();
        return content;
    }


    private static String readDocOrDocxContent(InputStream inputStream) {
        try {
            // 加载DOC文档
            XWPFDocument document = new XWPFDocument(inputStream);

            // 2. 提取文本内容
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            return extractor.getText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String readTextContent(InputStream inputStream) {
        StringBuilder content = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            int ch;
            while ((ch = isr.read()) != -1) {
                content.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

}
