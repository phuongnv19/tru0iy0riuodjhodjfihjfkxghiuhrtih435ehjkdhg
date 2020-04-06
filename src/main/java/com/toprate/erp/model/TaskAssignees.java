package com.toprate.erp.model;

import com.toprate.erp.dto.TaskAssigneesDto;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskAssignees.java
 */
@Entity
@Table(name = "TASK_ASSIGNEES")
@Data
public class TaskAssignees {
    @Id
    @SequenceGenerator(name="pk_sequence_task_assignees",sequenceName="TASK_ASSIGNEES_ASSIGNEE_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_task_assignees")
    @Column(name = "ASSIGNEE_ID")
    private Long assigneeId;

    @Column(name = "TASK_ID")
    private Long taskId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    public TaskAssigneesDto toDto() {
        TaskAssigneesDto taskAssignees = new TaskAssigneesDto();
        taskAssignees.setAssigneeId(assigneeId);
        taskAssignees.setTaskId(taskId);
        taskAssignees.setUserId(userId);
        taskAssignees.setIsActive(isActive);

        return taskAssignees;


    }


}
