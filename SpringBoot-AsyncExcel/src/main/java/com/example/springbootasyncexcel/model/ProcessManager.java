package com.example.springbootasyncexcel.model;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.example.springbootasyncexcel.annotation.ProcessHandle;
import com.example.springbootasyncexcel.entity.Process;
import com.example.springbootasyncexcel.service.ProcessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * ClassName: ProccessManager
 * Package: com.example.springbootasyncexcel.model
 * Description:
 *
 * @Author ms
 * @Create 2025/6/5 14:22
 * @Version 1.0
 */
@Component
@Slf4j
public class ProcessManager implements BeanPostProcessor, PriorityOrdered {

    private static final Map<String, Invoker> INVOKER_MAP = new ConcurrentHashMap<>();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final Pattern SERVICE_IMPL_BEAN_NAME_PATTERN = Pattern.compile("(?i)[.a-z]+ServiceImpl");


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = AopUtils.isAopProxy(bean) ? AopUtils.getTargetClass(bean) : bean.getClass();
        if (!SERVICE_IMPL_BEAN_NAME_PATTERN.matcher(beanClass.getName()).matches()) {
            return bean;
        }
        log.info("beanName:{}, beanType:{}", beanName, beanClass.getName());


        Method[] declaredMethods = ClassUtil.getDeclaredMethods(beanClass);
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(ProcessHandle.class)) {
                ProcessHandle processHandle = declaredMethod.getAnnotation(ProcessHandle.class);
                if (INVOKER_MAP.containsKey(processHandle.processType().toString())) {
                    throw new RuntimeException("processType 重复" + processHandle.processType().toString());
                }
                Parameter[] parameters = declaredMethod.getParameters();
                // 校验processId参数是否存在
                boolean processIdExist = false;
                for (Parameter parameter : parameters) {
                    String name = parameter.getName();
                    if (StrUtil.equalsIgnoreCase("processId", name)) {
                        processIdExist = true;
                        break;
                    }
                }
                if (!processIdExist) {
                    throw new RuntimeException("processId 参数不存在," + processHandle.processType().toString());
                }
                if (declaredMethod.getReturnType() != String.class) {
                    throw new RuntimeException("返回值必须为String," + processHandle.processType().toString());
                }
                INVOKER_MAP.put(processHandle.processType().name(), new Invoker(bean, declaredMethod, parameters));
            }
        }
        return bean;
    }

    public static void handleProcess(Long processId, boolean isEnd) {
        log.info("processId:{} isEnd:{}", processId, isEnd);
        ProcessService processService = SpringUtil.getBean(ProcessService.class);
        Process process = processService.queryById(processId);
        if (process == null) {
            throw new RuntimeException("processId 不存在" + processId);
        }
        if (isEnd) {
            process.setStatus((byte) 2);
        } else {
            process.setStatus((byte) 1);
        }
        processService.update(process);
        log.info("任务更新状态成功");
    }

    public void startProcess(Long processId) {
        ProcessService processService = SpringUtil.getBean(ProcessService.class);
        Process process = processService.queryById(processId);
        if (process == null) {
            return;
        }
        String params = process.getParams();
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(params);
            Invoker invoker = INVOKER_MAP.get(process.getProcessType());
            Method method = invoker.getMethod();
            Parameter[] parameters = method.getParameters();
            Object[] args = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                Class<?> type = parameter.getType();
                String name = parameter.getName();
                if (Long.class.isAssignableFrom(type) && StrUtil.equalsIgnoreCase("processId", name)) {
                    args[i] = jsonNode.get(name).asLong();
                } else if (Map.class.isAssignableFrom(type)) {
                    JsonNode valueNode = jsonNode.get(name);
                    args[i] = OBJECT_MAPPER.convertValue(valueNode, type);
                }
            }
            method.setAccessible(true);
            Object url = method.invoke(INVOKER_MAP.get(process.getProcessType()).getBean(), args);
            if (url != null) {
                process.setStatus((byte) 2);
                process.setUrl(url.toString());
                processService.update(process);
            }
        } catch (JsonProcessingException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Invoker {
        private Object bean;
        private Method method;
        private Object[] args;
    }
}
