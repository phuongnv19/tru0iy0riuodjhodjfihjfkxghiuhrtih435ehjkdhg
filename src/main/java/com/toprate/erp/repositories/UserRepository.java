package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleDTO;
import com.toprate.erp.dto.SysRoleUserDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.model.SysUser;
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
public class UserRepository extends HibernateRepository<SysUser,Long> {

    public SysUserDTO getUserInfo(String email){
        StringBuilder sql = new StringBuilder("SELECT "
                + "SYS_USER.USER_ID AS userId,"
                +"SYS_USER.CHECK_IP AS checkIp, "
                +"SYS_USER.create_date AS createDate, "//
                +"SYS_USER.create_user_id AS createUserid, "//
                //
                + "SYS_USER.DATE_OF_BIRTH AS dateOfBirth,"
                + "SYS_USER.description AS description,"//
                //
                +"SYS_USER.EMAIL AS email, "
                + "SYS_USER.FIRST_NAME AS firstName,"
                +"SYS_USER.GENDER AS gender, "
                +"SYS_USER.HOME_PHONE AS homePhone, "
                +"SYS_USER.identity_code AS identityCode, "
                +"SYS_USER.IDENTITY_TYPE AS identityType, "
                +"SYS_USER.IP_ADDRESS AS ipAddress, "
                +"SYS_USER.KEY_FOB_ID AS keyFobId, "
                +"SYS_USER.LAST_CHANGE_PASSWORD AS lastChangePassword, "
                +"SYS_USER.LAST_LOCK_DATE AS lastLockDate , "

                +"SYS_USER.LAST_NAME AS lastName, "
                +"SYS_USER.location_id AS locationId, "
                +"SYS_USER.LOGIN_FAILURE_COUNT AS loginFailureCount, "
                +"SYS_USER.MOBILE_PHONE AS mobilePhone, "
                +"SYS_USER.PASSWORD AS password, "
                +"SYS_USER.STATUS AS status, "
                +"SYS_USER.VALID_FROM AS validFrom, "
                +"SYS_USER.VALID_TO AS validTo "

                +" FROM SYS_USER "
                +" WHERE upper(SYS_USER.EMAIL) = upper(:email) ");
        NativeQuery query= currentSession().createNativeQuery(sql.toString());

        query.addScalar("userId", new LongType());
        query.addScalar("firstName", new StringType());
        query.addScalar("lastName", new StringType());
        query.addScalar("password", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("locationId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("gender", new LongType());
        query.addScalar("identityCode", new StringType());
        query.addScalar("createDate", new DateType());
        query.addScalar("userId", new LongType());
        query.addScalar("locationName", new StringType());
        query.addScalar("homePhone", new StringType());
        query.addScalar("mobilePhone", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("validFrom", new DateType());
        query.addScalar("validTo", new DateType());
        query.addScalar("checkIp", new LongType());
        query.addScalar("ipAddress", new StringType());
        query.addScalar("lastChangePassword", new DateType());
        query.addScalar("loginFailureCount", new LongType());
        query.addScalar("lastLockDate", new DateType());
        query.addScalar("dateOfBirth", new DateType());
        query.addScalar("keyFobId", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));

        query.setParameter("email", email);

        return (SysUserDTO) query.uniqueResult();
    }

    public SysUserDTO getUserById(Long id){
        StringBuilder sql = new StringBuilder("SELECT "
                + "SYS_USER.USER_ID AS userId,"
                +"SYS_USER.CHECK_IP AS checkIp, "
                +"SYS_USER.create_date AS createDate, "//
                +"SYS_USER.create_user_id AS createUserid, "//
                //
                + "SYS_USER.DATE_OF_BIRTH AS dateOfBirth,"
                + "SYS_USER.description AS description,"//
                //
                +"SYS_USER.EMAIL AS email, "
                + "SYS_USER.FIRST_NAME AS firstName,"
                +"SYS_USER.GENDER AS gender, "
                +"SYS_USER.HOME_PHONE AS homePhone, "
                +"SYS_USER.identity_code AS identityCode, "
                +"SYS_USER.IDENTITY_TYPE AS identityType, "
                +"SYS_USER.IP_ADDRESS AS ipAddress, "
                +"SYS_USER.KEY_FOB_ID AS keyFobId, "
                +"SYS_USER.LAST_CHANGE_PASSWORD AS lastChangePassword, "
                +"SYS_USER.LAST_LOCK_DATE AS lastLockDate , "

                +"SYS_USER.LAST_NAME AS lastName, "
                +"SYS_USER.location_id AS locationId, "
                +"SYS_USER.LOGIN_FAILURE_COUNT AS loginFailureCount, "
                +"SYS_USER.MOBILE_PHONE AS mobilePhone, "
                +"SYS_USER.PASSWORD AS password, "
                +"SYS_USER.STATUS AS status, "
                +"SYS_USER.VALID_FROM AS validFrom, "
                +"SYS_USER.VALID_TO AS validTo "

                +" FROM SYS_USER "
                +" WHERE SYS_USER.USER_ID = :id ");
        NativeQuery query= currentSession().createNativeQuery(sql.toString());

        query.addScalar("userId", new LongType());
        query.addScalar("firstName", new StringType());
        query.addScalar("lastName", new StringType());
        query.addScalar("password", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("locationId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("gender", new LongType());
        query.addScalar("identityCode", new StringType());
        query.addScalar("createDate", new DateType());
        query.addScalar("userId", new LongType());
//        query.addScalar("locationName", new StringType());
        query.addScalar("homePhone", new StringType());
        query.addScalar("mobilePhone", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("validFrom", new DateType());
        query.addScalar("validTo", new DateType());
        query.addScalar("checkIp", new LongType());
        query.addScalar("ipAddress", new StringType());
        query.addScalar("lastChangePassword", new DateType());
        query.addScalar("loginFailureCount", new LongType());
        query.addScalar("lastLockDate", new DateType());
        query.addScalar("dateOfBirth", new DateType());
        query.addScalar("keyFobId", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));

        query.setParameter("id", id);

        return (SysUserDTO) query.uniqueResult();
    }


    @SuppressWarnings("unchecked")
    public List<SysUserDTO> doSearch(SearchDTO obj){
        StringBuilder sql = new StringBuilder("SELECT "
                + "SYS_USER.USER_ID AS userId,"
                +"SYS_USER.CHECK_IP AS checkIp, "
                +"SYS_USER.create_date AS createDate, "//
                +"SYS_USER.create_user_id AS createUserid, "//
                //
                + "SYS_USER.DATE_OF_BIRTH AS dateOfBirth,"
                + "SYS_USER.description AS description,"//
                //
                +"SYS_USER.EMAIL AS email, "
                + "SYS_USER.FIRST_NAME AS firstName,"
                +"SYS_USER.GENDER AS gender, "
                +"SYS_USER.HOME_PHONE AS homePhone, "
                +"SYS_USER.identity_code AS identityCode, "
                +"SYS_USER.IDENTITY_TYPE AS identityType, "
                +"SYS_USER.IP_ADDRESS AS ipAddress, "
                +"SYS_USER.KEY_FOB_ID AS keyFobId, "
                +"SYS_USER.LAST_CHANGE_PASSWORD AS lastChangePassword, "
                +"SYS_USER.LAST_LOCK_DATE AS lastLockDate , "

                +"SYS_USER.LAST_NAME AS lastName, "
                +"SYS_USER.location_id AS locationId, "
                +"SYS_USER.LOGIN_FAILURE_COUNT AS loginFailureCount, "
                +"SYS_USER.MOBILE_PHONE AS mobilePhone, "
                +"SYS_USER.PASSWORD AS password, "
                +"SYS_USER.STATUS AS status, "
                +"SYS_USER.VALID_FROM AS validFrom, "
                +"SYS_USER.VALID_TO AS validTo, "
                +"location.location_name AS locationName  "

                +" FROM SYS_USER "
                + "LEFT JOIN location ON location.location_id=SYS_USER.location_id "
                +"WHERE 1=1 ");

//        if(StringUtils.isNotEmpty(obj.getEmail())){
//            sql.append(" AND upper(SYS_USER.email) LIKE upper(:email) escape '&'  ");
//        }
//
//        if(StringUtils.isNotEmpty(obj.getFirstName())){
//            sql.append("  AND upper(SYS_USER.FIRST_NAME) LIKE upper(:firstName)  escape '&' ");
//        }
//
//        if(StringUtils.isNotEmpty(obj.getLastName())){
//            sql.append("  AND upper(SYS_USER.LAST_NAME) LIKE upper(:lastName)  escape '&' ");
//        }
//
//        if(obj.getLocationId() != null){
//            sql.append(" AND SYS_USER.location_id = :locationId");
//        }
//        if(obj.getStatus() != null ){
//            sql.append(" AND SYS_USER.STATUS = :status");
//        }

        if(StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("  AND ( upper(SYS_USER.LAST_NAME) LIKE upper(:keySearch)  escape '&' OR upper(SYS_USER.FIRST_NAME) LIKE upper(:keySearch)  escape '&'" +
                    " OR upper(SYS_USER.email) LIKE upper(:keySearch)  escape '&') ");
        }


        sql.append(" ORDER BY SYS_USER.email ");

        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
        sqlCount.append(sql.toString());
        sqlCount.append(")");
        sqlCount.append(" as users ");

        NativeQuery query= currentSession().createNativeQuery(sql.toString());
        NativeQuery queryCount=currentSession().createNativeQuery(sqlCount.toString());


        query.addScalar("userId", new LongType());
        query.addScalar("firstName", new StringType());
        query.addScalar("lastName", new StringType());
        query.addScalar("password", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("locationId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("gender", new LongType());
        query.addScalar("identityCode", new StringType());
        query.addScalar("createDate", new DateType());
        query.addScalar("userId", new LongType());
        query.addScalar("locationName", new StringType());
        query.addScalar("homePhone", new StringType());
        query.addScalar("mobilePhone", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("validFrom", new DateType());
        query.addScalar("validTo", new DateType());
        query.addScalar("checkIp", new LongType());
        query.addScalar("ipAddress", new StringType());
        query.addScalar("lastChangePassword", new DateType());
        query.addScalar("loginFailureCount", new LongType());
        query.addScalar("lastLockDate", new DateType());
        query.addScalar("dateOfBirth", new DateType());
        query.addScalar("keyFobId", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));
//        if(StringUtils.isNotEmpty(obj.getEmail())){
//            query.setParameter("email", "%"+ ValidateUtils.validateKeySearch(obj.getEmail())+"%");
//            queryCount.setParameter("email", "%"+ ValidateUtils.validateKeySearch(obj.getEmail())+"%");
//        }
//        if(obj.getStatus() != null){
//            query.setParameter("status", obj.getStatus());
//            queryCount.setParameter("status", obj.getStatus());
//        }
//        if(obj.getLocationId() != null){
//            query.setParameter("locationId", obj.getLocationId());
//            queryCount.setParameter("locationId", obj.getLocationId());
//        }
//        if(StringUtils.isNotEmpty(obj.getFirstName())){
//            query.setParameter("firstName",  "%"+ ValidateUtils.validateKeySearch(obj.getFirstName())+"%");
//            queryCount.setParameter("firstName",  "%"+ ValidateUtils.validateKeySearch(obj.getFirstName())+"%");
//        }
//        if(StringUtils.isNotEmpty(obj.getLastName())){
//            query.setParameter("lastName", "%"+ ValidateUtils.validateKeySearch(obj.getLastName())+"%");
//            queryCount.setParameter("lastName", "%"+ ValidateUtils.validateKeySearch(obj.getLastName())+"%");
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


    public void updateLoginFailCount(String email){
        StringBuilder sql = new StringBuilder("UPDATE users SET LOGIN_FAILURE_COUNT=COALESCE(LOGIN_FAILURE_COUNT,0)+1 WHERE EMAIL=:email ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.setParameter("email", email);

        query.executeUpdate();
    }

    public void resetLoginFailCount(String email){
        StringBuilder sql = new StringBuilder("UPDATE sys_user SET LOGIN_FAILURE_COUNT=0 WHERE EMAIL=:email ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.setParameter("email", email);

        query.executeUpdate();
    }


    public void lockAccount(Long id){
        StringBuilder sql = new StringBuilder("UPDATE sys_user SET STATUS=0,LAST_LOCK_DATE= NOW() WHERE USER_ID=:id ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.setParameter("id", id);

        query.executeUpdate();


    }

    public void unlockAccount(Long id){
        StringBuilder sql = new StringBuilder("UPDATE sys_user SET STATUS=1,LOGIN_FAILURE_COUNT=0 WHERE USER_ID=:id ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.setParameter("id", id);

        query.executeUpdate();


    }

    public void updatePass(String email,String pass){
        StringBuilder sql = new StringBuilder("UPDATE sys_user SET PASSWORD=:pass,LAST_CHANGE_PASSWORD=NOW() WHERE EMAIL=:email ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.setParameter("email", email);
        query.setParameter("pass", pass);
        query.executeUpdate();
    }

    public void resetPass(Long userId,String pass){
        StringBuilder sql = new StringBuilder("UPDATE sys_user SET PASSWORD=:pass,LAST_CHANGE_PASSWORD=null,LOGIN_FAILURE_COUNT=0 WHERE USER_ID=:userId ");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.setParameter("userId", userId);
        query.setParameter("pass", pass);
        query.executeUpdate();
    }


    public List<SysRoleUserDTO> getListRoleByUserId(Long userId) {
        String sql = "select o.ROLE_ID AS roleId," +
                "o.STATUS status," +
                "o.ROLE_NAME roleName," +
                "o.ROLE_CODE roleCode, "
                + "o.DESCRIPTION  description,"
                + "ru.USER_ID  userId"
                + " from sys_role o "
                + " JOIN sys_role_user ru ON ru.ROLE_ID=o.ROLE_ID "
                + " WHERE o.status=1 AND  ru.USER_ID=:userId ";

        StringBuilder stringBuilder = new StringBuilder(sql);


        stringBuilder.append(" ORDER BY o.ROLE_CODE");

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
        query.addScalar("description", new StringType());
        query.addScalar("roleName", new StringType());
        query.addScalar("roleCode", new StringType());
        query.addScalar("roleId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("userId", new LongType());

        query.setResultTransformer(Transformers.aliasToBean(SysRoleUserDTO.class));
        query.setLong("userId", userId);
        return query.list();
    }


    public void deleteByUserId(Long userId){
        StringBuilder sql=new StringBuilder(" DELETE FROM sys_role_user WHERE USER_ID=:userId");
        SQLQuery query=currentSession().createSQLQuery(sql.toString());
        query.setParameter("userId", userId);

        query.executeUpdate();
    }

    public void insertToUserRole(SysRoleUserDTO roleUserDTO, SysUserDTO userDTO){
        StringBuilder sql = new StringBuilder(" INSERT INTO sys_role_user (ROLE_ID,USER_ID,IS_ACTIVE) values (:roleId,:userId,:isActive)");
        SQLQuery query=currentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId", roleUserDTO.getRoleId());
        query.setParameter("userId", userDTO.getUserId());
        query.setParameter("isActive", 1l);
        query.executeUpdate();
    }

    public List<SysUserDTO> autoCompleteSearch(SearchDTO obj){
        StringBuilder sql = new StringBuilder("SELECT DISTINCT "
                + "SYS_USER.USER_ID AS userId,"
                + "SYS_USER.FIRST_NAME AS firstName,"
                +"SYS_USER.LAST_NAME AS lastName, "
//                +"SYS_USER.PASSWORD AS password, "
                +"SYS_USER.EMAIL AS email, "
                +"SYS_USER.location_id AS locationId, "
                +"SYS_USER.STATUS AS status, "
                +"SYS_USER.GENDER AS gender, "
                +"SYS_USER.IDENTITY_CODE AS identityCode, "
                +"SYS_USER.KEY_FOB_ID AS keyFobId, "
                +"SYS_USER.IDENTITY_TYPE AS identityType, "
                +"SYS_USER.HOME_PHONE AS homePhone, "
                +"SYS_USER.MOBILE_PHONE AS mobilePhone, "
                +"SYS_USER.DESCRIPTION AS description, "
                +"SYS_USER.VALID_FROM AS validFrom, "
                +"SYS_USER.VALID_TO AS validTo, "
                +"SYS_USER.CHECK_IP AS checkIp, "
                +"SYS_USER.IP_ADDRESS AS ipAddress, "
                +"SYS_USER.LAST_CHANGE_PASSWORD AS lastChangePassword, "
                +"SYS_USER.LOGIN_FAILURE_COUNT AS loginFailureCount, "
                +"SYS_USER.LAST_LOCK_DATE AS lastLockDate , "
                + "SYS_USER.DATE_OF_BIRTH AS dateOfBirth," +
                "concat(SYS_USER.FIRST_NAME,' ',SYS_USER.LAST_NAME) as fullName "
                +" FROM SYS_USER "
                +"WHERE 1=1 ");
        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append(" AND ( upper(SYS_USER.FIRST_NAME) like upper(:keySearch) " +
                    "OR upper(SYS_USER.LAST_NAME) like upper(:keySearch) " +
                    "OR upper(SYS_USER.HOME_PHONE) like upper(:keySearch) " +
                    "OR upper(SYS_USER.MOBILE_PHONE) like upper(:keySearch) " +
                    "OR upper(SYS_USER.EMAIL) like upper(:keySearch) ) ");
        }


        sql.append(" ORDER BY SYS_USER.USER_ID  limit 20 ");

        NativeQuery query= currentSession().createNativeQuery(sql.toString());

        query.addScalar("userId", new LongType());
        query.addScalar("firstName", new StringType());
        query.addScalar("lastName", new StringType());
       // query.addScalar("password", new StringType());
        query.addScalar("email", new StringType());
        query.addScalar("locationId", new LongType());
        query.addScalar("status", new LongType());
        query.addScalar("gender", new LongType());
        query.addScalar("identityCode", new StringType());
        query.addScalar("userId", new LongType());
        query.addScalar("homePhone", new StringType());
        query.addScalar("mobilePhone", new StringType());
        query.addScalar("description", new StringType());
        query.addScalar("validFrom", new DateType());
        query.addScalar("validTo", new DateType());
        query.addScalar("checkIp", new LongType());
        query.addScalar("ipAddress", new StringType());
        query.addScalar("lastChangePassword", new DateType());
        query.addScalar("loginFailureCount", new LongType());
        query.addScalar("lastLockDate", new DateType());
        query.addScalar("dateOfBirth", new DateType());
        query.addScalar("keyFobId", new StringType());
        query.addScalar("fullName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }


        return query.list();
    }

    public List<SysUserDTO> getSysUser() {
        StringBuilder sql = new StringBuilder("SELECT "
                + "SYS_USER.USER_ID AS userId, "
                + "SYS_USER.FIRST_NAME AS firstName, "
                + "SYS_USER.LAST_NAME AS lastName, "
                + " CONCAT ( SYS_USER.FIRST_NAME, ' ', SYS_USER.LAST_NAME ) AS fullName "
                + " FROM SYS_USER "
                + "WHERE 1=1 ");
        StringBuilder stringBuilder = new StringBuilder(sql);

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
        query.addScalar("userId", new LongType());
        query.addScalar("firstName", new StringType());
        query.addScalar("lastName", new StringType());
        query.addScalar("fullName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));

        return query.list();
    }
}
