package com.toprate.erp.repositories;

import com.toprate.erp.dto.ActivityDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleUserDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.model.Activity;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.Constants;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ActivityRepository extends HibernateRepository<Activity, Long> {

    public List<ActivityDTO> getListActivityByUser(Long userId){
        StringBuilder sql= new StringBuilder("WITH AA AS (SELECT COUNT(*) as count_persion,event_id FROM  react_event Where react_type=:persionJoin GROUP BY event_id), ");
        sql.append(" AB AS (SELECT COUNT(*) as count_persion,event_id FROM  react_event Where react_type=:persionNotJoin GROUP BY event_id),");
        sql.append(" AC AS (SELECT persion_id,event_id FROM  react_event WHERE persion_id=:userId AND persion_type=:persionType)");

        sql.append(" SELECT DISTINCT");
        sql.append(" obj.activity_id AS activityId,");
        sql.append(" obj.activity_heading AS activityHeading,");
        sql.append(" obj.activity_content AS activityContent,");
        sql.append(" obj.activity_embeded_link AS activityEmbededLink,");
        sql.append(" obj.activity_is_pinned AS activityIsPinned,");
        sql.append(" obj.activity_user_id AS activityUserId,");
        sql.append(" obj.activity_created_time AS activityCreatedTime,");
        sql.append(" obj.event_from_time AS eventFromTime,");
        sql.append(" obj.event_to_time AS eventToTime,");
        sql.append(" obj.location_description AS locationDescription,");
        sql.append(" obj.activity_type AS activityType,");
        sql.append(" obj.list_tag_user AS listTagUser,");
        sql.append(" obj.list_tag_location AS listTagLocation,");
        sql.append(" attach_document.document_path AS documentPath,");
        sql.append(" attach_document.document_name AS documentName,");
        sql.append(" COALESCE(AA.count_persion,0) AS countPersionJoin,");
        sql.append(" COALESCE(AB.count_persion,0) AS countPersionNotJoin,");
        sql.append(" CASE  when AC.persion_id is null  then FALSE else true END isReact,");
        sql.append(" CASE  when obj.activity_user_id = 3 then true else false END isDeleteEdit ");
        sql.append(" FROM activity AS obj  ");
        sql.append(" LEFT JOIN attach_document on obj.activity_id=attach_document.source_id AND  upper(attach_document.table_name)=upper(:tableName)  ");
        sql.append(" LEFT JOIN AB ON AB.event_id=obj.activity_id  ");
        sql.append("  LEFT JOIN AC ON AC.event_id=obj.activity_id  ");
        sql.append(" WHERE  ( obj.list_tag_user is null  " +
                " OR (:userId = ANY (CAST(string_to_array(list_tag_user,',') AS INTEGER[])) )" +
                "  OR (obj.activity_user_id = :userId )" +
                " )");

        sql.append(" ORDER BY obj.activity_created_time,obj.activity_id ");

        NativeQuery query= currentSession().createNativeQuery(sql.toString());

        query.addScalar("activityId", new LongType());
        query.addScalar("activityHeading", new StringType());
        query.addScalar("activityContent", new StringType());
        query.addScalar("activityEmbededLink", new StringType());
        query.addScalar("activityIsPinned", new LongType());
        query.addScalar("activityUserId", new LongType());
        query.addScalar("activityCreatedTime", new DateType());
        query.addScalar("eventFromTime", new DateType());
        query.addScalar("eventToTime", new DateType());
        query.addScalar("locationDescription", new StringType());
        query.addScalar("activityType", new LongType());

        query.addScalar("listTagUser", new StringType());
        query.addScalar("documentName", new StringType());

        query.addScalar("documentPath", new StringType());
        query.addScalar("listTagLocation", new StringType());

        query.addScalar("countPersionJoin", new LongType());
        query.addScalar("isReact", new BooleanType());
        query.addScalar("isDeleteEdit", new BooleanType());

        query.setResultTransformer(Transformers.aliasToBean(ActivityDTO.class));

        query.setParameter("userId", userId);
        query.setParameter("persionJoin", Constants.REACT_TYPE.JOIN);
        query.setParameter("persionNotJoin", Constants.REACT_TYPE.NOTJOIN);
        query.setParameter("persionType", Constants.PERSION_TYPE.USER);
        query.setParameter("tableName", Constants.TABLE_NAME.ACTIVITY);

        return query.list();
    }

    public void insertToReactEvent(Long userId, Long  activityId,Long reactType){
        StringBuilder sql = new StringBuilder(" INSERT INTO react_event (event_id,react_type,persion_id,created_time,persion_type) values (:eventId,:reactType,:persionId,:createdTime,:persionType)");
        SQLQuery query=currentSession().createSQLQuery(sql.toString());
        query.setParameter("eventId", activityId);
        query.setParameter("reactType", reactType);
        query.setParameter("persionType", Constants.PERSION_TYPE.USER);
        query.setParameter("persionId", userId);
        query.setParameter("createdTime", new Date());
        query.executeUpdate();
    }

}
