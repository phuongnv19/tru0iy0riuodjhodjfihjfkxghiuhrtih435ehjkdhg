package com.toprate.erp.model;

import com.toprate.erp.dto.CrmLeadDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CRM_LEAD")
@Data
public class CrmLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAD_ID")
    private Long leadId;

    @Column(name = "LEAD_REF")
    private String leadRef;

    @Column(name = "CONTACT_ID")
    private Long contactId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_ID2")
    private Long userId2;

    @Column(name = "LEAD_SOURCE_ID")
    private Long leadSourceId;

    @Column(name = "PHASE_ID")
    private Long phaseId;

    @Column(name = "PRIORITY_LEVEL")
    private Long priorityLevel;

    @Column(name = "BOOKING_MEETING_ID")
    private Long bookingMeetingId;

    @Column(name = "LEAD_SCORE")
    private Long leadScore;

    @Column(name = "LEAD_TYPE")
    private Long leadType;

    @Column(name = "LEAD_BUDGET")
    private Long leadBudget;

    @Column(name = "LEAD_STATUS")
    private Long leadStatus;

    @Column(name = "TERMINATED_LEAD_REASON")
    private String terminatedLeadReason;

    @Column(name = "LEAD_TERMINATED_TYPE")
    private Long leadTerminatedType;

    @Column(name = "LEAD_NOTE")
    private String leadNote;

    @Column(name = "CREATE_USER_ID")
    private Long createUserId;

    @Column(name = "CREATE_DATETIME")
    private Date createDatetime;

    public CrmLeadDTO toDto (){
        CrmLeadDTO crmLead = new CrmLeadDTO();
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
        return  crmLead;
    }
}
