package com.toprate.erp.model;

import com.toprate.erp.dto.InventoryCalendarDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INVENTORY_CALENDAR")
@Data
public class InventoryCalendar {

    //    @SequenceGenerator(name="inventory_calendar_pk_sequence",sequenceName="inventory_calendar_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVENTORY_CALENDAR_ID")
    private Long inventoryCalendarId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PARENT_PRODUCT_ID")
    private Long parentProductId;

    @Column(name = "BOOKING_STATUS")
    private Long bookingStatus;

    @Column(name = "PRODUCT_USE_STATUS")
    private Long productUseStatus;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    @Column(name = "CREATE_USER_ID")
    private Long createUserId;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;


    public InventoryCalendarDTO toDto() {
        InventoryCalendarDTO inventoryCalendar = new InventoryCalendarDTO();
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
        return inventoryCalendar;
    }
}
