package com.toprate.erp.rest;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleDTO;
import com.toprate.erp.rest.VM.RoleVM;
import com.toprate.erp.service.impl.ObjectServiceImpl;
import com.toprate.erp.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RoleRestController  extends AbstractRestController{
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping("/role/save")
    public ResponseEntity save(@Valid @RequestBody RoleVM obj) throws Exception {
        return new ResponseEntity<>( roleServiceImpl.insert(obj.toDto()), HttpStatus.OK);
    }

    @PostMapping("/role/update")
    public ResponseEntity update(@Valid @RequestBody RoleVM obj) throws Exception {
        return new ResponseEntity<>( roleServiceImpl.update(obj.toDto()), HttpStatus.OK);
    }


    @PostMapping("/role/doSearch")
    public ResponseEntity doSearch(@Valid @RequestBody SearchDTO obj) {
        return new ResponseEntity<>( roleServiceImpl.doSearch(obj), HttpStatus.OK);
    }


    @PostMapping("/role/delete")
    public ResponseEntity delete(@Valid @RequestBody SysRoleDTO obj) throws Exception {
        roleServiceImpl.delete(obj);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @PostMapping("/role/getListObjectByRoleId")
    public ResponseEntity getListObjectByRoleId(@Valid @RequestBody RoleVM role) {
        return new ResponseEntity<>( roleServiceImpl.getListObjectByRoleId(role.getRoleId()), HttpStatus.OK);
    }


    @PostMapping("/role/roleMapping")
    public ResponseEntity roleMapping(@Valid @RequestBody SysRoleDTO obj) throws Exception {
        roleServiceImpl.insertToRoleObject(obj);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
