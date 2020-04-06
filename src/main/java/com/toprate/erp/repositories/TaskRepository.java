package com.toprate.erp.repositories;

import com.toprate.erp.dto.TaskDto;
import com.toprate.erp.model.Tasks;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * TaskRepository.java
 */
@Repository
public class TaskRepository extends HibernateRepository<Tasks,Long> {
    public TaskDto getTaskDetail(TaskDto obj) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "TASKS.TASK_ID AS taskId, "
                + "TASKS.PROJECT_ID AS projectId, "
                + "TASKS.TASK_NAME AS taskName, "
                + "TASKS.TASK_DESCRIPTION AS taskDescription, "
                + "TASKS.START_TIME AS startTime, "
                + "TASKS.END_TIME AS endTime, "
                + "TASKS.MODIFIED_TIME AS modifiedTime, "
                + "TASKS.IS_PINNED AS isPinned, "
                + "TASKS.PRIORITY_LEVEL AS priorityLevel, "
                + "TASKS.CREATOR_ID AS creatorId, "
                + "TASKS.PHASE_ID AS phaseId, "
                + "TASKS.PARENT_ID AS parentId, "
                + "TASKS.TASK_TYPE_ID AS taskTypeId, "
                + "TASKS.TASK_STATUS AS taskStatus, "
                + "PROJECTS.PROJECT_CODE AS projectCode, "
                + "PROJECTS.PROJECT_NAME AS projectName, "
                + "PROJECTS.PROJECT_STATUS AS projectStatus, "
                + "PROJECTS.PROJECT_DESCRIPTION AS projectDescription, "
                + "concat(sys_user.first_name,' ',sys_user.last_name) as userFullName "
                + "FROM TASKS "
                + "inner join PROJECTS  on PROJECTS.PROJECT_ID = TASKS.PROJECT_ID "
                + "LEFT JOIN sys_user on sys_user.user_id = TASKS.CREATOR_ID "
                + "WHERE TASKS.TASK_ID = :taskId ");

        StringBuilder stringBuilder = new StringBuilder(sql);

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
        query.addScalar("taskId", new LongType());
        query.addScalar("projectId", new LongType());
        query.addScalar("taskName", new StringType());
        query.addScalar("taskDescription", new StringType());
        query.addScalar("startTime", new TimestampType());
        query.addScalar("endTime", new TimestampType());
        query.addScalar("modifiedTime", new TimestampType());
        query.addScalar("isPinned", new IntegerType());
        query.addScalar("priorityLevel", new LongType());
        query.addScalar("creatorId", new LongType());
        query.addScalar("phaseId", new LongType());
        query.addScalar("parentId", new LongType());
        query.addScalar("taskTypeId", new LongType());
        query.addScalar("taskStatus", new LongType());
        query.addScalar("projectCode", new StringType());
        query.addScalar("projectName", new StringType());
        query.addScalar("projectStatus", new LongType());
        query.addScalar("projectDescription", new StringType());
        query.addScalar("userFullName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(TaskDto.class));
        query.setLong("taskId", obj.getTaskId());
        return (TaskDto) query.getSingleResult();
    }

    public List<TaskDto> getListSubTask(TaskDto obj) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "TASKS.TASK_ID AS taskId, "
                + "TASKS.PROJECT_ID AS projectId, "
                + "TASKS.TASK_NAME AS taskName, "
                + "TASKS.TASK_DESCRIPTION AS taskDescription, "
                + "TASKS.START_TIME AS startTime, "
                + "TASKS.END_TIME AS endTime, "
                + "TASKS.MODIFIED_TIME AS modifiedTime, "
                + "TASKS.IS_PINNED AS isPinned, "
                + "TASKS.PRIORITY_LEVEL AS priorityLevel, "
                + "TASKS.CREATOR_ID AS creatorId, "
                + "TASKS.PHASE_ID AS phaseId, "
                + "TASKS.PARENT_ID AS parentId, "
                + "TASKS.TASK_TYPE_ID AS taskTypeId, "
                + "TASKS.TASK_STATUS AS taskStatus, "
                + "PROJECTS.PROJECT_CODE AS projectCode, "
                + "PROJECTS.PROJECT_NAME AS projectName, "
                + "PROJECTS.PROJECT_STATUS AS projectStatus, "
                + "PROJECTS.PROJECT_DESCRIPTION AS projectDescription, "
                + "concat(sys_user.first_name,' ',sys_user.last_name) as userFullName "
                + "FROM TASKS "
                + "inner join PROJECTS  on PROJECTS.PROJECT_ID = TASKS.PROJECT_ID "
                + "LEFT JOIN sys_user on sys_user.user_id = TASKS.CREATOR_ID "
                + "WHERE TASKS.PARENT_ID = :taskId ");

        StringBuilder stringBuilder = new StringBuilder(sql);

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
        query.addScalar("taskId", new LongType());
        query.addScalar("projectId", new LongType());
        query.addScalar("taskName", new StringType());
        query.addScalar("taskDescription", new StringType());
        query.addScalar("startTime", new TimestampType());
        query.addScalar("endTime", new TimestampType());
        query.addScalar("modifiedTime", new TimestampType());
        query.addScalar("isPinned", new IntegerType());
        query.addScalar("priorityLevel", new LongType());
        query.addScalar("creatorId", new LongType());
        query.addScalar("phaseId", new LongType());
        query.addScalar("parentId", new LongType());
        query.addScalar("taskTypeId", new LongType());
        query.addScalar("taskStatus", new LongType());
        query.addScalar("projectCode", new StringType());
        query.addScalar("projectName", new StringType());
        query.addScalar("projectStatus", new LongType());
        query.addScalar("projectDescription", new StringType());
        query.addScalar("userFullName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(TaskDto.class));
        query.setLong("taskId", obj.getTaskId());
        return  query.list();
    }
}
