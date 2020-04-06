package com.toprate.erp.model;

import com.toprate.erp.dto.ContractItemDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONTRACT_ITEM")
@Data
public class ContractItem {

    @Id
//    @SequenceGenerator(name="contract_item_pk_sequence",sequenceName="contract_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTRACT_ITEM_ID")
    private Long contractItemId;

    @Column(name = "CONTRACT_ID")
    private Long contractId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_CATEGORY")
    private String productCategory;

    @Column(name = "LEASE_TERM")
    private Long leaseTerm;

    @Column(name = "MONTHLY_FEE")
    private Double monthlyFee;

    @Column(name = "NUM_FREE_WEEKS")
    private Long numFreeWeeks;

    @Column(name = "WEEK_DAY")
    private Long weekDay;

    @Column(name = "CONTRACT_ITEM_VALUE")
    private Long contractItemValue;

    @Column(name = "IS_RECURRING")
    private Long isRecurring;

    @Column(name = "CONTRACT_PERIOD")
    private Long contractPeriod;

    @Column(name = "BONUS_HOURS")
    private Long bonusHours;

    @Column(name = "CURR_BONUS_HOURS")
    private Long currBonusHours;

    @Column(name = "BONUS_CREDIT")
    private Long bonusCredit;

    @Column(name = "weekly_fee")
    private Double weeklyFee;

    @Column(name = "PRODUCT_VALUE")
    private Double productValue;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    public ContractItemDTO toDto (){
        ContractItemDTO contractItem = new ContractItemDTO();
        contractItem.setContractItemId(contractItemId);
        contractItem.setContractId(contractId);
        contractItem.setProductId(productId);
        contractItem.setProductCategory(productCategory);
        contractItem.setLeaseTerm(leaseTerm);
        contractItem.setMonthlyFee(monthlyFee);
        contractItem.setNumFreeWeeks(numFreeWeeks);
        contractItem.setWeekDay(weekDay);
        contractItem.setContractItemValue(contractItemValue);
        contractItem.setIsRecurring(isRecurring);
        contractItem.setContractPeriod(contractPeriod);
        contractItem.setBonusHours(bonusHours);
        contractItem.setCurrBonusHours(currBonusHours);
        contractItem.setBonusCredit(bonusCredit);
        return contractItem;
    }
}
