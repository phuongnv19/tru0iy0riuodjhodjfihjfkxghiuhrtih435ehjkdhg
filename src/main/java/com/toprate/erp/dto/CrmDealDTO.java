package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.CrmDeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmDealDTO extends ErpBaseDTO {
	private Long dealId;

	@NotNull
	@AdjHistory(field = "DEAL_REF")
	private String dealRef;

	@AdjHistory(field = "CONTRACT_ID")
	private Long contractId;

	@AdjHistory(field = "LEAD_ID")
	private Long leadId;

	@AdjHistory(field = "CONTACT_ID")
	private Long contactId;

	@AdjHistory(field = "USER_ID")
	private Long userId;

	@AdjHistory(field = "USER_ID2")
	private Long userId2;

	@AdjHistory(field = "USER_ID3")
	private Long userId3;

	@AdjHistory(field = "USER_ID4")
	private Long userId4;

	@AdjHistory(field = "LEAD_SOURCE_ID")
	private Long leadSourceId;

	@AdjHistory(field = "PHASE_ID")
	private Long phaseId;

	@AdjHistory(field = "DEAL_PRIORITY_LEVEL")
	private Long dealPriorityLevel;

	@AdjHistory(field = "BOOKING_MEETING_ID")
	private Long bookingMeetingId;

	@AdjHistory(field = "LEAD_SCORE")
	private Long leadScore;

	@AdjHistory(field = "DEAL_SCORE")
	private Long dealScore;

	@AdjHistory(field = "DEAL_BUDGET")
	private Long dealBudget;

	@AdjHistory(field = "DEAL_STATUS")
	private Long dealStatus;

	@AdjHistory(field = "DEAL_TERMINATED_TYPE")
	private Long dealTerminatedType;

	@AdjHistory(field = "TERMINATED_DEAL_REASON")
	private String terminatedDealReason;

	@AdjHistory(field = "DEAL_NOTE")
	private String dealNote;

	@AdjHistory(field = "DEAL_UPLOADS_PATH")
	private String dealUploadsPath;

	@AdjHistory(field = "CREATE_USER_ID")
	private String createUserId;

	@AdjHistory(field = "CREATE_DATE")
	private Date createDate;

	private String firstName;
	private String lastName;
	private String phone;
	private String email;

	private Long companyId;
	private Long abn;
	private String arn;

	private List<String> files;
	private Long locationId;
	// Phase Name
	private String status;
	private String leadSource;
	private String leadName;
	private String dealOwnerName;
	private String contactName;
	private Long phaseType;

	public CrmDeal toModel() {
		CrmDeal crmDeal = new CrmDeal();
		crmDeal.setDealId(dealId);
		crmDeal.setDealRef(dealRef);
		crmDeal.setContactId(contactId);
		crmDeal.setLeadId(leadId);
		crmDeal.setContactId(contactId);
		crmDeal.setUserId(userId);
		crmDeal.setUserId2(userId2);
		crmDeal.setUserId3(userId3);
		crmDeal.setUserId4(userId4);
		crmDeal.setLeadSourceId(leadSourceId);
		crmDeal.setPhaseId(phaseId);
		crmDeal.setDealPriorityLevel(dealPriorityLevel);
		crmDeal.setBookingMeetingId(bookingMeetingId);
		crmDeal.setLeadScore(leadScore);
		crmDeal.setDealScore(dealScore);
		crmDeal.setDealBudget(dealBudget);
		crmDeal.setDealStatus(dealStatus);
		crmDeal.setDealTerminatedType(dealTerminatedType);
		crmDeal.setTerminatedDealReason(terminatedDealReason);
		crmDeal.setDealNote(dealNote);
		crmDeal.setDealUploadsPath(dealUploadsPath);
		crmDeal.setCreateUserId(createUserId);
		crmDeal.setCreateDate(createDate);
		return crmDeal;
	}
}
