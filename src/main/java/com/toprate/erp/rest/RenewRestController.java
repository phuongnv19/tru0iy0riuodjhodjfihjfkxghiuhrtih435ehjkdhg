package com.toprate.erp.rest;

import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.service.impl.RenewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RenewRestController {

    @Autowired
    RenewServiceImpl renewService;

    @PostMapping("/renew/doSearch")
    public ResponseEntity doSearch(@RequestBody ContractDTO dto){
        return new ResponseEntity(renewService.doSearch(dto), HttpStatus.OK);
    }

    @PostMapping("/renew/update")
    public ResponseEntity update(@RequestBody ContractDTO dto){
        return new ResponseEntity(renewService.update(dto), HttpStatus.OK);
    }

    @PostMapping("/renew/delete")
    public ResponseEntity delete(@RequestBody ContractDTO dto){
        if (renewService.delete(dto)==1) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/renew/lockStatus")
//    public ResponseEntity lockStatus(@RequestBody ContractDTO dto){
//        return new ResponseEntity(renewTerService.lockStatus(dto), HttpStatus.OK);
//    }
//
//    @PostMapping("/renew/create")
//    public ResponseEntity create(@RequestBody ContractDTO dto){
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
