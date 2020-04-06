package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.ContractDTO;

public interface RenewService {

    DataListDTO doSearch(ContractDTO obj);

//    ContractDTO lockStatus(ContractDTO obj);

    ContractDTO update(ContractDTO obj);

    int delete(ContractDTO obj);
}
