package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.CrmBookingDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.model.Product;
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
public class ProductRepository extends HibernateRepository<Product, Long> {

    public List<ProductDTO> doSearch(ProductDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT obj.PRODUCT_ID productId," +
                " obj.PARENT_PRODUCT_ID parentProductId," +
                " objParent.PRODUCT_CODE parentProductCode," +
                " obj.LOCATION_ID locationId," +
                " obj.PRODUCT_NAME productName," +
                " obj.PRODUCT_CATEGORY_ID productCategoryId," +
                " obj.PRODUCT_TYPE productType," +
                " obj.PRODUCT_UNIT productUnit," +
                " obj.PRODUCT_CODE productCode," +
                " obj.PRODUCT_RATE productRate," +
                " obj.PRODUCT_QUANTITY productQuantity," +
                " obj.PRODUCT_MONTHLY_FEE productMonthlyFee," +
                " obj.PRODUCT_WEEKLY_FEE productWeeklyFee," +
                " obj.PRODUCT_HOURLY_FEE productHourlyFee," +
                " obj.PRODUCT_SIZE productSize," +
                " obj.PRODUCT_WORKSTATION productWorkstation," +
                " obj.PRODUCT_CAPACITY productCapacity," +
                " location.location_name productLocation," +
                " obj.PRODUCT_VALUE productValue," +
                " obj.PRODUCT_DESCRIPTION productDescription," +
                " obj.PRODUCT_IMAGE productImage," +
                " obj.PRODUCT_BONUS_HOURS productBonusHours," +
                " obj.PRODUCT_BONUS_CREDIT productBonusCredit," +
                " obj.CREATE_USER_ID createUserId," +
                " obj.CREATE_DATE createDate," +
                " obj.MODIFIED_DATE modifiedDate," +
                " obj.PRODUCT_TYPE_ID productTypeId," +
                " product_category.PRODUCT_CATEGORY_NAME productCategoryName," +
                " product_type.PRODUCT_TYPE_NAME productTypeName," +
                " COALESCE(ic.booking_status, 0) productUseStatus " +
                " FROM PRODUCT AS obj " +
                " LEFT join inventory_calendar ic on ic.product_id = obj.product_id and (now() BETWEEN ic.from_date and ic.to_date)" +
                " Left join PRODUCT objParent on  obj.PARENT_PRODUCT_ID = objParent.PRODUCT_ID " +
                 "LEFT JOIN location ON location.location_id=obj.location_id "+
                "LEFT JOIN product_type ON product_type.id=obj.PRODUCT_TYPE_ID "+
                "LEFT JOIN product_category ON TO_CHAR(product_category.id, '999999')=obj.PRODUCT_CATEGORY_ID ");

        sql.append(" WHERE 1=1  ");
        if (StringUtils.isNotEmpty(obj.getProductName())) {
            sql.append(" AND upper(obj.PRODUCT_NAME) LIKE upper(:name) escape '&'  ");
        }

        if (StringUtils.isNotEmpty(obj.getProductCode())) {
            sql.append(" AND upper(obj.PRODUCT_CODE) LIKE upper(:code) escape '&'  ");
        }

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND ( upper(obj.PRODUCT_CODE) LIKE upper(:keySearch) escape '&'  OR upper(obj.PRODUCT_NAME) LIKE upper(:keySearch) escape '&') ");
        }
        if (StringUtils.isNotEmpty(obj.getProductLocation())) {
            sql.append(" AND upper(obj.PRODUCT_LOCATION) LIKE upper(:productLocation) escape '&'  ");
        }

        if (StringUtils.isNotEmpty(obj.getProductType())) {
            sql.append(" AND upper(obj.PRODUCT_LOCATION) LIKE upper(:productType) escape '&'  ");
        }

