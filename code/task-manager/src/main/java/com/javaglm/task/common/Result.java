package com.javaglm.task.common;

/**
 * 统一响应结构（第 24 章）。
 * 所有接口都返回 { code, message, data }，前端只认这套结构。
 * 对标前端 axios 封装里 res.data.code / res.data.data 的统一约定。
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result() {
    }

    /** 成功并带数据。 */
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.code = 200;
        r.message = "success";
        r.data = data;
        return r;
    }

    /** 成功无数据。 */
    public static <T> Result<T> success() {
        return success(null);
    }

    /** 失败。 */
    public static <T> Result<T> error(int code, String message) {
        Result<T> r = new Result<>();
        r.code = code;
        r.message = message;
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
