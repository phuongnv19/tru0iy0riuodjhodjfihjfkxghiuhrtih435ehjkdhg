package com.toprate.erp.service;

import java.util.List;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.LeadSourceDTO;

public interface LeadSourceService {

	LeadSourceDTO addLeadSource(LeadSourceDTO leadSourceDTO) throws Exception;

	List<LeadSourceDTO> findAll();

	LeadSourceDTO create(LeadSourceDTO leadSourceDTO);

	LeadSourceDTO update(LeadSourceDTO leadSourceDTO);

	int delete(LeadSourceDTO leadSourceDTO);

	DataListDTO doSearch(LeadSourceDTO leadSourceDTO);

}
