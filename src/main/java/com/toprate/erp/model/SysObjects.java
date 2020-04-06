package com.toprate.erp.model;

import com.toprate.erp.dto.SysObjectsDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYS_OBJECTS")
@Data
public class SysObjects {

    @Id
    @SequenceGenerator(name="pk_sequence_object",sequenceName="SYS_OBJECTS_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_object")
    @Column(name = "OBJECT_ID")
    private Long objectId;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "ORD")
    private Long ord;

    @Column(name = "OBJECT_URL")
    private String objectUrl;

    @Column(name = "OBJECT_NAME")
    private String objectName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OBJECT_TYPE_ID")
    private Long objectTypeId;

    @Column(name = "OBJECT_CODE")
    private String objectCode;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "PATH_IMG")
    private String pathImg;

    public SysObjectsDTO toDto(){
        SysObjectsDTO sysObjects = new SysObjectsDTO();
        sysObjects.setObjectId(objectId);
        sysObjects.setParentId(parentId);
        sysObjects.setOrd(ord);
        sysObjects.setObjectUrl(objectUrl);
        sysObjects.setObjectName(objectName);
        sysObjects.setDescription(description);
        sysObjects.setObjectTypeId(objectTypeId);
        sysObjects.setObjectCode(objectCode);
        sysObjects.setCreateUser(createUser);
        sysObjects.setCreateDate(createDate);
        sysObjects.setPathImg(pathImg);
        return  sysObjects;
    }

}
