package com.lp.kh.springbootlpkh.advice;

import cn.hutool.core.util.StrUtil;
import com.lp.kh.springbootlpkh.exception.SystemException;
import com.lp.kh.springbootlpkh.utils.AjaxResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * 捕获控制层抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public AjaxResponseWrapper<Void> processException(Exception e) {
        log.error("捕获控制层异常：", e);
        String message = null;
        if (e instanceof SystemException) {
            SystemException s = (SystemException) e;
            Map<String, Object> extras = s.getExtras();
            AjaxResponseWrapper<Void> res = AjaxResponseWrapper.fail(s);
            res.setExtras(extras);
            return res;
        } else if (e instanceof MaxUploadSizeExceededException) {
            long maxSize = ((MaxUploadSizeExceededException) e).getMaxUploadSize();
            message = "上传文件太大，不能超过" + maxSize / 1024L / 1024L + "M";
        } else if (e instanceof BindException) {
            // 对象验证框架异常
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            } else {
                message = e.getMessage();
            }
        }  else if (e instanceof MissingServletRequestParameterException) {
            message = "参数缺失";
        } else {
            Throwable cause = e.getCause();
            if (cause instanceof SystemException) {
                SystemException s = (SystemException) cause;
                return AjaxResponseWrapper.fail(s);
            } else {
                message = "系统异常";
            }
        }
        if (StrUtil.isBlank(message)) {
            message = "服务不可用";
        }
        return AjaxResponseWrapper.fail(10004, message);
    }



    /**
     * 日期类型的属性，前段只需要传递相应格式的字段穿即可，springMVC自动将其转成java.util.Date
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
