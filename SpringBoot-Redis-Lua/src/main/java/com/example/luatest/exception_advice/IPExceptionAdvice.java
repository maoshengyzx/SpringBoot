package com.example.luatest.exception_advice;


import com.example.luatest.exception.IPException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Administrator
 */
@RestControllerAdvice
public class IPExceptionAdvice {

    @ExceptionHandler(value = IPException.class)
    public void ipException(IPException e){
        System.out.println(e.getMessage());
    }
}
