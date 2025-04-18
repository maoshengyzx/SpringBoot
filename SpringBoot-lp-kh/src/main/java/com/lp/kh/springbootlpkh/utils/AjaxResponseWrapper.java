package com.lp.kh.springbootlpkh.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.lp.kh.springbootlpkh.exception.SystemException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class AjaxResponseWrapper<T> implements Serializable {

    private static final long serialVersionUID = -4724017409089815575L;

    public static final int SUCCESS_CODE = 10000;
    /**
     * 状态码
     */
    private Integer code = 10000;

    /**
     * 当前页的数据
     */
    private T data;

    /**
     * 返回到前端的消息
     */
    private String message;

    private Map<String, Object> extras;

    private boolean success;

    protected AjaxResponseWrapper() {
    }

    /**
     * 返回成功的信息，包含展示的数据
     *
     * @param data 返回到前段的数据
     * @return
     */
    public static <T> AjaxResponseWrapper<T> data(T data) {
        AjaxResponseWrapper<T> wrapper = new AjaxResponseWrapper<>();
        wrapper.data = data;
        return wrapper;
    }

    /**
     * 给前端返回一个提示信息
     *
     * @param messagePattern 消息格式
     * @param arguments      参数
     * @return
     */
    public static AjaxResponseWrapper<Void> tip(String messagePattern, Object... arguments) {
        String message = MessageFormatter.arrayFormat(messagePattern, arguments).getMessage();
        AjaxResponseWrapper<Void> wrapper = new AjaxResponseWrapper<>();
        wrapper.setMessage(message);
        return wrapper;
    }

    public static AjaxResponseWrapper<Void> success() {
        return new AjaxResponseWrapper<>();
    }

    /**
     * @param status 是否成功
     * @param msg    前端提示消息
     * @return
     */
    public static AjaxResponseWrapper<Void> operate(boolean status, String msg) {
        AjaxResponseWrapper<Void> wrapper = new AjaxResponseWrapper<>();
        if (status) {
            msg = StrUtil.join(msg, "成功");
        } else {
            msg = StrUtil.join(msg, "失败");
        }
        wrapper.message = msg;
        return wrapper;
    }

    /**
     * @param status 是否成功
     * @param msg    前端提示消息
     * @param data   返回的数据
     * @return
     */
    public static <T> AjaxResponseWrapper<T> operate(boolean status, String msg, T data) {
        AjaxResponseWrapper<T> wrapper = new AjaxResponseWrapper<>();
        if (status) {
            msg = StrUtil.join(msg, "成功");
        } else {
            msg = StrUtil.join(msg, "失败");
        }
        wrapper.success = status;
        if (!wrapper.success) {
            wrapper.code = 10004;
        }
        wrapper.message = msg;
        wrapper.data = data;
        return wrapper;
    }

    /**
     * @param status   是否成功
     * @param msg      操作信息
     * @param extraMsg 额外信息
     * @return
     */
    public static AjaxResponseWrapper<Void> operate(boolean status, String msg, String extraMsg) {
        AjaxResponseWrapper<Void> wrapper = new AjaxResponseWrapper<>();
        if (status) {
            msg = StrUtil.join(msg, "成功", StrUtil.isNotBlank(extraMsg) ? "," : null, extraMsg);
        } else {
            msg = StrUtil.join(msg, "失败", StrUtil.isNotBlank(extraMsg) ? "," : null, extraMsg);
        }
        wrapper.message = msg;
        return wrapper;
    }




    public static <T> AjaxResponseWrapper<T> fail(SystemException systemException) {
        AjaxResponseWrapper<T> wrapper = new AjaxResponseWrapper<>();
        wrapper.code = systemException.getCode();
        wrapper.message = systemException.getMsg();
        return wrapper;
    }

    public static <T> AjaxResponseWrapper<T> fail(int code, String msg) {
        AjaxResponseWrapper<T> wrapper = new AjaxResponseWrapper<>();
        wrapper.code = code;
        wrapper.message = msg;
        return wrapper;
    }

    public static <T> AjaxResponseWrapper<T> fail(String messagePattern, Object... arguments) {
        AjaxResponseWrapper<T> wrapper = new AjaxResponseWrapper<>();
        String message = MessageFormatter.arrayFormat(messagePattern, arguments).getMessage();
        wrapper.setCode(10004);
        wrapper.setMessage(message);
        return wrapper;
    }

    public static <T> AjaxResponseWrapper<T> fail(Exception e) {
        return fail(SystemException.wrap(e));
    }

    public static <T> AjaxResponseWrapper<T> fail() {
        AjaxResponseWrapper<T> wrapper = new AjaxResponseWrapper<>();
        wrapper.setCode(10004);
        return wrapper;
    }

    /**
     * 判断当前响应结果是否是正确的
     *
     * @return
     */
    public boolean isSuccess() {
        return getCode() != null && getCode().equals(10000);
    }

    /**
     * 将对象转换成JSON格式的字符串
     *
     * @return
     */
    public String toJsonString() {
        return JSONUtil.toJsonStr(this);
    }


    @Override
    public String toString() {
        return toJsonString();
    }

    /**
     * 新增附加数据
     *
     * @param key
     * @param value
     * @return
     */
    public AjaxResponseWrapper<T> addExtras(String key, Object value) {
        if (this.extras == null) {
            this.extras = new HashMap<>(0);
        }
        this.extras.put(key, value);
        return this;
    }

    public AjaxResponseWrapper<T> headers(List<?> headers) {
        if (this.extras == null) {
            this.extras = new HashMap<>(0);
        }
        this.extras.put("headers", headers);
        return this;
    }

    public AjaxResponseWrapper<T> clearExtras() {
        this.extras = null;
        return this;
    }
}
