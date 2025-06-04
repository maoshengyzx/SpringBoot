package com.example.springbootelasticsearch.aop;

import com.example.springbootelasticsearch.common.Page;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Aspect
@Component
public class PageAop {

    @Pointcut("execution(* com.example.springbootelasticsearch.mapper..*(..))")
    public void mapperMethod() {
    }

    @Around("mapperMethod()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Page<?> page = null;
        for (Object arg : args) {
            if (arg instanceof Page) {
                page = (Page<?>) arg;
                break;
            }
        }
        if (page == null) {
            return point.proceed();
        }
        PageHelper.startPage((int) page.getPageNum(), (int) page.getPageSize(), true);
        Object proceed = point.proceed();

        if (proceed instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page p = (com.github.pagehelper.Page) proceed;
            page.setRecords(new ArrayList<>(p.getResult()));
            page.setTotal((int) p.getTotal());
        }
        return proceed;
    }
}