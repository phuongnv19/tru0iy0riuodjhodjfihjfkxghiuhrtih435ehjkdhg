package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.TaskComments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskCommentDto.java
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskCommentDto {

    private Long commentId;

    @AdjHistory(field = "TASK_ID")
    private Long taskId;

    @AdjHistory(field = "COMMENT")
    private String comment;

    @AdjHistory(field = "USER_ID")
    private Long userId;

    @AdjHistory(field = "MODIFIED_TIME")
    private Date modifiedTime;

    private String taskName;

    private String userName;

    public TaskComments toModel() {
        TaskComments taskComments = new TaskComments();
        taskComments.setCommentId(commentId);
        taskComments.setTaskId(taskId);
        taskComments.setComment(comment);
        taskComments.setUserId(userId);
        taskComments.setModifiedTime(modifiedTime);

        return taskComments;
    }
}
