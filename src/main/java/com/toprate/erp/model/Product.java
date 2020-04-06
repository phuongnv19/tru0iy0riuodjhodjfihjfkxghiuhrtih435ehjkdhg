package com.toprate.erp.model;

import com.toprate.erp.dto.ProductDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "pk_product_sequence", sequenceName = "product_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_product_sequence")
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PARENT_PRODUCT_ID")
    private Long parentProductId;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_CATEGORY_ID")
    private String productCategoryId;

    @Column(name = "PRODUCT_TYPE")
    private String productType;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "PRODUCT_RATE")
    private Double productRate;

    @Column(name = "PRODUCT_QUANTITY")
    private Double productQuantity;

    @Column(name = "PRODUCT_UNIT")
    private Double productUnit;

    @Column(name = "PRODUCT_MONTHLY_FEE")
    private Double productMonthlyFee;

    @Column(name = "PRODUCT_WEEKLY_FEE")
    private Double productWeeklyFee;

    @Column(name = "PRODUCT_HOURLY_FEE")
    private Double productHourlyFee;

    @Column(name = "PRODUCT_SIZE")
    private Long productSize;

    @Column(name = "PRODUCT_WORKSTATION")
    private Long productWorkstation;

    @Column(name = "PRODUCT_CAPACITY")
    private Long productCapacity;

    @Column(name = "PRODUCT_LOCATION")
    private String productLocation;

    @Column(name = "PRODUCT_VALUE")
    private Double productValue;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "PRODUCT_IMAGE")
    private String productImage;

    @Column(name = "PRODUCT_BONUS_HOURS")
    private Long productBonusHours;

    @Column(name = "PRODUCT_BONUS_CREDIT")
    private Long productBonusCredit;

    @Column(name = "CREATE_USER_ID")
    private Long createUserId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "PRODUCT_TYPE_ID")
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
