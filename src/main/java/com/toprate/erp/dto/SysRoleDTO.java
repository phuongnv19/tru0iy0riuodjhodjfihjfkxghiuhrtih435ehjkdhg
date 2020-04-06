package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysRoleDTO extends ErpBaseDTO {
    private Long roleId;

    @AdjHistory(field = "ROLE_NAME")
    private String roleName;

    @AdjHistory(field = "DESCRIPTION")
    private String description;

    @AdjHistory(field = "ROLE_CODE")
    private String roleCode;

    @AdjHistory(field = "CREATE_USER")
    private String createUser;

    @AdjHistory(field = "CREATE_DATE")
    private Date createDate;

    @AdjHistory(field = "STATUS")
    private Long status;
    private List<SysRoleObjectDTO> sysObjectsList = Lists.newArrayList();
    public SysRole toModel(){
        SysRole sysRole = new SysRole();
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
