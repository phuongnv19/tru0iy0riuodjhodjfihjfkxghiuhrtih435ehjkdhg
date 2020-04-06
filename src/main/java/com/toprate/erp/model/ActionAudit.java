package com.toprate.erp.model;

import com.toprate.erp.dto.ActionAuditDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACTION_AUDIT")
@Data
public class ActionAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID")
    private Long auditId;

    @Column(name = "AUDIT_TYPE_ID")
    private Long auditTypeId;

    @Column(name = "ACTOR_TYPE")
    private Long actorType;

    @Column(name = "ACTORY_ID")
    private Long actoryId;

    @Column(name = "OBJECT_ID")
    private Long objectId;

    @Column(name = "OBJECT_TYPE_ID")
    private Long objectTypeId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "SERVER_IP")
    private String serverIp;

    @Column(name = "SOURCE_ID")
    private Long sourceId;



    public ActionAuditDTO toDto (){
        ActionAuditDTO actionAudit = new ActionAuditDTO();
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
