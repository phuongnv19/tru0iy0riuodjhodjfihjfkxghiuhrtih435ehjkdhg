package com.toprate.erp.model;

import com.toprate.erp.dto.ProductTypeDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_TYPE")
@Data
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_TYPE_NAME")
    private String productTypeName;

    @Column(name = "PRODUCT_TYPE_CODE")
    private String productTypeCode;

    public ProductTypeDTO toDto() {
        ProductTypeDTO typeDTO = new ProductTypeDTO();
        typeDTO.setId(id);
        typeDTO.setProductTypeCode(productTypeCode);
        typeDTO.setProductTypeName(productTypeName);
        return typeDTO;
    }
}
