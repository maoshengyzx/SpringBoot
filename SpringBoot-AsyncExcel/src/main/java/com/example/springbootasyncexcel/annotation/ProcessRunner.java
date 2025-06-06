package com.example.springbootasyncexcel.annotation;

import com.example.springbootasyncexcel.model.ProcessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessRunner {

    String processName() default "";

    ProcessType processType() default ProcessType.EXCEL_TYPE;

    String description() default "";
}
