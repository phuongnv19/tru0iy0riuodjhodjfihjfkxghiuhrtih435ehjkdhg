package com.toprate.erp.repositories;

import com.toprate.erp.dto.TaskAssigneesDto;
import com.toprate.erp.model.TaskAssignees;
import com.toprate.erp.model.Tasks;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskAssigneesRepository.java
 */
@Repository
public class TaskAssigneesRepository extends HibernateRepository<TaskAssignees,Long> {
    public List<TaskAssigneesDto> getTaskAssigneesDto(Long taskId) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "TASK_ASSIGNEES.ASSIGNEE_ID AS assigneeId, "
                + "TASK_ASSIGNEES.TASK_ID AS taskId, "
                + "TASK_ASSIGNEES.USER_ID AS userId, "
                + "TASK_ASSIGNEES.IS_ACTIVE AS isActive, "
                + "concat(sys_user.first_name,' ',sys_user.last_name) as userFullName, "
                + "sys_user.EMAIL AS email "
                + "FROM TASK_ASSIGNEES "
                + "LEFT JOIN sys_user on sys_user.user_id = TASK_ASSIGNEES.USER_ID "
                + "WHERE TASK_ASSIGNEES.TASK_ID = :taskId ");

        StringBuilder stringBuilder = new StringBuilder(sql);

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
        query.addScalar("assigneeId", new LongType());
        query.addScalar("taskId", new LongType());
        query.addScalar("userId", new LongType());
        query.addScalar("isActive", new IntegerType());
        query.addScalar("userFullName", new StringType());
        query.addScalar("email", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(TaskAssigneesDto.class));
        query.setLong("taskId", taskId);
        return query.list();
    }

}
