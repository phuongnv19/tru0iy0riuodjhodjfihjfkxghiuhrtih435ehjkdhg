package com.toprate.erp.repositories;


import com.toprate.erp.dto.ActionAuditDTO;
import com.toprate.erp.dto.LocationDTO;
import com.toprate.erp.model.ActionAudit;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ActionAuditRepository extends HibernateRepository<ActionAudit, Long> {

    public List<ActionAuditDTO> doSearch(ActionAuditDTO obj){
        StringBuilder sql = new StringBuilder("SELECT "
                + "ACTION_AUDIT.AUDIT_ID as auditId,"
                + "ACTION_AUDIT.AUDIT_TYPE_ID as auditTypeId,"
                + "ACTION_AUDIT.ACTOR_TYPE as actorType,"
                + "ACTION_AUDIT.ACTORY_ID as actoryId,"
                + "ACTION_AUDIT.OBJECT_ID as objectId,"
                + "ACTION_AUDIT.OBJECT_TYPE_ID as objectTypeId,"
                + "ACTION_AUDIT.CREATE_DATE as createDate,"
                + "ACTION_AUDIT.DESCRIPTION as description,"
                + "ACTION_AUDIT.USER_ID as userId,"
                + "ACTION_AUDIT.SERVER_IP as serverIp,"
                + "ACTION_AUDIT.SOURCE_ID as sourceId "
                + "FROM ACTION_AUDIT "
                + "WHERE 1=1 ");

        // add where sql
//        if (StringUtils.isNotEmpty(obj.getLocationName())){
//            sql.append("AND upper(LOCATION.LOCATION_NAME) LIKE upper(:locationName) escape '&'");
//        }


        sql.append(" ORDER BY ACTION_AUDIT.AUDIT_ID");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT (*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as objects;");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());
        SQLQuery queryCount=currentSession().createSQLQuery(sqlCount.toString());

        query.addScalar("auditId",new LongType());
        query.addScalar("auditTypeId",new LongType());
        query.addScalar("actorType",new LongType());
        query.addScalar("actoryId",new LongType());
        query.addScalar("objectId",new LongType());
        query.addScalar("objectTypeId",new LongType());
        query.addScalar("createDate",new DateType());
        query.addScalar("description",new StringType());
        query.addScalar("userId",new LongType());
        query.addScalar("serverIp",new StringType());
        query.addScalar("sourceId",new LongType());

        query.setResultTransformer(Transformers.aliasToBean(ActionAuditDTO.class));

        //  check null and setParameter
//        if (StringUtils.isNotEmpty(obj.getLocationName())){
//            query.setParameter("locationName","%"+ ValidateUtils.validateKeySearch(obj.getLocationName())+"%");
//            queryCount.setParameter("locationName","%"+ValidateUtils.validateKeySearch(obj.getLocationName())+"%");
//        }

        query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());

        return query.list();
    }

}
