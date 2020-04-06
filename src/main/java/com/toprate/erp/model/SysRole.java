package com.toprate.erp.model;

import com.toprate.erp.dto.SysRoleDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYS_ROLE")
@Data // lombok
public class SysRole {

    @Id
    @SequenceGenerator(name="pk_sequence_role",sequenceName="SYS_ROLES_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_role")
    @Column(name = "ROLE_ID")
    private Long roleId;


    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "STATUS")
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
