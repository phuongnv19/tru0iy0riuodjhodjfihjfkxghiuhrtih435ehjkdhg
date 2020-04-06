package com.toprate.erp.repositories;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.toprate.erp.dto.CrmDealDTO;
import com.toprate.erp.dto.CrmLeadDTO;
import com.toprate.erp.model.CrmLead;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.ValidateUtils;

@Repository
public class CrmLeadRepository extends HibernateRepository<CrmLead, Long> implements ErpConstants {
	public void deleteCrmLead(CrmLeadDTO crmLeadDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_lead SET lead_terminated_type =");
		sql.append(crmLeadDTO.getLeadTerminatedType());
		sql.append(", terminated_lead_reason = '");
		sql.append(crmLeadDTO.getTerminatedLeadReason());
		sql.append("', lead_status = ");
		sql.append(crmLeadDTO.getLeadStatus());
		sql.append(" WHERE lead_id = ");
		sql.append(crmLeadDTO.getLeadId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}
	
	public void updatePhase(CrmLeadDTO crmLeadDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_lead SET phase_id =");
		sql.append(crmLeadDTO.getPhaseId());
		sql.append(" WHERE lead_id = ");
		sql.append(crmLeadDTO.getLeadId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}

	public void updateCrmLead(CrmLeadDTO crmLeadDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_lead SET booking_meeting_id =");
		sql.append(crmLeadDTO.getBookingMeetingId());
		sql.append(", contact_id = ");
		sql.append(crmLeadDTO.getContactId());
		sql.append(", lead_budget = ");
		sql.append(crmLeadDTO.getLeadBudget());
		sql.append(", create_datetime = ");
		sql.append("'").append(crmLeadDTO.getCreateDatetime()).append("'");
		sql.append(", create_user_id = ");
		sql.append(crmLeadDTO.getCreateUserId());
//		sql.append(", lead_type = ");
//		sql.append(crmLeadDTO.getLeadType());
		sql.append(", lead_note = ");
		sql.append("'").append(crmLeadDTO.getLeadNote()).append("'");
		sql.append(", lead_source_id = ");
		sql.append(crmLeadDTO.getLeadSourceId());
		sql.append(", user_id = ");
		sql.append(crmLeadDTO.getUserId());
		sql.append(", user_id2 = ");
		sql.append(crmLeadDTO.getUserId2());
		sql.append(" WHERE lead_id = ");
		sql.append(crmLeadDTO.getLeadId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}

	public List<CrmLeadDTO> doSearch(CrmLeadDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT distinct"
				+ " cl.lead_id AS leadId," 
				+ " cl.priority_level priorityLevel,"
				+ " ls.lead_source_company_name AS leadSource,"
				+ " CONCAT ( su.first_name, ' ', su.last_name ) AS leadName," 
				+ " su.home_phone AS homePhone,"
				+ " su.mobile_phone AS mobilePhone," 
//				+ " su.email," 
				+ " cl.phase_id AS phaseId,"
				+ " cp.phase_name AS status,"
				+ " cl.lead_note AS leadNote," 
				+ " cl.create_datetime AS createDatetime,"
				+ " cl.lead_type AS leadType," 
				+ " cl.lead_ref AS leadRef,"
				+ " CASE cl.lead_type WHEN 0 THEN 'Co-working' WHEN 1 THEN 'Virtual Office' ELSE'Part-time Office'  END AS officeSpaces, "
				+ " cl.booking_meeting_id bookingMeetingId,"
				+ " cl.contact_id contactId,"
				+ " cl.create_user_id createUserId,"
				+ " cl.lead_budget leadBudget,"
				+ " cl.lead_note leadNote,"
				+ " cl.lead_status leadStatus,"
				+ " cl.lead_terminated_type leadTerminatedType,"
				+ " cl.terminated_lead_reason terminatedLeadReason,"
				+ " cl.priority_level priorityLevel,"
				+ " cl.user_id userId,"
				+ " cl.user_id2 userId2,"
				+ " cl.lead_source_id leadSourceId,"
				+ " ct.contact_email email,"
				+ " ct.contact_phone phone,"
				+ " ct.contact_last_name lastName,"
				+ " ct.contact_first_name firstName,"
				+ " cpn.abn abn,"
				+ " cpn.acn arn,"
				+ " cpn.company_id companyId "
//				+ " su.location_id locationId"
				+ " FROM " 
				+ " crm_lead cl" 
				+ " LEFT JOIN sys_user su ON su.user_id = cl.user_id"
				+ " LEFT JOIN sys_user su2 ON su2.user_id = cl.create_user_id"
				+ " LEFT JOIN lead_source ls ON ls.\"id\" = cl.lead_source_id"
				+ " LEFT JOIN crm_phase cp ON cp.phase_id = cl.phase_id "
				+ " LEFT JOIN contact ct ON ct.contact_id = cl.contact_id"
				+ " LEFT JOIN company cpn ON cpn.company_id = ct.company_id"
				+ " WHERE");

		sql.append(" cl.lead_status = ");
		sql.append(ErpConstants.ACTIVE);
		sql.append(" AND cp.phase_type = ");
		sql.append(ErpConstants.PHASE_TYPE_LEAD);
		sql.append(" AND cp.phase_status = ");
		sql.append(ErpConstants.ACTIVE);
		
		if (null != obj.getLeadId() && 0L != obj.getLeadId()) {
			sql.append(" AND cl.lead_id = :leadIdParam");
		}
		
		if (null != obj.getLocationId() && 0L != obj.getLocationId()) {
			sql.append(" AND su2.location_id = :locationIdParam");
		}
		if (StringUtils.isNotEmpty(obj.getLeadNote())) {
			sql.append(" AND ( upper(cl.lead_note) LIKE upper(:param) escape '&'  ");
			sql.append(" OR upper(cl.lead_ref) LIKE upper(:param) escape '&'  )");
		}	
		if (null != obj.getUserId() && 0L != obj.getUserId()) {
			sql.append(" AND cl.user_id = :leadOwnerId");
		}
		if (null != obj.getLeadSourceId() && 0L != obj.getLeadSourceId()) {
			sql.append(" AND cl.lead_source_id = :leadSourceId");
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ( upper(ct.lastName) LIKE upper(:keySearch) escape '&'  ");
			sql.append(" OR upper(ct.email) LIKE upper(:keySearch) escape '&'  ");
			sql.append(" OR upper(ct.phone) LIKE upper(:keySearch) escape '&'  ");
			sql.append(" OR upper(ct.firstName) LIKE upper(:keySearch) escape '&'  )");
		}

		sql.append(" ORDER BY cl.lead_id ");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as crmlead;");

		NativeQuery query = currentSession().createNativeQuery(sql.toString());
		NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

		query.addScalar("leadId", new LongType());
		query.addScalar("priorityLevel", new LongType());
		query.addScalar("leadSource", new StringType());
		query.addScalar("leadName", new StringType());
		query.addScalar("homePhone", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("phaseId", new LongType());
		query.addScalar("status", new StringType());
		query.addScalar("leadNote", new StringType());
		query.addScalar("createDatetime", new DateType());
		query.addScalar("leadType", new LongType());
		query.addScalar("leadRef", new StringType());
		query.addScalar("officeSpaces", new StringType());
		query.addScalar("bookingMeetingId", new LongType());
		query.addScalar("contactId", new LongType());
		query.addScalar("createUserId", new LongType());
		query.addScalar("leadBudget", new LongType());
		query.addScalar("leadNote", new StringType());
		query.addScalar("leadStatus", new LongType());
		query.addScalar("leadTerminatedType", new LongType());
		query.addScalar("terminatedLeadReason", new StringType());
		query.addScalar("priorityLevel", new LongType());
		query.addScalar("userId", new LongType());
		query.addScalar("userId2", new LongType());
		query.addScalar("phone", new StringType());
		query.addScalar("lastName", new StringType());
		query.addScalar("firstName", new StringType());
		query.addScalar("abn", new LongType());
		query.addScalar("arn", new StringType());
		query.addScalar("companyId", new LongType());
//		query.addScalar("locationId", new LongType());

//		query.setResultTransformer(Transformers.aliasToBean(CrmLeadDTO.class));
		
		if (null != obj.getLeadId() && 0L != obj.getLeadId()) {
			query.setParameter("leadIdParam", obj.getLeadId());
			queryCount.setParameter("leadIdParam", obj.getLeadId());
		}
		
		if (StringUtils.isNotEmpty(obj.getLeadNote())) {
			query.setParameter("param", "%" + ValidateUtils.validateKeySearch(obj.getLeadNote()) + "%");
			queryCount.setParameter("param", "%" + ValidateUtils.validateKeySearch(obj.getLeadNote()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		if (null != obj.getLocationId() && 0L != obj.getLocationId()) {
			query.setParameter("locationIdParam", obj.getLocationId());
			queryCount.setParameter("locationIdParam", obj.getLocationId());
		}

		if (null != obj.getUserId() && 0L != obj.getUserId()) {
			query.setParameter("leadOwnerId", obj.getUserId());
			queryCount.setParameter("leadOwnerId", obj.getUserId());
		}
		if (null != obj.getLeadSourceId() && 0L != obj.getLeadSourceId()) {
			query.setParameter("leadSourceId", obj.getLeadSourceId());
			queryCount.setParameter("leadSourceId", obj.getLeadSourceId());
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		List<CrmLeadDTO> results =  query.setResultTransformer(Transformers.aliasToBean(CrmLeadDTO.class)).list();
		return results;
	}

}
