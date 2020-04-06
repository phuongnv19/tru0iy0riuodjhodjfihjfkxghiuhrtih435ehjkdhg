package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.toprate.erp.dto.CompanyDTO;
import com.toprate.erp.model.Company;
import com.toprate.erp.repositories.common.HibernateRepository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class CompanyRepository extends HibernateRepository<Company, Long> {

    public void updateFromCrmLead(CompanyDTO companyDto) {
        StringBuilder sql = new StringBuilder("UPDATE company SET abn =");
        sql.append(companyDto.getAbn());
        sql.append(", acn = '");
        sql.append(companyDto.getAcn());
        sql.append("' WHERE company_id = ");
        sql.append(companyDto.getCompanyId());

        SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
        sqlQuery.executeUpdate();

    }

    public List<CompanyDTO> autoCompleteSearch(SearchDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT "
                + "cpn.company_id as companyId,"
                + "cpn.company_code as companyCode,"
                + "cpn.company_email as companyEmail,"
                + "cpn.company_full_name as companyFullName,"
                + "cpn.company_phone as companyPhone,"
                + "cpn.country as country "
                + "FROM COMPANY as cpn "
                + " LEFT JOIN MEMBER as mb ON mb.company_id = cpn.company_id "
                + "WHERE 1=1 ");
        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND ( "
                    + " upper(COMPANY_FULL_NAME) like upper(:keySearch) OR "
                    + " upper(mb.FIRST_NAME) like upper(:keySearch) OR "
                    + " upper(mb.LAST_NAME) like upper(:keySearch) OR "
                    + " upper(concat(mb.FIRST_NAME,' ',mb.LAST_NAME)) like upper(:keySearch) OR "
                    + " upper(mb.MEMBER_EMAIL) like upper(:keySearch) "
                    + " ) ");
        }


        sql.append(" ORDER BY cpn.COMPANY_FULL_NAME  limit 20");

        SQLQuery query = currentSession().createSQLQuery(sql.toString());

        query.addScalar("companyId", new LongType());
        query.addScalar("companyCode", new StringType());
        query.addScalar("companyEmail", new StringType());
        query.addScalar("companyFullName", new StringType());
        query.addScalar("companyPhone", new LongType());
        query.addScalar("country", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(CompanyDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
        }


        return query.list();
    }

    public List<CompanyDTO> doSearch(SearchDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "cpn.company_id as companyId,"
                + "cpn.company_code as companyCode,"
                + "cpn.company_email as companyEmail,"
                + "cpn.company_full_name as companyFullName,"
                + "cpn.company_phone as companyPhone,"
                + "cpn.official_company_address as officialCompanyAddress,"
                + "cpn.country as country,"
                + "cpn.country_num_prefix as countryNumPrefix,"
                + "cpn.company_landline_phone as companyLandlinePhone,"
                + "cpn.fax as fax,"
                + "cpn.abn as abn,"
                + "cpn.acn as acn,"
                + "cpn.company_intro as companyIntro "
                + "FROM COMPANY as cpn "
                + " LEFT JOIN MEMBER as mb ON mb.company_id = cpn.company_id "
                + "WHERE 1=1 ");
        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND ( "
                    + " upper(COMPANY_FULL_NAME) like upper(:keySearch) OR "
                    + " upper(mb.FIRST_NAME) like upper(:keySearch) OR "
                    + " upper(mb.LAST_NAME) like upper(:keySearch) OR "
                    + " upper(concat(mb.FIRST_NAME,' ',mb.LAST_NAME)) like upper(:keySearch) OR "
                    + " upper(mb.MEMBER_EMAIL) like upper(:keySearch) "
                    + " ) ");
        }


        sql.append(" ORDER BY cpn.COMPANY_FULL_NAME ");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as COMPANY ");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("companyId", new LongType());
        query.addScalar("companyCode", new StringType());
        query.addScalar("companyEmail", new StringType());
        query.addScalar("companyFullName", new StringType());
        query.addScalar("companyPhone", new LongType());
        query.addScalar("officialCompanyAddress", new StringType());
        query.addScalar("country", new StringType());
        query.addScalar("countryNumPrefix", new LongType());
        query.addScalar("companyLandlinePhone", new LongType());
        query.addScalar("fax", new LongType());
        query.addScalar("abn", new LongType());
        query.addScalar("acn", new StringType());
        query.addScalar("companyIntro", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(CompanyDTO.class));

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
