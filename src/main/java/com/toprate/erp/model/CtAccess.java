package com.toprate.erp.model;

import com.toprate.erp.dto.CtAccessDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CT_ACCESS")
@Data
public class CtAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCESS_ID")
    private Long accessId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "ACCESS_START_DATE")
    private Date accessStartDate;

    @Column(name = "ACCESS_END_DATE")
    private Date accessEndDate;

    public CtAccessDTO toDto(){
        CtAccessDTO ctAccess =  new CtAccessDTO();
        ctAccess.setAccessId(accessId);
        ctAccess.setMemberId(memberId);
        ctAccess.setProductId(productId);
        ctAccess.setAccessStartDate(accessStartDate);
        ctAccess.setAccessEndDate(accessEndDate);
        return  ctAccess;
    }
}
