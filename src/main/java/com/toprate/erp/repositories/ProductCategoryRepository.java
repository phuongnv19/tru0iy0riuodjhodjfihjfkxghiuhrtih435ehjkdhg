package com.toprate.erp.repositories;

import com.toprate.erp.model.ProductCategory;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCategoryRepository extends HibernateRepository<ProductCategory, Long> {

    public boolean checkExistFtMappingCategoryAndType(String productCategoryTypeCode) {
        StringBuilder sql = new StringBuilder("select * from product_type pt  join product_type_category ptc on pt.id = ptc.product_type_id " +
                " where pt.id in (select pt1.id from product_type pt1 where pt1.product_type_code = :productCategoryTypeCode)");
        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        query.setParameter("productCategoryTypeCode", productCategoryTypeCode);
        if (query.list().size() > 0) {
            return true;
        }
        return false;
    }
}
