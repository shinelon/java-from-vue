package com.javaglm.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaglm.task.dto.TaskRequest;
import com.javaglm.task.entity.Task;

/**
 * 任务服务接口（第 28 章）。
 * 继承 IService<Task>，白嫖 MyBatis-Plus 的 save/getById/updateById/removeById 等方法，
 * 只需声明本系统特有的业务方法。
 */
public interface TaskService extends IService<Task> {

    /** 分页查询某用户的任务，可按状态过滤。 */
    IPage<Task> pageTasks(long current, long size, Long userId, Integer status);

    /** 新建任务。 */
    Task createTask(TaskRequest req, Long userId);

    /** 修改任务（校验归属）。 */
    Task updateTask(Long id, TaskRequest req, Long userId);

    /** 删除任务（逻辑删除，校验归属）。 */
    void deleteTask(Long id, Long userId);
}
