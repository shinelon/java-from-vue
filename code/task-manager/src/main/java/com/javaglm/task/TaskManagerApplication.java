package com.javaglm.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 任务管理系统 · 启动入口。
 *
 * <p>对标前端：这相当于 Vue 项目的 main.js 里 createApp(App).mount('#app') 的"启动点"。
 * 一个 Spring Boot 应用从这个带 @SpringBootApplication 的 main 方法启动。
 *
 * <p>@MapperScan：告诉 MyBatis-Plus 去这个包下扫描 Mapper 接口（第 26 章）。
 *
 * <p>运行：IDEA 点运行按钮，或 mvn spring-boot:run。启动后监听 http://localhost:8080
 */
@SpringBootApplication
@MapperScan("com.javaglm.task.mapper")
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}
