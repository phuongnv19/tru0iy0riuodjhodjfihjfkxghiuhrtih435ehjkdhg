package com.toprate.erp.repositories;

import com.toprate.erp.dto.StockDTO;
import com.toprate.erp.model.Stock;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class StockRepository extends HibernateRepository<Stock, Long> {

    public List<StockDTO> doSearch(StockDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT " +
                " obj.STOCK_ID stockId," +
                " obj.STOCK_PARENT_ID stockParentId," +
                " obj.STOCK_NAME stockName," +
                " obj.STOCK_CATEGORY stockCategory," +
                " obj.STOCK_INIT_QUATITY stockInitQuatity," +
                " obj.STOCK_CODE stockCode," +
                " obj.STOCK_QUANTITY stockQuantity," +
                " obj.STOCK_TYPE stockType," +
                " obj.STOCK_UNIT stockUnit," +
                " obj.STOCK_STATUS stockStatus," +
                " location.location_name stockLocation," +
                " obj.STOCK_VALUE stockValue," +
                " obj.STOCK_DESCRIPTION stockDescription," +
                " obj.STOCK_IMAGE stockImage," +
                " obj.CREATE_USER_ID createUserId," +
                " obj.CREATE_DATE createDate," +
                " obj.location_id locationId," +
                " obj.MODIFIED_DATE modifiedDate" +
                " FROM STOCK AS obj  " +
                " LEFT JOIN location ON location.location_id=obj.location_id "+
                "");

        sql.append(" WHERE 1=1  ");

        if (StringUtils.isNotEmpty(obj.getStockCode())) {
            sql.append(" AND upper(obj.STOCK_CODE) LIKE upper(:stockCode) escape '&'  ");
        }
        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND ( upper(obj.STOCK_CODE) LIKE upper(:keySearch) escape '&'  OR upper(obj.STOCK_NAME) LIKE upper(:keySearch) escape '&' ) ");
        }

        if (StringUtils.isNotEmpty(obj.getStockType())) {
            sql.append(" AND upper(obj.STOCK_TYPE) LIKE upper(:stockType) escape '&'  ");
        }
        if (StringUtils.isNotEmpty(obj.getStockName())) {
            sql.append(" AND upper(obj.STOCK_NAME) LIKE upper(:stockName) escape '&'  ");
        }
        if (null != obj.getStockInitQuatity()) {
            sql.append(" AND obj.STOCK_INIT_QUATITY =:stockInitQuatity");
        }

        sql.append(" ORDER BY obj.STOCK_ID ");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as stock;");


        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());
        query.addScalar("stockId", new LongType());
        query.addScalar("stockParentId", new LongType());
        query.addScalar("stockName", new StringType());
        query.addScalar("stockCategory", new StringType());
        query.addScalar("stockInitQuatity", new LongType());
        query.addScalar("stockCode", new StringType());
        query.addScalar("stockQuantity", new LongType());
        query.addScalar("stockType", new StringType());
        query.addScalar("stockUnit", new LongType());
        query.addScalar("stockStatus", new LongType());
        query.addScalar("stockLocation", new StringType());
        query.addScalar("stockValue", new DoubleType());
        query.addScalar("stockDescription", new StringType());
        query.addScalar("stockImage", new StringType());
        query.addScalar("createUserId", new LongType());
        query.addScalar("locationId", new LongType());
        query.addScalar("createDate", new DateType());
        query.addScalar("modifiedDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
        if (StringUtils.isNotEmpty(obj.getStockCode())) {
            query.setParameter("stockCode", "%"+ ValidateUtils.validateKeySearch(obj.getStockCode())+"%");
            queryCount.setParameter("stockCode", "%"+ ValidateUtils.validateKeySearch(obj.getStockCode())+"%");
        }

        if (StringUtils.isNotEmpty(obj.getStockType())) {
            query.setParameter("stockType", "%"+ ValidateUtils.validateKeySearch(obj.getStockType())+"%");
            queryCount.setParameter("stockType", "%"+ ValidateUtils.validateKeySearch(obj.getStockType())+"%");
        }
        if (StringUtils.isNotEmpty(obj.getStockName())) {
            query.setParameter("stockName", "%"+ ValidateUtils.validateKeySearch(obj.getStockName())+"%");
            queryCount.setParameter("stockName", "%"+ ValidateUtils.validateKeySearch(obj.getStockName())+"%");
        }
        if (null != obj.getStockInitQuatity()) {
            query.setParameter("stockInitQuatity", obj.getStockInitQuatity());
            queryCount.setParameter("stockInitQuatity", obj.getStockInitQuatity());
        }
        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
            queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }
        if(obj.getPage()!=null && obj.getPageSize()!=null) {
            query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
        }
        if (obj.getPage() != null && obj.getPageSize() != null) {
            query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
            query.setMaxResults(obj.getPageSize().intValue());
            obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
        }
        return query.list();
    }
}
