package com.toprate.erp.rest;

import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.service.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractRestController extends AbstractRestController {

    @Autowired
    private ContractServiceImpl contractServiceImpl;

    @PostMapping("/contract/save")
    public ResponseEntity save(@RequestBody ContractDTO contract) throws Exception{
        return new ResponseEntity<>(contractServiceImpl.insert(request,contract), HttpStatus.OK);
//        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @PostMapping("/contract/update")
    public ResponseEntity update(@RequestBody ContractDTO contract) throws Exception{
        return new ResponseEntity<>(contractServiceImpl.update(request,contract), HttpStatus.OK);
//        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @PostMapping("/contract/doSearch")
    public ResponseEntity doSearch(@RequestBody ContractDTO contract) throws Exception{
        return new ResponseEntity<>(contractServiceImpl.doSearch(contract), HttpStatus.OK);
    }

    @PostMapping("/contract/getDetailById")
    public ResponseEntity getDetailById(@RequestBody ContractDTO contract) throws Exception{
//        return new ResponseEntity<>(contract, HttpStatus.OK);
        return new ResponseEntity<>(contractServiceImpl.getDetailById(contract), HttpStatus.OK);
    }

}
