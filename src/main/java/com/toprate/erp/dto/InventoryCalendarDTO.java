package com.toprate.erp.dto;

import com.toprate.erp.model.InventoryCalendar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCalendarDTO {
    private Long productId;
    private Long parentProductId;
    private Long bookingStatus;
    private Long productUseStatus;
    private Date fromDate;
    private Date toDate;
    private Long createUserId;
    private Long contractId;
    private Date createDate;
    private Date modifiedDate;
    private Long inventoryCalendarId;

    public InventoryCalendar toModel(){
        InventoryCalendar inventoryCalendar = new InventoryCalendar();
        inventoryCalendar.setProductId(productId);
        inventoryCalendar.setParentProductId(parentProductId);
        inventoryCalendar.setBookingStatus(bookingStatus);
        inventoryCalendar.setProductUseStatus(productUseStatus);
        inventoryCalendar.setFromDate(fromDate);
        inventoryCalendar.setToDate(toDate);
        inventoryCalendar.setCreateUserId(createUserId);
        inventoryCalendar.setContractId(contractId);
        inventoryCalendar.setCreateDate(createDate);
        inventoryCalendar.setModifiedDate(modifiedDate);
        inventoryCalendar.setInventoryCalendarId(inventoryCalendarId);
        return  inventoryCalendar;
    }

}
