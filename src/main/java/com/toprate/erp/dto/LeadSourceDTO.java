package com.toprate.erp.dto;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.LeadSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadSourceDTO extends ErpBaseDTO {
    private Long id;
    private String leadSourceCompanyName;
    private String leadSourceType;
    private Long userId;
    private Long memberId;
    private String userFullName;
    private  String memberFullName;

    public LeadSource toModel(){
        LeadSource leadSource = new LeadSource();
        leadSource.setId(id);
        leadSource.setLeadSourceCompanyName(leadSourceCompanyName);
        leadSource.setLeadSourceType(leadSourceType);
        leadSource.setUserId(userId);
        leadSource.setMemberId(memberId);
        return leadSource;
    }
}
