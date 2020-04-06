package com.toprate.erp.dto;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.AuditDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDetailDTO extends ErpBaseDTO {
    private Long id;
    private Long auditId;
    private Date createDate;
    private String colName;
    private String oldName;
    private String newName;

    public AuditDetail toModel (){
        AuditDetail auditDetail  = new AuditDetail();
        auditDetail.setAuditId(auditId);
        auditDetail.setId(id);
        auditDetail.setCreateDate(createDate);
        auditDetail.setColName(colName);
        auditDetail.setOldName(oldName);
        auditDetail.setNewName(newName);

        return  auditDetail;
    }
}
