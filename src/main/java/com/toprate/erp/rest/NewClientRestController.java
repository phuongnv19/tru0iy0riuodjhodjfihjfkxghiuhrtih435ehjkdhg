package com.toprate.erp.rest;

import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.service.impl.NewClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class NewClientRestController {

    @Autowired
    NewClientServiceImpl newClientService;

    @PostMapping("/newClient/doSearch")
    public ResponseEntity doSearch(@RequestBody ContractDTO dto){
        return new ResponseEntity(newClientService.doSearch(dto), HttpStatus.OK);
    }

    @PostMapping("/newClient/update")
    public ResponseEntity update(@RequestBody ContractDTO dto){
        return new ResponseEntity(newClientService.update(dto), HttpStatus.OK);
    }

    @PostMapping("/newClient/lockStatus")
    public ResponseEntity lockStatus(@RequestBody ContractDTO dto){
        return new ResponseEntity(newClientService.lockStatus(dto), HttpStatus.OK);
    }
}
