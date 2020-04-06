package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.ProductTypeDTO;
import com.toprate.erp.model.ProductType;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ProductTypeRepository extends HibernateRepository<ProductType, Long> {

    public List<ProductTypeDTO> getListProductType(Long categoryId){

        StringBuilder sql = new StringBuilder( "SELECT DISTINCT "
                + "PRODUCT_TYPE.ID as id, "
                + "PRODUCT_TYPE.PRODUCT_TYPE_CODE as productTypeCode, "
                + "PRODUCT_TYPE.PRODUCT_TYPE_NAME as productTypeName "
                + "FROM PRODUCT_TYPE "
                + "LEFT JOIN PRODUCT_TYPE_CATEGORY "
                + "ON PRODUCT_TYPE.ID = PRODUCT_TYPE_CATEGORY.PRODUCT_TYPE_ID "
                + "WHERE PRODUCT_CATEGORY_ID = :categoryId "
                + "ORDER BY PRODUCT_TYPE.ID ");
        SQLQuery query = currentSession().createSQLQuery(sql.toString());

        query.addScalar("id",new LongType());
        query.addScalar("productTypeCode",new StringType());
        query.addScalar("productTypeName",new StringType());

        query.setParameter("categoryId",categoryId);

        query.setResultTransformer(Transformers.aliasToBean(ProductTypeDTO.class));

        return query.list();
    }

    public List<ProductTypeDTO> autoCompleteSearch(SearchDTO obj){
        StringBuilder sql = new StringBuilder( "SELECT DISTINCT "
                + "PRODUCT_TYPE.ID as id, "
                + "PRODUCT_TYPE.PRODUCT_TYPE_CODE as productTypeCode, "
                + "PRODUCT_TYPE.PRODUCT_TYPE_NAME as productTypeName "
                + "FROM PRODUCT_TYPE "
                + "WHERE 1=1 ");

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append(" AND ( " +
                    " upper(PRODUCT_TYPE_CODE) like upper(:keySearch) OR " +
                    " upper(PRODUCT_TYPE_NAME) like upper(:keySearch) " +
                    " ) ");
        }


        sql.append(" ORDER BY PRODUCT_TYPE_NAME  limit 20 ");
        SQLQuery query = currentSession().createSQLQuery(sql.toString());

        query.addScalar("id",new LongType());
        query.addScalar("productTypeCode",new StringType());
        query.addScalar("productTypeName",new StringType());
        query.setResultTransformer(Transformers.aliasToBean(ProductTypeDTO.class));
        query.setResultTransformer(Transformers.aliasToBean(ProductTypeDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }


        return query.list();
    }
}
