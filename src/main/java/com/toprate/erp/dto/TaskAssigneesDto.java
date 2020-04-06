package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.TaskAssignees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskAssigneesDto.java
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskAssigneesDto extends ErpBaseDTO {
    private Long assigneeId;

    @AdjHistory(field = "TASK_ID")
    private Long taskId;

    @AdjHistory(field = "USER_ID")
    private Long userId;

    @AdjHistory(field = "IS_ACTIVE")
    private int isActive;

    private String userFullName;

    private String email;

    public TaskAssignees toModel() {
        TaskAssignees taskAssignees = new TaskAssignees();
        taskAssignees.setAssigneeId(assigneeId);
        taskAssignees.setTaskId(taskId);
        taskAssignees.setUserId(userId);
        taskAssignees.setIsActive(isActive);

        return taskAssignees;


    }
}
