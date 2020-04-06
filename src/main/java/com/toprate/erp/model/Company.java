package com.toprate.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.toprate.erp.dto.CompanyDTO;

import lombok.Data;

@Entity
@Table(name = "COMPANY")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Long companyId;
//    @Column(name = "PARENT_MEMBER_ID")
//    private Long parentMemberId;

    @Column(name = "COMPANY_FULL_NAME")
    private String companyFullName;

    @Column(name = "ABN")
    private Long abn;

    @Column(name = "ACN")
    private String acn;

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "OFFICIAL_COMPANY_ADDRESS")
    private String officialCompanyAddress;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "COUNTRY_NUM_PREFIX")
    private Long countryNumPrefix;

    @Column(name = "COMPANY_PHONE")
    private Long companyPhone;

    @Column(name = "COMPANY_LANDLINE_PHONE")
    private Long companyLandlinePhone;

    @Column(name = "FAX")
    private Long fax;

    @Column(name = "COMPANY_INTRO")
    private String companyIntro;

    @Column(name = "COMPANY_EMAIL")
    private String companyEmail;

    @Column(name = "COMPANY_VERIFICATION")
    private String companyVerification;

    @Column(name = "IS_VERIFIED")
    private Long isVerified;

    public CompanyDTO toDto(){
        CompanyDTO company = new CompanyDTO();
        company.setCompanyId(this.companyId);
//        company.setParentMemberId(this.parentMemberId);
        company.setCompanyFullName(companyFullName);
        company.setAbn(abn);
        company.setAcn(acn);
        company.setCompanyCode(companyCode);
        company.setOfficialCompanyAddress(officialCompanyAddress);
        company.setCountry(country);
        company.setCountryNumPrefix(countryNumPrefix);
        company.setCompanyPhone(companyPhone);
        company.setCompanyLandlinePhone(companyLandlinePhone);
        company.setFax(fax);
        company.setCompanyIntro(companyIntro);
        company.setCompanyEmail(companyEmail);
        company.setCompanyVerification(companyVerification);
        company.setIsVerified(isVerified);

        return  company;

    }
}
