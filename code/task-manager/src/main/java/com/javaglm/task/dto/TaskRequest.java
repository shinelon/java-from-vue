package com.javaglm.task.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** 任务新增/修改请求 DTO（第 28 章）。 */
public class TaskRequest {

    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题最长 100 字")
    private String title;

    private String description;

    /** 0=待办 1=进行中 2=已完成，可选。 */
    private Integer status;

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
}
