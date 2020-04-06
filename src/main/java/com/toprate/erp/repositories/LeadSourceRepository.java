package com.toprate.erp.repositories;

import com.toprate.erp.dto.LeadSourceDTO;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.toprate.erp.model.LeadSource;
import com.toprate.erp.repositories.common.HibernateRepository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class LeadSourceRepository extends HibernateRepository<LeadSource, Long> {
    public List<LeadSourceDTO> doSearch(LeadSourceDTO obj){
        StringBuilder sql = new StringBuilder("SELECT " +
                "ls.id as id, " +
                "ls.lead_source_company_name as leadSourceCompanyName, " +
                "ls.lead_source_type as leadSourceType, " +
                "ls.member_id as memberId, " +
                "ls.user_id as userId," +
                "concat(member.first_name,' ',member.last_name) as memberFullName," +
                "concat(sys_user.first_name,' ',sys_user.last_name) as userFullName " +
                "FROM " +
                "lead_source as ls " +
                "LEFT JOIN member on member.member_id = ls.member_id " +
                "LEFT JOIN sys_user on sys_user.user_id = ls.user_id " +
                "WHERE 1=1 ");
        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("AND ( " );
                sql.append(" upper(ls.lead_source_company_name) LIKE upper(:keySearch) ");
            sql.append(" )");
        }

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT (*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as objects;");

        sql.append(" ORDER BY ls.lead_source_company_name ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());
        SQLQuery queryCount=currentSession().createSQLQuery(sqlCount.toString());

        query.addScalar("id", new LongType());
        query.addScalar("leadSourceCompanyName", new StringType());
        query.addScalar("leadSourceType", new StringType());
        query.addScalar("userId", new LongType());
        query.addScalar("memberId", new LongType());
        query.addScalar("memberFullName", new StringType());
        query.addScalar("userFullName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(LeadSourceDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
            queryCount.setParameter("keySearch","%"+ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }

        query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());

        return query.list();
    }
}
