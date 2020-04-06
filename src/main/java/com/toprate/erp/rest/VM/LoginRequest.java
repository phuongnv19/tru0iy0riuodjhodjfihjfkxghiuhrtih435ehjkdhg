package com.toprate.erp.rest.VM;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String passwordOld;
    private String passwordConfirm;
}
