package com.toprate.erp.service;


import com.toprate.erp.dto.SysUserDTO;

public interface AuthenticationService {
    SysUserDTO getCurrentUser();
}
