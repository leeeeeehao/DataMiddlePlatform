package com.saas.datamiddleend.config;

import com.saas.datamiddleend.basic.ApiResult;
import org.pentaho.di.core.exception.KettleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理
 *
 * @author leehao
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionConfiguration {

    private FieldError fieldError;

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionConfiguration.class);

    /**
     * 其他错误拦截处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handle(Exception e) {
        LOG.error("catch error", e);
        return ApiResult.fail(e.getMessage(), null);
    }

    /**
     * kettle错误拦截处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(KettleException.class)
    public ApiResult<?> handle(KettleException e) {
        LOG.error("catch error", e);
        return ApiResult.fail(e.getMessage(), null);
    }

    /**
     * 校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> validationBodyException(MethodArgumentNotValidException exception) {

        BindingResult result = exception.getBindingResult();

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                fieldError = (FieldError) p;
            });
        }
        //返回自定义错误格式
        fieldError.getDefaultMessage();
        return ApiResult.fail(fieldError.getDefaultMessage(), null);
    }


}
