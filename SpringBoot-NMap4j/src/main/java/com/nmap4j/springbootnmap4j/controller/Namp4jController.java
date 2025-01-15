package com.nmap4j.springbootnmap4j.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.nmap4j.springbootnmap4j.SystemEnum.SystemEnum;
import com.nmap4j.springbootnmap4j.pojo.NmapPortInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nmap4j.Nmap4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: Namp4jController
 * Package: com.nmap4j.springbootnmap4j.controller
 * Description:
 *
 * @Author ms
 * @Create 2025/1/10 16:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/nmap")
@Slf4j
public class Namp4jController {

    private final ThreadPoolExecutor threadPoolExecutor;


    public Namp4jController(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }


    /**
     * 测试 nmap4j 工具
     *
     * @param ip    目标 ip
     * @param ports 目标端口
     * @return 端口信息列表
     */
    @RequestMapping("/queryDb")
    public List<NmapPortInfo> queryDb(String ip, List<String> ports) {
        // 判断系统
        String system = System.getProperty("os.name").toLowerCase();
        if (StrUtil.equals(system, SystemEnum.WINDOWS.getValue())) {
            return windowsQuerydb(ip, ports);
        } else {
            return linuxQuerydb(ip, ports);
        }
    }

    /**
     * 使用 nmap4j 工具进行扫描, windows系统
     *
     * @param ip    目标 ip
     * @param ports 目标端口
     * @return 端口信息列表
     */
    public List<NmapPortInfo> windowsQuerydb(String ip, List<String> ports) {
        ArrayList<NmapPortInfo> portInfos = new ArrayList<>();
        // 1.拼接端口
        String portStr = StrUtil.join(",", ports);

        //2. 指定 nmap 路径
        String path = "D:/StudyApps/nmap";
        String fileName = "temp_result.xml";


        // 3.获取当前系统信息
        String system = System.getProperty("os.name");
        log.info("当前系统：{}", system);


        Nmap4j nmap4j = new Nmap4j(path);

        //3. 读取端口耗时较长，可以使用异步
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            nmap4j.addFlags("-sV -p " + portStr + " -T5 -O -oX " + fileName);
            nmap4j.includeHosts(ip);
            try {
                nmap4j.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, threadPoolExecutor);

        future.join();

        //4. 获取端口信息
        return getPortInfo(portInfos, fileName);
    }


    /**
     * 使用 nmap4j 工具进行扫描, linux系统
     *
     * @param ip    目标 ip
     * @param ports 目标端口
     * @return 端口信息列表
     */
    @SneakyThrows
    public List<NmapPortInfo> linuxQuerydb(String ip, List<String> ports) {
        ArrayList<NmapPortInfo> portInfos = new ArrayList<>();
        // 1.拼接端口
        String portStr = StrUtil.join(",", ports);
        String fileName = "temp_result.xml";
        //2. linux namp 命令
        String nmapCommand = "nmap -sV -p " + portStr + " -T5 -O -oX " + fileName + " " + ip;

        //3. 读取端口耗时较长，可以使用异步
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            Process nampProcess = null;
            try {
                // 3. 运行命令
                nampProcess = Runtime.getRuntime().exec(nmapCommand);
                // 4. 等待命令执行完成
                nampProcess.waitFor();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, threadPoolExecutor);

        future.join();

        // 5. 获取端口信息
        return getPortInfo(portInfos, fileName);
    }


    /**
     * 获取 ip + 端口信息,封装为集合返回前端
     *
     * @param portInfos 返回前端集合
     * @param fileName  临时的 xml 文件
     * @return 信息列表
     */
    @SneakyThrows
    private List<NmapPortInfo> getPortInfo(List<NmapPortInfo> portInfos, String fileName) {
        // 获取项目所在路径
        String projectPath = System.getProperty("user.dir");
        // 拼接文件路径
        String filePath = projectPath + FileUtil.FILE_SEPARATOR + fileName;
        log.info("文件路径：{}", filePath);

        // nmap 返回 xml 格式固定，使用 dom4j 解析
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = reader.read(FileUtil.file(filePath));
        org.dom4j.Element rootElement = document.getRootElement();
        org.dom4j.Element hostElement = rootElement.element("host");

        org.dom4j.Element portsElement = hostElement.element("ports");

        List<org.dom4j.Element> portElements = portsElement.elements("port");
        for (org.dom4j.Element port : portElements) {
            Element serviceElement = port.element("service");
            String product = serviceElement.attributeValue("product");
            String version = serviceElement.attributeValue("version");
            NmapPortInfo nmapPortInfo = new NmapPortInfo(product, version);
            portInfos.add(nmapPortInfo);
        }

        // 删除临时文件
        FileUtil.del(filePath);
        return portInfos;
    }
}
