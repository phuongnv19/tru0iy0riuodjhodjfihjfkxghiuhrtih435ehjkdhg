package com.toprate.erp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.CrmDealDTO;

public interface CrmDealService {

	CrmDealDTO addCrmDeal(CrmDealDTO crmLeadDTO) throws Exception;

	List<CrmDealDTO> findAll();

	CrmDealDTO saveAndUpdate(CrmDealDTO crmLeadDTO) throws IOException;

	List<String> upLoadFileCrmDeal(MultipartFile[] files, Long id) throws Exception;

	void updatePhase(CrmDealDTO crmLeadDTO);

	void deleteCrmDeal(CrmDealDTO crmDealDTO);

	DataListDTO doSearch(CrmDealDTO crmDealDTO);

	void lockStatus(CrmDealDTO crmLeadDTO);

}
