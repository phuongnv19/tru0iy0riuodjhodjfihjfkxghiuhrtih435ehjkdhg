package com.toprate.erp.repositories;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.dto.LocationDTO;
import com.toprate.erp.dto.MemberDTO;
import com.toprate.erp.model.Member;
import com.toprate.erp.repositories.common.HibernateRepository;
import com.toprate.erp.util.ValidateUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository extends HibernateRepository<Member,Long> {

    private static final int MEMBER_ACTIVE_STATUS = 1;

    public Member getParentMemberId(long companyId){
        StringBuilder sql = new StringBuilder("SELECT parent_member_id parentMemberId " +
                "FROM member " +
                "WHERE company_id = :companyId " +
                "AND parent_member_id IS NOT NULL " +
                "LIMIT 1 ");
        NativeQuery query = currentSession().createNativeQuery(sql.toString());
        query.addScalar("parentMemberId", new LongType());

        query.setResultTransformer(Transformers.aliasToBean(Member.class));
        query.setParameter("companyId",companyId);

        return (Member) query.uniqueResult();
    }

    public List<MemberDTO> getListMemberForAutoComplete(SearchDTO obj){
        StringBuilder sql = new StringBuilder("SELECT DISTINCT " +
                "mb.member_id as memberId, " +
                "concat(mb.FIRST_NAME,' ',mb.LAST_NAME) as memberFullName, " +
                "mb.first_name as firstName, " +
                "mb.last_name as lastName, " +
                "mb.MEMBER_EMAIL as memberEmail, " +
                "mb.parent_member_id as parentMemberId, " +
                "mb.company_id as companyId, " +
                "mb.mem_postal_address as memPostalAddress, " +
                "mb.dob as dob, " +
                "mb.member_status as memberStatus, " +
                "mb.gender as gender, " +
                "mb.IDENTITY_CODE as identityCode, " +
                "mb.identity_type as identityType, " +
                "mb.member_type as memberType, " +
                "mb.physical_key_code as physicalKeyCode, " +
                "mb.member_longro as memberLongro, " +
                "mb.valid_from as validFrom, " +
                "mb.valid_to as validTo, " +
                "mb.check_ip as checkIp, " +
                "mb.ip as ip, " +
                "mb.created_by as createdBy," +
                "concat(mb.first_name,' ',mb.last_name) as memberFullName " +
                "FROM MEMBER as mb " +
                "WHERE mb.MEMBER_STATUS = "+MEMBER_ACTIVE_STATUS);

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            sql.append("  AND ( " +
                "upper(mb.FIRST_NAME) LIKE upper(:keySearch) " +
                "OR  upper(mb.LAST_NAME) LIKE upper(:keySearch) " +
                "OR  upper(concat(mb.FIRST_NAME,' ',mb.LAST_NAME)) LIKE upper(:keySearch) " +
                "OR  upper(mb.MEMBER_EMAIL) LIKE upper(:keySearch) " +
                "OR  upper(mb.MEM_POSTAL_ADDRESS) LIKE upper(:keySearch) " +
                "OR  upper(mb.GENDER) LIKE upper(:keySearch) " +
                "OR  upper(mb.PHYSICAL_KEY_CODE) LIKE upper(:keySearch) " +
                    ")");
        }

        sql.append(" ORDER BY mb.first_name");

        SQLQuery query= currentSession().createSQLQuery(sql.toString());

        query.addScalar("memberId", new LongType());
        query.addScalar("memberStatus", new LongType());
        query.addScalar("parentMemberId", new LongType());
        query.addScalar("firstName", new StringType());
        query.addScalar("lastName", new StringType());
        query.addScalar("memberEmail", new StringType());
        query.addScalar("companyId", new LongType());
        query.addScalar("memPostalAddress", new StringType());
        query.addScalar("gender", new StringType());
        query.addScalar("memberFullName", new StringType());

        query.setResultTransformer(Transformers.aliasToBean(MemberDTO.class));

        if (StringUtils.isNotEmpty(obj.getKeySearch())){
            query.setParameter("keySearch","%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
        }

        return query.list();
    }
}
