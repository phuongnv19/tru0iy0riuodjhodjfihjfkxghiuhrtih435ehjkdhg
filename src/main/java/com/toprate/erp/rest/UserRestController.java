package com.toprate.erp.rest;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.rest.VM.LoginRequest;
import com.toprate.erp.rest.VM.UserVM;
import com.toprate.erp.service.impl.AuthenticationServiceImpl;
import com.toprate.erp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRestController extends AbstractRestController {


    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;



    @PostMapping("/user/save")
    public ResponseEntity save(@Valid @RequestBody UserVM sysUser) throws Exception {
        return new ResponseEntity<>( userServiceImpl.insert(sysUser.toDto()), HttpStatus.OK);
    }

    @PostMapping("/user/update")
    public ResponseEntity update(@Valid @RequestBody UserVM sysUser) throws Exception {
        return new ResponseEntity<>( userServiceImpl.update(sysUser.toDto()), HttpStatus.OK);
    }

    @GetMapping("/user/findByEmail")
    public ResponseEntity findByEmail(){
        return new ResponseEntity<>(userServiceImpl.findByEmail("toanbd@toprate.io"), HttpStatus.OK);
    }

    @PostMapping("/user/doSearch")
    public ResponseEntity doSearch(@Valid @RequestBody SearchDTO obj) {
        return new ResponseEntity<>( userServiceImpl.doSearch(obj), HttpStatus.OK);
    }


    @PostMapping("/updatePass")
    public ResponseEntity updatePass(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        userServiceImpl.updatePass(loginRequest);
        return new ResponseEntity<>( null, HttpStatus.OK);
    }

    @PostMapping("/user/resetPass")
    public ResponseEntity resetPass(@Valid @RequestBody UserVM sysUser) throws Exception {
        return new ResponseEntity<>( userServiceImpl.resetPass(sysUser.toDto()), HttpStatus.OK);
    }

    @PostMapping("/user/lock")
    public ResponseEntity lock(@Valid @RequestBody SysUserDTO obj) throws Exception {
        userServiceImpl.lock(obj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

        @PostMapping("/user/unlock")
    public ResponseEntity unlock(@Valid @RequestBody SysUserDTO obj) throws Exception {
            userServiceImpl.unlock(obj);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/getListRoleByUserId")
    public ResponseEntity getListRoleByUserId(@Valid @RequestBody UserVM user) throws Exception {
        return new ResponseEntity<>(userServiceImpl.getListRoleByUserId(user.getUserId()),HttpStatus.OK);
    }

    @PostMapping("/user/roleMapping")
    public ResponseEntity insertUserRoleData(@Valid @RequestBody SysUserDTO userDto) throws Exception {
        userServiceImpl.insertUserRoleData(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/getAllUser")
    public ResponseEntity getAllUser() throws Exception {
        return new ResponseEntity<>( userServiceImpl.getAllUser(), HttpStatus.OK);
    }
}
