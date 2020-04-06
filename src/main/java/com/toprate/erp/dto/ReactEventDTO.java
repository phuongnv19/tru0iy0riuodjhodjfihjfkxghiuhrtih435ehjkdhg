package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.ReactEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactEventDTO  {

    @AdjHistory(field = "EVENT_ID")
    private Long eventId;

    @AdjHistory(field = "REACT_TYPE")
    private Long reactType;

    @AdjHistory(field = "PERSION_ID")
    private Long persionId;

    @AdjHistory(field = "CREATED_TIME")
    private Date createdTime;

    @AdjHistory(field = "PERSION_TYPE")
    private Long persionType;

}
