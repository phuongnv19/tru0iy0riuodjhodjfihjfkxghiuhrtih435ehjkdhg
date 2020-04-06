package com.toprate.erp.rest;

import com.toprate.erp.dto.LeadSourceDTO;
import com.toprate.erp.service.impl.LeadSourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LeadSourceRestController {

    @Autowired
    LeadSourceServiceImpl leadSourceService;

    @PostMapping("/leadSource/create")
    public ResponseEntity create(@RequestBody LeadSourceDTO leadSourceDTO){
        return new ResponseEntity(leadSourceService.create(leadSourceDTO),HttpStatus.OK);
    }

    @PostMapping("/leadSource/update")
    public ResponseEntity update(@RequestBody LeadSourceDTO leadSourceDTO){
        return new ResponseEntity(leadSourceService.update(leadSourceDTO),HttpStatus.OK);
    }

    @PostMapping("/leadSource/delete")
    public ResponseEntity delete(@RequestBody LeadSourceDTO leadSourceDTO){
        if (leadSourceService.delete(leadSourceDTO)==1){
            return new ResponseEntity(HttpStatus.OK);
        } else  return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/leadSource/doSearch")
    public ResponseEntity doSearch(@RequestBody LeadSourceDTO leadSourceDTO){
        return new ResponseEntity(leadSourceService.doSearch(leadSourceDTO),HttpStatus.OK);
    }
}
