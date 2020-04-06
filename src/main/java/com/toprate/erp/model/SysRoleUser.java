package com.toprate.erp.model;

import com.toprate.erp.dto.SysRoleUserDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SYS_ROLE_USER")
@Data
public class SysRoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public SysRoleUserDTO toDto(){
        SysRoleUserDTO sysRoleUser = new SysRoleUserDTO();
        sysRoleUser.setRoleId(roleId);
        sysRoleUser.setUserId(userId);
        sysRoleUser.setIsActive(isActive);
        return  sysRoleUser;
    }
}
