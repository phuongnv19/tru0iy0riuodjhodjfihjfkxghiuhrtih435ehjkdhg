package com.toprate.erp.model;

import com.toprate.erp.dto.WorkAtDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WORK_AT")
@Data
public class WorkAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_ID")
    private Long workId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "WORK_START_DATE")
    private Date workStartDate;

    @Column(name = "WORK_END_DATE")
    private Date workEndDate;

    @Column(name = "WOKR_STATUS")
    private Long wokrStatus;

    @Column(name = "MODIFIED_WORK_STATUS_BY")
    private Long modifiedWorkStatusBy;

    @Column(name = "MODIFIED_WORK_STATUS_TIME")
    private Date modifiedWorkStatusTime;

    public WorkAtDTO toDto() {
        WorkAtDTO workAt = new WorkAtDTO();
        workAt.setWorkId(workId);
        workAt.setMemberId(memberId);
        workAt.setLocationId(locationId);
        workAt.setProductId(productId);
        workAt.setWorkStartDate(workStartDate);
        workAt.setWorkEndDate(workEndDate);
        workAt.setWokrStatus(wokrStatus);
        workAt.setModifiedWorkStatusBy(modifiedWorkStatusBy);
        workAt.setModifiedWorkStatusTime(modifiedWorkStatusTime);
        return workAt;

    }
}
