package com.javaglm.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaglm.task.common.Result;
import com.javaglm.task.dto.TaskRequest;
import com.javaglm.task.entity.Task;
import com.javaglm.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 任务接口（第 28 章）：增删改查 + 分页。
 * 注意 userId 来自 JWT 拦截器放进请求上下文的值（不是前端传的，防伪造）。
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Result<IPage<Task>> list(
            @RequestParam(defaultValue = "1") long current,
            @RequestParam(defaultValue = "10") long size,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.success(taskService.pageTasks(current, size, userId, status));
    }

    @PostMapping
    public Result<Task> create(@RequestBody @Valid TaskRequest req,
                               HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.success(taskService.createTask(req, userId));
    }

    @PutMapping("/{id}")
    public Result<Task> update(@PathVariable Long id,
                               @RequestBody @Valid TaskRequest req,
                               HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.success(taskService.updateTask(id, req, userId));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        taskService.deleteTask(id, userId);
        return Result.success();
    }
}
