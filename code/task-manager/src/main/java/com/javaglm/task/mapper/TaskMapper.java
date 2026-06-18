package com.javaglm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaglm.task.entity.Task;

/**
 * 任务 Mapper（第 26 章）。
 * 继承 BaseMapper<Task>，白嫖一堆 CRUD 方法（insert/updateById/deleteById/selectById/selectList...），
 * 一行 SQL 都不用写。需要自定义 SQL 时再在这里加方法 + 写 XML/注解。
 */
public interface TaskMapper extends BaseMapper<Task> {
}
