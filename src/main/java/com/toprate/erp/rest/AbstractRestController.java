package com.toprate.erp.rest;

import com.toprate.erp.authen.TokenProvider;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1")
public abstract class AbstractRestController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    private TokenProvider tokenProvider;

    protected SysUserDTO getUserInfor(){
        SysUser sysUser=(SysUser) request.getAttribute("USER_INFO");
        return sysUser.toDto();
    }
}
