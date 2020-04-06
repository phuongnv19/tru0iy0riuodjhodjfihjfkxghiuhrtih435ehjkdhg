package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO extends ErpBaseDTO {

    private Long companyId;
//    @AdjHistory(field = "PARENT_MEMBER_ID")
//    private Long parentMemberId;

    @NotNull
    @AdjHistory(field = "COMPANY_FULL_NAME")
    private String companyFullName;

    @AdjHistory(field = "ABN")
    private Long abn;

    @AdjHistory(field = "ACN")
    private String acn;

    @AdjHistory(field = "COMPANY_CODE")
    private String companyCode;

    @AdjHistory(field = "OFFICIAL_COMPANY_ADDRESS")
    private String officialCompanyAddress;

    @AdjHistory(field = "COUNTRY")
    private String country;

    @AdjHistory(field = "COUNTRY_NUM_PREFIX")
    private Long countryNumPrefix;

    @AdjHistory(field = "COMPANY_PHONE")
    private Long companyPhone;

    @AdjHistory(field = "COMPANY_LANDLINE_PHONE")
    private Long companyLandlinePhone;

    @AdjHistory(field = "FAX")
    private Long fax;

    @AdjHistory(field = "COMPANY_INTRO")
    private String companyIntro;

    @AdjHistory(field = "COMPANY_EMAIL")
    private String companyEmail;

    @AdjHistory(field = "COMPANY_VERIFICATION")
    private String companyVerification;

    @AdjHistory(field = "IS_VERIFIED")
    private Long isVerified;

    public Company toModel(){
        Company company = new Company();
        company.setCompanyId(companyId);
//        company.setParentMemberId(parentMemberId);
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
