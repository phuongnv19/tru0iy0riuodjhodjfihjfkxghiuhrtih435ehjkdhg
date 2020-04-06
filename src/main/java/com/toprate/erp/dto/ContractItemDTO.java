package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.toprate.erp.model.ContractItem;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractItemDTO extends ErpBaseDTO {
    private Long contractItemId;

    @AdjHistory(field = "CONTRACT_ID")
    private Long contractId;

    @AdjHistory(field = "PRODUCT_ID")
    private Long productId;

    @AdjHistory(field = "PRODUCT_CATEGORY")
    private String productCategory;

    @AdjHistory(field = "LEASE_TERM")
    private Long leaseTerm;

    @AdjHistory(field = "MONTHLY_FEE")
    private Double monthlyFee;

    @AdjHistory(field = "weekly_fee")
    private Double weeklyFee;

    @AdjHistory(field = "NUM_FREE_WEEKS")
    private Long numFreeWeeks;

    @AdjHistory(field = "WEEK_DAY")
    private Long weekDay;

    @AdjHistory(field = "PAID_START_DATE")
    private Date paidStartDate;

    @AdjHistory(field = "PAID_END_DATE")
    private Date paidEndDate;

    @AdjHistory(field = "CONTRACT_ITEM_VALUE")
    private Long contractItemValue;

    @AdjHistory(field = "IS_RECURRING")
    private Long isRecurring;

    @AdjHistory(field = "CONTRACT_PERIOD")
    private Long contractPeriod;

    @AdjHistory(field = "BONUS_HOURS")
    private Long bonusHours;

    @AdjHistory(field = "CURR_BONUS_HOURS")
    private Long currBonusHours;

    @AdjHistory(field = "BONUS_CREDIT")
    private Long bonusCredit;

    @AdjHistory(field = "PRODUCT_VALUE")
    private Double productValue;

    @AdjHistory(field = "PRODUCT_DESCRIPTION")
    private Double productDescription;

    public  ContractItem toModel (){
      ContractItem contractItem = new ContractItem();
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
