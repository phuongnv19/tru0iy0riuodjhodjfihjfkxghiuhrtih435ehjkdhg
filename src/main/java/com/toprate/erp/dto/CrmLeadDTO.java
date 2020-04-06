package com.toprate.erp.dto;

import java.util.Date;
import java.util.List;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.CrmLead;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmLeadDTO extends ErpBaseDTO {
	private Long leadId;

	@AdjHistory(field = "LEAD_REF")
	private String leadRef;

	@AdjHistory(field = "CONTACT_ID")
	private Long contactId;

	@AdjHistory(field = "USER_ID")
	// lead Owner
	private Long userId;

	@AdjHistory(field = "USER_ID2")
	// Lead Observers
	private Long userId2;

	@AdjHistory(field = "LEAD_SOURCE_ID")
	private Long leadSourceId;

	@AdjHistory(field = "PHASE_ID")
	private Long phaseId;

	@AdjHistory(field = "PRIORITY_LEVEL")
	private Long priorityLevel;

	//Visit Booking
	@AdjHistory(field = "BOOKING_MEETING_ID")
	private Long bookingMeetingId;

	@AdjHistory(field = "LEAD_SCORE")
	private Long leadScore;

	@AdjHistory(field = "LEAD_TYPE")
	private Long leadType;

	@AdjHistory(field = "LEAD_BUDGET")
	private Long leadBudget;

	@AdjHistory(field = "LEAD_STATUS")
	private Long leadStatus;

	@AdjHistory(field = "TERMINATED_LEAD_REASON")
	private String terminatedLeadReason;

	@AdjHistory(field = "LEAD_TERMINATED_TYPE")
	private Long leadTerminatedType;

	@AdjHistory(field = "LEAD_NOTE")
	private String leadNote;

	@AdjHistory(field = "CREATE_USER_ID")
	private Long createUserId;

	@AdjHistory(field = "CREATE_DATETIME")
	private Date createDatetime;
	
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
	private String homePhone;
	private String mobilePhone;
	private String officeSpaces;
	private Long phaseType;

	public CrmLead toModel() {
		CrmLead crmLead = new CrmLead();
		crmLead.setLeadId(leadId);
		crmLead.setLeadRef(leadRef);
		crmLead.setContactId(contactId);
		crmLead.setUserId(userId);
		crmLead.setUserId2(userId2);
		crmLead.setLeadSourceId(leadSourceId);
		crmLead.setPhaseId(phaseId);
		crmLead.setPriorityLevel(priorityLevel);
		crmLead.setBookingMeetingId(bookingMeetingId);
		crmLead.setLeadScore(leadScore);
		crmLead.setLeadType(leadType);
		crmLead.setLeadBudget(leadBudget);
		crmLead.setLeadStatus(leadStatus);
		crmLead.setTerminatedLeadReason(terminatedLeadReason);
		crmLead.setLeadTerminatedType(leadTerminatedType);
		crmLead.setLeadNote(leadNote);
		crmLead.setCreateUserId(createUserId);
		crmLead.setCreateDatetime(createDatetime);
		return crmLead;
	}

}
