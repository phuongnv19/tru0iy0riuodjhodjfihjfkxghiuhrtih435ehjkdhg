package com.toprate.erp.repositories;

import com.toprate.erp.dto.TaskCommentDto;
import com.toprate.erp.model.TaskComments;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/12/2019
 * To change this template use File | Settings | File and Code Templates.
 */
@Repository
public class TaskCommentRepository extends HibernateRepository<TaskComments, Long> {
    public List<TaskCommentDto> getCommentTask(Long taskId) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "TASK_COMMENTS.COMMENT_ID AS commentId, "
                + "TASK_COMMENTS.TASK_ID AS taskId, "
                + "TASK_COMMENTS.COMMENT AS comment, "
                + "TASK_COMMENTS.USER_ID AS userId, "
                + "TASK_COMMENTS.MODIFIED_TIME AS modifiedTime, "
                + "TASKS.TASK_NAME AS taskName, "
                + "concat(sys_user.first_name,' ',sys_user.last_name) as userName "
                + "FROM TASK_COMMENTS "
                + "LEFT JOIN TASKS on TASKS.TASK_ID = TASK_COMMENTS.TASK_ID "
                + "LEFT JOIN sys_user on sys_user.user_id = TASK_COMMENTS.USER_ID "
                + "WHERE TASK_COMMENTS.TASK_ID = :taskId ");

        StringBuilder stringBuilder = new StringBuilder(sql);

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
        query.addScalar("commentId", new LongType());
        query.addScalar("taskId", new LongType());
        query.addScalar("comment", new StringType());
        query.addScalar("userId", new LongType());
        query.addScalar("modifiedTime", new TimestampType());
        query.addScalar("taskName", new StringType());
        query.addScalar("userName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(TaskCommentDto.class));
        query.setLong("taskId", taskId);
        return query.list();
    }
}
