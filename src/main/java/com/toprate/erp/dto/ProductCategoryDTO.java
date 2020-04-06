package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCategoryDTO {

    private Long id;

    @AdjHistory(field = "PRODUCT_CATEGORY_NAME")
    private String productCategoryName;

    @AdjHistory(field = "PRODUCT_CATEGORY_CODE")
    private String productCategoryCode;


    public ProductCategory toModel() {
        ProductCategory category = new ProductCategory();
        category.setId(id);
        category.setProductCategoryCode(productCategoryCode);
        category.setProductCategoryName(productCategoryName);
        return category;
    }
}