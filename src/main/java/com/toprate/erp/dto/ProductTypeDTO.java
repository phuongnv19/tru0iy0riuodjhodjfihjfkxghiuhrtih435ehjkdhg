package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductTypeDTO extends ErpBaseDTO {

    private Long id;

    @AdjHistory(field = "PRODUCT_TYPE_NAME")
    private String productTypeName;

    @AdjHistory(field = "PRODUCT_TYPE_CODE")
    private String productTypeCode;

    public ProductType toModel() {
        ProductType type = new ProductType();
        type.setId(id);
        type.setProductTypeCode(productTypeCode);
        type.setProductTypeName(productTypeName);
        return type;
    }
}