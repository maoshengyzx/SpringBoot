package com.example.springbootasyncexcel.annotation;

import com.example.springbootasyncexcel.model.ProcessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProcessHandle {

    String value() default "";

    ProcessType processType() default ProcessType.EXCEL_TYPE;
}
