package com.toprate.erp.repositories;

import com.toprate.erp.dto.ContractItemDTO;
import com.toprate.erp.model.ContractItem;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ContractItemRepository extends HibernateRepository<ContractItem, Long> {
    public List<ContractItemDTO> doSearch(ContractItemDTO obj) {

        StringBuilder sql = new StringBuilder("SELECT " +
                "coIt.contract_item_id as contractItemId " +
                "FROM contract_item as coIt " +
                "WHERE 1 = 1 ");
        if (obj.getContractId() != null) {
            sql.append("AND coIt.contract_id = :contractId");
        }

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as contract_item;");

        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("contractItemId", new LongType());
//        query.addScalar("parentMemberId", new LongType());
//        query.addScalar("totalValue", new LongType());
//        query.addScalar("contractNote", new StringType());
//        query.addScalar("createdBy", new LongType());
//        query.addScalar("contractComment", new StringType());
        query.setResultTransformer(Transformers.aliasToBean(ContractItemDTO.class));

        if (obj.getContractId() != null) {
            query.setParameter("contractId", obj.getContractId());
            queryCount.setParameter("contractId", obj.getContractId());
        }

        query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

        return query.list();
    }
}
