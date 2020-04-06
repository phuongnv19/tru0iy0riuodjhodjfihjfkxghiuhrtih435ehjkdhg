package com.toprate.erp.rest;

import com.toprate.erp.authen.TokenProvider;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.*;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.CrmPhase;
import com.toprate.erp.model.Product;
import com.toprate.erp.repositories.CrmPhaseRepository;
import com.toprate.erp.service.impl.*;
import com.toprate.erp.util.EncryptUtils;
import com.toprate.erp.util.UFile;
import com.toprate.erp.util.UString;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CommonRestController {

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    CompanyServiceImpl companyService;

    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    @Autowired
    ProductTypeServiceImpl productTypeService;

    @Autowired
    CrmPhaseServiceImpl crmPhaseService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/common/getListLocation")
    public ResponseEntity<?> findAllLocation(){
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/common/getListProductCategory")
    public ResponseEntity<?> getListProductCategory(){
        return new ResponseEntity<>(productCategoryService.getListProductCategory(),HttpStatus.OK);
    }

    @GetMapping("/common/getListProductType")
    public ResponseEntity<?> getListProductType(){
        return new ResponseEntity<>(productTypeService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/common/getListProductTypeByCategoryId")
    public ResponseEntity<?> getListProductTypeByCategory(@RequestParam(name = "category_id") long categoryId){
        return new ResponseEntity<>(productTypeService.getListProductType(categoryId),HttpStatus.OK);
    }

    @GetMapping("/common/getCrmPhaseByPhaseType")
    public ResponseEntity getListCrm(@RequestParam(name = "phase_type") long phaseType){
        return new ResponseEntity(crmPhaseService.getListCrmPhaseByPhaseType(phaseType), HttpStatus.OK);
    }
//
    @PostMapping("/common/getCompanyForAutocomplete")
    public ResponseEntity<?> getListCompany(@RequestBody SearchDTO companyDTO){
        return new ResponseEntity<>(companyService.autoCompleteSearch(companyDTO), HttpStatus.OK);
    }

    @PostMapping("/common/getForAutocompleteUser")
    public ResponseEntity getForAutocompleteUser(@RequestBody SearchDTO dto){
        return new ResponseEntity<>(userServiceImpl.getForAutocompleteUser(dto), HttpStatus.OK);
    }

    @PostMapping("/common/getProductForAutocomplete")
    public ResponseEntity getProductForAutocomplete(@RequestBody SearchDTO dto){
        return new ResponseEntity<>(productServiceImpl.autoCompleteSearch(dto), HttpStatus.OK);
    }

    @PostMapping("/common/getProductTypeForAutocomplete")
    public ResponseEntity<?> getProductTypeForAutocomplete(@RequestBody SearchDTO dto){
        return new ResponseEntity<>(productTypeService.autoCompleteSearch(dto),HttpStatus.OK);
    }

    @PostMapping("/common/getLocationForAutocomplete")
    public ResponseEntity<?> getLocationForAutocomplete(@RequestBody SearchDTO dto){
        return new ResponseEntity<>(locationService.getForAutocomplete(dto),HttpStatus.OK);
    }

    @PostMapping("/common/getMemberForAutocomplete")
    public ResponseEntity<?> getMemberForAutocomplete(@RequestBody SearchDTO dto){
        return new ResponseEntity<>(memberService.getListMemberForAutocomplete(dto),HttpStatus.OK);
    }


}
