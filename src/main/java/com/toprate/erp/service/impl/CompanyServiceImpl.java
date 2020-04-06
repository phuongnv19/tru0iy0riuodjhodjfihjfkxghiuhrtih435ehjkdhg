package com.toprate.erp.service.impl;

import java.util.List;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toprate.erp.dto.CompanyDTO;
import com.toprate.erp.model.Company;
import com.toprate.erp.repositories.CompanyRepository;
import com.toprate.erp.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public long addCompany(CompanyDTO companyDTO) throws Exception {
        return companyRepository.insert(companyDTO.toModel());
    }

    @Override
    public void saveAndUpdate(CompanyDTO companyDTO) {
        companyRepository.updateFromCrmLead(companyDTO);
    }

    @Override
    public Company findCompanyById(long companyId) {
        return companyRepository.find(companyId);
    }

    @Override
    public List<CompanyDTO> autoCompleteSearch(SearchDTO companyDTO) {
        return companyRepository.autoCompleteSearch(companyDTO);
    }

    @Override
    public void delete(CompanyDTO companyDTO) throws BusinessException {
        try {
            companyRepository.delete(companyRepository.find(companyDTO.getCompanyId()));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void updateCompany(CompanyDTO companyDTO) {
        companyRepository.update(companyDTO.toModel());
    }

    @Override
    public DataListDTO doSearch(SearchDTO obj) {
        List<CompanyDTO> ls = companyRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;

    }
}
