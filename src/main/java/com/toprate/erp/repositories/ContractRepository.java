package com.toprate.erp.repositories;

import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.model.Contract;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ContractRepository extends HibernateRepository<Contract, Long> {
    public List<ContractDTO> doSearch(ContractDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT " +
                "DISTINCT ON (ct.id) id, " +
                "ct.created_by createdBy, " +
                "ct.total_value totalValue ," +
                "ct.contract_note contractNote," +
                "ct.contract_signature contractSignature, " +
                "ct.paid_start_date paidStartDate, " +
                "ct.paid_end_date paidEndDate," +
                "cpn.company_full_name companyName  " +
                "FROM contract as ct " +
                "INNER JOIN member as mb ON ct.parent_member_id = mb.member_id " +
                "INNER JOIN company as cpn ON mb.company_id = cpn.company_id  " +
                "WHERE 1 = 1 ");
        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND (upper(mb.member_email) like upper(:searchEmail) ");
            sql.append(" OR upper(concat(mb.last_name,' ',mb.first_name)) LIKE upper(:keySearch) ");
            sql.append(" OR upper(cpn.company_full_name) LIKE upper(:keySearch) )");
        }
        if (obj.getStatusSign() == 1) {
            sql.append(" AND ct.contract_signature IS NOT NULL");
        }
        if (obj.getStatusSign() == 0) {
            sql.append(" AND ct.contract_signature IS NULL");
        }

        if (obj.getLocationId() != null) {
            sql.append(" AND ct.location_id = :locationId");
        }

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as contract;");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("id", new LongType());
        query.addScalar("totalValue", new LongType());
        query.addScalar("contractNote", new StringType());
        query.addScalar("createdBy", new LongType());
        query.addScalar("contractSignature", new StringType());
        query.addScalar("paidEndDate", new DateType());
        query.addScalar("paidStartDate", new DateType());
        query.addScalar("companyName", new StringType());
        query.setResultTransformer(Transformers.aliasToBean(ContractDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            query.setParameter("searchEmail", ValidateUtils.validateKeySearch(obj.getKeySearch().trim()));
            queryCount.setParameter("searchEmail", ValidateUtils.validateKeySearch(obj.getKeySearch()).trim());

            query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch().trim()) + "%");
            queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch().trim()) + "%");
        }

        if (obj.getLocationId() != null) {
            query.setParameter("locationId", obj.getLocationId());
            queryCount.setParameter("locationId", obj.getLocationId());
        }

        query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

        return query.list();
    }

    public List<ContractDTO> doSearchNewClient(ContractDTO obj) {
//          Search theo: contractRef, parentMemberId, parentMemberName, parentMemberEmail, productCode, productName, companyName
        StringBuilder sql = new StringBuilder("SELECT DISTINCT "
                + "CONTRACT.ID as id, "
                + "CONTRACT.CONTRACT_REF as contractRef, "
                + "CONTRACT.PARENT_MEMBER_ID as parentMemberId, "
                + "CONTRACT.DEAL_ID as dealId, "
                + "CONTRACT.TOTAL_VALUE as totalValue, "
                + "CONTRACT.CONTRACT_NOTE as contractNote, "
                + "CONTRACT.CREATED_BY as createdBy, "
                + "CONTRACT.CONTRACT_COMMENT as contractComment, "
                + "CONTRACT.CONTRACT_STATUS as contractStatus, "
                + "CONTRACT.CONTRACT_UPLOAD_DOCS as contractUploadDocs, "
                + "CONTRACT.CONTRACT_SIGNATURE as contractSignature, "
                + "CONTRACT.CREATE_TIME as createTime ,"
                + "CONTRACT.MODIFIED_TIME as modifiedTime, "
                + "CONTRACT.PHASE_ID as phaseId, "
                + "CONTRACT.LOCATION_ID as locationId, "
                + "CONTRACT.CONTRACT_TYPE as contractType, "
                + "CONTRACT.PAID_START_DATE as paidStartDate, "
                + "CONTRACT.PAID_END_DATE as paidEndDate, "
                + "concat(member.first_name, ' ', member.last_name) as customerName "
                + "FROM CONTRACT "
                + "LEFT JOIN CONTRACT_ITEM ON CONTRACT.ID = CONTRACT_ITEM.CONTRACT_ID "
                + "LEFT JOIN MEMBER ON CONTRACT.PARENT_MEMBER_ID = MEMBER.MEMBER_ID "
                + "LEFT JOIN PRODUCT ON PRODUCT.PRODUCT_ID = CONTRACT_ITEM.PRODUCT_ID "
                + "LEFT JOIN CRM_PHASE ON CONTRACT.PHASE_ID = CRM_PHASE.PHASE_ID "
                + "LEFT JOIN COMPANY ON COMPANY.COMPANY_ID = MEMBER.COMPANY_ID "
                + "WHERE CRM_PHASE.PHASE_TYPE = 2 AND CONTRACT_STATUS != 0  "); // phase thuoc newclient, contract != inactive

        if (obj.getLocationId() != null) {
            sql.append(" AND CONTRACT.LOCATION_ID = :locationId "); // search tren location
        }

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND ( " +
                    "upper(CONTRACT.CONTRACT_REF) LIKE upper(:keySearch) " +
                    "OR upper(MEMBER.MEMBER_EMAIL) LIKE upper(:keySearch) " +
                    "OR upper(MEMBER.FIRST_NAME) LIKE upper(:keySearch)" +
                    "OR upper(MEMBER.LAST_NAME) LIKE upper(:keySearch)" +
                    "OR upper(PRODUCT_NAME) LIKE upper(:keySearch) " +
                    "OR upper(PRODUCT_CODE) LIKE upper(:keySearch) " +
                    "OR upper(COMPANY_FULL_NAME) LIKE upper(:keySearch)) ");
        }

        sql.append(" ORDER BY CONTRACT.ID ");

        SQLQuery query = currentSession().createSQLQuery(sql.toString());

        query.addScalar("id", new LongType());
        query.addScalar("contractRef", new StringType());
        query.addScalar("parentMemberId", new LongType());
        query.addScalar("dealId", new LongType());
        query.addScalar("totalValue", new LongType());
        query.addScalar("contractNote", new StringType());
        query.addScalar("createdBy", new LongType());
        query.addScalar("contractComment", new StringType());
        query.addScalar("contractStatus", new LongType());
        query.addScalar("contractUploadDocs", new StringType());
        query.addScalar("contractSignature", new StringType());
        query.addScalar("createTime", new DateType());
        query.addScalar("modifiedTime", new DateType());
        query.addScalar("phaseId", new LongType());
        query.addScalar("locationId", new LongType());
        query.addScalar("contractType", new LongType());
        query.addScalar("paidStartDate", new DateType());
        query.addScalar("paidEndDate", new DateType());
        query.addScalar("customerName", new StringType());
        query.setResultTransformer(Transformers.aliasToBean(ContractDTO.class));

        if (obj.getLocationId()!=null){
            query.setParameter("locationId",obj.getLocationId());
        }

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }

        return query.list();
    }

    public List<ContractDTO> doSearchRenew(ContractDTO obj) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT "
                + "CONTRACT.ID as id, "
                + "CONTRACT.CONTRACT_REF as contractRef, "
                + "CONTRACT.PARENT_MEMBER_ID as parentMemberId, "
                + "CONTRACT.DEAL_ID as dealId, "
                + "CONTRACT.TOTAL_VALUE as totalValue, "
                + "CONTRACT.CONTRACT_NOTE as contractNote, "
                + "CONTRACT.CREATED_BY as createdBy, "
                + "CONTRACT.CONTRACT_COMMENT as contractComment, "
                + "CONTRACT.CONTRACT_STATUS as contractStatus, "
                + "CONTRACT.CONTRACT_UPLOAD_DOCS as contractUploadDocs, "
                + "CONTRACT.CONTRACT_SIGNATURE as contractSignature, "
                + "CONTRACT.CREATE_TIME as createTime ,"
                + "CONTRACT.MODIFIED_TIME as modifiedTime, "
                + "CONTRACT.PHASE_ID as phaseId, "
                + "CONTRACT.LOCATION_ID as locationId, "
                + "CONTRACT.CONTRACT_TYPE as contractType, "
                + "CONTRACT.PAID_START_DATE as paidStartDate, "
                + "CONTRACT.PAID_END_DATE as paidEndDate, "
                + "concat(member.first_name, ' ', member.last_name) as customerName "
                + "FROM CONTRACT "
                + "LEFT JOIN CONTRACT_ITEM ON CONTRACT.ID = CONTRACT_ITEM.CONTRACT_ID "
                + "LEFT JOIN MEMBER ON CONTRACT.PARENT_MEMBER_ID = MEMBER.MEMBER_ID "
                + "LEFT JOIN PRODUCT ON PRODUCT.PRODUCT_ID = CONTRACT_ITEM.PRODUCT_ID "
                + "LEFT JOIN CRM_PHASE ON CONTRACT.PHASE_ID = CRM_PHASE.PHASE_ID "
                + "LEFT JOIN COMPANY ON COMPANY.COMPANY_ID = MEMBER.COMPANY_ID "
                + " WHERE 1=1"
                + " AND CONTRACT_STATUS = 0 "  // seaarch theo contractStatus
        );
        if (obj.getLocationId() != null) {
            sql.append(" AND CONTRACT.LOCATION_ID = :locationId "); // search tren location
        }

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            sql.append(" AND ( ");
            if (ValidateUtils.isInteger(obj.getKeySearch())) {
                sql.append(" ABN = :abn OR ");
            }
            sql.append(
                    "upper(COMPANY_FULL_NAME) LIKE upper(:keySearch) " +
                            "OR upper(MEMBER.MEMBER_EMAIL) LIKE upper(:keySearch) " +
                            "OR upper(MEMBER.FIRST_NAME) LIKE upper(:keySearch)" +
                            "OR upper(MEMBER.LAST_NAME) LIKE upper(:keySearch)" +
                            "OR upper(PRODUCT_NAME) LIKE upper(:keySearch) " +
                            "OR upper(PRODUCT_CODE) LIKE upper(:keySearch) " +
                            "OR upper(ACN) LIKE upper(:keySearch) " +
                            ") "
            );
        }

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*)  FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as objects;");

        sql.append(" ORDER BY CONTRACT.ID ");

        SQLQuery query = currentSession().createSQLQuery(sql.toString());
        SQLQuery queryCount = currentSession().createSQLQuery(sqlCount.toString());

        query.addScalar("id", new LongType());
        query.addScalar("contractRef", new StringType());
        query.addScalar("parentMemberId", new LongType());
        query.addScalar("dealId", new LongType());
        query.addScalar("totalValue", new LongType());
        query.addScalar("contractNote", new StringType());
        query.addScalar("createdBy", new LongType());
        query.addScalar("contractComment", new StringType());
        query.addScalar("contractStatus", new LongType());
        query.addScalar("contractUploadDocs", new StringType());
        query.addScalar("contractSignature", new StringType());
        query.addScalar("createTime", new DateType());
        query.addScalar("modifiedTime", new DateType());
        query.addScalar("phaseId", new LongType());
        query.addScalar("locationId", new LongType());
        query.addScalar("contractType", new LongType());
        query.addScalar("paidStartDate", new DateType());
        query.addScalar("paidEndDate", new DateType());
        query.addScalar("customerName", new StringType());
        query.setResultTransformer(Transformers.aliasToBean(ContractDTO.class));

        if (obj.getLocationId() != null) {
            query.setParameter("locationId", obj.getLocationId());
            queryCount.setParameter("locationId", obj.getLocationId());
        }

        if (StringUtils.isNotEmpty(obj.getKeySearch())) {
            if (ValidateUtils.isInteger(obj.getKeySearch())) {
                query.setParameter("abn", Integer.parseInt(obj.getKeySearch()));
                queryCount.setParameter("abn", Integer.parseInt(obj.getKeySearch()));
            }
            query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
            queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
        }

        query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

        return query.list();
    }
}
