package com.example.springbootrabbitmq.utils;

import com.example.springbootrabbitmq.entity.CanalEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * ClassName: ParseEntityUtils
 * Package: com.example.springbootrabbitmq.utils
 * Description:
 * 用于将Map结构的数据转换为实体类
 * 主要用于接受RabbitMq的消息
 *
 * @Author ms
 * @Create 2024/11/6 12:57
 * @Version 1.0
 */
public class ParseEntityUtils {

    private static final List<String> OBJECT_FIELDS = Arrays.asList("data", "mysqlType", "pkNames", "sqlType");


    /**
     * 将byte结构的数据转换为实体类
     *
     * @param messageBody 字节数据
     * @param clazz       实体的Class对象
     * @param <T>
     * @return
     */
    public static <T> T parseEntity(byte[] messageBody, Class<T> clazz) {
        // 1.将字节数组转换为字符串
        String jsonString = new String(messageBody, StandardCharsets.UTF_8);
        // 2.使用JackSon库将String转换为Map结构
        ObjectMapper mapper = new ObjectMapper();
        // 3.将String转换为Map结构
        // todo:ObjectMapper其实提供了将String转换为实体类的方式，这里无法直接使用，是应为RabbitMQ返回的数据读取完成后是多层嵌套的对象，
        //  所以我封装了内部类，而ObjectMapper是无法直接映射内部类的
        T t = null;

        Map<String, Object> map;
        try {
            map = mapper.readValue(jsonString, Map.class);

            t = clazz.getDeclaredConstructor().newInstance();

            Class<?>[] innerClasss = clazz.getDeclaredClasses();

            // 获得当前类的所有字段  去除 data、MySqlType、pkNames字段，其他的字段类型都是基本数据类型或String类型，直接赋值
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 获得字段的名称
                String fieldName = field.getName();
                if (!OBJECT_FIELDS.contains(fieldName)) {
                    // 直接赋值
                    field.setAccessible(true);
                    field.set(t, map.get(fieldName));
                }

                // 对于其他类型，比如List、Map类型，额外处理
                if (List.class.isAssignableFrom(field.getType())) {
                    // 获取List结构中的数据类型
                    Class<?> elementType = findListElementType(field);
                    Assert.notNull(elementType, "List结构中的数据类型不能为空");
                    // 获取clazz的内部类

                    for (Class<?> innerClass : innerClasss) {
                        if (elementType.getSimpleName().equals(innerClass.getSimpleName())) {
                            // 对innerClass对象赋值
                            Object innerObject = innerClass.getDeclaredConstructor().newInstance();

                            ArrayList<Map<String, Object>> innerValue = (ArrayList<Map<String, Object>>) map.get(replaceFieldToLower(fieldName));
                            ArrayList<Object> list = new ArrayList<>();
                            for (Map<String, Object> objectMap : innerValue) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                                    hashMap.put(replaceFieldToUpper(entry.getKey()), entry.getValue());
                                }
                                String str = mapper.writeValueAsString(hashMap);
                                Object object = mapper.readValue(str, innerClass);
                                list.add(object);
                            }
                            field.setAccessible(true);
                            field.set(t, list);
                        }
                    }
                } else if (Map.class.isAssignableFrom(field.getType())) {
//                    // 如果是字段类型为Map
//                    field.setAccessible(true);
//                    field.set(t, map.get(fieldName));
                } else if (CanalEntity.MysqlType.class.isAssignableFrom(field.getType()) || CanalEntity.SqlType.class.isAssignableFrom(field.getType())) {
                    // 最后表示字段类型为Object类型
                    Class<?> aClass = field.getType();
                    // 获得所以字段
                    Field[] innerFields = aClass.getDeclaredFields();
                    // 获得字段的值
                    HashMap<String, Object> innerFieldValue = (HashMap<String, Object>) map.get(field.getName());
                    HashMap<String, Object> hashMap = new HashMap<>();

                    for (Map.Entry<String, Object> entry : innerFieldValue.entrySet()) {
                        if (entry.getKey().contains("_")) {
                            hashMap.put(replaceFieldToUpper(entry.getKey()), entry.getValue());
                        } else {
                            hashMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    String string = mapper.writeValueAsString(hashMap);
                    Object value = mapper.readValue(string, aClass);
                    field.setAccessible(true);
                    field.set(t, value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return t;
    }


    /**
     * 将驼峰命名法替换为 _   例如：userName -> user_name
     *
     * @param fieldName
     * @return
     */
    public static String replaceFieldToLower(String fieldName) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fieldName.length(); i++) {
            char c = fieldName.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                result.append("_");
            }
            result.append(Character.toLowerCase(c));
        }
        return result.toString();
    }


    /**
     * 将 _ 替换为驼峰命名法 例如：user_name -> userName
     *
     * @param fieldName
     * @return
     */
    public static String replaceFieldToUpper(String fieldName) {
        StringBuilder result = new StringBuilder();
        boolean nextToUpperCase = false;
        for (char c : fieldName.toCharArray()) {
            if (c == '_') {
                nextToUpperCase = true;
            } else {
                if (nextToUpperCase) {
                    result.append(Character.toUpperCase(c));
                    nextToUpperCase = false;
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }


    /**
     * 获取List结构中的数据类型
     *
     * @param field
     * @return
     */
    public static Class<?> findListElementType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.ParameterizedType parameterizedType = (java.lang.reflect.ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                if (actualTypeArguments[0] instanceof Class<?>) {
                    return (Class<?>) actualTypeArguments[0];
                }
            }
        }
        return null;
    }
}

