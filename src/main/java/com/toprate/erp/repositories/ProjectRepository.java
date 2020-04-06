package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.ProjectDto;
import com.toprate.erp.model.Projects;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by SonNPT.
 * Date: 12/11/2019
 * ProjectRepository.java
 */
@Repository
public class ProjectRepository extends HibernateRepository<Projects, Long> {

    public List<ProjectDto> getAllProject(SearchDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "PROJECTS.PROJECT_ID AS projectId, "
                + "PROJECTS.PROJECT_CODE AS projectCode, "
                + "PROJECTS.CREATOR_ID AS creatorId, "
                + "PROJECTS.PROJECT_NAME AS projectName, "
                + "PROJECTS.PROJECT_STATUS AS projectStatus, "
                + "PROJECTS.PROJECT_DESCRIPTION AS projectDescription, "
                + "PROJECTS.CREATED_TIME AS createdTime "
                + " FROM PROJECTS "
                + "WHERE 1=1 ");

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append("  AND ( upper(PROJECTS.PROJECT_NAME) LIKE upper(:keySearch)  escape '&'" +
                    " OR upper(PROJECTS.PROJECT_CODE) LIKE upper(:keySearch)  escape '&') ");
        }

        sql.append(" ORDER BY PROJECTS.PROJECT_NAME ");
        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as PROJECTS ");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("projectId", new LongType());
        query.addScalar("projectCode", new StringType());
        query.addScalar("creatorId", new LongType());
        query.addScalar("projectName", new StringType());
        query.addScalar("projectStatus", new LongType());
        query.addScalar("projectDescription", new StringType());
        query.addScalar("createdTime", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(ProjectDto.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
            queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
        }

        query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

        return query.list();
    }
}
