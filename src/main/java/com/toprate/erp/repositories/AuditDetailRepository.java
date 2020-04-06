package com.toprate.erp.repositories;

import com.toprate.erp.model.ActionAudit;
import com.toprate.erp.model.AuditDetail;
import com.toprate.erp.repositories.common.HibernateRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDetailRepository extends HibernateRepository<AuditDetail,Long> {
}
