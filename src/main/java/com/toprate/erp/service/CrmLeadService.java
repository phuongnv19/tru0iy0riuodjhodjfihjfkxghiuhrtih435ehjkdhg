package com.toprate.erp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.CrmLeadDTO;
import com.toprate.erp.dto.LeadSourceDTO;

public interface CrmLeadService {

	CrmLeadDTO addCrmLead(CrmLeadDTO crmLeadDTO) throws Exception;

	List<CrmLeadDTO> findAll();

	CrmLeadDTO saveAndUpdate(CrmLeadDTO crmLeadDTO) throws IOException;

	List<String> insertFiles(CrmLeadDTO crmLeadDTO) throws Exception;

	void updatePhase(CrmLeadDTO crmLeadDTO) throws Exception;

	void deleteCrmLead(CrmLeadDTO CrmLeadDTO);

	DataListDTO doSearch(CrmLeadDTO CrmLeadDTO);

	boolean importCrmLead(MultipartFile file);

	LeadSourceDTO addLeadSource(LeadSourceDTO leadSourceDTO) throws Exception;

	List<LeadSourceDTO> getLeadSources();

}
