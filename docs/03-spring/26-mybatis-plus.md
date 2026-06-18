# MyBatis-Plus 数据访问

操作数据库。本书用 **MyBatis-Plus**（简称 MP）——SQL 透明可控、CRUD 自动生成、对前端人最直观。

## Mapper 继承 BaseMapper，白嫖 CRUD

只要让你的 Mapper 继承 `BaseMapper<实体>`，一行 SQL 不写，自动获得一堆方法：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/mapper/TaskMapper.java"
```

自动获得的方法：

| 方法 | 作用 |
|---|---|
| `insert(task)` | 插入 |
| `updateById(task)` | 按 id 更新 |
| `deleteById(id)` | 按 id 删除 |
| `selectById(id)` | 按 id 查询 |
| `selectList(wrapper)` | 条件查询 |
| `selectCount(wrapper)` | 计数 |
| `selectPage(page, wrapper)` | 分页查询 |

## 条件构造器：LambdaQueryWrapper

查询不用拼 SQL 字符串，用类型安全的 `LambdaQueryWrapper`：

```java
LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
wrapper.eq(Task::getUserId, userId);          // WHERE user_id = ?
if (status != null) {
    wrapper.eq(Task::getStatus, status);      // AND status = ?
}
wrapper.orderByDesc(Task::getCreateTime);     // ORDER BY create_time DESC
List<Task> list = taskMapper.selectList(wrapper);
```

`Task::getUserId` 是方法引用（第 15 章），MP 据此推出列名 `user_id`（驼峰转下划线）。

## 分页：要注册插件

分页不是开箱即用，得注册分页插件，否则 `page()` 会查出全部：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/config/MybatisPlusConfig.java"
```

## 实战：分页查询任务

Service 层组合 wrapper + page：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/service/impl/TaskServiceImpl.java"
```

看 `pageTasks` 方法：组装条件 → `this.page(new Page<>(current, size), wrapper)` 返回 `IPage<Task>`，里面含 `records`（当前页数据）、`total`（总数）、`current`/`size`（页码/页大小）。

## 为什么不用 JPA

- MyBatis-Plus：**你看得见 SQL**（或自动生成可控），调试简单，国内主流。
- JPA：声明接口自动生成 SQL，优雅但"魔法"重，复杂查询难写，国内用得少。

对前端人：MP 的"写条件→出数据"和你的"发请求→拿数据"心智最接近。

!!! info "需要自定义 SQL 怎么办"
    BaseMapper 不够用时，在 Mapper 接口加方法 + 写 XML 或注解 SQL。本书任务系统的需求 BaseMapper 全够了，不展开。

---

[:octicons-arrow-left-16: 上一章：配置管理](25-config.md) ｜ 下一章：数据库设计实战
