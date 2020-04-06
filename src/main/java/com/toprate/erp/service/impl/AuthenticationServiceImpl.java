package com.toprate.erp.service.impl;


import com.toprate.erp.base.dto.CustomUserDetails;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    public SysUserDTO getCurrentUser() {
        // integtate with SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null!=authentication) {
            CustomUserDetails customUserCurrent = (CustomUserDetails) authentication.getPrincipal();
            return customUserCurrent.getUser();
        }

        return new SysUserDTO();
    }

    public CustomUserDetails getCustomUserDetails() {
        // integtate with SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null!=authentication) {
            CustomUserDetails customUserCurrent = (CustomUserDetails) authentication.getPrincipal();
            return customUserCurrent;
        }

        return new CustomUserDetails(new SysUserDTO(),null);
    }

}
