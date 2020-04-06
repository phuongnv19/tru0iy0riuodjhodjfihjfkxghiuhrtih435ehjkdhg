package com.toprate.erp.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CRM_BOOKING")
@Data
public class CrmBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private Long bookingId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "BOOKING_TITLE")
    private String bookingTitle;

    @Column(name = "BOOKING_START_TIME")
    private Date bookingStartTime;

    @Column(name = "BOOKING_END_TIME")
    private Date bookingEndTime;

    @Column(name = "DEBITTED_PRICE")
    private Long debittedPrice;

    @Column(name = "BOOKING_REF")
    private String bookingRef;

    @Column(name = "WEEK_DAY")
    private Long weekDay;

    @Column(name = "WEEK_NUM")
    private Long weekNum;

    @Column(name = "ADD_ON_PRODUCT_ID")
    private Long addOnProductId;

    @Column(name = "RECURRING_END_DATE")
    private Date recurringEndDate;

    @Column(name = "BASE_PRICE")
    private Long basePrice;

    @Column(name = "BOOK_BY")
    private Long bookBy;

    @Column(name = "BOOKER_TYPE")
    private Long bookerType;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    public CrmBooking toDto(){
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
