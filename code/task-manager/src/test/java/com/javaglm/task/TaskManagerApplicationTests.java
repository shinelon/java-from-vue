package com.javaglm.task;

import com.javaglm.task.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 单元测试示例（第 31 章）。
 * 用 JUnit 5（spring-boot-starter-test 自带）。
 * 这种"纯逻辑测试"不启动 Spring 上下文、不连数据库，秒级跑完，是日常写得最多的测试。
 */
class TaskManagerApplicationTests {

    @Test
    void successResultShouldCarryData() {
        Result<String> r = Result.success("hi");
        assertEquals(200, r.getCode());
        assertEquals("success", r.getMessage());
        assertEquals("hi", r.getData());
    }

    @Test
    void bcryptPasswordShouldMatch() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123456");
        assertTrue(encoder.matches("123456", hash));    // 正确密码：匹配
        assertFalse(encoder.matches("wrong", hash));    // 错误密码：不匹配
    }
}
