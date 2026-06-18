package com.javaglm.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaglm.task.common.BizException;
import com.javaglm.task.dto.TaskRequest;
import com.javaglm.task.entity.Task;
import com.javaglm.task.mapper.TaskMapper;
import com.javaglm.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 任务服务实现（第 28 章）。
 * 继承 ServiceImpl<TaskMapper, Task>：自动拥有 CRUD，Mapper 也自动注入。
 * 业务逻辑写在这里（校验归属、组装数据），Controller 保持"瘦"。
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public IPage<Task> pageTasks(long current, long size, Long userId, Integer status) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Task::getUserId, userId);
        if (status != null) {
            wrapper.eq(Task::getStatus, status);
        }
        wrapper.orderByDesc(Task::getCreateTime);
        return this.page(new Page<>(current, size), wrapper);
    }

    @Override
    public Task createTask(TaskRequest req, Long userId) {
        Task task = new Task();
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setStatus(req.getStatus() == null ? 0 : req.getStatus());
        task.setUserId(userId);
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        this.save(task);
        return task;
    }

    @Override
    public Task updateTask(Long id, TaskRequest req, Long userId) {
        Task task = this.getById(id);
        if (task == null || !task.getUserId().equals(userId)) {
            throw new BizException(404, "任务不存在或无权操作");
        }
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        if (req.getStatus() != null) {
            task.setStatus(req.getStatus());
        }
        task.setUpdateTime(LocalDateTime.now());
        this.updateById(task);
        return task;
    }

    @Override
    public void deleteTask(Long id, Long userId) {
        Task task = this.getById(id);
        if (task == null || !task.getUserId().equals(userId)) {
            throw new BizException(404, "任务不存在或无权操作");
        }
        this.removeById(id);   // @TableLogic：实际执行逻辑删除
    }
}
