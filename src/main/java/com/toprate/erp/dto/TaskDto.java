package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.SysUser;
import com.toprate.erp.model.TaskAssignees;
import com.toprate.erp.model.Tasks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import java.util.Date;
import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskDto.java
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {
    private Long taskId;

    @AdjHistory(field = "PROJECT_ID")
    private Long projectId;

    @AdjHistory(field = "TASK_NAME")
    private String taskName;

    @AdjHistory(field = "TASK_DESCRIPTION")
    private String taskDescription;

    @AdjHistory(field = "START_TIME")
    private Date startTime;

    @AdjHistory(field = "END_TIME")
    private Date endTime;

    @AdjHistory(field = "MODIFIED_TIME")
    private Date modifiedTime;

    @AdjHistory(field = "IS_PINNED")
    private int isPinned;

    @AdjHistory(field = "PRIORITY_LEVEL")
    private Long priorityLevel;

    @AdjHistory(field = "CREATOR_ID")
    private Long creatorId;

    @AdjHistory(field = "PHASE_ID")
    private Long phaseId;

    @AdjHistory(field = "PARENT_ID")
    private Long parentId;

    @AdjHistory(field = "TASK_STATUS")
    private Long taskStatus;

    @AdjHistory(field = "TASK_TYPE_ID")
    private Long taskTypeId;

    private String projectCode;

    private String projectName;

    private Long projectStatus;

    private String projectDescription;

    private String userFullName ;

    private  String priorityName;

    private String isPinnedName;

    private String taskTypeName;

    private String taskStatusName;

    private List<TaskAssigneesDto> taskAssigneesList = Lists.newArrayList();

    private List<TaskDto> listSubTask = Lists.newArrayList();

    private List<TaskCommentDto> listComment = Lists.newArrayList();

    public Tasks toModel() {
        Tasks tasks = new Tasks();
        tasks.setTaskId(taskId);
        tasks.setProjectId(projectId);
        tasks.setTaskName(taskName);
        tasks.setTaskDescription(taskDescription);
        tasks.setStartTime(startTime);
        tasks.setEndTime(endTime);
        tasks.setModifiedTime(modifiedTime);
        tasks.setIsPinned(isPinned);
        tasks.setPriorityLevel(priorityLevel);
        tasks.setCreatorId(creatorId);
        tasks.setPhaseId(phaseId);
        tasks.setParentId(parentId);
        tasks.setTaskStatus(taskStatus);
        tasks.setTaskTypeId(taskTypeId);

        return tasks;
    }
}
