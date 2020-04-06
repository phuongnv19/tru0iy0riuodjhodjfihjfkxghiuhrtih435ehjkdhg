package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.ProductDTO;
import com.toprate.erp.dto.ProductTypeDTO;
import com.toprate.erp.model.ProductType;
import com.toprate.erp.repositories.ProductTypeRepository;
import com.toprate.erp.service.ProductTypeService;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductTypeDTO> getListProductType(Long categoryId) {
        return productTypeRepository.getListProductType(categoryId);
    }

    @Override
    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }


    @Override
    public List<ProductTypeDTO> autoCompleteSearch(SearchDTO obj) {
        return productTypeRepository.autoCompleteSearch(obj);
    }


}
