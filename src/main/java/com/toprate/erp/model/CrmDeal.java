package com.toprate.erp.model;

import com.toprate.erp.dto.CrmDealDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CRM_DEAL")
@Data
public class CrmDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEAL_ID")
    private Long dealId;

    @Column(name = "DEAL_REF")
    private String dealRef;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "LEAD_ID")
    private Long leadId;

    @Column(name = "CONTACT_ID")
    private Long contactId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_ID2")
    private Long userId2;

    @Column(name = "USER_ID3")
    private Long userId3;

    @Column(name = "USER_ID4")
    private Long userId4;

    @Column(name = "LEAD_SOURCE_ID")
    private Long leadSourceId;

    @Column(name = "PHASE_ID")
    private Long phaseId;

    @Column(name = "DEAL_PRIORITY_LEVEL")
    private Long dealPriorityLevel;

    @Column(name = "BOOKING_MEETING_ID")
    private Long bookingMeetingId;

    @Column(name = "LEAD_SCORE")
    private Long leadScore;

    @Column(name = "DEAL_SCORE")
    private Long dealScore;

    @Column(name = "DEAL_BUDGET")
    private Long dealBudget;

    @Column(name = "DEAL_STATUS")
    private Long dealStatus;

    @Column(name = "DEAL_TERMINATED_TYPE")
    private Long dealTerminatedType;

    @Column(name = "TERMINATED_DEAL_REASON")
    private String terminatedDealReason;

    @Column(name = "DEAL_NOTE")
    private String dealNote;

    @Column(name = "DEAL_UPLOADS_PATH")
    private String dealUploadsPath;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public CrmDealDTO toDto(){
        CrmDealDTO crmDeal = new CrmDealDTO();
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
