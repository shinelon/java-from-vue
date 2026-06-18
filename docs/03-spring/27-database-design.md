# 数据库设计实战

设计任务管理系统的两张表：`t_user`（用户）和 `t_task`（任务），并理解"实体类 ↔ 数据库表"的映射。

## 表结构

```sql
--8<-- "task-manager/src/main/resources/db/schema.sql"
```

## 实体类 ↔ 表的映射

每个实体类对应一张表，每个字段对应一列。MyBatis-Plus 靠注解映射：

| 注解 | 作用 |
|---|---|
| `@TableName("t_task")` | 类对应表 `t_task` |
| `@TableId(type = AUTO)` | 主键，数据库自增 |
| `@TableLogic` | 逻辑删除字段（删 = 改 deleted=1，数据还在） |
| 不写注解的字段 | 字段名驼峰转下划线（`createTime` → `create_time`） |

任务实体完整代码：

```java
--8<-- "task-manager/src/main/java/com/javaglm/task/entity/Task.java"
```

## 设计要点

**1. 主键自增 id**：每行唯一标识，前端列表的 `:key` 就用它。

**2. user_id 外键关联**：任务归属某个用户。查询时 `WHERE user_id = 当前用户`，实现"各人看各人的任务"。注意我们没用数据库层面的外键约束（性能 + 灵活），靠应用层（Service 里校验归属）保证。

**3. 逻辑删除 deleted**：删任务不是 `DELETE`，而是 `UPDATE deleted=1`。`@TableLogic` 让 MP 查询自动加 `WHERE deleted=0`，已删除的对应用透明不可见。好处：数据可恢复、可审计。

**4. 时间字段 create_time / update_time**：记录创建和修改时间，排序、审计用。

## 跑起来

```bash
# 1. 建库建表
mysql -u root -p < code/task-manager/src/main/resources/db/schema.sql

# 2. 改 application.yml 里的 password 为你的 MySQL 密码
# 3. 启动
cd code/task-manager && mvn spring-boot:run
```

## 实体设计对照

| 数据库 | Java 实体 | 类型 |
|---|---|---|
| `BIGINT id` | `Long id` | 主键 |
| `VARCHAR(100) title` | `String title` | 字符串 |
| `TINYINT status` | `Integer status` | 小整数 |
| `DATETIME create_time` | `LocalDateTime createTime` | 时间 |

!!! tip "为什么 Java 用包装类型 Long 而不是 long"
    `long` 默认值是 0，`Long` 默认是 null。新建任务时 id 还没有，应该是 null（未设置），不是 0。所以主键、可空字段用包装类型。

---

[:octicons-arrow-left-16: 上一章：MyBatis-Plus 数据访问](26-mybatis-plus.md) ｜ 下一章：任务 CRUD 业务实现
