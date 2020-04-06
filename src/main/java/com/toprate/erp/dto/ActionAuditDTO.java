package com.toprate.erp.dto;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.ActionAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActionAuditDTO extends ErpBaseDTO {
    private Long auditId;
    private Long auditTypeId;
    private Long actorType;
    private Long actoryId;
    private Long objectId;
    private Long objectTypeId;
    private Date createDate;
    private String description;
    private Long userId;
    private String serverIp;
    private Long sourceId;
    private String tableName;

    public ActionAudit toModel (){
        ActionAudit actionAudit = new ActionAudit();
        actionAudit.setAuditId(auditId);
        actionAudit.setAuditTypeId(auditTypeId);
        actionAudit.setActorType(actorType);
        actionAudit.setActoryId(actoryId);
        actionAudit.setObjectId(objectId);
        actionAudit.setObjectTypeId(objectTypeId);
        actionAudit.setCreateDate(createDate);
        actionAudit.setDescription(description);
        actionAudit.setUserId(userId);
        actionAudit.setServerIp(serverIp);
        actionAudit.setSourceId(sourceId);

        return  actionAudit;
    }
}
