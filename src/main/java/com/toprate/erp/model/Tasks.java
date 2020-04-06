package com.toprate.erp.model;

import com.toprate.erp.dto.TaskDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * Tasks.java
 */
@Entity
@Table(name = "TASKS")
@Data
public class Tasks {
    @Id
    @SequenceGenerator(name="pk_sequence_task",sequenceName="TASKS_TASK_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_task")
    @Column(name = "TASK_ID")
    private Long taskId;

    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;

    @Column(name = "IS_PINNED")
    private int isPinned;

    @Column(name = "PRIORITY_LEVEL")
    private Long priorityLevel;

    @Column(name = "CREATOR_ID")
    private Long creatorId;

    @Column(name = "PHASE_ID")
    private Long phaseId;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "TASK_STATUS")
    private Long taskStatus;

    @Column(name = "TASK_TYPE_ID")
    private Long taskTypeId;

    public TaskDto toDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(taskId);
        taskDto.setProjectId(projectId);
        taskDto.setTaskName(taskName);
        taskDto.setTaskDescription(taskDescription);
        taskDto.setStartTime(startTime);
        taskDto.setEndTime(endTime);
        taskDto.setModifiedTime(modifiedTime);
        taskDto.setIsPinned(isPinned);
        taskDto.setPriorityLevel(priorityLevel);
        taskDto.setCreatorId(creatorId);
        taskDto.setPhaseId(phaseId);
        taskDto.setParentId(parentId);
        taskDto.setTaskStatus(taskStatus);
        taskDto.setTaskTypeId(taskTypeId);

        return taskDto;
    }
}
