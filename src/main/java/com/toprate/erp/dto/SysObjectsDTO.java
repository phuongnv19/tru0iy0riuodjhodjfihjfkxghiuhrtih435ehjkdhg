package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.SysObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysObjectsDTO extends ErpBaseDTO {

    private Long objectId;

    @AdjHistory(field = "PARENT_ID")
    private Long parentId;

//    @NotNull
    @AdjHistory(field = "STATUS")
    private Long status;

    @AdjHistory(field = "ORD")
    private Long ord;

    @AdjHistory(field = "OBJECT_URL")
    private String objectUrl;

    @NotNull
    @AdjHistory(field = "OBJECT_NAME")
    private String objectName;

    @AdjHistory(field = "DESCRIPTION")
    private String description;

    @NotNull
    @AdjHistory(field = "OBJECT_TYPE_ID")
    private Long objectTypeId;

    @AdjHistory(field = "OBJECT_CODE")
    private String objectCode;

    @AdjHistory(field = "CREATE_USER")
    private String createUser;

    @AdjHistory(field = "CREATE_DATE")
    private Date createDate;

    @AdjHistory(field = "PATH_IMG")
    private String pathImg;

    private List<SysObjectsDTO> ListChildObject= Lists.newArrayList();

    public SysObjects toModel(){
        SysObjects sysObjects = new SysObjects();
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
        sysObjects.setStatus(status);
        return  sysObjects;
    }
}
