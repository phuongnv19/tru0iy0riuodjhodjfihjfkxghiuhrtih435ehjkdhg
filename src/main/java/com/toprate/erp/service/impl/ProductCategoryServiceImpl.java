package com.toprate.erp.service.impl;

import com.toprate.erp.dto.ProductCategoryDTO;
import com.toprate.erp.model.ProductCategory;
import com.toprate.erp.repositories.ProductCategoryRepository;
import com.toprate.erp.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategoryDTO> getListProductCategory() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        List<ProductCategoryDTO> dtos = new ArrayList<>();
        if (productCategories != null && !productCategories.isEmpty()){
            for (ProductCategory productCategory : productCategories){
                dtos.add(productCategory.toDto());
            }
        }
        return dtos;
    }
}
