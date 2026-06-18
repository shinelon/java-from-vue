package com.javaglm.task.config;

import com.javaglm.task.common.BizException;
import com.javaglm.task.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器（第 24 章）。
 * @RestControllerAdvice：全局拦截所有 Controller 抛出的异常，统一转成 Result 返回。
 * 好处：Controller / Service 里只管"抛异常"，"怎么变成友好响应"集中在这里处理。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 业务异常：返回业务错误码 + 消息。 */
    @ExceptionHandler(BizException.class)
    public Result<?> handleBiz(BizException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /** 参数校验失败：@Valid 触发的，提取第一条错误消息。 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValid(MethodArgumentNotValidException e) {
        FieldError fe = e.getBindingResult().getFieldError();
        String msg = fe != null ? fe.getDefaultMessage() : "参数校验失败";
        return Result.error(400, msg);
    }

    /** 兜底：其他未预料异常。细节只写日志，不返回给前端（防信息泄漏）。 */
    @ExceptionHandler(Exception.class)
    public Result<?> handleOther(Exception e) {
        log.error("未预期异常", e);
        return Result.error(500, "服务器内部错误");
    }
}
