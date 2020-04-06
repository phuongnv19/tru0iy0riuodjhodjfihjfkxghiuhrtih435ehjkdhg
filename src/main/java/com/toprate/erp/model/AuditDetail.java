package com.toprate.erp.model;

import com.toprate.erp.dto.ActionAuditDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUDIT_DETAIL")
@Data
public class AuditDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AUDIT_ID")
    private Long auditId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "COL_NAME")
    private String colName;

    @Column(name = "OLD_NAME")
    private String oldName;

    @Column(name = "NEW_NAME")
    private String newName;

    @Column(name = "TABKE_NAME")
    private String tableName;

    @Column(name = "RECORD_ID")
    private Long recordId;

}
