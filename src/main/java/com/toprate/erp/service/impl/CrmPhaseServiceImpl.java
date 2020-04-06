package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.CrmPhaseDTO;
import com.toprate.erp.model.CrmPhase;
import com.toprate.erp.repositories.CrmPhaseRepository;
import com.toprate.erp.service.CrmPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrmPhaseServiceImpl implements CrmPhaseService {

    @Autowired
    CrmPhaseRepository crmPhaseRepository;

    @Override
    public List<CrmPhaseDTO> getListCrmPhaseByPhaseType(long phaseType) {
        List<CrmPhase> phases = crmPhaseRepository.find("phaseType", phaseType);
        List<CrmPhaseDTO> phaseDTOS = new ArrayList<>();
        if (!phases.isEmpty()&&phases!=null){
            for (CrmPhase crmPhase:phases) {
                phaseDTOS.add(crmPhase.toDto());
            }
        }
        return phaseDTOS;
    }
}
