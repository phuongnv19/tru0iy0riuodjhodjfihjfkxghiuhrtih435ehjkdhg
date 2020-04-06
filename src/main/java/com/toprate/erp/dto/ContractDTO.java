package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Contract;
import com.toprate.erp.model.ContractItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO extends ErpBaseDTO {
    private Long id;

    @AdjHistory(field = "CONTRACT_COMMENT")
    private String contractComment;

    @AdjHistory(field = "CONTRACT_NOTE")
    private String contractNote;

    @NotNull
    @AdjHistory(field = "CONTRACT_REF")
    private String contractRef;

    @AdjHistory(field = "CONTRACT_SIGNATURE")
    private String contractSignature;

    @AdjHistory(field = "CONTRACT_STATUS")
    private Long contractStatus;

    @AdjHistory(field = "COMPANY_NAME")
    private String companyName;

    @AdjHistory(field = "CONTRACT_UPLOAD_DOCS")
    private String contractUploadDocs;

    @AdjHistory(field = "DEAL_ID")
    private Long dealId;

    @AdjHistory(field = "MODIFIED_TIME")
    private Date modifiedTime;

    @AdjHistory(field = "TOTAL_VALUE")
    private Long totalValue;

    @NotNull
    @AdjHistory(field = "CREATED_BY")
    private Long createdBy;

    @AdjHistory(field = "CONTRACT_NAME")
    private String contractName;

    @AdjHistory(field = "CREATE_TIME")
    private Date createTime;

    @AdjHistory(field = "CONTRACT_TYPE")
    private Long contractType;

    @AdjHistory(field = "PHASE_ID")
    private Long phaseId;

    @AdjHistory(field = "COMPANY_ID")
    private Long companyId;

    @AdjHistory(field = "TEMPLATE_ID")
    private Long templateId;

    @AdjHistory(field = "LOCATION_ID")
    private Long locationId;

    @AdjHistory(field = "STATUS_SIGN")
    private Long statusSign;

    @AdjHistory(field = "PAID_START_DATE")
    private Date paidStartDate;

    @AdjHistory(field = "PAID_END_DATE")
    private Date paidEndDate;

    @AdjHistory(field = "PARENT_MEMBER_ID")
    private Long parentMemberId;

    @AdjHistory(field = "TERMINATION_NOTICE_ID")
    private Long terminationNoticeId;

    @AdjHistory(field = "UPFRONT_ID")
    private Long upfrontId;

    @AdjHistory(field = "PAYMENT_METHOD_ID")
    private Long paymentMethodId;

    @AdjHistory(field = "DEPOSIT_ID")
    private Long depositId;

    @AdjHistory(field = "DEPOSIT_FEE")
    private Long depositFee;

    @AdjHistory(field = "N_OCCUPANTS")
    private Long nOccupants;

    @AdjHistory(field = "lock_status")
    private Long lockStatus;


    @AdjHistory(field = "LIST_OFFICE_CONTRACT")
    private List<ContractItem> listOfficeContract;

    @AdjHistory(field = "LIST_ADD_ON_CONTRACT")
    private List<ContractItem> listAddOnContract;

    private String customerName;

    public Contract toModel() {

        Contract contract = new Contract();
        contract.setId(id);
        contract.setContractRef(contractRef);
        contract.setDealId(dealId);
        contract.setContractType(contractType);
        contract.setTotalValue(totalValue);
        contract.setContractNote(contractNote);
        contract.setCreatedBy(createdBy);
        contract.setParentMemberId(parentMemberId);
        contract.setContractComment(contractComment);
        contract.setContractStatus(contractStatus);
        contract.setContractUploadDocs(contractUploadDocs);
        contract.setContractSignature(contractSignature);
        contract.setCreateTime(createTime);
        contract.setModifiedTime(modifiedTime);
        contract.setPhaseId(phaseId);
        contract.setLocationId(locationId);
        contract.setContractType(contractType);
        contract.setPaidEndDate(paidEndDate);
        contract.setPaidStartDate(paidStartDate);
        contract.setLockStatus(lockStatus);
        return  contract;

    }
}
