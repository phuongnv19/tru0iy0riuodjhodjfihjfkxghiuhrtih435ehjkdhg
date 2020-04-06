package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SonNPT.
 * Date: 12/20/2019
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskTypeDto {
    @AdjHistory(field = "TASK_TYPE_ID")
    private long taskTypeId;

    @AdjHistory(field = "TASK_TYPE_NAME")
    private String taskTypeName;

    public TaskType toDto() {
        TaskType taskType = new TaskType();
        taskType.setTaskTypeId(taskTypeId);
        taskType.setTaskTypeName(taskTypeName);
        return taskType;
    }
}
