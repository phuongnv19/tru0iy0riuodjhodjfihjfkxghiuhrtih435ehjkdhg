package com.toprate.erp.rest.VM;

import com.toprate.erp.dto.SysUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String jwt;
    private SysUserDTO userDTO;
}
