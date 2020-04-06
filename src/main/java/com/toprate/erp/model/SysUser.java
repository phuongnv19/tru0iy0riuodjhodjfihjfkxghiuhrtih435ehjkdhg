package com.toprate.erp.model;

import com.toprate.erp.dto.SysUserDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYS_USER")
@Data // lombok
public class SysUser {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="SYS_USER_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "USER_ID")
    private Long userId;


    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;

    @Column(name = "HOME_PHONE")
    private String homePhone;

    @Column(name = "GENDER")
    private Long gender;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "IDENTITY_CODE")
    private String identityCode;

    @Column(name = "IDENTITY_TYPE")
    private Long identityType;

    @Column(name = "KEY_FOB_ID")
    private String keyFobId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALID_FROM")
    private Date validFrom;

    @Column(name = "VALID_TO")
    private Date validTo;

    @Column(name = "CHECK_IP")
    private Long checkIp;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "LAST_CHANGE_PASSWORD")
    private Date lastChangePassword;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "LOGIN_FAILURE_COUNT")
    private Long loginFailureCount;

    @Column(name = "LAST_LOCK_DATE")
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
