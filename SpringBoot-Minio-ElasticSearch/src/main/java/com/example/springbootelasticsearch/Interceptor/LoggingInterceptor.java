package com.example.springbootelasticsearch.Interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: LoggingInteceptor
 * Package: com.example.springbootelasticsearch.inteceptor
 * Description:
 * mybatis 日志拦截器
 *
 * @Author ms
 * @Create 2025/5/19 10:30
 * @Version 1.0
 */
@Intercepts(value = {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class LoggingInterceptor implements Interceptor {

    private final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggingInterceptor.class);

    private final Duration slownessThreshold = Duration.ofMillis(1000);

    private static final Pattern PARAMETER_PATTERN = Pattern.compile("\\?");

    private final static String QUERY_METHOD = "query";
    private final static String UPDATE_METHOD = "update";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        if (StrUtil.equalsIgnoreCase(method.getName(), QUERY_METHOD)) {
            return query(invocation);
        } else if (StrUtil.equalsIgnoreCase(method.getName(), UPDATE_METHOD)) {
            return update(invocation);
        } else {
            return invocation.proceed();
        }
    }


    private Object query(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        if (args.length == 4) {
            boundSql = mappedStatement.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
        } else {
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        long start = SystemClock.now();
        Object proceed = invocation.proceed();
        long end = SystemClock.now();
        printExecuteSql(mappedStatement, boundSql, end - start);
        return proceed;
    }

    private Object update(Invocation invocation) throws Throwable {
        return null;
    }


    private void printExecuteSql(MappedStatement ms, BoundSql boundSql, long time) {
        Configuration configuration = ms.getConfiguration();
        if (time > slownessThreshold.toMillis()) {
            LOGGER.info("{} execute sql: {} ({} ms)", ms.getId(), getSql(configuration, boundSql), time);
        } else {
            LOGGER.warn("{} execute sql: {} ({} ms)", ms.getId(), getSql(configuration, boundSql), time);
        }
    }

    private String getSql(Configuration configuration, BoundSql boundSql) {
        // 替换 SQL 语句中的换行符和多余的空格
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        if (CollUtil.isEmpty(parameterMappings) || Objects.isNull(parameterObject)) {
            return sql;
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            // 替换 SQL 语句中的参数占位符,typeHandlerRegistry 中包含了参数类型的处理器，都是一些基本类型的处理器，所以对于像集合、对象等复杂类型的参数，需要特殊处理
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
        } else {
            // 处理复杂类型参数
            // 遍历参数映射列表，获取每个参数的值，并替换 SQL 语句中的占位符
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            Matcher matcher = PARAMETER_PATTERN.matcher(sql);
            StringBuffer sqlBuffer = new StringBuffer();
            for (ParameterMapping parameterMapping : parameterMappings) {
                // 获取参数名称
                String propertyName = parameterMapping.getProperty();
                // 获取参数值
                Object value = null;
                if (metaObject.hasGetter(propertyName)) {
                    value = metaObject.getValue(propertyName);
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    value = boundSql.getAdditionalParameter(propertyName);
                }
                if (matcher.find()) {
                    // 替换 SQL 语句中的占位符
                    matcher.appendReplacement(sqlBuffer, Matcher.quoteReplacement(getParameterValue(value)));
                }
                matcher.appendTail(sqlBuffer);
            }
            return sqlBuffer.toString();
        }
        return sql;
    }


    private String getParameterValue(Object obj) {
        if (obj instanceof CharSequence) {
            return "'" + obj + "'";
        }
        if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            return "'" + formatter.format(obj) + "'";
        }
        return obj == null ? "" : String.valueOf(obj);
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }


}