//        if (null!=obj.getProductUseStatus()) {
//            sql.append(" AND ic.product_use_status =:productUseStatus  ");
//        }

        if (null!=obj.getProductWorkstation()) {
            sql.append(" AND obj.PRODUCT_WORKSTATION =:productWorkstation ");
        }

        if (null!=obj.getProductSize()) {
            sql.append(" AND obj.PRODUCT_SIZE =:productSize ");
        }

        if (null!=obj.getProductValue()) {
            sql.append(" AND obj.PRODUCT_VALUE =:productValue ");
        }

        sql.append(" ORDER BY obj.PRODUCT_ID ");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as product;");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("productId", new LongType());
        query.addScalar("parentProductId", new LongType());
        query.addScalar("parentProductCode", new StringType());
        query.addScalar("productTypeId", new LongType());
        query.addScalar("locationId", new LongType());
        query.addScalar("productName", new StringType());
        query.addScalar("productCategoryId", new StringType());
        query.addScalar("productType", new StringType());
        query.addScalar("productCode", new StringType());
        query.addScalar("productRate", new DoubleType());
        query.addScalar("productQuantity", new DoubleType());
        query.addScalar("productUnit", new DoubleType());
        query.addScalar("productMonthlyFee", new DoubleType());
        query.addScalar("productWeeklyFee", new DoubleType());
        query.addScalar("productHourlyFee", new DoubleType());
        query.addScalar("productSize", new LongType());
        query.addScalar("productWorkstation", new LongType());
        query.addScalar("productCapacity", new LongType());
        query.addScalar("productLocation", new StringType());
        query.addScalar("productValue", new DoubleType());
        query.addScalar("productDescription", new StringType());
        query.addScalar("productImage", new StringType());
        query.addScalar("productBonusHours", new LongType());
        query.addScalar("productBonusCredit", new LongType());
        query.addScalar("createUserId", new LongType());
        query.addScalar("createDate", new DateType());
        query.addScalar("modifiedDate", new DateType());
        query.addScalar("productCategoryName", new StringType());
        query.addScalar("productTypeName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
        if (StringUtils.isNotEmpty(obj.getProductName())) {
            query.setParameter("name", "%"+ ValidateUtils.validateKeySearch(obj.getProductName())+"%");
            queryCount.setParameter("name", "%"+ ValidateUtils.validateKeySearch(obj.getProductName())+"%");
        }

        if (StringUtils.isNotEmpty(obj.getProductCode())) {
            query.setParameter("code", "%"+ ValidateUtils.validateKeySearch(obj.getProductCode())+"%");
            queryCount.setParameter("code", "%"+ ValidateUtils.validateKeySearch(obj.getProductCode())+"%");
        }

        if (StringUtils.isNotEmpty(obj.getProductLocation())) {
            query.setParameter("productLocation", "%"+ ValidateUtils.validateKeySearch(obj.getProductLocation())+"%");
            queryCount.setParameter("productLocation", "%"+ ValidateUtils.validateKeySearch(obj.getProductLocation())+"%");
        }

        if (StringUtils.isNotEmpty(obj.getProductType())) {
            query.setParameter("productType", "%"+ ValidateUtils.validateKeySearch(obj.getProductType())+"%");
            queryCount.setParameter("productType", "%"+ ValidateUtils.validateKeySearch(obj.getProductType())+"%");
        }

//        if (null!=obj.getProductUseStatus()) {
//            query.setParameter("productUseStatus", obj.getProductUseStatus());
//            queryCount.setParameter("productUseStatus", obj.getProductUseStatus());
//        }

        if (null!=obj.getProductWorkstation()) {
            query.setParameter("productWorkstation", obj.getProductWorkstation());
            queryCount.setParameter("productWorkstation", obj.getProductWorkstation());
        }

        if (null!=obj.getProductSize()) {
            query.setParameter("productSize", obj.getProductSize());
            queryCount.setParameter("productSize", obj.getProductSize());
        }

        if (null!=obj.getProductValue()) {
            query.setParameter("productValue", obj.getProductValue());
            queryCount.setParameter("productValue", obj.getProductValue());
        }
        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
            queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }
        if(obj.getPage()!=null && obj.getPageSize()!=null){
            query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
            query.setMaxResults(obj.getPageSize().intValue());
            obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());
        }

        return query.list();
    }

    public List<ProductDTO> doSearchForMasterCalender(ProductDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT ic.PRODUCT_ID productId, " +
                " pd.product_code productCode, " +
                " pd.product_name productName, " +
                " objParent.product_name parentProductName, " +
                " objParent.product_code parentProductCode, " +
                " pd.PARENT_PRODUCT_ID parentProductId, " +
                " ic.product_use_status productUseStatus, " +
                " ic.CREATE_DATE createDate, " +
                " ic.from_date fromDate, " +
                " ic.to_date toDate " +
                "                FROM  inventory_calendar ic join PRODUCT pd on ic.product_id = pd.product_id " +
                " left join PRODUCT objParent on  pd.PARENT_PRODUCT_ID = objParent.PRODUCT_ID " +
//                " where EXTRACT(MONTH FROM ic.create_date) = EXTRACT(MONTH FROM CURRENT_DATE)" +
                "");

        sql.append(" ORDER BY ic.PRODUCT_ID ");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as productForMasterCalender;");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("productId", new LongType());
        query.addScalar("parentProductId", new LongType());
        query.addScalar("productUseStatus", new LongType());
        query.addScalar("parentProductCode", new StringType());
        query.addScalar("parentProductName", new StringType());
        query.addScalar("productName", new StringType());
        query.addScalar("productCode", new StringType());
        query.addScalar("createDate", new DateType());
        query.addScalar("fromDate", new DateType());
        query.addScalar("toDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
//        if(obj.getPage()!=null && obj.getPageSize()!=null){
//            query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//            query.setMaxResults(obj.getPageSize().intValue());
//            obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());
//        }


        return query.list();
    }

    public boolean checkProParentHasExist(String parentCode) {
        StringBuilder sql = new StringBuilder("SELECT obj.PRODUCT_ID productId " +
                "FROM PRODUCT AS obj " +
                "where obj.PRODUCT_CODE = :productCode and obj.PARENT_PRODUCT_ID is null");
        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
        query.setParameter("productCode", parentCode);
        if (query.list().size() > 0) {
            return true;
        }
        return false;
    }

    public List<ProductDTO> getByLocationId(Long locationId) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT obj.PRODUCT_ID productId," +
                " obj.PARENT_PRODUCT_ID parentProductId," +
                " objParent.PRODUCT_CODE parentProductCode," +
                " obj.LOCATION_ID locationId," +
                " obj.PRODUCT_NAME productName," +
                " obj.PRODUCT_CATEGORY_ID productCategoryId," +
                " obj.PRODUCT_TYPE productType," +
                " obj.PRODUCT_UNIT productUnit," +
                " obj.PRODUCT_CODE productCode," +
                " obj.PRODUCT_RATE productRate," +
                " obj.PRODUCT_QUANTITY productQuantity," +
                " obj.PRODUCT_MONTHLY_FEE productMonthlyFee," +
                " obj.PRODUCT_WEEKLY_FEE productWeeklyFee," +
                " obj.PRODUCT_HOURLY_FEE productHourlyFee," +
                " obj.PRODUCT_SIZE productSize," +
                " obj.PRODUCT_WORKSTATION productWorkstation," +
                " obj.PRODUCT_CAPACITY productCapacity," +
                " location.location_name productLocation," +
                " obj.PRODUCT_VALUE productValue," +
                " obj.PRODUCT_DESCRIPTION productDescription," +
                " obj.PRODUCT_IMAGE productImage," +
                " obj.PRODUCT_BONUS_HOURS productBonusHours," +
                " obj.PRODUCT_BONUS_CREDIT productBonusCredit," +
                " obj.CREATE_USER_ID createUserId," +
                " obj.CREATE_DATE createDate," +
                " obj.MODIFIED_DATE modifiedDate," +
                " obj.PRODUCT_TYPE_ID productTypeId" +
