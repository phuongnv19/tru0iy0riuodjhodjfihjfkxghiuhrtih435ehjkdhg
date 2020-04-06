package com.toprate.erp.repositories;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.toprate.erp.model.AttachDocument;
import com.toprate.erp.repositories.common.HibernateRepository;

@Repository
public class AttachDocumentRepository extends HibernateRepository<AttachDocument, Long> {


    public void deleteBySourceIdAndTableName(Long sourceId, String tableName){
        StringBuilder sql = new StringBuilder(" DELETE FROM attach_document WHERE source_id=:sourceId AND upper(table_name)=upper(:tableName) ");
        SQLQuery query=currentSession().createSQLQuery(sql.toString());
        query.setParameter("sourceId", sourceId);
        query.setParameter("tableName", tableName);
        query.executeUpdate();
    }
}
