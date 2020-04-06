package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.CtAccess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtAccessDTO extends ErpBaseDTO {
    private Long accessId;

    @AdjHistory(field = "MEMBER_ID")
    private Long memberId;

    @AdjHistory(field = "PRODUCT_ID")
    private Long productId;

    @AdjHistory(field = "ACCESS_START_DATE")
    private Date accessStartDate;

    @AdjHistory(field = "ACCESS_END_DATE")
    private Date accessEndDate;

    public CtAccess toModel(){
        CtAccess ctAccess =  new CtAccess();
        ctAccess.setAccessId(accessId);
        ctAccess.setMemberId(memberId);
        ctAccess.setProductId(productId);
        ctAccess.setAccessStartDate(accessStartDate);
        ctAccess.setAccessEndDate(accessEndDate);
         return  ctAccess;
    }
}
