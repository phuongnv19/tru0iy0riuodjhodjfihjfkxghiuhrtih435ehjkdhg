package com.toprate.erp.model;

import com.toprate.erp.dto.LeadSourceDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "LEAD_SOURCE")
@Data
public class LeadSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LEAD_SOURCE_COMPANY_NAME")
    private String leadSourceCompanyName;

    @Column(name = "LEAD_SOURCE_TYPE")
    private String leadSourceType;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    public LeadSourceDTO toDto(){
        LeadSourceDTO leadSource = new LeadSourceDTO();
        leadSource.setId(id);
        leadSource.setLeadSourceCompanyName(leadSourceCompanyName);
        leadSource.setLeadSourceType(leadSourceType);
        leadSource.setUserId(userId);
        leadSource.setMemberId(memberId);
        return leadSource;
    }
}
