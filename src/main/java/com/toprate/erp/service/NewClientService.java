package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.ContractDTO;

import java.util.List;

public interface NewClientService {
    List<ContractDTO> doSearch(ContractDTO contractDTO);

    ContractDTO update(ContractDTO dto);

    ContractDTO lockStatus(ContractDTO dto);
}
