package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ResultDTO;
import com.toprate.erp.dto.ContractDTO;

import javax.servlet.http.HttpServletRequest;

public interface ContractService {

    ContractDTO insert(HttpServletRequest request, ContractDTO obj) throws Exception;

    ContractDTO update(HttpServletRequest request,ContractDTO obj) throws Exception;

    DataListDTO doSearch(ContractDTO obj) throws Exception;

    ContractDTO getDetailById(ContractDTO contractDTO) throws Exception;
}
