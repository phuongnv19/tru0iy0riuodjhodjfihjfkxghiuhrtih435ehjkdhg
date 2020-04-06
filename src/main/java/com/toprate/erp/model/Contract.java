package com.toprate.erp.model;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.dto.ContractDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONTRACT")
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTRACT_REF")
    private String contractRef;

    @Column(name = "DEAL_ID")
    private Long dealId;

    @Column(name = "CONTRACT_TYPE")
    private Long contractType;

    @Column(name = "TOTAL_VALUE")
    private Long totalValue;

    @Column(name = "CONTRACT_NOTE")
    private String contractNote;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "CONTRACT_COMMENT")
    private String contractComment;

    @Column(name = "CONTRACT_STATUS")
    private Long contractStatus;

    @Column(name = "CONTRACT_UPLOAD_DOCS")
    private String contractUploadDocs;

    @Column(name = "CONTRACT_SIGNATURE")
    private String contractSignature;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;

    @Column(name = "PHASE_ID")
    private Long phaseId;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "PAID_START_DATE")
    private Date paidStartDate;

    @Column(name = "PAID_END_DATE")
    private Date paidEndDate;

    @Column( name ="PARENT_MEMBER_ID")
    private Long parentMemberId;

    @Column( name ="lock_status")
    private Long lockStatus;

    @Column(name = "TEMPLATE_ID")
    private Long templateId;

    public ContractDTO toDto (){
        ContractDTO contract = new ContractDTO();
        contract.setId(id);
        contract.setContractRef(contractRef);
        contract.setDealId(dealId);
        contract.setTotalValue(totalValue);
        contract.setContractNote(contractNote);
        contract.setCreatedBy(createdBy);
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
        contract.setParentMemberId(parentMemberId);
        return  contract;
    }
}
