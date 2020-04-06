package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.toprate.erp.model.CrmPhase;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmPhaseDTO extends ErpBaseDTO {
    private Long phaseId;

    @AdjHistory(field = "PHASE_NAME")
    private String phaseName;

    @AdjHistory(field = "PHASE_ORDER")
    private Long phaseOrder;

    @AdjHistory(field = "PHASE_TYPE")
    private Long phaseType;

    @AdjHistory(field = "PHASE_STATUS")
    private Long phaseStatus;

    public CrmPhase toModel(){
        CrmPhase crmPhase = new CrmPhase();
        crmPhase.setPhaseId(phaseId);
        crmPhase.setPhaseName(phaseName);
        crmPhase.setPhaseOrder(phaseOrder);
        crmPhase.setPhaseType(phaseType);
        crmPhase.setPhaseType(phaseType);
        return  crmPhase;
    }
}
