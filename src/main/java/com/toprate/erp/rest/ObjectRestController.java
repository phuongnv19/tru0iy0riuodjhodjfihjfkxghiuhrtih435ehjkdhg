package com.toprate.erp.rest;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.service.impl.ObjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
public class ObjectRestController extends AbstractRestController {
    @Autowired
    private ObjectServiceImpl objectServiceImpl;
    
    

    @PostMapping("/object/save")
    public ResponseEntity save(@Valid @RequestBody SysObjectsDTO sysobject) throws Exception {
        return new ResponseEntity<>( objectServiceImpl.insert(sysobject), HttpStatus.OK);
    }

    @PostMapping("/object/update")
    public ResponseEntity update(@Valid @RequestBody SysObjectsDTO sysobject) throws Exception {
        return new ResponseEntity<>( objectServiceImpl.update(sysobject), HttpStatus.OK);
    }


    @PostMapping("/object/doSearch")
    public ResponseEntity doSearch(@Valid @RequestBody SearchDTO obj) {
        return new ResponseEntity<>( objectServiceImpl.doSearch(obj), HttpStatus.OK);
    }

    @PostMapping("/object/delete")
    public ResponseEntity delete(@Valid @RequestBody SysObjectsDTO sysobject) throws Exception {
        objectServiceImpl.delete(sysobject);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
