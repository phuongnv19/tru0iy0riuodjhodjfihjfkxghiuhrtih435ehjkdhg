package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.CrmBooking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmBookingDTO extends ErpBaseDTO {
    private Long bookingId;

    @AdjHistory(field = "MEMBER_ID")
    private Long memberId;

    @AdjHistory(field = "PRODUCT_ID")
    private Long productId;

    @AdjHistory(field = "BOOKING_TITLE")
    private String bookingTitle;

    @AdjHistory(field = "BOOKING_START_TIME")
    private Date bookingStartTime;

    @AdjHistory(field = "BOOKING_END_TIME")
    private Date bookingEndTime;

    @AdjHistory(field = "DEBITTED_PRICE")
    private Long debittedPrice;

    @AdjHistory(field = "BOOKING_REF")
    private String bookingRef;

    @AdjHistory(field = "WEEK_DAY")
    private Long weekDay;

    @AdjHistory(field = "WEEK_NUM")
    private Long weekNum;

    @AdjHistory(field = "ADD_ON_PRODUCT_ID")
    private Long addOnProductId;

    @AdjHistory(field = "RECURRING_END_DATE")
    private Date recurringEndDate;

    @AdjHistory(field = "BASE_PRICE")
    private Long basePrice;

    @AdjHistory(field = "BOOK_BY")
    private Long bookBy;

    @AdjHistory(field = "BOOKER_TYPE")
    private Long bookerType;

    @AdjHistory(field = "CREATE_TIME")
    private Date createTime;

    private String productLocation;

    private String productCapacity;

    private String productName;

    public CrmBooking toModel(){
        CrmBooking crmBooking = new CrmBooking();
        crmBooking.setBookingId(bookingId);
        crmBooking.setMemberId(memberId);
        crmBooking.setProductId(productId);
        crmBooking.setBookingTitle(bookingTitle);
        crmBooking.setBookingStartTime(bookingStartTime);
        crmBooking.setBookingEndTime(bookingEndTime);
        crmBooking.setDebittedPrice(debittedPrice);
        crmBooking.setBookingRef(bookingRef);
        crmBooking.setWeekDay(weekDay);
        crmBooking.setWeekNum(weekNum);
        crmBooking.setAddOnProductId(addOnProductId);
        crmBooking.setRecurringEndDate(recurringEndDate);
        crmBooking.setBasePrice(basePrice);
        crmBooking.setBookBy(bookBy);
        crmBooking.setBookerType(bookerType);
        crmBooking.setCreateTime(createTime);
        return  crmBooking;
    }
}
