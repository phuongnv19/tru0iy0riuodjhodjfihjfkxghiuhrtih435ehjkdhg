package com.toprate.erp.service.impl;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.dto.ActionAuditDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.model.ActionAudit;
import com.toprate.erp.model.AuditDetail;
import com.toprate.erp.repositories.ActionAuditRepository;
import com.toprate.erp.repositories.AuditDetailRepository;
import com.toprate.erp.service.ActionAuditService;
import com.toprate.erp.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;



@Service("ActionAuditServiceImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActionAuditServiceImpl
        implements ActionAuditService {
    private static final Logger log = Logger.getLogger(ActionAuditServiceImpl.class);

    @Autowired
    private ActionAuditRepository actionAuditRepository;

    @Autowired
    private AuditDetailRepository auditDetailRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;






    @Override
    public void insertOrUpdate(ActionAuditDTO dto) {
        actionAuditRepository.insertOrUpdate(dto.toModel());
    }

    @Override
    public Long trackingAdjustment(Long auditTypeId,Long objectId,String tableName, Long recordId, Object newObj, Object oldObj,String des,Long auditId) {
        log.debug("logHistory");
//        HttpSession httpSession=request.getSession();
        if(null==auditId) {
            SysUserDTO usersDTO = authenticationServiceImpl.getCurrentUser();
            ActionAudit actionAudit = new ActionAudit();
            actionAudit.setAuditTypeId(auditTypeId);
            actionAudit.setCreateDate(new Date());
            actionAudit.setObjectId(objectId);
            actionAudit.setDescription(des);
            actionAudit.setServerIp(getIpServer());
//        actionAudit.setSourceId(recordId);
            actionAudit.setUserId(usersDTO.getUserId());
             auditId = actionAuditRepository.insert(actionAudit);
        }
        try {
            if(newObj !=null && oldObj !=null){
                Class<?> newObjClass = newObj.getClass();

                for (Field field : newObjClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(AdjHistory.class)) {
                        Object propertyValue = field.get(newObj);
                        String propertyName = field.getName();
                        Object oldVal = getPropertyValue(oldObj, propertyName);

                        // verfiy and checking
                        if (compare(propertyValue, oldVal) == false) {
                            verifyAndTrackingAdjHistory(auditId,tableName, getFieldName(field), recordId, propertyValue, oldVal);
                        }
                    }
                }

                if (newObjClass.getSuperclass() != null) {
                    for (Field field : newObjClass.getSuperclass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(AdjHistory.class)) {
                            Object propertyValue = field.get(newObj);
                            String propertyName = field.getName();
                            Object oldVal = getPropertyValue(oldObj, propertyName);

                            // verfiy and checking
                            if (compare(propertyValue, oldVal) == false) {
                                verifyAndTrackingAdjHistory(auditId,tableName, getFieldName(field), recordId, propertyValue, oldVal);
                            }
                        }
                    }
                }
            }
            if(newObj==null && oldObj!=null){
                Class<?> oldObjClass = oldObj.getClass();
                for (Field field : oldObjClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(AdjHistory.class)) {
                        Object propertyValue = field.get(oldObj);

                        verifyAndTrackingAdjHistory(auditId,tableName, getFieldName(field), recordId, null, propertyValue);
                    }
                }

                if (oldObjClass.getSuperclass() != null) {
                    for (Field field : oldObjClass.getSuperclass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(AdjHistory.class)) {
                            Object propertyValue = field.get(oldObj);

                            verifyAndTrackingAdjHistory(auditId,tableName, getFieldName(field), recordId, null, propertyValue);
                        }
                    }
                }

            }

            if(oldObj==null && newObj!=null){
                Class<?> newObjClass = newObj.getClass();
                for (Field field : newObjClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(AdjHistory.class)) {
                        Object propertyValue = field.get(newObj);

                        verifyAndTrackingAdjHistory(auditId,tableName, getFieldName(field), recordId, propertyValue, null);
                    }
                }

                if (newObjClass.getSuperclass() != null) {
                    for (Field field : newObjClass.getSuperclass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(AdjHistory.class)) {
                            Object propertyValue = field.get(newObj);

                            verifyAndTrackingAdjHistory(auditId,tableName, getFieldName(field), recordId, propertyValue, null);
                        }
                    }
                }

            }
        } catch (Exception e) {
            log.error(e);
        }
        return auditId;
    }

    private boolean compare(Object val1, Object val2) {
        if (val1 instanceof Date) {
            String strDate1 = DateUtils.date2str((Date) val1);
            String strDate2 = DateUtils.date2str((Date) val2);
            if (StringUtils.equals(strDate1, strDate2)) {
                return true;
            } else {
                return false;
            }
        }

        // Convert string to compare
        if (StringUtils.equals(String.valueOf(val1), String.valueOf(val2))) {
            return true;
        }

        return false;
    }

    private String object2String(Object obj) {
        if(null==obj)
            return null;
        if (obj instanceof Date) {
            return DateUtils.date2str((Date) obj);
        }

        return String.valueOf(obj);
    }

    // ================== private ==================
    private void verifyAndTrackingAdjHistory(Long auditId,String tableName, String fieldName, Long recordId
            , Object val1, Object val2) {
        addAdjustRecord(auditId, tableName, fieldName, recordId,val1,val2);
    }

    private void addAdjustRecord(Long auditId, String tableName, String fieldName, Long recordId, Object val1, Object val2) {
        AuditDetail adjObj = new AuditDetail();
        adjObj.setAuditId(auditId);
        adjObj.setColName(fieldName);
		adjObj.setTableName(tableName);
		adjObj.setRecordId(recordId);
        adjObj.setNewName(object2String(val1));
        adjObj.setOldName(object2String(val2));
        adjObj.setCreateDate(new Date());
        auditDetailRepository.insert(adjObj);
    }

    private String getFieldName(Field field) {
        String annotationValue = field.getAnnotation(AdjHistory.class).field();
        if (annotationValue.isEmpty()) {
            return field.getName();
        }
        else {
            return annotationValue;
        }
    }

    private Object getPropertyValue(Object obj, String propertyName) {
        try {
            Class<?> newObjClass = obj.getClass();
            for (Field field : newObjClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(AdjHistory.class) && StringUtils.equals(propertyName, field.getName())) {
                    return field.get(obj);
                }
            }

            if (newObjClass.getSuperclass() != null) {
                for (Field field : newObjClass.getSuperclass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(AdjHistory.class) && StringUtils.equals(propertyName, field.getName())) {
                        return field.get(obj);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public String getIpServer(){
        try {
            InetAddress ip=InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }
}
