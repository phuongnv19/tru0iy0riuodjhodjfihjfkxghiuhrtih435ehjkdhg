package com.toprate.erp.model;

import com.toprate.erp.dto.MemberDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "PARENT_MEMBER_ID")
    private Long parentMemberId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name     = "LAST_NAME")
    private String lastName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "MEM_POSTAL_ADDRESS")
    private String memPostalAddress;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "MEMBER_STATUS")
    private Long memberStatus;

    @Column(name = "KEY_FOB_ID")
    private String keyFobId;

    @Column(name = "PHYSICAL_KEY_CODE")
    private String physicalKeyCode;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "IDENTITY_CODE")
    private String identityCode;

    @Column(name = "IDENTITY_TYPE")
    private String identityType;

    @Column(name = "MEMBER_TYPE")
    private Long memberType;

    @Column(name = "MEMBER_LongRO")
    private String memberLongro;

    @Column(name = "VALID_FROM")
    private Date validFrom;

    @Column(name = "VALID_TO")
    private Date validTo;

    @Column(name = "CHECK_IP")
    private Long checkIp;

    @Column(name = "IP")
    private String ip;

    @Column(name = "LAST_CHANGE_PASSWORD")
    private Date lastChangePassword;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "LOGIN_FAILURE_COUNT")
    private Long loginFailureCount;

    @Column(name = "LAST_LOCK_DATE")
    private Date lastLockDate;

    public MemberDTO toDto(){
        MemberDTO member = new MemberDTO();
        member.setMemberId(memberId);
        member.setParentMemberId(parentMemberId);
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setPassword(password);
        member.setMemberEmail(memberEmail);
        member.setCompanyId(companyId);
        member.setMemPostalAddress(memPostalAddress);
        member.setDob(dob);
        member.setMemberStatus(memberStatus);
        member.setKeyFobId(keyFobId);
        member.setPhysicalKeyCode(physicalKeyCode);
        member.setGender(gender);
        member.setIdentityCode(identityCode);
        member.setIdentityType(identityType);
        member.setMemberType(memberType);
        member.setMemberLongro(memberLongro);
        member.setValidFrom(validFrom);
        member.setValidTo(validTo);
        member.setCheckIp(checkIp);
        member.setIp(ip);
        member.setLastChangePassword(lastChangePassword);
        member.setCreatedBy(createdBy);
        member.setCreateDate(createDate);
        member.setLoginFailureCount(loginFailureCount);
        member.setLastLockDate(lastLockDate);
        return  member;
    }
}
