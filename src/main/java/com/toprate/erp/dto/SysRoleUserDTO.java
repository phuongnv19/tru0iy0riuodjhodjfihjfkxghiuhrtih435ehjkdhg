package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.SysRoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleUserDTO {
    private Long roleId;

    @AdjHistory(field = "USER_ID")
    private Long userId;

    @NotNull
    @AdjHistory(field = "IS_ACTIVE")
    private Long isActive;

    private Long status;
    private String roleName;
    private String description;
    private String roleCode;
    public SysRoleUser toModel(){
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setRoleId(roleId);
        sysRoleUser.setUserId(userId);
        sysRoleUser.setIsActive(isActive);
        return  sysRoleUser;
    }
}
