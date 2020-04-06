package com.toprate.erp.model;

import com.toprate.erp.dto.ProductCategoryDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_CATEGORY")
@Data
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_CATEGORY_NAME")
    private String productCategoryName;

    @Column(name = "PRODUCT_CATEGORY_CODE")
    private String productCategoryCode;


    public ProductCategoryDTO toDto() {
        ProductCategoryDTO category = new ProductCategoryDTO();
        category.setId(id);
        category.setProductCategoryCode(productCategoryCode);
        category.setProductCategoryName(productCategoryName);
        return category;
    }

}