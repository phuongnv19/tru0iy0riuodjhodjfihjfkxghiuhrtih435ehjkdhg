package com.toprate.erp.rest.VM;

import com.toprate.erp.dto.SysUserDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
public class UserVM {
    private Long userId;
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Long locationId;

    private Long status;

    private String mobilePhone;

    private String homePhone;

    private Long gender;

    private Date dateOfBirth;

    private String identityCode;

    private Long identityType;

    private String keyFobId;

    private String description;

    private Date validFrom;

    private Date validTo;

    private Long checkIp;

    private String ipAddress;

    private Date lastChangePassword;

    private String createUserId;

    private Date createDate;

    private Long loginFailureCount;

    private Date lastLockDate;


    public SysUserDTO toDto(){
        SysUserDTO sysUser= new SysUserDTO();
        sysUser.setCheckIp(this.checkIp);
        sysUser.setCreateDate(this.createDate);
        sysUser.setCreateUserId(createUserId);
        sysUser.setDateOfBirth(dateOfBirth);
        sysUser.setDescription(description);
        sysUser.setEmail(email);
        sysUser.setFirstName(firstName);
        sysUser.setGender(gender);
        sysUser.setHomePhone(homePhone);
        sysUser.setIdentityCode(identityCode);
        sysUser.setIdentityType(identityType);
        sysUser.setIpAddress(ipAddress);
        sysUser.setKeyFobId(keyFobId);
        sysUser.setLastChangePassword(lastChangePassword);
        sysUser.setLastLockDate(lastLockDate);
        sysUser.setLastName(lastName);
        sysUser.setLocationId(locationId);
        sysUser.setLoginFailureCount(loginFailureCount);
        sysUser.setMobilePhone(mobilePhone);
        sysUser.setPassword(password);
        sysUser.setStatus(status);
        sysUser.setUserId(userId);
        sysUser.setValidFrom(validFrom);
        sysUser.setValidTo(validTo);

        return sysUser;
    }
}
