package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.LocationDTO;
import com.toprate.erp.model.Location;
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
public class LocationRepository extends HibernateRepository<Location, Long> {

    public List<LocationDTO> doSearch(LocationDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "LOCATION.LOCATION_ID as locationId,"
                + "LOCATION.LOCATION_NAME as locationName,"
                + "LOCATION.ADDRESS as address,"
                + "LOCATION.OPEN_TIME as openTime,"
                + "LOCATION.CLOSE_TIME as closeTime, "
                + "LOCATION.LOCATION_CODE as locationCode "
                + "FROM LOCATION "
                + "WHERE 1=1 ");

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("AND upper(LOCATION.LOCATION_NAME) LIKE upper(:keySearch) escape '&'");
        }

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT (*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as objects;");

        sql.append(" ORDER BY LOCATION.LOCATION_NAME");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());
        SQLQuery queryCount=currentSession().createSQLQuery(sqlCount.toString());

        query.addScalar("locationId", new LongType());
        query.addScalar("locationName", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("openTime", new DateType());
        query.addScalar("closeTime", new DateType());
        query.addScalar("locationCode", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(LocationDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
            queryCount.setParameter("keySearch","%"+ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }

        query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());

        return query.list();
    }

    public List<LocationDTO> getForAutocomplete(SearchDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT"
                + "LOCATION.LOCATION_ID as locationId,"
                + "LOCATION.LOCATION_NAME as locationName,"
                + "LOCATION.ADDRESS as address,"
                + "LOCATION.OPEN_TIME as openTime,"
                + "LOCATION.CLOSE_TIME as closeTime, "
                + "LOCATION.LOCATION_CODE as locationCode "
                + "FROM LOCATION "
                + "WHERE 1=1 ");

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("AND ( upper(LOCATION.LOCATION_NAME) LIKE upper(:keySearch) escape '&'  OR  upper(LOCATION.LOCATION_CODE) LIKE upper(:keySearch) escape '&' ) ");
        }


        sql.append(" ORDER BY LOCATION.LOCATION_NAME");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.addScalar("locationId", new LongType());
        query.addScalar("locationName", new StringType());
        query.addScalar("address", new StringType());
        query.addScalar("openTime", new DateType());
        query.addScalar("closeTime", new DateType());
        query.addScalar("locationCode", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(LocationDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }


        return query.list();
    }
}
