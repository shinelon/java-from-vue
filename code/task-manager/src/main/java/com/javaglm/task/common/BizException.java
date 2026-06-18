package com.javaglm.task.common;

/**
 * 业务异常（第 24 章）。
 * 继承 RuntimeException（非受检），不用每个方法都 throws。
 * 抛出后由全局异常处理器统一接住，转成友好响应。
 */
public class BizException extends RuntimeException {

    private final int code;

    public BizException(String message) {
        this(400, message);
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
