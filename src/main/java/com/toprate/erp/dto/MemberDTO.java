package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.elasticsearch.common.recycler.Recycler;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;
    @AdjHistory(field = "PARENT_MEMBER_ID")
    private Long parentMemberId;

    @AdjHistory(field = "FIRST_NAME")
    private String firstName;

    @AdjHistory(field     = "LAST_NAME")
    private String lastName;

    @AdjHistory(field = "PASSWORD")
    private String password;

    @AdjHistory(field = "MEMBER_EMAIL")
    @NotNull
    private String memberEmail;

    @AdjHistory(field = "COMPANY_ID")
    private Long companyId;

    @AdjHistory(field = "MEM_POSTAL_ADDRESS")
    private String memPostalAddress;

    @AdjHistory(field = "DOB")
    private Date dob;

    @AdjHistory(field = "MEMBER_STATUS")
    private Long memberStatus;

    @AdjHistory(field = "KEY_FOB_ID")
    private String keyFobId;

    @AdjHistory(field = "PHYSICAL_KEY_CODE")
    private String physicalKeyCode;

    @AdjHistory(field = "GENDER")
    private String gender;

    @AdjHistory(field = "IDENTITY_CODE")
    private String identityCode;

    @AdjHistory(field = "IDENTITY_TYPE")
    private String identityType;

    @AdjHistory(field = "MEMBER_TYPE")
    private Long memberType;

    @AdjHistory(field = "MEMBER_LongRO")
    private String memberLongro;

    @AdjHistory(field = "VALID_FROM")
    private Date validFrom;

    @AdjHistory(field = "VALID_TO")
    private Date validTo;

    @AdjHistory(field = "CHECK_IP")
    private Long checkIp;

    @AdjHistory(field = "IP")
    private String ip;

    @AdjHistory(field = "LAST_CHANGE_PASSWORD")
    private Date lastChangePassword;

    @AdjHistory(field = "CREATED_BY")
    private Long createdBy;

    @AdjHistory(field = "CREATE_DATE")
    private Date createDate;

    @AdjHistory(field = "LOGIN_FAILURE_COUNT")
    private Long loginFailureCount;

    @AdjHistory(field = "LAST_LOCK_DATE")
    private Date lastLockDate;

    private String memberFullName;

    public Member toModel(){
        Member member = new Member();
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
