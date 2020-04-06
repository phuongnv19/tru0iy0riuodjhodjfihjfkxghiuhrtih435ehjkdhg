package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.SysRoleObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleObjectDTO {
    private Long roleId;

    @AdjHistory(field = "OBJECT_ID")
    private Long objectId;

    @NotNull
    @AdjHistory(field = "IS_ACTIVE")
    private Long isActive;
    private String objectCode;
    private String objectName;
    private Long ord;
    private Long objectTypeId;
    private String objectUrl;

    public SysRoleObject toModel(){
        SysRoleObject sysRoleObject  = new SysRoleObject();
        sysRoleObject.setRoleId(roleId);
        sysRoleObject.setObjectId(objectId);
        sysRoleObject.setIsActive(isActive);
        return sysRoleObject;
    }
}
