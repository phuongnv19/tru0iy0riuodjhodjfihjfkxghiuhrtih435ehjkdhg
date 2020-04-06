package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.WorkAt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.digester3.internal.asm.$ClassWriter;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkAtDTO {
    private Long workId;

    @AdjHistory(field = "MEMBER_ID")
    private Long memberId;

    @AdjHistory(field = "LOCATION_ID")
    private Long locationId;

    @AdjHistory(field = "PRODUCT_ID")
    private Long productId;

    @AdjHistory(field = "WORK_START_DATE")
    private Date workStartDate;

    @AdjHistory(field = "WORK_END_DATE")
    private Date workEndDate;

    @AdjHistory(field = "WOKR_STATUS")
    private Long wokrStatus;

    @AdjHistory(field = "MODIFIED_WORK_STATUS_BY")
    private Long modifiedWorkStatusBy;

    @AdjHistory(field = "MODIFIED_WORK_STATUS_TIME")
    private Date modifiedWorkStatusTime;

    public WorkAt toModel() {
        WorkAt workAt = new WorkAt();
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
