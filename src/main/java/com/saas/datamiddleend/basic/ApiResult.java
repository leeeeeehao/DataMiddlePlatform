package com.saas.datamiddleend.basic;

import com.saas.datamiddleend.enums.ResultCodeEnums;

import java.io.Serializable;

/**
 * api返回格式
 *
 * @author leehao
 * @since 1.0
 */
public class ApiResult<T> implements Serializable {

    private String code;

    private Boolean success;

    private String message;

    private T data;

    private ApiResult() {
    }

    private ApiResult(Boolean success, String message, String code
            , T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  类型
     * @return Api
     */
    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(true, "请求成功", ResultCodeEnums.SUCCESS.getType(), data);
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> ok() {
        return new ApiResult<>(true, "请求成功", ResultCodeEnums.SUCCESS.getType(), null);
    }

    /**
     * 失败
     *
     * @param message 信息
     * @param data    数据
     * @param <T>     类型
     * @return Api
     */
    public static <T> ApiResult<T> fail(String message, T data) {
        return new ApiResult<>(false, message, ResultCodeEnums.FAIL.getType(), data);
    }

    public static <T> ApiResult<T> fail() {
        return new ApiResult<>(false, "请求失败", ResultCodeEnums.FAIL.getType(), null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