//                " ic.product_use_status productUseStatus " +
                " FROM PRODUCT AS obj " +
                " join inventory_calendar ic on ic.product_id = obj.product_id" +
                " Left join PRODUCT objParent on  obj.PARENT_PRODUCT_ID = objParent.PRODUCT_ID " +
                "LEFT JOIN location ON location.location_id=obj.location_id "+
                "");

        sql.append(" WHERE 1=1  ");

        sql.append(" ORDER BY obj.PRODUCT_ID ");


        NativeQuery query = currentSession().createNativeQuery(sql.toString());

        query.addScalar("productId", new LongType());
        query.addScalar("parentProductId", new LongType());
        query.addScalar("parentProductCode", new StringType());
        query.addScalar("productTypeId", new LongType());
        query.addScalar("locationId", new LongType());
        query.addScalar("productName", new StringType());
        query.addScalar("productCategoryId", new StringType());
        query.addScalar("productType", new StringType());
        query.addScalar("productCode", new StringType());
        query.addScalar("productRate", new DoubleType());
        query.addScalar("productQuantity", new DoubleType());
        query.addScalar("productUnit", new DoubleType());
        query.addScalar("productMonthlyFee", new DoubleType());
        query.addScalar("productWeeklyFee", new DoubleType());
        query.addScalar("productHourlyFee", new DoubleType());
        query.addScalar("productSize", new LongType());
        query.addScalar("productWorkstation", new LongType());
        query.addScalar("productCapacity", new LongType());
        query.addScalar("productLocation", new StringType());
        query.addScalar("productValue", new DoubleType());
        query.addScalar("productDescription", new StringType());
        query.addScalar("productImage", new StringType());
        query.addScalar("productBonusHours", new LongType());
        query.addScalar("productBonusCredit", new LongType());
        query.addScalar("createUserId", new LongType());
        query.addScalar("createDate", new DateType());
        query.addScalar("modifiedDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
            query.setParameter("name", locationId);


        return query.list();
    }

    public List<ProductDTO> autoCompleteSearch(SearchDTO obj){
        StringBuilder sql = new StringBuilder("SELECT DISTINCT obj.PRODUCT_ID productId," +
                " obj.PARENT_PRODUCT_ID parentProductId," +
                " obj.LOCATION_ID locationId," +
                " obj.PRODUCT_NAME productName," +
                " obj.PRODUCT_CATEGORY_ID productCategoryId," +
                " obj.PRODUCT_TYPE productType," +
                " obj.PRODUCT_UNIT productUnit," +
                " obj.PRODUCT_CODE productCode," +
                " obj.PRODUCT_RATE productRate," +
                " obj.PRODUCT_QUANTITY productQuantity," +
                " obj.PRODUCT_MONTHLY_FEE productMonthlyFee," +
                " obj.PRODUCT_WEEKLY_FEE productWeeklyFee," +
                " obj.PRODUCT_HOURLY_FEE productHourlyFee," +
                " obj.PRODUCT_SIZE productSize," +
                " obj.PRODUCT_WORKSTATION productWorkstation," +
                " obj.PRODUCT_CAPACITY productCapacity," +
                " obj.PRODUCT_VALUE productValue," +
                " obj.PRODUCT_DESCRIPTION productDescription," +
                " obj.PRODUCT_IMAGE productImage," +
                " obj.PRODUCT_BONUS_HOURS productBonusHours," +
                " obj.PRODUCT_BONUS_CREDIT productBonusCredit," +
                " obj.CREATE_USER_ID createUserId," +
                " obj.CREATE_DATE createDate," +
                " obj.MODIFIED_DATE modifiedDate," +
                " obj.PRODUCT_TYPE_ID productTypeId " +
                " FROM PRODUCT AS obj " +
                " WHERE 1=1 ");
        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append(" AND (" +
                    " upper(PRODUCT_CODE) like upper(:keySearch) OR " +
                    " upper(PRODUCT_NAME) like upper(:keySearch) " +
                    ")");
        }


        sql.append(" ORDER BY obj.PRODUCT_NAME  limit 20");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());

        query.addScalar("productId", new LongType());
        query.addScalar("parentProductId", new LongType());
        query.addScalar("productTypeId", new LongType());
        query.addScalar("locationId", new LongType());
        query.addScalar("productName", new StringType());
        query.addScalar("productCategoryId", new StringType());
        query.addScalar("productType", new StringType());
        query.addScalar("productCode", new StringType());
        query.addScalar("productRate", new DoubleType());
        query.addScalar("productQuantity", new DoubleType());
        query.addScalar("productUnit", new DoubleType());
        query.addScalar("productMonthlyFee", new DoubleType());
        query.addScalar("productWeeklyFee", new DoubleType());
        query.addScalar("productHourlyFee", new DoubleType());
        query.addScalar("productSize", new LongType());
        query.addScalar("productWorkstation", new LongType());
        query.addScalar("productCapacity", new LongType());
        query.addScalar("productValue", new DoubleType());
        query.addScalar("productDescription", new StringType());
        query.addScalar("productImage", new StringType());
        query.addScalar("productBonusHours", new LongType());
        query.addScalar("productBonusCredit", new LongType());
        query.addScalar("createUserId", new LongType());
        query.addScalar("createDate", new DateType());
        query.addScalar("modifiedDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch" ,"%"+ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }


        return query.list();
    }

    public List<CrmBookingDTO> doSearchForBooking(CrmBookingDTO obj) {
        StringBuilder sql = new StringBuilder("select bk.add_on_product_id addOnProductId,bk.base_price basePrice," +
                " bk.book_by bookBy,bk.booker_type bookerType, " +
                " bk.booking_end_time bookingEndTime,bk.booking_id bookingId,bk.booking_ref bookingRef," +
                " bk.booking_start_time bookingStartTime,bk.booking_title bookingTitle,bk.create_time createTime," +
                " bk.debitted_price debittedPrice,bk.member_id memberId,bk.product_id productId,bk.week_num weekNum," +
                " bk.week_day weekDay,bk.recurring_end_date recurringEndDate, p.product_name productName " +
                " from crm_booking bk  " +
                " right join product p on p.product_id = bk.product_id " +
                " left join member mb on mb.member_id = bk.member_id ");

        sql.append(" WHERE 1=1  ");
        if (StringUtils.isNotEmpty(obj.getProductLocation())) {
            sql.append(" AND upper(p.product_location LIKE upper(:productLacation) escape '&'  ");
        }

        if (StringUtils.isNotEmpty(obj.getProductCapacity())) {
            sql.append(" AND p.product_capacity =:productCapacity ");
        }

        sql.append(" ORDER BY bk.booking_id ");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as crmBooking;");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("addOnProductId", new LongType());
        query.addScalar("basePrice", new LongType());
        query.addScalar("bookBy", new LongType());
        query.addScalar("bookerType", new LongType());
        query.addScalar("bookingEndTime", new DateType());
        query.addScalar("bookingId", new LongType());
        query.addScalar("bookingRef", new StringType());
        query.addScalar("bookingStartTime", new DateType());
        query.addScalar("bookingTitle", new StringType());
        query.addScalar("createTime", new DateType());
        query.addScalar("debittedPrice", new LongType());
        query.addScalar("memberId", new LongType());
        query.addScalar("productId", new LongType());
        query.addScalar("weekNum", new LongType());
        query.addScalar("weekDay", new LongType());
        query.addScalar("recurringEndDate", new DateType());
        query.addScalar("productName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(CrmBookingDTO.class));
        if (StringUtils.isNotEmpty(obj.getProductLocation())) {
            query.setParameter("productLacation", "%"+ ValidateUtils.validateKeySearch(obj.getProductLocation())+"%");
            queryCount.setParameter("productLacation", "%"+ ValidateUtils.validateKeySearch(obj.getProductLocation())+"%");
        }

        if (StringUtils.isNotEmpty(obj.getProductCapacity())) {
            query.setParameter("productCapacity", obj.getProductCapacity());
            queryCount.setParameter("productCapacity", obj.getProductCapacity());
        }

        if(obj.getPage()!=null && obj.getPageSize()!=null){
            query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
            query.setMaxResults(obj.getPageSize().intValue());
            obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());
        }

        return query.list();
    }
}

