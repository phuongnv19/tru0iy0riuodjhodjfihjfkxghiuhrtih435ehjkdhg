package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleDTO;
import com.toprate.erp.dto.SysRoleObjectDTO;
import com.toprate.erp.dto.SysRoleUserDTO;
import com.toprate.erp.model.SysRole;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class RoleRepository extends HibernateRepository<SysRole,Long> {
    public List<SysRoleDTO> doSearch(SearchDTO obj){
        StringBuilder sql = new StringBuilder("SELECT o.ROLE_ID roleId,"
                + "o.ROLE_CODE roleCode,"
                + "o.ROLE_NAME roleName,"
                + "o.DESCRIPTION description,"
                + "o.CREATE_USER createUser,"
                + "o.CREATE_DATE createDate,"
                + "o.STATUS status"
                + " FROM sys_role o"
                + " WHERE 1=1");
//        if(StringUtils.isNotEmpty(obj.getRoleCode())){
//            sql.append(" AND (upper(o.ROLE_CODE) LIKE upper(:roleCode) escape '&') ");
//        }
//
//        if(obj.getStatus() != null ){
//            sql.append(" AND o.STATUS = :status");
//        }
//
//        if(StringUtils.isNotEmpty(obj.getRoleName())){
//            sql.append(" AND (upper(o.ROLE_NAME) LIKE upper(:roleName) escape '&') ");
//        }
//
//        if(StringUtils.isNotEmpty(obj.getDescription())){
//            sql.append(" AND (upper(o.DESCRIPTION) LIKE upper(:description) escape '&') ");
//        }

        if(StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("  AND ( upper(o.ROLE_NAME) LIKE upper(:keySearch)  escape '&' OR upper(o.ROLE_CODE) LIKE upper(:keySearch)  escape '&' )");
        }

        sql.append(" ORDER BY o.ROLE_CODE");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as roles ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());
        SQLQuery queryCount=currentSession().createSQLQuery(sqlCount.toString());

        query.addScalar("roleId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("roleName", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("roleCode", new StringType());
        query.addScalar("createUser", new StringType());
        query.addScalar("createDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(SysRoleDTO.class));
//        if(StringUtils.isNotEmpty(obj.getRoleCode())){
//            query.setParameter("roleCode", "%"+ ValidateUtils.validateKeySearch(obj.getRoleCode())+"%");
//            queryCount.setParameter("roleCode", "%"+ ValidateUtils.validateKeySearch(obj.getRoleCode())+"%");
//        }
//        if(obj.getStatus() != null && obj.getStatus() != 2L){
//            query.setLong("status", obj.getStatus());
//            queryCount.setLong("status", obj.getStatus());
//        }
//
//        if(StringUtils.isNotEmpty(obj.getRoleName())){
//            query.setParameter("roleName", "%"+ ValidateUtils.validateKeySearch(obj.getRoleName())+"%");
//            queryCount.setParameter("roleName", "%"+ ValidateUtils.validateKeySearch(obj.getRoleName())+"%");
//        }
//        if(StringUtils.isNotEmpty(obj.getDescription())){
//            query.setParameter("description", "%"+ ValidateUtils.validateKeySearch(obj.getDescription())+"%");
//            queryCount.setParameter("description", "%"+ ValidateUtils.validateKeySearch(obj.getDescription())+"%");
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


    public SysRoleDTO getById(Long id){
        StringBuilder sql = new StringBuilder("SELECT o.ROLE_ID roleId,"
                + "o.ROLE_CODE roleCode,"
                + "o.ROLE_NAME roleName,"
                + "o.DESCRIPTION description,"
                + "o.CREATE_USER createUser,"
                + "o.CREATE_DATE createDate,"
                + "o.STATUS status"
                + " FROM sys_role o"
                + " WHERE o.ROLE_ID=:id ");



        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.addScalar("roleId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("roleName", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("roleCode", new StringType());
        query.addScalar("createUser", new StringType());
        query.addScalar("createDate", new DateType());

        query.setResultTransformer(Transformers.aliasToBean(SysRoleDTO.class));

            query.setParameter("id", id);


        return (SysRoleDTO) query.uniqueResult();
    }

    public List<SysRoleObjectDTO> getListObjectByRoleId(Long roleId){
        StringBuilder sql = new StringBuilder("select o.OBJECT_CODE objectCode,"
                + "o.OBJECT_NAME objectName,"
                + "o.OBJECT_URL objectUrl,"
                + "o.ORD ord,"
                + "o.OBJECT_TYPE_ID objectTypeId,"
                + "ro.ROLE_ID roleId,"
                + "ro.OBJECT_ID objectId "
                + "from sys_objects o "
                +"inner join sys_role_object ro on o.OBJECT_ID = ro.OBJECT_ID "
                +"inner join sys_role r on r.ROLE_ID=ro.ROLE_ID "
                +"where ro.ROLE_ID = :roleId "
                +"and ro.IS_ACTIVE=1 ");

        sql.append(" ORDER BY o.OBJECT_CODE");
        SQLQuery query= currentSession().createSQLQuery(sql.toString());


        query.addScalar("objectCode", new StringType());
        query.addScalar("objectName", new StringType());
        query.addScalar("objectUrl", new StringType());
        query.addScalar("ord", new LongType());
        query.addScalar("objectTypeId", new LongType());
        query.addScalar("roleId", new LongType());
        query.addScalar("objectId", new LongType());


        query.setResultTransformer(Transformers.aliasToBean(SysRoleObjectDTO.class));

        query.setLong("roleId", roleId);


        return query.list();
    }



    public void deleteByRoleId(Long roleId){
        StringBuilder sql=new StringBuilder(" DELETE FROM sys_role_object WHERE ROLE_ID=:roleId");
        SQLQuery query=currentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId", roleId);

        query.executeUpdate();
    }

    public void insertToRoleObject(SysRoleObjectDTO roleObjectDto, SysRoleDTO roleDto){
        StringBuilder sql = new StringBuilder(" INSERT INTO sys_role_object (ROLE_ID,OBJECT_ID,IS_ACTIVE) values (:roleId,:objectId,:isActive)");
        SQLQuery query=currentSession().createSQLQuery(sql.toString());
        query.setParameter("objectId", roleObjectDto.getObjectId());
        query.setParameter("roleId", roleDto.getRoleId());
        query.setParameter("isActive", 1l);
        query.executeUpdate();
    }
}
