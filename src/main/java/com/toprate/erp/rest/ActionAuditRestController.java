package com.toprate.erp.rest;

import com.toprate.erp.dto.ActionAuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ActionAuditRestController {

//    @Autowired
//    ActionAuditServiceImpl actionAuditService;
//
//    @PostMapping("/action/create")
//    public ResponseEntity<?> crateAcitonAudit(@RequestBody ActionAuditDTO actionAuditDTO){
//        actionAuditService.addActionAudit(actionAuditDTO);
//        return new ResponseEntity<>(actionAuditDTO,HttpStatus.OK);
//    }
//
//    @PostMapping("/action/update")
//    public ResponseEntity<?> updateActionAudit (@RequestBody ActionAuditDTO actionAuditDTO ){
//        actionAuditService.saveAndUpdate(actionAuditDTO);
//        return  new ResponseEntity<>(actionAuditDTO, HttpStatus.OK);
//    }
//
//    @PostMapping("/action/delete")
//    public  ResponseEntity<?> deleteActionAudit (@RequestBody ActionAuditDTO  actionAuditDTO){
//        if (actionAuditService.deleteAction(actionAuditDTO) ==1 ){
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else
//        return new ResponseEntity <>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("/action/doSearch")
//    public ResponseEntity<List<ActionAuditDTO>> doSearch(@RequestBody ActionAuditDTO actionAuditDTO){
//        return new ResponseEntity<>(actionAuditService.doSearch(actionAuditDTO), HttpStatus.OK);
//    }
}

