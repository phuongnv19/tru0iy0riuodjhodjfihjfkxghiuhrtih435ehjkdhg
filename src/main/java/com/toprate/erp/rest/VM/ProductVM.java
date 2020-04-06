package com.toprate.erp.rest.VM;

import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.model.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class ProductVM   {

    private Long productId;

    private Long parentProductId;

    private Long locationId;

    private String productName;

    private String productCategoryId;

    private String productType;

    private String productCode;

    private Double productRate;

    private Double productQuantity;

    private Double productUnit;

    private Double productMonthlyFee;

    private Double productWeeklyFee;

    private Double productHourlyFee;

    private Long productSize;

    private Long productWorkstation;

    private Long productCapacity;

    private String productLocation;

    private Double productValue;

    private String productDescription;

    private String productImage;

    private Long productBonusHours;

    private Long productBonusCredit;

    private Long createUserId;

    private Date createDate;

    private Date modifiedDate;

    private Long productTypeId;

    public ProductDTO toDto() {
        ProductDTO product = new ProductDTO();
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
        return product;
    }
}
