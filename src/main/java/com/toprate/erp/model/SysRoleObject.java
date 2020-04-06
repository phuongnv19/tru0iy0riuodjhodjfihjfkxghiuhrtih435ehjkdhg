package com.toprate.erp.model;

import com.toprate.erp.dto.SysRoleObjectDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SYS_ROLE_OBJECT")
@Data
public class SysRoleObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "OBJECT_ID")
    private Long objectId;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public SysRoleObjectDTO toDto(){
        SysRoleObjectDTO sysRoleObject  = new SysRoleObjectDTO();
        sysRoleObject.setRoleId(roleId);
        sysRoleObject.setObjectId(objectId);
        sysRoleObject.setIsActive(isActive);
        return sysRoleObject;
    }
}
