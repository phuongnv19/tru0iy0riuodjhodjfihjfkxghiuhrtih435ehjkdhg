package com.toprate.erp.rest.VM;

import com.toprate.erp.dto.SysRoleDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
public class RoleVM {
    private Long roleId;


    private String roleName;

    private String description;

    private String roleCode;

    private String createUser;

    private Date createDate;

    private Long status;

    public SysRoleDTO toDto() {
        SysRoleDTO sysRole = new SysRoleDTO();
        sysRole.setRoleId(roleId);
        sysRole.setRoleName(roleName);
        sysRole.setDescription(description);
        sysRole.setRoleCode(roleCode);
        sysRole.setCreateUser(createUser);
        sysRole.setCreateDate(createDate);
        sysRole.setStatus(status);
        return sysRole;

    }
}
