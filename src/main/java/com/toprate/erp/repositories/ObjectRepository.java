package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.model.SysObjects;
import com.toprate.erp.model.SysRole;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public class ObjectRepository  extends HibernateRepository<SysObjects,Long> {




    public List<SysObjectsDTO> getListObjects(Long userId, Long parentId){
        StringBuilder sql= new StringBuilder("SELECT DISTINCT ");
        sql.append(" obj.OBJECT_ID AS objectId,");
        sql.append(" obj.PARENT_ID AS parentId,");
        sql.append(" obj.STATUS AS status,");
        sql.append(" obj.ORD AS ord,");
        sql.append(" obj.OBJECT_URL AS objectUrl,");
        sql.append(" obj.OBJECT_NAME AS objectName,");
        sql.append(" obj.DESCRIPTION AS description,");
        sql.append(" obj.OBJECT_TYPE_ID AS objectTypeId,");
        sql.append(" obj.OBJECT_CODE AS objectCode,");
        sql.append(" obj.CREATE_USER AS createUser,");
        sql.append(" obj.CREATE_DATE AS createDate");
        sql.append(" FROM sys_objects AS obj  ");

        if(parentId !=null){
            sql.append(" JOIN sys_role_object on obj.OBJECT_ID=sys_role_object.OBJECT_ID");
            sql.append(" JOIN sys_role_user ON sys_role_user.ROLE_ID=sys_role_object.ROLE_ID");
            sql.append(" WHERE sys_role_user.USER_ID=:userId AND sys_role_user.IS_ACTIVE=1 AND sys_role_object.IS_ACTIVE=1 AND obj.STATUS=1 ");
            sql.append(" AND obj.PARENT_ID =:parentId ");
        } else {
            sql.append(" WHERE obj.OBJECT_ID IN (SELECT sys_objects.PARENT_ID FROM  sys_objects ");
            sql.append(" JOIN sys_role_object on sys_objects.OBJECT_ID=sys_role_object.OBJECT_ID");
            sql.append(" JOIN sys_role_user ON sys_role_user.ROLE_ID=sys_role_object.ROLE_ID");
            sql.append(" WHERE sys_role_user.USER_ID=:userId AND sys_role_user.IS_ACTIVE=1 AND sys_role_object.IS_ACTIVE=1  ) AND obj.STATUS=1 ");
        }
        sql.append(" ORDER BY obj.ORD ");

        NativeQuery query= currentSession().createNativeQuery(sql.toString());

        query.addScalar("objectId", new LongType());
        query.addScalar("parentId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("ord", new LongType());
        query.addScalar("objectUrl", new StringType());
        query.addScalar("objectName", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("objectTypeId", new LongType());
        query.addScalar("objectCode", new StringType());
        query.addScalar("createUser", new StringType());
        query.addScalar("createDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(SysObjectsDTO.class));

        query.setParameter("userId", userId);
        if(parentId!=null){
            query.setParameter("parentId", parentId);
        }

        return query.list();
    }


    public List<SysObjectsDTO> doSearch(SearchDTO obj){
        StringBuilder sql= new StringBuilder("SELECT DISTINCT ");
        sql.append(" obj.OBJECT_ID AS objectId,");
        sql.append(" obj.PARENT_ID AS parentId,");
        sql.append(" obj.STATUS AS status,");
        sql.append(" obj.ORD AS ord,");
        sql.append(" obj.OBJECT_URL AS objectUrl,");
        sql.append(" obj.OBJECT_NAME AS objectName,");
        sql.append(" obj.DESCRIPTION AS description,");
        sql.append(" obj.OBJECT_TYPE_ID AS objectTypeId,");
        sql.append(" obj.OBJECT_CODE AS objectCode,");
        sql.append(" obj.CREATE_USER AS createUser,");
        sql.append(" obj.CREATE_DATE AS createDate");
        sql.append(" FROM sys_objects AS obj  ");

        sql.append(" WHERE 1=1  ");

//        if(StringUtils.isNotEmpty(obj.getObjectName())){
//            sql.append(" AND upper(obj.OBJECT_NAME) LIKE upper(:name) escape '&'  ");
//        }
//
//        if(StringUtils.isNotEmpty(obj.getObjectCode())){
//            sql.append("  AND upper(obj.OBJECT_CODE) LIKE upper(:code)  escape '&' ");
//        }

        if(StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("  AND ( upper(obj.OBJECT_CODE) LIKE upper(:keySearch)  escape '&' OR upper(obj.OBJECT_NAME) LIKE upper(:keySearch)  escape '&' )");
        }

//        if(StringUtils.isNotEmpty(obj.getLastName())){
//            sql.append("  AND upper(obj.LAST_NAME) LIKE upper(:lastName)  escape '&' ");
//        }

//        if(obj.getLocationId() != null){
//            sql.append(" AND obj.location_id = :locationId");
//        }
//        if(obj.getStatus() != null ){
//            sql.append(" AND obj.STATUS = :status");
//        }

        sql.append(" ORDER BY obj.ORD ");



        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as objects ");

        NativeQuery query= currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount=currentSession().createNativeQuery(sqlCount.toString());

        query.addScalar("objectId", new LongType());
        query.addScalar("parentId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("ord", new LongType());
        query.addScalar("objectUrl", new StringType());
        query.addScalar("objectName", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("objectTypeId", new LongType());
        query.addScalar("objectCode", new StringType());
        query.addScalar("createUser", new StringType());
        query.addScalar("createDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(SysObjectsDTO.class));

//        if(StringUtils.isNotEmpty(obj.getObjectName())){
//            query.setParameter("name", "%"+ ValidateUtils.validateKeySearch(obj.getObjectName())+"%");
//            queryCount.setParameter("name", "%"+ ValidateUtils.validateKeySearch(obj.getObjectName())+"%");
//        }
//        if(obj.getStatus() != null){
//            query.setParameter("status", obj.getStatus());
//            queryCount.setParameter("status", obj.getStatus());
//        }
//        if(StringUtils.isNotEmpty(obj.getObjectCode())){
//            query.setParameter("code", "%"+ ValidateUtils.validateKeySearch(obj.getObjectCode())+"%");
//            queryCount.setParameter("code", "%"+ ValidateUtils.validateKeySearch(obj.getObjectCode())+"%");
//        }

        if(StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
            queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }

        query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
        query.setMaxResults(obj.getPageSize().intValue());
        obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());

        return query.list();
    }

}
