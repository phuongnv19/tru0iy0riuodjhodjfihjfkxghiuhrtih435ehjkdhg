package com.toprate.erp.model;

import com.toprate.erp.dto.CrmPhaseDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CRM_PHASE")
@Data
public class CrmPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHASE_ID")
    private Long phaseId;

    @Column(name = "PHASE_NAME")
    private String phaseName;

    @Column(name = "PHASE_ORDER")
    private Long phaseOrder;

    @Column(name = "PHASE_TYPE")
    private Long phaseType;

    @Column(name = "PHASE_STATUS")
    private Long phaseStatus;

    public CrmPhaseDTO toDto(){
        CrmPhaseDTO crmPhase = new CrmPhaseDTO();
        crmPhase.setPhaseId(phaseId);
        crmPhase.setPhaseName(phaseName);
        crmPhase.setPhaseOrder(phaseOrder);
        crmPhase.setPhaseType(phaseType);
        crmPhase.setPhaseType(phaseType);
        return  crmPhase;
    }
}
