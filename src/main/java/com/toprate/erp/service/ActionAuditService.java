package com.toprate.erp.service;

import com.toprate.erp.dto.ActionAuditDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;




public interface ActionAuditService {



    void insertOrUpdate(ActionAuditDTO dto);


    Long trackingAdjustment(Long auditTypeId,Long objectId,String tableName, Long recordId, Object newObj, Object oldObj, String des,Long auditId);

    /**
     * ImsCommonConstant.ADJ_HISTORY_TYPE.ADJ_TYPE_EDIT
     * ImsCommonConstant.ADJ_HISTORY_TYPE.ADJ_TYPE_DELETE
     *
     * @param adjType
     * @param tableName
     * @param fieldName
     * @param recordId
     * @param description
     */
}

