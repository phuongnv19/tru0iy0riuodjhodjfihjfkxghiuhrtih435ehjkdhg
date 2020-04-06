package com.toprate.erp.rest;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.CompanyDTO;
import com.toprate.erp.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class CompanyRestController {

    @Autowired
    CompanyServiceImpl companyService;

    @PostMapping("/company/create")
    public ResponseEntity create(@RequestBody CompanyDTO companyDTO) throws Exception {
        long id = companyService.addCompany(companyDTO);
        companyDTO.setCompanyId(id);
        return new ResponseEntity(companyDTO, HttpStatus.CREATED);
    }

    @PostMapping("/company/delete")
    public ResponseEntity delete(@RequestBody CompanyDTO companyDTO){
        companyService.delete(companyDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/company/update")
    public ResponseEntity update(@RequestBody CompanyDTO companyDTO){
        companyService.updateCompany(companyDTO);
        return new ResponseEntity(companyDTO, HttpStatus.OK);
    }

    @PostMapping("/company/doSearch")
    public ResponseEntity doSearch(@Valid @RequestBody SearchDTO obj) {
        return new ResponseEntity<>( companyService.doSearch(obj), HttpStatus.OK);
    }
}
