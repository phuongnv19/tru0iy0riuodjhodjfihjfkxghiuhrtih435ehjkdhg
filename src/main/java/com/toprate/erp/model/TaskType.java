package com.toprate.erp.model;

import com.toprate.erp.dto.TaskTypeDto;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by SonNPT.
 * Date: 12/20/2019
 */
@Entity
@Table(name = "TASK_TYPE")
@Data
public class TaskType {
    @Id
    @SequenceGenerator(name="pk_sequence_task_type",sequenceName="TASK_TYPE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_task_type")
    @Column(name = "TASK_TYPE_ID")
    private Long taskTypeId;

    @Column(name = "TASK_TYPE_NAME")
    private String taskTypeName;

    public TaskTypeDto toDto() {
        TaskTypeDto taskTypeDto = new TaskTypeDto();
        taskTypeDto.setTaskTypeId(taskTypeId);
        taskTypeDto.setTaskTypeName(taskTypeName);
        return taskTypeDto;
    }
}
