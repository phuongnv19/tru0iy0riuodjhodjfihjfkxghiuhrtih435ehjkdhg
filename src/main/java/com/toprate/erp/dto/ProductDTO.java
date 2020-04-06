package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO extends ErpBaseDTO {
    private Long productId;

    @AdjHistory(field = "PARENT_PRODUCT_ID")
    private Long parentProductId;

    @AdjHistory(field = "LOCATION_ID")
    private Long locationId;

    @AdjHistory(field = "PRODUCT_NAME")
    private String productName;

    @AdjHistory(field = "PRODUCT_CATEGORY_ID")
    private String productCategoryId;

    @AdjHistory(field = "PRODUCT_TYPE")
    private String productType;

    @AdjHistory(field = "PRODUCT_CODE")
    private String productCode;

    @AdjHistory(field = "PRODUCT_RATE")
    private Double productRate;

    @AdjHistory(field = "PRODUCT_QUANTITY")
    private Double productQuantity;

    @AdjHistory(field = "PRODUCT_UNIT")
    private Double productUnit;

    @AdjHistory(field = "PRODUCT_MONTHLY_FEE")
    private Double productMonthlyFee;

    @AdjHistory(field = "PRODUCT_WEEKLY_FEE")
    private Double productWeeklyFee;

    @AdjHistory(field = "PRODUCT_HOURLY_FEE")
    private Double productHourlyFee;

    @AdjHistory(field = "PRODUCT_SIZE")
    private Long productSize;

    @AdjHistory(field = "PRODUCT_WORKSTATION")
    private Long productWorkstation;

    @AdjHistory(field = "PRODUCT_CAPACITY")
    private Long productCapacity;

    @AdjHistory(field = "PRODUCT_LOCATION")
    private String productLocation;

    @AdjHistory(field = "PRODUCT_VALUE")
    private Double productValue;

    @AdjHistory(field = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @AdjHistory(field = "PRODUCT_IMAGE")
    private String productImage;

    @AdjHistory(field = "PRODUCT_BONUS_HOURS")
    private Long productBonusHours;

    @AdjHistory(field = "PRODUCT_BONUS_CREDIT")
    private Long productBonusCredit;

    @AdjHistory(field = "CREATE_USER_ID")
    private Long createUserId;

    @AdjHistory(field = "CREATE_DATE")
    private Date createDate;

    @AdjHistory(field = "MODIFIED_DATE")
    private Date modifiedDate;

    @AdjHistory(field = "PRODUCT_TYPE_ID")
    private Long productTypeId;

    private String parentProductCode;

    private String parentProductName;

    private Long productUseStatus;

    private Date fromDate;

    private Date toDate;

    private List<ProductDTO> lstProductSuccess;

    List<ObjFailsDTO> workLstErr;

    private String pathError;

    private Long  countSuccess;
    private Integer  countFails;

    private String productCategoryName;
    private String productTypeName;
    public Product toModel(){
        Product product = new Product();
        product.setProductId(productId);
        product.setLocationId(locationId);
        product.setParentProductId(parentProductId);
        product.setProductName(productName);
        product.setProductCategoryId(productCategoryId);
        product.setProductType(productType);
        product.setProductCode(productCode);
        product.setProductRate(productRate);
        product.setProductQuantity(productQuantity);
        product.setProductUnit(productUnit);
        product.setProductMonthlyFee(productMonthlyFee);
        product.setProductWeeklyFee(productWeeklyFee);
        product.setProductHourlyFee(productHourlyFee);
        product.setProductSize(productSize);
        product.setProductWorkstation(productWorkstation);
        product.setProductCapacity(productCapacity);
        product.setProductLocation(productLocation);
        product.setProductValue(productValue);
        product.setProductDescription(productDescription);
        product.setProductImage(productImage);
        product.setProductBonusHours(productBonusHours);
        product.setProductBonusCredit(productBonusCredit);
        product.setCreateDate(createDate);
        product.setModifiedDate(modifiedDate);
        product.setProductTypeId(productTypeId);
        return  product;
    }
}
