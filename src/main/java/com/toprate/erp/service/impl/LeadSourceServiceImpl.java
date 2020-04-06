package com.toprate.erp.service.impl;

import java.util.List;

import com.toprate.erp.base.dto.DataListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toprate.erp.dto.LeadSourceDTO;
import com.toprate.erp.repositories.LeadSourceRepository;
import com.toprate.erp.service.LeadSourceService;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.ValidateUtils;

@Service
public class LeadSourceServiceImpl implements LeadSourceService, ErpConstants {

	private static final String LEAD_SOURCE_COMPANY_NAME = "Lead source company name";
	private static final String LEAD_SOURCE_TYPE = "Lead source type";
	private static final int MAX_LENGHT = 255;

	@Autowired
	LeadSourceRepository leadSourceRepository;

	@Override
	public LeadSourceDTO addLeadSource(LeadSourceDTO leadSourceDTO) throws Exception {
		checkMaxlength(leadSourceDTO);
		long id = leadSourceRepository.insert(leadSourceDTO.toModel());
		leadSourceDTO.setId(id);
		return leadSourceDTO;

	}


	private void checkMaxlength(LeadSourceDTO leadSourceDTO) {
		ValidateUtils.checkMaxlength(leadSourceDTO.getLeadSourceCompanyName(), ErpConstants.PARAM_IS_MAXLENGTH, LEAD_SOURCE_COMPANY_NAME,
				MAX_LENGHT);
		ValidateUtils.checkMaxlength(leadSourceDTO.getLeadSourceType(), ErpConstants.PARAM_IS_MAXLENGTH,
				LEAD_SOURCE_TYPE, MAX_LENGHT);

	}


	@Override
	public List<LeadSourceDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LeadSourceDTO create(LeadSourceDTO leadSourceDTO) {
		long id =leadSourceRepository.insert(leadSourceDTO.toModel());
		leadSourceDTO.setId(id);
		return leadSourceDTO;
	}

	@Override
	public LeadSourceDTO update(LeadSourceDTO leadSourceDTO) {
		leadSourceRepository.update(leadSourceDTO.toModel());
		return leadSourceDTO;
	}

	@Override
	public int delete(LeadSourceDTO leadSourceDTO) {
		try {
			leadSourceRepository.delete(leadSourceRepository.find(leadSourceDTO.getId()));
			return 1;
		} catch (Exception e){
			return 0;
		}
	}

	@Override
	public DataListDTO doSearch(LeadSourceDTO leadSourceDTO) {
		List<LeadSourceDTO> listDTO = leadSourceRepository.doSearch(leadSourceDTO);
		DataListDTO data = new DataListDTO();
		data.setData(listDTO);
		data.setSize(leadSourceDTO.getPageSize());
		data.setStart(1);
		data.setTotal(leadSourceDTO.getTotalRecord());
		return data;
	}

}
