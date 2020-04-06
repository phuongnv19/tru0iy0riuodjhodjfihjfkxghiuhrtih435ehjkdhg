package com.toprate.erp.service;

import com.toprate.erp.dto.CrmPhaseDTO;

import java.util.List;

public interface CrmPhaseService {

    List<CrmPhaseDTO> getListCrmPhaseByPhaseType(long phaseType);
}
