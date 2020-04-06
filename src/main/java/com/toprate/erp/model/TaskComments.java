package com.toprate.erp.model;

import com.toprate.erp.dto.TaskCommentDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskComments.java
 */
@Entity
@Table(name = "TASK_COMMENTS")
@Data
public class TaskComments {
    @Id
    @SequenceGenerator(name="pk_sequence_comment",sequenceName="TASK_COMMENTS_COMMENT_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_comment")
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @Column(name = "TASK_ID")
    private Long taskId;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;

    public TaskCommentDto toDto() {
        TaskCommentDto taskComments = new TaskCommentDto();
        taskComments.setCommentId(commentId);
        taskComments.setTaskId(taskId);
        taskComments.setComment(comment);
        taskComments.setUserId(userId);
        taskComments.setModifiedTime(modifiedTime);
        return taskComments;
    }

}
