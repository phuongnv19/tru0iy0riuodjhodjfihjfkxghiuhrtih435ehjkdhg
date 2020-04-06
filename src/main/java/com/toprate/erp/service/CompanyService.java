package com.toprate.erp.service;

import java.util.List;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.CompanyDTO;
import com.toprate.erp.model.Company;

public interface CompanyService {

	List<Company> findAll();

	long addCompany(CompanyDTO companyDTO) throws Exception;

	void saveAndUpdate(CompanyDTO companyDTO);

	Company findCompanyById(long companyId);

	List<CompanyDTO> autoCompleteSearch(SearchDTO companyDTO);

	void delete(CompanyDTO companyDTO);

	void updateCompany(CompanyDTO companyDTO);

	DataListDTO doSearch(SearchDTO obj);
}
