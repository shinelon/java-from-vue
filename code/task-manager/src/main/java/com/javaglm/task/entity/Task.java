package com.javaglm.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 任务实体（第 27 章），对应数据库表 t_task。
 * 一个字段 = 一列。MyBatis-Plus 根据它自动生成 SQL。
 */
@TableName("t_task")
public class Task {

    /** 主键，数据库自增。 */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String description;

    /** 0=待办 1=进行中 2=已完成。 */
    private Integer status;

    /** 归属用户 ID（外键关联 t_user）。 */
    private Long userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /** 逻辑删除：0=正常 1=已删除。@TableLogic 让 MyBatis-Plus 查询自动过滤已删除。 */
    @TableLogic
    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
