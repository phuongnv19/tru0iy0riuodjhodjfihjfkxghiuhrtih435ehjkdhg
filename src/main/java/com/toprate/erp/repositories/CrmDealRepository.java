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
import com.toprate.erp.model.CrmDeal;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.ValidateUtils;

@Repository
public class CrmDealRepository extends HibernateRepository<CrmDeal, Long> implements ErpConstants {
	public void deleteCrmDeal(CrmDealDTO crmDealDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_deal SET deal_terminated_type =");
		sql.append(crmDealDTO.getDealTerminatedType());
		sql.append(", terminated_deal_reason = '");
		sql.append(crmDealDTO.getTerminatedDealReason());
		sql.append("', deal_status = ");
		sql.append(crmDealDTO.getDealStatus());
		sql.append(" WHERE deal_id = ");
		sql.append(crmDealDTO.getDealId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}
	
	public void updatePhase(CrmDealDTO crmDealDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_deal SET phase_id =");
		sql.append(crmDealDTO.getPhaseId());
		sql.append(" WHERE deal_id = ");
		sql.append(crmDealDTO.getDealId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}
	
	public void lockStatus(CrmDealDTO crmDealDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_deal SET deal_status =");
		sql.append(crmDealDTO.getDealStatus());
		sql.append(" WHERE deal_id = ");
		sql.append(crmDealDTO.getDealId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}

	public void updateCrmDeal(CrmDealDTO crmDealDTO) {
		StringBuilder sql = new StringBuilder("UPDATE crm_deal SET booking_meeting_id =");
		sql.append(crmDealDTO.getBookingMeetingId());
		sql.append(", contact_id = ");
		sql.append(crmDealDTO.getContactId());
		sql.append(", deal_budget = ");
		sql.append(crmDealDTO.getDealBudget());
		sql.append(", create_date = ");
		sql.append("'").append(crmDealDTO.getCreateDate()).append("'");
		sql.append(", create_user_id = ");
		sql.append(crmDealDTO.getCreateUserId());
		sql.append(", user_id = ");
		sql.append(crmDealDTO.getUserId());
		sql.append(", user_id2 = ");
		sql.append(crmDealDTO.getUserId2());
		sql.append(", user_id3 = ");
		sql.append(crmDealDTO.getUserId3());
		sql.append(", user_id4 = ");
		sql.append(crmDealDTO.getUserId4());
		sql.append(", deal_score = ");
		sql.append(crmDealDTO.getDealScore());
		sql.append(", lead_source_id = ");
		sql.append(crmDealDTO.getLeadSourceId());
		sql.append(", deal_status = ");
		sql.append(crmDealDTO.getDealStatus());
		sql.append(", deal_note = ");
		sql.append("'").append(crmDealDTO.getDealNote()).append("'");
		sql.append(" WHERE deal_id = ");
		sql.append(crmDealDTO.getLeadId());

		SQLQuery sqlQuery = currentSession().createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}

	public List<CrmDealDTO> doSearch(CrmDealDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  distinct"
				+ " CONCAT(su3.first_name, su3.last_name) dealOwnerName," 
				+ " CONCAT(ct.contact_last_name, ct.contact_first_name) contactName,"
				+ " cp.phase_name phaseName,"
				+ " cd.deal_id dealId," 
				+ " cd.booking_meeting_id bookingMeetingId,"
				+ " cd.contact_id contactId," 
				+ " cd.contract_id contractId," 
				+ " cd.create_date createDate,"
				+ " cd.create_user_id createUserId,"
				+ " cd.deal_budget dealBudget," 
				+ " cd.deal_note dealNote," 
				+ " cd.deal_priority_level dealPriorityLevel," 
				+ " cd.deal_ref dealRef," 
				+ " cd.deal_score dealScore," 
				+ " cd.deal_status dealStatus," 
				+ " cd.deal_terminated_type dealTerminatedType," 
				+ " cd.lead_id leadId," 
				+ " cd.lead_score leadScore," 
				+ " cd.lead_source_id leadSourceId," 
				+ " cd.phase_id phaseId,"
				+ " cd.terminated_deal_reason terminatedDealReason,"
				+ " cd.user_id userId,"
				+ " cd.user_id2 userId2,"
				+ " cd.user_id3 userId3,"
				+ " cd.user_id4 userId4,"
				+ " ct.contact_email email,"
				+ " ct.contact_phone phone,"
				+ " ct.contact_last_name lastName,"
				+ " ct.contact_first_name firstName,"
				+ " cpn.abn abn,"
				+ " cpn.acn arn,"
				+ " cpn.company_id companyId "
//				+ " su.location_id locationId"
				+ " FROM " 
				+ " crm_deal cd"
				+ " INNER JOIN sys_user su ON cd.user_id = su.user_id"
				+ " INNER JOIN sys_user su2 ON cd.user_id2 = su2.user_id"
				+ " LEFT JOIN sys_user su3 ON cd.user_id3 = su3.user_id"
				+ " LEFT JOIN sys_user su4 ON cd.user_id4 = su4.user_id"
				+ " INNER JOIN crm_phase cp ON cp.phase_id = cd.phase_id"
				+ " INNER JOIN contact ct ON ct.contact_id = cd.contact_id"
				+ " INNER JOIN company cpn ON cpn.company_id = ct.company_id"
				+ " WHERE");

		sql.append(" cd.deal_status = ");
		sql.append(ErpConstants.ACTIVE);
		sql.append(" AND cp.phase_type = ");
		sql.append(ErpConstants.PHASE_TYPE_DEAL);
		sql.append(" AND cp.phase_status != ");
		sql.append(ErpConstants.INACTIVE);
		
		if (null != obj.getDealId() && 0L != obj.getDealId()) {
			sql.append(" AND cd.deal_id = :dealId");
		}
		
		if (null != obj.getLocationId() && 0L != obj.getLocationId()) {
			sql.append(" AND su3.location_id = :locationId");
		}
		
		if (StringUtils.isNotEmpty(obj.getDealNote())) {
			sql.append(" AND ( upper(cd.deal_note) LIKE upper(:param) escape '&'  ");
			sql.append(" OR upper(cd.deal_ref) LIKE upper(:param) escape '&'  )");
		}
		
		if (null != obj.getUserId() && 0L != obj.getUserId()) {
			sql.append(" AND cd.user_id = :leadOwner");
		}
		
		if (null != obj.getUserId3() && 0L != obj.getUserId3()) {
			sql.append(" AND cd.user_id3 = :dealOwner");
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ( upper(cd.lastName) LIKE upper(:keySearch) escape '&'  ");
			sql.append(" OR upper(cd.email) LIKE upper(:keySearch) escape '&'  ");
			sql.append(" OR upper(cd.phone) LIKE upper(:keySearch) escape '&'  ");
			sql.append(" OR upper(cd.firstName) LIKE upper(:keySearch) escape '&'  )");
		}

		sql.append(" ORDER BY cd.deal_id ");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as crmdeal;");

		NativeQuery query = currentSession().createNativeQuery(sql.toString());
		NativeQuery queryCount = currentSession().createNativeQuery(sqlCount.toString());

		query.addScalar("dealId", new LongType());
		query.addScalar("bookingMeetingId", new LongType());
		query.addScalar("contactId", new LongType());
		query.addScalar("contractId", new LongType());
		query.addScalar("createDate", new DateType());
		query.addScalar("email", new StringType());
		
		query.addScalar("dealOwnerName", new StringType());
		query.addScalar("contactName", new StringType());
		
		query.addScalar("createUserId", new StringType());
		query.addScalar("dealBudget", new LongType());
		query.addScalar("dealNote", new StringType());
		query.addScalar("dealPriorityLevel", new LongType());
		query.addScalar("dealRef", new StringType());
		query.addScalar("dealScore", new LongType());
		query.addScalar("dealStatus", new LongType());
		query.addScalar("dealTerminatedType", new LongType());
		query.addScalar("leadId", new LongType());
		query.addScalar("leadScore", new LongType());
		query.addScalar("leadSourceId", new LongType());
		query.addScalar("phaseId", new LongType());
		query.addScalar("terminatedDealReason", new StringType());
		query.addScalar("userId", new LongType());
		query.addScalar("userId2", new LongType());
		query.addScalar("userId3", new LongType());
		query.addScalar("userId4", new LongType());
		query.addScalar("phone", new StringType());
		query.addScalar("lastName", new StringType());
		query.addScalar("firstName", new StringType());
		query.addScalar("abn", new LongType());
		query.addScalar("arn", new StringType());
		query.addScalar("companyId", new LongType());
//		query.addScalar("locationId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(CrmDealDTO.class));
		
		if (null != obj.getDealId() && 0L != obj.getDealId()) {
			query.setParameter("dealId", obj.getDealId());
			queryCount.setParameter("dealId", obj.getDealId());
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getDealNote())) {
			query.setParameter("param", "%" + ValidateUtils.validateKeySearch(obj.getDealNote()) + "%");
			queryCount.setParameter("param", "%" + ValidateUtils.validateKeySearch(obj.getDealNote()) + "%");
		}
		
		if (null != obj.getLocationId() && 0L != obj.getLocationId()) {
			query.setParameter("locationId", obj.getLocationId());
			queryCount.setParameter("locationId", obj.getLocationId());
		}

		if (null != obj.getUserId() && 0L != obj.getUserId()) {
			query.setParameter("leadOwner", obj.getUserId());
			queryCount.setParameter("leadOwner", obj.getUserId());
		}
		if (null != obj.getUserId3() && 0L != obj.getUserId3()) {
			query.setParameter("dealOwner", obj.getUserId3());
			queryCount.setParameter("dealOwner", obj.getUserId3());
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}

}
