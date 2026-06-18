package com.javaglm.task.config;

import com.javaglm.task.common.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * 全局异常处理器测试（第 24 章）。
 * 兜底异常（Exception）往往携带内部细节（SQL 语句、文件路径等），
 * 不应原样返回给前端，否则信息泄漏。
 */
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void unexpectedExceptionShouldNotLeakInternalDetails() {
        Result<?> r = handler.handleOther(new RuntimeException("sensitive: DROP TABLE users"));

        assertEquals(500, r.getCode());
        assertFalse(r.getMessage().contains("DROP TABLE"), "不应把异常细节返回给前端");
        assertFalse(r.getMessage().contains("sensitive"), "不应把异常细节返回给前端");
    }
}
